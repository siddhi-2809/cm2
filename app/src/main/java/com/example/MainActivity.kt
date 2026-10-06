package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.DataRepository
import com.example.model.*
import com.example.ui.components.*
import com.example.ui.screens.*
import com.example.ui.theme.*
import com.example.viewmodel.CherishHomeViewModel

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      CherishHomeTheme {
        CherishHomeApp()
      }
    }
  }
}

@Composable
fun CherishHomeApp(viewModel: CherishHomeViewModel = viewModel()) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val scope = rememberCoroutineScope()
  var bookingProvider by remember { mutableStateOf<ServiceProvider?>(null) }

  // Sync drawer state with ViewModel
  LaunchedEffect(uiState.isCategoryDrawerOpen) {
    if (uiState.isCategoryDrawerOpen) {
      drawerState.open()
    } else {
      drawerState.close()
    }
  }

  LaunchedEffect(drawerState.isOpen) {
    if (!drawerState.isOpen && uiState.isCategoryDrawerOpen) {
      viewModel.toggleCategoryDrawer(false)
    }
  }

  // Handle Back Button
  BackHandler(enabled = uiState.selectedCategoryId != null || uiState.selectedMarketCategory != null || uiState.currentTab != BottomTab.HOME || uiState.currentScreen != Screen.SPLASH) {
    when {
      uiState.selectedCategoryId != null -> viewModel.closeCategoryDetail()
      uiState.selectedMarketCategory != null -> viewModel.closeMarketCategory()
      uiState.currentTab != BottomTab.HOME -> viewModel.selectTab(BottomTab.HOME)
      uiState.currentScreen == Screen.LOCATION_SETUP -> viewModel.navigateTo(Screen.AUTH)
      uiState.currentScreen == Screen.AUTH -> viewModel.navigateTo(Screen.INTRO_UNIVERSE)
      uiState.currentScreen == Screen.INTRO_UNIVERSE -> viewModel.navigateTo(Screen.SPLASH)
    }
  }

  val totalCartItems = uiState.cart.values.sumOf { it.quantity }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    containerColor = CherishSurfaceLight
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      when (uiState.currentScreen) {
        Screen.SPLASH -> {
          SplashScreen(
            onProceed = { viewModel.navigateTo(Screen.INTRO_UNIVERSE) }
          )
        }

        Screen.INTRO_UNIVERSE -> {
          IntroUniverseScreen(
            onProceed = { viewModel.navigateTo(Screen.AUTH) }
          )
        }

        Screen.AUTH -> {
          AuthScreen(
            onLoginSuccess = { phone ->
              viewModel.navigateTo(Screen.LOCATION_SETUP)
            },
            onExploreAsGuest = {
              viewModel.navigateTo(Screen.MAIN_APP)
            }
          )
        }

        Screen.LOCATION_SETUP -> {
          OnboardingLocationScreen(
            initialName = uiState.userName,
            initialLocation = uiState.userLocation,
            onBack = { viewModel.navigateTo(Screen.AUTH) },
            onContinue = { name, location ->
              viewModel.updateUserName(name)
              viewModel.updateUserLocation(location)
              viewModel.navigateTo(Screen.MAIN_APP)
            }
          )
        }

        Screen.MAIN_APP -> {
          ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
              CategorySideDrawerSheet(
                selectedCategoryId = uiState.selectedCategoryId,
                onSelectCategory = { catId ->
                  viewModel.openCategory(catId)
                },
                onClose = {
                  viewModel.toggleCategoryDrawer(false)
                }
              )
            }
          ) {
            Box(modifier = Modifier.fillMaxSize()) {
              Column(modifier = Modifier.fillMaxSize()) {
                // Top Bar
                CherishTopBar(
                  title = when {
                    uiState.selectedCategoryId != null -> DataRepository.serviceCategories.firstOrNull { it.id == uiState.selectedCategoryId }?.name
                    uiState.selectedMarketCategory != null -> "Daily Groceries"
                    uiState.currentTab == BottomTab.SERVICES -> "Services"
                    uiState.currentTab == BottomTab.MARKET -> "Marketplace"
                    uiState.currentTab == BottomTab.SOCIETY -> "Society Living"
                    uiState.currentTab == BottomTab.ACCOUNT -> "My Account"
                    else -> null
                  },
                  location = uiState.userLocation,
                  cartCount = totalCartItems,
                  showBack = uiState.selectedCategoryId != null || uiState.selectedMarketCategory != null,
                  showMenu = true,
                  onBackClick = {
                    if (uiState.selectedCategoryId != null) viewModel.closeCategoryDetail()
                    else if (uiState.selectedMarketCategory != null) viewModel.closeMarketCategory()
                  },
                  onMenuClick = {
                    viewModel.toggleCategoryDrawer(true)
                  },
                  onCartClick = {
                    viewModel.toggleCartDrawer(true)
                  },
                  onNotificationClick = {
                    viewModel.showToast("You have 3 active updates from Kharadi RWA")
                  },
                  onProfileClick = {
                    viewModel.selectTab(BottomTab.ACCOUNT)
                  }
                )

                // Main Page Content Area
                Box(modifier = Modifier.weight(1f)) {
                  when {
                    uiState.selectedCategoryId != null -> {
                      val currentCat = DataRepository.serviceCategories.firstOrNull { it.id == uiState.selectedCategoryId }
                        ?: DataRepository.serviceCategories.first()

                      ServiceDetailCategoryScreen(
                        category = currentCat,
                        onBack = { viewModel.closeCategoryDetail() },
                        onFilterClick = { viewModel.toggleFilterSheet(true) },
                        onBookProvider = { provider -> bookingProvider = provider }
                      )
                    }

                    uiState.selectedMarketCategory != null -> {
                      DailyGroceriesScreen(
                        cart = uiState.cart,
                        wishlist = uiState.wishlist,
                        onBack = { viewModel.closeMarketCategory() },
                        onOpenDrawer = { viewModel.toggleCategoryDrawer(true) },
                        onOpenCart = { viewModel.toggleCartDrawer(true) },
                        onAddToCart = { prod -> viewModel.addToCart(prod) },
                        onDecrease = { id -> viewModel.decreaseFromCart(id) },
                        onToggleWishlist = { id -> viewModel.toggleWishlist(id) }
                      )
                    }

                    else -> {
                      when (uiState.currentTab) {
                        BottomTab.HOME -> {
                          HomeScreen(
                            userName = uiState.userName,
                            onNavigateToTab = { tab -> viewModel.selectTab(tab) },
                            onNavigateToCategory = { catId -> viewModel.openCategory(catId) },
                            onNavigateToMarketCategory = { catId -> viewModel.openMarketCategory(catId) },
                            onBookServiceDialog = { provider -> bookingProvider = provider }
                          )
                        }

                        BottomTab.SERVICES -> {
                          ServicesCatalogScreen(
                            onSelectCategory = { catId -> viewModel.openCategory(catId) }
                          )
                        }

                        BottomTab.MARKET -> {
                          MarketplaceScreen(
                            cart = uiState.cart,
                            wishlist = uiState.wishlist,
                            onAddToCart = { prod -> viewModel.addToCart(prod) },
                            onDecrease = { id -> viewModel.decreaseFromCart(id) },
                            onToggleWishlist = { id -> viewModel.toggleWishlist(id) },
                            onSelectCategory = { catId ->
                              if (catId == "groceries") viewModel.openMarketCategory("groceries")
                              else viewModel.showToast("Opening $catId collection")
                            },
                            onOpenCart = { viewModel.toggleCartDrawer(true) }
                          )
                        }

                        BottomTab.SOCIETY -> {
                          SocietyScreen(
                            societyName = uiState.userSociety,
                            flatNumber = uiState.userFlat,
                            visitorPasses = uiState.visitorPasses,
                            isMaintenancePaid = uiState.maintenancePaid,
                            onPayMaintenance = { viewModel.payMaintenanceBill() },
                            onCreateVisitorPass = { name, purpose -> viewModel.createVisitorPass(name, purpose) }
                          )
                        }

                        BottomTab.ACCOUNT -> {
                          AccountScreen(
                            userName = uiState.userName,
                            userPhone = uiState.userPhone,
                            userSociety = uiState.userSociety,
                            userFlat = uiState.userFlat,
                            bookings = uiState.bookings,
                            onLogout = { viewModel.navigateTo(Screen.SPLASH) }
                          )
                        }
                      }
                    }
                  }
                }

                // Bottom Tab Navigation Bar
                CherishBottomBar(
                  currentTab = uiState.currentTab,
                  onTabSelected = { tab -> viewModel.selectTab(tab) },
                  cartCount = totalCartItems
                )
              }

              // Floating Action Button (+) Speed Dial
              Box(
                modifier = Modifier
                  .align(Alignment.BottomEnd)
                  .padding(end = 20.dp, bottom = 84.dp)
              ) {
                QuickActionFab(
                  isOpen = uiState.isQuickActionOpen,
                  onToggle = { viewModel.toggleQuickAction() },
                  onEmergencySOS = { viewModel.showToast("🚨 Emergency Intercom Alert Sent to Society Security Desk") },
                  onReportIssue = { viewModel.showToast("Report issue modal opened for Society Management") },
                  onBookService = { viewModel.selectTab(BottomTab.SERVICES) },
                  onCartAction = { viewModel.toggleCartDrawer(true) }
                )
              }
            }
          }
        }
      }

      // Modals and Bottom Sheets
      if (uiState.isCartDrawerOpen) {
        CartBottomSheet(
          cart = uiState.cart,
          onClose = { viewModel.toggleCartDrawer(false) },
          onAddToCart = { p -> viewModel.addToCart(p) },
          onDecrease = { id -> viewModel.decreaseFromCart(id) },
          onRemove = { id -> viewModel.removeFromCart(id) },
          onCheckout = {
            viewModel.toggleCartDrawer(false)
            viewModel.showToast("Order placed successfully for 30-min express delivery! ✓")
          }
        )
      }

      if (uiState.isFilterSheetOpen) {
        ServiceFilterBottomSheet(
          currentDistance = uiState.filterDistanceMax,
          currentRating = uiState.filterMinRating,
          onClose = { viewModel.toggleFilterSheet(false) },
          onApply = { d, r -> viewModel.updateFilters(d, r) },
          onReset = { viewModel.updateFilters(5f, 4.5f) }
        )
      }

      bookingProvider?.let { provider ->
        BookingDialog(
          provider = provider,
          onDismiss = { bookingProvider = null },
          onConfirm = { date, slot ->
            viewModel.bookService(provider, date, slot)
            bookingProvider = null
          }
        )
      }

      // Floating In-App Toast
      uiState.toastMessage?.let { msg ->
        LaunchedEffect(msg) {
          kotlinx.coroutines.delay(2600)
          viewModel.dismissToast()
        }

        Box(
          modifier = Modifier
            .align(Alignment.TopCenter)
            .statusBarsPadding()
            .padding(top = 16.dp, start = 20.dp, end = 20.dp)
        ) {
          Surface(
            shape = CircleShape,
            color = CherishTeal,
            shadowElevation = 8.dp
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(CherishMintBright))
              Text(
                text = msg,
                color = Color.White,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }
      }
    }
  }
}
