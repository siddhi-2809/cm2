package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.DataRepository
import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UiState(
  val currentScreen: Screen = Screen.SPLASH,
  val currentTab: BottomTab = BottomTab.HOME,
  val selectedCategoryId: String? = null,
  val selectedMarketCategory: String? = null,
  val userName: String = "Siddhi",
  val userPhone: String = "9876543210",
  val userLocation: String = "Kharadi, Pune",
  val userSociety: String = "Eon Waterfront Residences",
  val userFlat: String = "Tower A, 1402",
  val isLocationGranted: Boolean = true,
  val cart: Map<String, CartItem> = mapOf(
    "prod_matcha_mug" to CartItem(
      product = DataRepository.marketProducts.first { it.id == "prod_matcha_mug" },
      quantity = 1
    ),
    "prod_olive_oil" to CartItem(
      product = DataRepository.marketProducts.first { it.id == "prod_olive_oil" },
      quantity = 1
    ),
    "prod_linen_towel" to CartItem(
      product = DataRepository.marketProducts.first { it.id == "prod_linen_towel" },
      quantity = 1
    )
  ),
  val wishlist: Set<String> = setOf("prod_matcha_mug", "groc_apples"),
  val bookings: List<BookingRecord> = emptyList(),
  val registeredVendors: Map<String, List<ServiceProvider>> = emptyMap(),
  val visitorPasses: List<VisitorPass> = listOf(
    VisitorPass("vp_1", "Rahul Deshpande", "Guest", "Today, 04:00 PM", "CH-7821", "Active"),
    VisitorPass("vp_2", "Swiggy Delivery", "Delivery", "Today, 01:15 PM", "CH-4490", "Completed")
  ),
  val maintenancePaid: Boolean = false,
  val isQuickActionOpen: Boolean = false,
  val isCartDrawerOpen: Boolean = false,
  val isFilterSheetOpen: Boolean = false,
  val isCategoryDrawerOpen: Boolean = false,
  val searchQuery: String = "",
  val activeServiceChip: String = "All",
  val filterDistanceMax: Float = 5f,
  val filterMinRating: Float = 4.5f,
  val toastMessage: String? = null
)

class CherishHomeViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(UiState())
  val uiState: StateFlow<UiState> = _uiState.asStateFlow()

  fun navigateTo(screen: Screen) {
    _uiState.update { it.copy(currentScreen = screen) }
  }

  fun selectTab(tab: BottomTab) {
    _uiState.update { 
      it.copy(
        currentTab = tab,
        currentScreen = Screen.MAIN_APP,
        selectedCategoryId = null,
        selectedMarketCategory = null
      ) 
    }
  }

  fun openCategory(categoryId: String) {
    _uiState.update { 
      it.copy(
        selectedCategoryId = categoryId,
        isCategoryDrawerOpen = false
      ) 
    }
  }

  fun closeCategoryDetail() {
    _uiState.update { it.copy(selectedCategoryId = null) }
  }

  fun openMarketCategory(categoryId: String) {
    _uiState.update { it.copy(selectedMarketCategory = categoryId) }
  }

  fun closeMarketCategory() {
    _uiState.update { it.copy(selectedMarketCategory = null) }
  }

  fun updateUserName(name: String) {
    _uiState.update { it.copy(userName = name) }
  }

  fun updateUserLocation(location: String) {
    _uiState.update { it.copy(userLocation = location, isLocationGranted = true) }
    showToast("Location updated to $location")
  }

  fun addToCart(product: MarketProduct) {
    _uiState.update { state ->
      val currentCart = state.cart.toMutableMap()
      val existing = currentCart[product.id]
      if (existing != null) {
        currentCart[product.id] = existing.copy(quantity = existing.quantity + 1)
      } else {
        currentCart[product.id] = CartItem(product = product, quantity = 1)
      }
      state.copy(cart = currentCart)
    }
    showToast("Added ${product.name} to cart")
  }

  fun decreaseFromCart(productId: String) {
    _uiState.update { state ->
      val currentCart = state.cart.toMutableMap()
      val existing = currentCart[productId]
      if (existing != null) {
        if (existing.quantity > 1) {
          currentCart[productId] = existing.copy(quantity = existing.quantity - 1)
        } else {
          currentCart.remove(productId)
        }
      }
      state.copy(cart = currentCart)
    }
  }

  fun removeFromCart(productId: String) {
    _uiState.update { state ->
      val currentCart = state.cart.toMutableMap()
      currentCart.remove(productId)
      state.copy(cart = currentCart)
    }
    showToast("Item removed from cart")
  }

  fun clearCart() {
    _uiState.update { it.copy(cart = emptyMap()) }
  }

  fun toggleWishlist(productId: String) {
    _uiState.update { state ->
      val current = state.wishlist.toMutableSet()
      if (current.contains(productId)) {
        current.remove(productId)
        showToast("Removed from wishlist")
      } else {
        current.add(productId)
        showToast("Saved to your wishlist ❤️")
      }
      state.copy(wishlist = current)
    }
  }

  fun bookService(provider: ServiceProvider, date: String, slot: String) {
    val newBooking = BookingRecord(
      id = "bk_${System.currentTimeMillis() % 10000}",
      serviceTitle = provider.title,
      providerName = provider.providerName,
      date = date,
      timeSlot = slot,
      price = provider.price,
      status = "Confirmed"
    )
    _uiState.update { state ->
      state.copy(bookings = listOf(newBooking) + state.bookings)
    }
    showToast("Service successfully booked with ${provider.providerName}! ✓")
  }

  fun registerVendor(
    businessName: String,
    contactPerson: String,
    categoryId: String,
    phone: String,
    serviceTitle: String,
    price: Int,
    priceUnit: String,
    features: List<String>
  ) {
    val newVendor = ServiceProvider(
      id = "vendor_${System.currentTimeMillis() % 100000}",
      categoryId = categoryId,
      title = serviceTitle,
      providerName = "$businessName • $contactPerson",
      credentials = "Registered Partner • $phone",
      rating = 5.0,
      reviewCount = 1,
      distanceKm = 0.5,
      isVerified = true,
      highlightBadge = "New Partner",
      price = price,
      priceUnit = priceUnit,
      features = features,
      initials = businessName.take(2).uppercase()
    )
    _uiState.update { state ->
      val currentMap = state.registeredVendors.toMutableMap()
      val list = currentMap[categoryId]?.toMutableList() ?: mutableListOf()
      list.add(0, newVendor)
      currentMap[categoryId] = list
      state.copy(
        registeredVendors = currentMap,
        currentScreen = Screen.MAIN_APP,
        currentTab = BottomTab.SERVICES,
        selectedCategoryId = categoryId
      )
    }
    showToast("Vendor $businessName registered successfully! ✓")
  }

  fun createVisitorPass(name: String, purpose: String) {
    val passCode = "CH-" + (1000..9999).random()
    val newPass = VisitorPass(
      id = "vp_${System.currentTimeMillis() % 10000}",
      visitorName = name,
      purpose = purpose,
      time = "Today, Now",
      passCode = passCode,
      status = "Active"
    )
    _uiState.update { state ->
      state.copy(visitorPasses = listOf(newPass) + state.visitorPasses)
    }
    showToast("Digital Gate Pass $passCode generated for $name")
  }

  fun payMaintenanceBill() {
    _uiState.update { it.copy(maintenancePaid = true) }
    showToast("Maintenance bill of ₹3,450 paid successfully! Receipt sent.")
  }

  fun toggleQuickAction(open: Boolean? = null) {
    _uiState.update { it.copy(isQuickActionOpen = open ?: !it.isQuickActionOpen) }
  }

  fun toggleCartDrawer(open: Boolean? = null) {
    _uiState.update { it.copy(isCartDrawerOpen = open ?: !it.isCartDrawerOpen) }
  }

  fun toggleFilterSheet(open: Boolean? = null) {
    _uiState.update { it.copy(isFilterSheetOpen = open ?: !it.isFilterSheetOpen) }
  }

  fun toggleCategoryDrawer(open: Boolean? = null) {
    _uiState.update { it.copy(isCategoryDrawerOpen = open ?: !it.isCategoryDrawerOpen) }
  }

  fun setSearchQuery(query: String) {
    _uiState.update { it.copy(searchQuery = query) }
  }

  fun setActiveServiceChip(chip: String) {
    _uiState.update { it.copy(activeServiceChip = chip) }
  }

  fun updateFilters(distanceMax: Float, minRating: Float) {
    _uiState.update { 
      it.copy(
        filterDistanceMax = distanceMax,
        filterMinRating = minRating,
        isFilterSheetOpen = false
      ) 
    }
    showToast("Filters applied")
  }

  fun showToast(msg: String) {
    _uiState.update { it.copy(toastMessage = msg) }
  }

  fun dismissToast() {
    _uiState.update { it.copy(toastMessage = null) }
  }
}
