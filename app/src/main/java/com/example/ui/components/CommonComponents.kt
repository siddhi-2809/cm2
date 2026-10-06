package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.DataRepository
import com.example.model.*
import com.example.ui.theme.*

@Composable
fun CherishTopBar(
  title: String? = null,
  location: String = "Kharadi, Pune",
  notificationCount: Int = 3,
  cartCount: Int = 0,
  showBack: Boolean = false,
  showMenu: Boolean = true,
  onBackClick: () -> Unit = {},
  onMenuClick: () -> Unit = {},
  onSearchClick: () -> Unit = {},
  onCartClick: () -> Unit = {},
  onNotificationClick: () -> Unit = {},
  onProfileClick: () -> Unit = {}
) {
  Surface(
    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
    tonalElevation = 2.dp,
    shadowElevation = 1.dp
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          if (showBack) {
            IconButton(
              onClick = onBackClick,
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, CherishBorder, CircleShape)
            ) {
              Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = CherishNavy,
                modifier = Modifier.size(20.dp)
              )
            }
          }

          if (showMenu) {
            IconButton(
              onClick = onMenuClick,
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, CherishBorder, CircleShape)
            ) {
              Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = CherishTeal,
                modifier = Modifier.size(20.dp)
              )
            }
          }

          if (title != null) {
            Text(
              text = title,
              style = MaterialTheme.typography.titleLarge,
              color = CherishNavy,
              fontWeight = FontWeight.Bold
            )
          } else {
            // Logo
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(28.dp)
                  .clip(RoundedCornerShape(8.dp))
                  .background(CherishTeal),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = "CH",
                  color = CherishMint,
                  fontWeight = FontWeight.Black,
                  fontSize = 12.sp
                )
              }
              Text(
                text = "CherishHome",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = CherishTeal
              )
            }
          }
        }

        // Right side tools
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          IconButton(
            onClick = onSearchClick,
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(Color.White)
              .border(1.dp, CherishBorder, CircleShape)
          ) {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = "Search",
              tint = CherishTeal,
              modifier = Modifier.size(18.dp)
            )
          }

          // Cart Button
          Box {
            IconButton(
              onClick = onCartClick,
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, CherishBorder, CircleShape)
            ) {
              Icon(
                imageVector = Icons.Outlined.ShoppingBag,
                contentDescription = "Cart",
                tint = CherishTeal,
                modifier = Modifier.size(18.dp)
              )
            }
            if (cartCount > 0) {
              Box(
                modifier = Modifier
                  .align(Alignment.TopEnd)
                  .offset(x = 2.dp, y = (-2).dp)
                  .size(16.dp)
                  .clip(CircleShape)
                  .background(CherishCoral),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = cartCount.toString(),
                  color = Color.White,
                  fontSize = 9.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }

          // Notification Button
          Box {
            IconButton(
              onClick = onNotificationClick,
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White)
                .border(1.dp, CherishBorder, CircleShape)
            ) {
              Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications",
                tint = CherishTeal,
                modifier = Modifier.size(18.dp)
              )
            }
            if (notificationCount > 0) {
              Box(
                modifier = Modifier
                  .align(Alignment.TopEnd)
                  .offset(x = 2.dp, y = (-2).dp)
                  .size(16.dp)
                  .clip(CircleShape)
                  .background(CherishCoral),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = notificationCount.toString(),
                  color = Color.White,
                  fontSize = 9.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }

          // Profile Avatar
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(CherishTeal)
              .clickable { onProfileClick() },
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "SK",
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp
            )
            // Online green indicator
            Box(
              modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(Color(0xFF10B981))
                .border(1.5.dp, Color.White, CircleShape)
                .align(Alignment.BottomEnd)
            )
          }
        }
      }

      // Location Sub-bar
      Spacer(modifier = Modifier.height(6.dp))
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Surface(
          shape = CircleShape,
          color = CherishMint,
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF)),
          modifier = Modifier.clickable { onSearchClick() }
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
          ) {
            Text(text = "📍", fontSize = 11.sp)
            Text(
              text = location,
              style = MaterialTheme.typography.labelMedium,
              color = CherishTeal,
              fontWeight = FontWeight.Bold
            )
            Icon(
              imageVector = Icons.Default.KeyboardArrowDown,
              contentDescription = null,
              tint = CherishTeal,
              modifier = Modifier.size(14.dp)
            )
          }
        }

        Text(
          text = "Serving Eon Free Zone & vicinity",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}

@Composable
fun CherishBottomBar(
  currentTab: BottomTab,
  onTabSelected: (BottomTab) -> Unit,
  cartCount: Int = 0
) {
  Surface(
    color = Color.White.copy(alpha = 0.95f),
    tonalElevation = 8.dp,
    shadowElevation = 12.dp,
    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .navigationBarsPadding()
        .padding(horizontal = 12.dp, vertical = 6.dp),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      BottomTab.values().forEach { tab ->
        val isSelected = tab == currentTab
        val icon = when (tab) {
          BottomTab.HOME -> Icons.Default.Home
          BottomTab.SERVICES -> Icons.Default.Build
          BottomTab.MARKET -> Icons.Default.ShoppingCart
          BottomTab.SOCIETY -> Icons.Default.LocationCity
          BottomTab.ACCOUNT -> Icons.Default.Person
        }

        Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) CherishMint else Color.Transparent)
            .clickable { onTabSelected(tab) }
            .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            Box {
              Icon(
                imageVector = icon,
                contentDescription = tab.label,
                tint = if (isSelected) CherishTeal else CherishSlate,
                modifier = Modifier.size(20.dp)
              )
              if (tab == BottomTab.MARKET && cartCount > 0) {
                Box(
                  modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 6.dp, y = (-4).dp)
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(CherishCoral),
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    text = cartCount.toString(),
                    color = Color.White,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = tab.label,
              style = MaterialTheme.typography.labelSmall,
              fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
              color = if (isSelected) CherishTeal else CherishSlate
            )
          }
        }
      }
    }
  }
}

@Composable
fun QuickActionFab(
  isOpen: Boolean,
  onToggle: () -> Unit,
  onEmergencySOS: () -> Unit,
  onReportIssue: () -> Unit,
  onBookService: () -> Unit,
  onCartAction: () -> Unit
) {
  val rotation by animateFloatAsState(
    targetValue = if (isOpen) 45f else 0f,
    animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
  )

  Column(
    horizontalAlignment = Alignment.End,
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    AnimatedVisibility(
      visible = isOpen,
      enter = fadeIn() + expandVertically(expandFrom = Alignment.Bottom),
      exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom)
    ) {
      Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        QuickActionButton(
          label = "⚠️ Emergency SOS",
          color = Color(0xFFE11D48),
          textColor = Color.White,
          onClick = {
            onEmergencySOS()
            onToggle()
          }
        )
        QuickActionButton(
          label = "👥 Report Issue",
          color = Color.White,
          textColor = CherishTeal,
          onClick = {
            onReportIssue()
            onToggle()
          }
        )
        QuickActionButton(
          label = "🛒 View Basket",
          color = Color.White,
          textColor = CherishTeal,
          onClick = {
            onCartAction()
            onToggle()
          }
        )
        QuickActionButton(
          label = "🔧 Book Service",
          color = CherishTeal,
          textColor = Color.White,
          onClick = {
            onBookService()
            onToggle()
          }
        )
      }
    }

    // Main Floating Button
    FloatingActionButton(
      onClick = onToggle,
      containerColor = CherishCoral,
      contentColor = Color.White,
      shape = CircleShape,
      elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
      modifier = Modifier.size(54.dp)
    ) {
      Icon(
        imageVector = Icons.Default.Add,
        contentDescription = "Quick Actions",
        modifier = Modifier
          .size(28.dp)
          .rotate(rotation)
      )
    }
  }
}

@Composable
private fun QuickActionButton(
  label: String,
  color: Color,
  textColor: Color,
  onClick: () -> Unit
) {
  Surface(
    shape = CircleShape,
    color = color,
    shadowElevation = 6.dp,
    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
    modifier = Modifier.clickable { onClick() }
  ) {
    Text(
      text = label,
      color = textColor,
      style = MaterialTheme.typography.labelMedium,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartBottomSheet(
  cart: Map<String, CartItem>,
  onClose: () -> Unit,
  onAddToCart: (MarketProduct) -> Unit,
  onDecrease: (String) -> Unit,
  onRemove: (String) -> Unit,
  onCheckout: () -> Unit
) {
  val subtotal = cart.values.sumOf { it.product.price * it.quantity }
  val totalItems = cart.values.sumOf { it.quantity }

  ModalBottomSheet(
    onDismissRequest = onClose,
    containerColor = Color.White,
    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
    dragHandle = {
      Box(
        modifier = Modifier
          .padding(top = 10.dp, bottom = 4.dp)
          .width(44.dp)
          .height(4.dp)
          .clip(CircleShape)
          .background(Color(0xFFCBD5E1))
      )
    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 8.dp)
        .navigationBarsPadding()
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Your Home Basket",
            style = MaterialTheme.typography.titleLarge,
            color = CherishNavy,
            fontWeight = FontWeight.ExtraBold
          )
          Text(
            text = "$totalItems items from verified artisans & local stores",
            style = MaterialTheme.typography.bodySmall,
            color = CherishSlate
          )
        }
        IconButton(onClick = onClose) {
          Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = CherishSlate)
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      if (cart.isEmpty()) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "🛒", fontSize = 48.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "Your basket is currently empty",
              style = MaterialTheme.typography.titleMedium,
              color = CherishNavy,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = "Add fresh groceries or handcrafted essentials",
              style = MaterialTheme.typography.bodySmall,
              color = CherishSlate
            )
          }
        }
      } else {
        Column(
          verticalArrangement = Arrangement.spacedBy(10.dp),
          modifier = Modifier.weight(1f, fill = false)
        ) {
          cart.values.forEach { item ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(CherishSurfaceLight)
                .border(1.dp, CherishBorder, RoundedCornerShape(16.dp))
                .padding(10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(54.dp)
                  .clip(RoundedCornerShape(12.dp))
                  .background(Color.White)
              ) {
                AsyncImage(
                  model = item.product.imageUrl,
                  contentDescription = item.product.name,
                  modifier = Modifier.fillMaxSize()
                )
              }

              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = item.product.name,
                  style = MaterialTheme.typography.titleSmall,
                  color = CherishNavy,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis
                )
                Text(
                  text = item.product.unit,
                  style = MaterialTheme.typography.bodySmall,
                  color = CherishSlate
                )
                Text(
                  text = "₹${item.product.price}",
                  style = MaterialTheme.typography.titleMedium,
                  color = CherishTeal,
                  fontWeight = FontWeight.Bold
                )
              }

              // Stepper (+ -)
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                  .clip(CircleShape)
                  .background(CherishCoral)
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(20.dp)
                    .clickable { onDecrease(item.product.id) },
                  contentAlignment = Alignment.Center
                ) {
                  Text(text = "−", color = Color.White, fontWeight = FontWeight.Black, fontSize = 14.sp)
                }
                Text(
                  text = item.quantity.toString(),
                  color = Color.White,
                  style = MaterialTheme.typography.labelMedium,
                  fontWeight = FontWeight.Bold
                )
                Box(
                  modifier = Modifier
                    .size(20.dp)
                    .clickable { onAddToCart(item.product) },
                  contentAlignment = Alignment.Center
                ) {
                  Text(text = "+", color = Color.White, fontWeight = FontWeight.Black, fontSize = 14.sp)
                }
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Price summary
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(CherishMintLight)
            .padding(14.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = "Delivery to Kharadi", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
            Text(text = "FREE", style = MaterialTheme.typography.labelMedium, color = CherishTeal, fontWeight = FontWeight.Bold)
          }
          Divider(color = CherishBorder)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(text = "Total to Pay", style = MaterialTheme.typography.titleMedium, color = CherishNavy, fontWeight = FontWeight.Bold)
            Text(text = "₹$subtotal", style = MaterialTheme.typography.headlineSmall, color = CherishTeal, fontWeight = FontWeight.ExtraBold)
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
          onClick = onCheckout,
          colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(
              text = "Proceed to Express Checkout",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
          }
        }
      }
    }
  }
}

@Composable
fun BookingDialog(
  provider: ServiceProvider,
  onDismiss: () -> Unit,
  onConfirm: (date: String, slot: String) -> Unit
) {
  var selectedDate by remember { mutableStateOf("Tomorrow, Oct 6") }
  var selectedSlot by remember { mutableStateOf("11:00 AM - 01:00 PM") }
  val dates = listOf("Today, Oct 5", "Tomorrow, Oct 6", "Wednesday, Oct 7")
  val slots = listOf("09:00 AM - 11:00 AM", "11:00 AM - 01:00 PM", "02:00 PM - 04:00 PM", "05:00 PM - 07:00 PM")

  AlertDialog(
    onDismissRequest = onDismiss,
    containerColor = Color.White,
    shape = RoundedCornerShape(24.dp),
    title = {
      Column {
        Text(
          text = "Book Service",
          style = MaterialTheme.typography.titleLarge,
          fontWeight = FontWeight.Bold,
          color = CherishNavy
        )
        Text(
          text = provider.title,
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }
    },
    text = {
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CherishMint)
            .padding(10.dp)
        ) {
          Text(text = "Verified Professional", style = MaterialTheme.typography.labelSmall, color = CherishTeal)
          Text(text = "₹${provider.price} ${provider.priceUnit}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishTeal)
        }

        Text(text = "Select Preferred Date", style = MaterialTheme.typography.labelMedium, color = CherishNavy)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          dates.forEach { d ->
            val isSelected = d == selectedDate
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = if (isSelected) CherishTeal else CherishSurfaceLight,
              border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CherishTeal else CherishBorder),
              modifier = Modifier
                .weight(1f)
                .clickable { selectedDate = d }
            ) {
              Text(
                text = d,
                color = if (isSelected) Color.White else CherishNavy,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
              )
            }
          }
        }

        Text(text = "Select Time Slot", style = MaterialTheme.typography.labelMedium, color = CherishNavy)
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          slots.forEach { slot ->
            val isSelected = slot == selectedSlot
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = if (isSelected) CherishMint else Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CherishTeal else CherishBorder),
              modifier = Modifier
                .fillMaxWidth()
                .clickable { selectedSlot = slot }
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(text = slot, style = MaterialTheme.typography.bodySmall, color = CherishNavy, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
                if (isSelected) {
                  Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = CherishTeal, modifier = Modifier.size(16.dp))
                }
              }
            }
          }
        }
      }
    },
    confirmButton = {
      Button(
        onClick = { onConfirm(selectedDate, selectedSlot) },
        colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
        shape = RoundedCornerShape(12.dp)
      ) {
        Text(text = "Confirm Booking", color = Color.White, fontWeight = FontWeight.Bold)
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text(text = "Cancel", color = CherishSlate)
      }
    }
  )
}
