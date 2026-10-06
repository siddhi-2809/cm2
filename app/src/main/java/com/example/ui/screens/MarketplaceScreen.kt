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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
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
import com.example.model.MarketCategory
import com.example.model.MarketProduct
import com.example.ui.theme.*

@Composable
fun MarketplaceScreen(
  cart: Map<String, CartItem>,
  wishlist: Set<String>,
  onAddToCart: (MarketProduct) -> Unit,
  onDecrease: (String) -> Unit,
  onToggleWishlist: (String) -> Unit,
  onSelectCategory: (String) -> Unit,
  onOpenCart: () -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedChip by remember { mutableStateOf("All Products") }

  val filterChips = listOf(
    "All Products",
    "Daily Groceries",
    "Home Decor",
    "Handmade Textiles",
    "Pottery & Ceramics",
    "Organic & Wellness"
  )

  val trendingProducts = remember {
    DataRepository.marketProducts.filter { it.isTrending }
  }

  val pickedProducts = remember {
    DataRepository.marketProducts.filter { it.isPicked }
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Page Title
    item {
      Column {
        Text(
          text = "Marketplace",
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.ExtraBold,
          color = CherishNavy
        )
        Text(
          text = "Everything your home needs, all in one place.",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate
        )
      }
    }

    // Search & Filter Bar
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, CherishBorder, RoundedCornerShape(16.dp))
            .padding(horizontal = 14.dp, vertical = 2.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = CherishSlate, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(8.dp))
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(text = "Search products, groceries, pottery...", color = CherishMuted, fontSize = 12.sp) },
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = Color.Transparent,
              unfocusedBorderColor = Color.Transparent
            ),
            singleLine = true,
            modifier = Modifier.weight(1f)
          )
        }

        Surface(
          shape = CircleShape,
          color = CherishMint,
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF)),
          modifier = Modifier.clickable { onOpenCart() }
        ) {
          Box(modifier = Modifier.padding(10.dp)) {
            Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filter", tint = CherishTeal, modifier = Modifier.size(18.dp))
          }
        }
      }
    }

    // Horizontal Chips
    item {
      LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        items(filterChips) { chip ->
          val isSelected = selectedChip == chip
          Surface(
            shape = CircleShape,
            color = if (isSelected) CherishMint else Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CherishTeal else CherishBorder),
            modifier = Modifier.clickable {
              selectedChip = chip
              if (chip == "Daily Groceries") onSelectCategory("groceries")
            }
          ) {
            Text(
              text = chip,
              color = if (isSelected) CherishTeal else CherishNavy,
              style = MaterialTheme.typography.labelSmall,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
            )
          }
        }
      }
    }

    // Curated For Living Hero Banner
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
            Text(text = "✨ CURATED FOR LIVING", color = CherishMint, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = "Everything your home deserves.",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )

          Text(
            text = "Shop everyday essentials, beautiful handmade products and trusted home collections.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.85f),
            modifier = Modifier.padding(top = 4.dp)
          )

          Spacer(modifier = Modifier.height(12.dp))

          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = { onSelectCategory("groceries") },
              colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
              shape = CircleShape,
              contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(text = "Shop Now", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = Color.White)
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
              }
            }

            Surface(
              shape = CircleShape,
              color = Color.White.copy(alpha = 0.15f),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.2f))
            ) {
              Text(
                text = "Free Pune Delivery",
                color = Color(0xFF98EAF0),
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(120.dp)
              .clip(RoundedCornerShape(16.dp))
          ) {
            AsyncImage(
              model = "https://lh3.googleusercontent.com/aida/AEtjO1UMGRW1tnQfQcSZzkXH8eApJMdPuY6OLRwrmhAuZQfjjcpazdavxnyjHXuf80cKjtKK-wzoYcoIJhBAt8fkNStpOC5-6St6HCrJm2hekpz1ensUsaFEbn0l0ZGLz-39Cxmo7-VKfnyOL6nCO1dgOTRLvLWQF__SJ7IkfPlilGw8p2-RojAeT-0_V1tJQSUZOh43YmYbO9pMImzk1YTn4GTz4hfrJn_JPP1nl7r3jMUtHi2CDCl3-zLplhq3",
              contentDescription = "Lifestyle",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )
          }
        }
      }
    }

    // 12 Explore Categories
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Column {
          Text(text = "Explore Categories", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
          Text(text = "Shop from our curated home collections (12 Categories)", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          DataRepository.marketCategories.chunked(2).forEach { pair ->
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              pair.forEach { cat ->
                MarketCategoryTile(
                  category = cat,
                  modifier = Modifier.weight(1f),
                  onClick = { onSelectCategory(cat.id) }
                )
              }
            }
          }
        }
      }
    }

    // Trending for You Carousel
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(text = "Trending for You", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
            Text(text = "Top rated by your residential community", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
          Text(
            text = "View All →",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = CherishTeal,
            modifier = Modifier.clickable { onSelectCategory("groceries") }
          )
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
          items(trendingProducts) { product ->
            val inCartQty = cart[product.id]?.quantity ?: 0
            val isFav = wishlist.contains(product.id)

            ProductCard(
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

    // Picked for your home (Vertical Stack)
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Column {
          Text(text = "Picked for your home", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
          Text(text = "Recommended from your resident history", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
        }

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          pickedProducts.forEach { product ->
            val inCartQty = cart[product.id]?.quantity ?: 0

            Surface(
              shape = RoundedCornerShape(18.dp),
              color = Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
              shadowElevation = 1.dp,
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(62.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CherishSurfaceLight)
                ) {
                  AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                  )
                }

                Column(modifier = Modifier.weight(1f)) {
                  Text(text = product.category.uppercase(), style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                  Text(text = product.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
                  Text(text = product.unit, style = MaterialTheme.typography.bodySmall, color = CherishSlate)
                }

                Column(
                  horizontalAlignment = Alignment.End,
                  verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  Text(text = "₹${product.price}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)

                  if (inCartQty > 0) {
                    Row(
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.spacedBy(6.dp),
                      modifier = Modifier
                        .clip(CircleShape)
                        .background(CherishMint)
                        .border(1.dp, Color(0xFFC8E6DF), CircleShape)
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                      Box(modifier = Modifier.clickable { onDecrease(product.id) }) {
                        Text(text = "−", color = CherishTeal, fontWeight = FontWeight.Bold)
                      }
                      Text(text = inCartQty.toString(), style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
                      Box(modifier = Modifier.clickable { onAddToCart(product) }) {
                        Text(text = "+", color = CherishTeal, fontWeight = FontWeight.Bold)
                      }
                    }
                  } else {
                    Button(
                      onClick = { onAddToCart(product) },
                      colors = ButtonDefaults.buttonColors(containerColor = CherishMint),
                      shape = CircleShape,
                      contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                      modifier = Modifier.height(28.dp)
                    ) {
                      Text(text = "+ Add", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    // Trust Pillars (30 min delivery, verified makers, easy returns)
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        TrustPillarCard("🛵", "30 Min Delivery", "To your doorstep", Modifier.weight(1f))
        TrustPillarCard("🛡️", "Verified Makers", "Ethical & local", Modifier.weight(1f))
        TrustPillarCard("😊", "Easy Returns", "No questions asked", Modifier.weight(1f))
      }
    }
  }
}

@Composable
private fun MarketCategoryTile(
  category: MarketCategory,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Surface(
    shape = RoundedCornerShape(18.dp),
    color = Color.White,
    border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
    shadowElevation = 1.dp,
    modifier = modifier.clickable { onClick() }
  ) {
    Column(
      modifier = Modifier.padding(12.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(34.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(CherishMint),
          contentAlignment = Alignment.Center
        ) {
          Text(text = category.iconEmoji, fontSize = 16.sp)
        }
        Text(text = "↗", color = CherishMintBright, fontSize = 14.sp, fontWeight = FontWeight.Bold)
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = category.name,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = CherishNavy,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
      )

      Text(
        text = category.description,
        style = MaterialTheme.typography.bodySmall,
        color = CherishSlate,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 11.sp
      )
    }
  }
}

@Composable
private fun ProductCard(
  product: MarketProduct,
  inCartQty: Int,
  isFavorite: Boolean,
  onAdd: () -> Unit,
  onDecrease: () -> Unit,
  onToggleWishlist: () -> Unit
) {
  Surface(
    shape = RoundedCornerShape(20.dp),
    color = Color.White,
    border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
    shadowElevation = 2.dp,
    modifier = Modifier.width(180.dp)
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      // Image Box with discount & wishlist button
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(120.dp)
          .clip(RoundedCornerShape(14.dp))
          .background(CherishSurfaceLight)
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
              .padding(6.dp)
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
            .padding(4.dp)
            .size(28.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.9f))
        ) {
          Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "Wishlist",
            tint = if (isFavorite) CherishCoral else CherishSlate,
            modifier = Modifier.size(16.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
      ) {
        Text(text = "★ ${product.rating}", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD97706), fontWeight = FontWeight.Bold)
        Text(text = "(${product.reviewCount})", style = MaterialTheme.typography.bodySmall, color = CherishSlate, fontSize = 10.sp)
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
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.Bottom) {
          Text(text = "₹${product.price}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = CherishNavy)
          if (product.originalPrice != null) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "₹${product.originalPrice}",
              style = MaterialTheme.typography.bodySmall.copy(textDecoration = TextDecoration.LineThrough),
              color = CherishSlate
            )
          }
        }

        if (inCartQty > 0) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
              .clip(CircleShape)
              .background(CherishCoral)
              .padding(horizontal = 8.dp, vertical = 2.dp)
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
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            modifier = Modifier.height(28.dp)
          ) {
            Text(text = "+ Add", style = MaterialTheme.typography.labelSmall, color = Color.White, fontWeight = FontWeight.Bold)
          }
        }
      }
    }
  }
}

@Composable
private fun TrustPillarCard(
  emoji: String,
  title: String,
  subtitle: String,
  modifier: Modifier = Modifier
) {
  Surface(
    shape = RoundedCornerShape(16.dp),
    color = Color.White,
    border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
    modifier = modifier
  ) {
    Column(
      modifier = Modifier.padding(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = emoji, fontSize = 20.sp)
      Spacer(modifier = Modifier.height(4.dp))
      Text(text = title, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
      Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = CherishSlate, fontSize = 9.sp)
    }
  }
}
