package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.DataRepository
import com.example.model.CartItem
import com.example.model.MarketProduct
import com.example.ui.theme.*

@Composable
fun DailyGroceriesScreen(
  cart: Map<String, CartItem>,
  wishlist: Set<String>,
  onBack: () -> Unit,
  onOpenDrawer: () -> Unit,
  onOpenCart: () -> Unit,
  onAddToCart: (MarketProduct) -> Unit,
  onDecrease: (String) -> Unit,
  onToggleWishlist: (String) -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedSubcat by remember { mutableStateOf("All Groceries") }

  val groceryPills = listOf(
    "All Groceries",
    "🥬 Fruits & Vegetables",
    "🥛 Dairy & Eggs",
    "🌾 Staples & Grains",
    "🍪 Snacks & Munchies",
    "🧃 Beverages & Juices"
  )

  val allGroceryProducts = remember {
    DataRepository.marketProducts.filter { it.category == "Daily Groceries" }
  }

  val freshPicks = remember {
    allGroceryProducts.take(4)
  }

  val dailyEssentials = remember {
    allGroceryProducts.drop(4)
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Top Bar
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          IconButton(
            onClick = onBack,
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(Color.White)
              .border(1.dp, CherishBorder, CircleShape)
          ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = CherishNavy)
          }

          IconButton(
            onClick = onOpenDrawer,
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(Color.White)
              .border(1.dp, CherishBorder, CircleShape)
          ) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "Drawer", tint = CherishNavy)
          }

          Text(
            text = "Daily Groceries",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = CherishNavy
          )
        }

        Surface(
          shape = CircleShape,
          color = CherishMint,
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF)),
          modifier = Modifier.clickable { onOpenCart() }
        ) {
          Box(modifier = Modifier.padding(8.dp)) {
            Text(text = "🛒 Basket", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
          }
        }
      }
    }

    // Hero Banner
    item {
      Surface(
        shape = RoundedCornerShape(26.dp),
        color = CherishTeal,
        shadowElevation = 3.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Box(
            modifier = Modifier
              .clip(CircleShape)
              .background(Color.White.copy(alpha = 0.15f))
              .padding(horizontal = 10.dp, vertical = 3.dp)
          ) {
            Text(text = "✨ EVERYDAY ESSENTIALS", color = CherishMint, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "Daily Groceries",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )

          Text(
            text = "Fresh everyday essentials delivered conveniently to your home in 30 mins.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.9f),
            modifier = Modifier.padding(top = 4.dp)
          )

          Spacer(modifier = Modifier.height(12.dp))

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(130.dp)
              .clip(RoundedCornerShape(16.dp))
          ) {
            AsyncImage(
              model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAqurEusqgMWtcbyS8538b6zshcd5HV4O4IF1Kh6wcNTGRSAIB9PLTyHDZVZFXb0xWsnQCARnRd4Ieanxj_lyKM5NvM1zR9GpO0t7IMX72Qr_YFRkrVDrFe5Wi2WvaT33pKFV8sgmWQYPTHXyodkRCFeCeDXeXZ-rW5ihCv4fJvJT071PIUIDkoLTVu534fKRZH5IML1YteYNZ5J-P1mUtBOzkAYXml2yRHf1MaRYzVDlgapgRdYbzpow",
              contentDescription = "Groceries",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Surface(
              shape = CircleShape,
              color = Color.White.copy(alpha = 0.15f)
            ) {
              Text(
                text = "⚡ 30-Min Fast Delivery",
                color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }

            Surface(
              shape = CircleShape,
              color = Color.White.copy(alpha = 0.15f)
            ) {
              Text(
                text = "🌱 Farm Fresh Guarantee",
                color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
          }
        }
      }
    }

    // Subcategories Horizontal Scroll
    item {
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "Shop Groceries", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
          Text(text = "Swipe for more", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          items(groceryPills) { pill ->
            val isSelected = selectedSubcat == pill
            Surface(
              shape = CircleShape,
              color = if (isSelected) CherishMint else Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CherishTeal else CherishBorder),
              modifier = Modifier.clickable { selectedSubcat = pill }
            ) {
              Text(
                text = pill,
                color = if (isSelected) CherishTeal else CherishNavy,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
              )
            }
          }
        }
      }
    }

    // Fresh Picks Carousel
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(text = "Fresh Picks", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
            Text(text = "Farm to Table • Fresh Today", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
          Text(text = "View All →", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          items(freshPicks) { product ->
            val inCartQty = cart[product.id]?.quantity ?: 0
            val isFav = wishlist.contains(product.id)

            GroceryCard(
              product = product,
              inCartQty = inCartQty,
              isFavorite = isFav,
              onAdd = { onAddToCart(product) },
              onDecrease = { onDecrease(product.id) },
              onToggleWishlist = { onToggleWishlist(product.id) }
            )
          }
        }
      }
    }

    // Daily Essentials 2-Column Grid
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(text = "Daily Essentials", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
            Text(text = "Community pantry staples", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
          Text(text = "View All →", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
        }

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          dailyEssentials.chunked(2).forEach { rowProducts ->
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              rowProducts.forEach { product ->
                val inCartQty = cart[product.id]?.quantity ?: 0
                val isFav = wishlist.contains(product.id)

                GroceryGridCard(
                  product = product,
                  inCartQty = inCartQty,
                  isFavorite = isFav,
                  modifier = Modifier.weight(1f),
                  onAdd = { onAddToCart(product) },
                  onDecrease = { onDecrease(product.id) },
                  onToggleWishlist = { onToggleWishlist(product.id) }
                )
              }
              if (rowProducts.size == 1) {
                Spacer(modifier = Modifier.weight(1f))
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun GroceryCard(
  product: MarketProduct,
  inCartQty: Int,
  isFavorite: Boolean,
  onAdd: () -> Unit,
  onDecrease: () -> Unit,
  onToggleWishlist: () -> Unit
) {
  Surface(
    shape = RoundedCornerShape(18.dp),
    color = Color.White,
    border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
    shadowElevation = 2.dp,
    modifier = Modifier.width(160.dp)
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(110.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(Color(0xFFF4F9F6))
      ) {
        AsyncImage(
          model = product.imageUrl,
          contentDescription = product.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )

        if (product.discountPercent != null) {
          Box(
            modifier = Modifier
              .align(Alignment.TopStart)
              .padding(4.dp)
              .clip(CircleShape)
              .background(CherishCoral)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(text = "-${product.discountPercent}%", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
          }
        }

        IconButton(
          onClick = onToggleWishlist,
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(2.dp)
            .size(26.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.9f))
        ) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            tint = if (isFavorite) CherishCoral else CherishSlate,
            modifier = Modifier.size(14.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "★ ${product.rating}", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD97706), fontWeight = FontWeight.Bold)
        Text(text = " (${product.reviewCount})", style = MaterialTheme.typography.bodySmall, color = CherishSlate, fontSize = 9.sp)
      }

      Text(
        text = product.name,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = CherishNavy,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Text(
        text = product.unit,
        style = MaterialTheme.typography.bodySmall,
        color = CherishSlate,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 11.sp
      )

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = "₹${product.price}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)

        if (inCartQty > 0) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
              .clip(CircleShape)
              .background(CherishCoral)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Box(modifier = Modifier.clickable { onDecrease() }) {
              Text(text = "−", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Text(text = inCartQty.toString(), style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
            Box(modifier = Modifier.clickable { onAdd() }) {
              Text(text = "+", color = Color.White, fontWeight = FontWeight.Bold)
            }
          }
        } else {
          Button(
            onClick = onAdd,
            colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
            shape = CircleShape,
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
            modifier = Modifier.height(26.dp)
          ) {
            Text(text = "+ Add", style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
          }
        }
      }
    }
  }
}

@Composable
private fun GroceryGridCard(
  product: MarketProduct,
  inCartQty: Int,
  isFavorite: Boolean,
  modifier: Modifier = Modifier,
  onAdd: () -> Unit,
  onDecrease: () -> Unit,
  onToggleWishlist: () -> Unit
) {
  Surface(
    shape = RoundedCornerShape(18.dp),
    color = Color.White,
    border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
    shadowElevation = 2.dp,
    modifier = modifier
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(115.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(Color(0xFFFFFBEB))
      ) {
        AsyncImage(
          model = product.imageUrl,
          contentDescription = product.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )

        if (product.discountPercent != null) {
          Box(
            modifier = Modifier
              .align(Alignment.TopStart)
              .padding(4.dp)
              .clip(CircleShape)
              .background(CherishCoral)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(text = "-${product.discountPercent}%", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
          }
        }

        IconButton(
          onClick = onToggleWishlist,
          modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(2.dp)
            .size(26.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.9f))
        ) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = null,
            tint = if (isFavorite) CherishCoral else CherishSlate,
            modifier = Modifier.size(14.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "★ ${product.rating}", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD97706), fontWeight = FontWeight.Bold)
        Text(text = " (${product.reviewCount})", style = MaterialTheme.typography.bodySmall, color = CherishSlate, fontSize = 9.sp)
      }

      Text(
        text = product.name,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = CherishNavy,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Text(
        text = product.unit,
        style = MaterialTheme.typography.bodySmall,
        color = CherishSlate,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 11.sp
      )

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.Bottom) {
          Text(text = "₹${product.price}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
          if (product.originalPrice != null) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = "₹${product.originalPrice}", style = MaterialTheme.typography.bodySmall.copy(textDecoration = TextDecoration.LineThrough), color = CherishSlate)
          }
        }

        if (inCartQty > 0) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
              .clip(CircleShape)
              .background(CherishCoral)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Box(modifier = Modifier.clickable { onDecrease() }) {
              Text(text = "−", color = Color.White, fontWeight = FontWeight.Bold)
            }
            Text(text = inCartQty.toString(), style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
            Box(modifier = Modifier.clickable { onAdd() }) {
              Text(text = "+", color = Color.White, fontWeight = FontWeight.Bold)
            }
          }
        } else {
          Button(
            onClick = onAdd,
            colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
            shape = CircleShape,
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp),
            modifier = Modifier.height(26.dp)
          ) {
            Text(text = "+ Add", style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
          }
        }
      }
    }
  }
}
