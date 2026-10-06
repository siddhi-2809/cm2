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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.ServiceCategory
import com.example.model.ServiceProvider
import com.example.ui.theme.*

@Composable
fun ServiceDetailCategoryScreen(
  category: ServiceCategory,
  registeredVendors: List<ServiceProvider> = emptyList(),
  onBack: () -> Unit,
  onFilterClick: () -> Unit,
  onBookProvider: (ServiceProvider) -> Unit,
  onNavigateToVendorRegistration: () -> Unit = {}
) {
  var selectedSubcat by remember { mutableStateOf("All Services") }
  var searchQuery by remember { mutableStateOf("") }

  val allProviders = remember(category.providers, registeredVendors) {
    category.providers + registeredVendors
  }

  val filteredProviders = remember(searchQuery, selectedSubcat, allProviders) {
    allProviders.filter { p ->
      val matchesQuery = searchQuery.isEmpty() ||
        p.title.contains(searchQuery, ignoreCase = true) ||
        p.providerName.contains(searchQuery, ignoreCase = true)

      val matchesChip = selectedSubcat == "All Services" ||
        p.title.contains(selectedSubcat, ignoreCase = true) ||
        p.features.any { it.contains(selectedSubcat, ignoreCase = true) }

      matchesQuery && matchesChip
    }
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Top Bar Row inside page
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
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = CherishTeal)
          }

          Column {
            Text(
              text = category.name,
              style = MaterialTheme.typography.titleLarge,
              fontWeight = FontWeight.Bold,
              color = CherishNavy
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(text = "📍 Kharadi, Pune", style = MaterialTheme.typography.labelSmall, color = CherishSlate)
            }
          }
        }

        IconButton(
          onClick = onFilterClick,
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, CherishBorder, CircleShape)
        ) {
          Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filter", tint = CherishTeal)
        }
      }
    }

    // Hero Banner Card with Image & Dark Gradient Overlay
    item {
      Surface(
        shape = RoundedCornerShape(22.dp),
        color = CherishTeal,
        shadowElevation = 3.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
        ) {
          AsyncImage(
            model = category.heroImage,
            contentDescription = category.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
          )

          // Dark vignette
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(
                androidx.compose.ui.graphics.Brush.verticalGradient(
                  colors = listOf(
                    Color.Transparent,
                    CherishTealDark.copy(alpha = 0.85f),
                    CherishTealDeep
                  )
                )
              )
          )

          Column(
            modifier = Modifier
              .align(Alignment.BottomStart)
              .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Box(
              modifier = Modifier
                .clip(CircleShape)
                .background(CherishMint.copy(alpha = 0.9f))
                .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
              Text(
                text = "✨ TRUSTED HOME EXPERTS",
                color = CherishTeal,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp
              )
            }

            Text(
              text = category.name,
              style = MaterialTheme.typography.headlineMedium,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )

            Text(
              text = category.description,
              style = MaterialTheme.typography.bodySmall,
              color = Color.White.copy(alpha = 0.9f),
              maxLines = 2,
              overflow = TextOverflow.Ellipsis
            )
          }
        }
      }
    }

    // Search bar & Filter Trigger
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier
            .weight(1f)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, CherishBorder, CircleShape)
            .padding(horizontal = 14.dp, vertical = 2.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = CherishSlate, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(8.dp))
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(text = "Search ${category.name} services...", color = CherishMuted, fontSize = 12.sp) },
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
          color = Color.White,
          border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
          modifier = Modifier.clickable { onFilterClick() }
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(imageVector = Icons.Default.FilterList, contentDescription = null, tint = CherishTeal, modifier = Modifier.size(16.dp))
            Text(text = "Filter", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
            Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(CherishCoral))
          }
        }
      }
    }

    // Popular Chips (Horizontal)
    item {
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "POPULAR IN ${category.name.uppercase()}", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
          Text(text = "View all", style = MaterialTheme.typography.labelSmall, color = CherishCoral, fontWeight = FontWeight.Bold)
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          items(category.popularChips) { chip ->
            val isSelected = selectedSubcat == chip
            Surface(
              shape = CircleShape,
              color = if (isSelected) CherishTeal else Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CherishTeal else CherishBorder),
              modifier = Modifier.clickable { selectedSubcat = chip }
            ) {
              Text(
                text = chip,
                color = if (isSelected) Color.White else CherishNavy,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
              )
            }
          }
        }
      }
    }

    // Providers List Header
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = "${category.name} Services",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = CherishNavy
          )
          Surface(
            shape = CircleShape,
            color = CherishMint,
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF))
          ) {
            Text(
              text = "(${filteredProviders.size} available)",
              color = CherishTeal,
              style = MaterialTheme.typography.labelSmall,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
          }
        }

        Text(text = "Sort: Recommended ▾", style = MaterialTheme.typography.labelSmall, color = CherishSlate)
      }
    }

    // Provider Cards or Empty State
    if (filteredProviders.isEmpty()) {
      item {
        Surface(
          shape = RoundedCornerShape(22.dp),
          color = Color.White,
          border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
          shadowElevation = 1.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
          ) {
            Box(
              modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(CherishMint),
              contentAlignment = Alignment.Center
            ) {
              Text(text = "🔍", fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
              text = "No verified vendors listed",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = CherishNavy
            )
            Text(
              text = "No service professionals currently registered in this category.",
              style = MaterialTheme.typography.bodySmall,
              color = CherishSlate,
              modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            )
            Button(
              onClick = onNavigateToVendorRegistration,
              colors = ButtonDefaults.buttonColors(containerColor = CherishTeal),
              shape = RoundedCornerShape(12.dp)
            ) {
              Text(text = "Register as Vendor →", color = Color.White, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    } else {
      items(filteredProviders) { provider ->
        ProviderCard(
          provider = provider,
          onBook = { onBookProvider(provider) }
        )
      }
    }
  }
}

@Composable
private fun ProviderCard(
  provider: ServiceProvider,
  onBook: () -> Unit
) {
  Surface(
    shape = RoundedCornerShape(22.dp),
    color = Color.White,
    border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
    shadowElevation = 2.dp,
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(
      modifier = Modifier.padding(14.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
      ) {
        // Avatar / Monogram Box
        Box(
          modifier = Modifier
            .size(76.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CherishSurfaceLight)
            .border(1.dp, CherishBorder, RoundedCornerShape(16.dp))
        ) {
          if (provider.imageUrl.isNotBlank()) {
            AsyncImage(
              model = provider.imageUrl,
              contentDescription = provider.providerName,
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )
          } else {
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(CherishMint),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = provider.initials.ifBlank { "CH" },
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = CherishTeal
              )
            }
          }

          // Verified Green Checkmark Badge
          Box(
            modifier = Modifier
              .align(Alignment.BottomEnd)
              .offset(x = 2.dp, y = 2.dp)
              .size(18.dp)
              .clip(CircleShape)
              .background(Color(0xFF10B981))
              .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = "Verified",
              tint = Color.White,
              modifier = Modifier.size(10.dp)
            )
          }
        }

        // Details
        Column(modifier = Modifier.weight(1f)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = CherishMintLight,
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF))
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
              ) {
                Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(Color(0xFF0284C7)))
                Text(text = "VERIFIED EXPERT", color = Color(0xFF0369A1), fontSize = 8.sp, fontWeight = FontWeight.Bold)
              }
            }

            if (provider.highlightBadge.isNotBlank()) {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = CherishPeach,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFD8CC))
              ) {
                Text(
                  text = provider.highlightBadge,
                  color = CherishCoral,
                  fontSize = 8.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = provider.title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = CherishNavy,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
          )

          Text(
            text = provider.providerName,
            style = MaterialTheme.typography.bodySmall,
            color = CherishSlate
          )

          Spacer(modifier = Modifier.height(2.dp))

          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Text(text = "★ ${provider.rating}", style = MaterialTheme.typography.labelSmall, color = Color(0xFFD97706), fontWeight = FontWeight.Bold)
            Text(text = "(${provider.reviewCount})", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
            Text(text = "•", color = CherishBorder)
            Text(text = "📍 ${provider.distanceKm} km away", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
        }
      }

      // Feature Line Checklist
      if (provider.features.isNotEmpty()) {
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = CherishSurfaceLight,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "✓ ${provider.features.joinToString(" • ")}",
            style = MaterialTheme.typography.bodySmall,
            color = CherishSlate,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
          )
        }
      }

      // Price & View Details Action
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(text = "PRICING", style = MaterialTheme.typography.labelSmall, color = CherishMuted, fontSize = 9.sp)
          Row(verticalAlignment = Alignment.Bottom) {
            Text(text = "₹${provider.price}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = CherishTeal)
            Text(text = " ${provider.priceUnit}", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
        }

        Button(
          onClick = onBook,
          colors = ButtonDefaults.buttonColors(containerColor = CherishTeal),
          shape = RoundedCornerShape(12.dp),
          contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
          Text(text = "View Details", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color.White)
        }
      }
    }
  }
}
