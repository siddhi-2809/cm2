package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DataRepository
import com.example.model.ServiceCategory
import com.example.ui.theme.*

@Composable
fun ServicesCatalogScreen(
  onSelectCategory: (String) -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedChip by remember { mutableStateOf("All") }

  val filterChips = listOf(
    "All" to "All",
    "training" to "📚 Training",
    "needs" to "🛠️ Needs",
    "care" to "❤️ Care",
    "kitchen" to "🍳 Kitchen",
    "security" to "🛡️ Security",
    "society" to "🏢 Society",
    "entertainment" to "🎬 Entertainment"
  )

  val filteredCategories = remember(searchQuery, selectedChip) {
    DataRepository.serviceCategories.filter { cat ->
      val matchesQuery = searchQuery.isEmpty() ||
        cat.name.contains(searchQuery, ignoreCase = true) ||
        cat.tagline.contains(searchQuery, ignoreCase = true)

      val matchesChip = selectedChip == "All" || when (selectedChip) {
        "training" -> cat.id == "home_training"
        "needs" -> cat.id == "home_needs"
        "care" -> cat.id == "home_care"
        "kitchen" -> cat.id == "kitchen_needs"
        "security" -> cat.id == "home_security"
        "society" -> cat.id == "society_needs"
        "entertainment" -> cat.id == "home_entertainment"
        else -> true
      }

      matchesQuery && matchesChip
    }
  }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header Title & Tagline
    item {
      Column {
        Text(
          text = "Services",
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.ExtraBold,
          color = CherishTeal
        )
        Text(
          text = "Find trusted help for everything your home needs.",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate
        )
      }
    }

    // Search Bar
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(CircleShape)
          .background(Color.White)
          .border(1.dp, CherishBorder, CircleShape)
          .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = CherishSlate, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text(text = "Search services...", color = CherishMuted, fontSize = 12.sp) },
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
          ),
          singleLine = true,
          modifier = Modifier.weight(1f)
        )
        Icon(imageVector = Icons.Default.Mic, contentDescription = "Voice", tint = CherishSlate, modifier = Modifier.size(18.dp))
      }
    }

    // Quick Filter Chips (Horizontal Scroll)
    item {
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        items(filterChips) { (chipKey, label) ->
          val isSelected = selectedChip == chipKey
          Surface(
            shape = CircleShape,
            color = if (isSelected) CherishTeal else Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CherishTeal else CherishBorder),
            modifier = Modifier.clickable { selectedChip = chipKey }
          ) {
            Text(
              text = label,
              color = if (isSelected) Color.White else CherishTeal,
              style = MaterialTheme.typography.labelSmall,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            )
          }
        }
      }
    }

    // Sub-header with Category Count
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "What do you need help with?",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = CherishTeal
          )
          Text(
            text = "Choose a category to get started.",
            style = MaterialTheme.typography.bodySmall,
            color = CherishSlate
          )
        }

        Surface(
          shape = CircleShape,
          color = CherishMint,
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF))
        ) {
          Text(
            text = "${filteredCategories.size} Categories",
            color = CherishTeal,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
          )
        }
      }
    }

    // 2-Column Grid of Service Categories
    item {
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        filteredCategories.chunked(2).forEach { rowCats ->
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            rowCats.forEach { cat ->
              ServiceCategoryCard(
                category = cat,
                modifier = Modifier.weight(1f),
                onClick = { onSelectCategory(cat.id) }
              )
            }
            if (rowCats.size == 1) {
              Spacer(modifier = Modifier.weight(1f))
            }
          }
        }
      }
    }

    // Cherish Assurance Guarantee Banner
    item {
      Surface(
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Box(
            modifier = Modifier
              .size(40.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(CherishMint),
            contentAlignment = Alignment.Center
          ) {
            Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = CherishTeal, modifier = Modifier.size(22.dp))
          }
          Column {
            Text(text = "CherishHome Assurance", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
            Text(text = "₹10,000 damage protection with every booking.", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
        }
      }
    }
  }
}

@Composable
private fun ServiceCategoryCard(
  category: ServiceCategory,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Surface(
    shape = RoundedCornerShape(22.dp),
    color = Color.White,
    border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
    shadowElevation = 2.dp,
    modifier = modifier.clickable { onClick() }
  ) {
    Column(
      modifier = Modifier.padding(12.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(CherishMint),
            contentAlignment = Alignment.Center
          ) {
            Text(text = category.iconEmoji, fontSize = 18.sp)
          }

          Box(
            modifier = Modifier
              .clip(CircleShape)
              .background(CherishMint)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(
              text = category.badge.uppercase(),
              color = CherishTeal,
              style = MaterialTheme.typography.labelSmall,
              fontWeight = FontWeight.Bold,
              fontSize = 8.sp
            )
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = category.name,
          style = MaterialTheme.typography.titleSmall,
          fontWeight = FontWeight.Bold,
          color = CherishNavy,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )

        Text(
          text = category.tagline,
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
          lineHeight = 15.sp,
          modifier = Modifier.padding(top = 2.dp)
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "${category.providers.size} Pros",
          style = MaterialTheme.typography.labelSmall,
          color = CherishTeal,
          fontWeight = FontWeight.SemiBold
        )

        Box(
          modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(CherishTeal),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "→", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}
