package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DataRepository
import com.example.model.ServiceCategory
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceFilterBottomSheet(
  currentDistance: Float,
  currentRating: Float,
  onClose: () -> Unit,
  onApply: (distance: Float, rating: Float) -> Unit,
  onReset: () -> Unit
) {
  var distance by remember { mutableStateOf(currentDistance) }
  var rating by remember { mutableStateOf(currentRating) }

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
        .navigationBarsPadding(),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Filter Services",
            style = MaterialTheme.typography.titleLarge,
            color = CherishNavy,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "Narrow down verified local experts",
            style = MaterialTheme.typography.bodySmall,
            color = CherishSlate
          )
        }
        IconButton(onClick = onClose) {
          Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = CherishSlate)
        }
      }

      // Distance Slider
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(text = "Distance Radius", style = MaterialTheme.typography.labelMedium, color = CherishNavy)
          Text(
            text = "Within ${distance.toInt()} km",
            style = MaterialTheme.typography.labelMedium,
            color = CherishTeal,
            fontWeight = FontWeight.Bold
          )
        }
        Slider(
          value = distance,
          onValueChange = { distance = it },
          valueRange = 1f..15f,
          steps = 13,
          colors = SliderDefaults.colors(
            thumbColor = CherishTeal,
            activeTrackColor = CherishTeal,
            inactiveTrackColor = CherishMint
          )
        )
      }

      // Rating selector
      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Minimum Rating", style = MaterialTheme.typography.labelMedium, color = CherishNavy)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          listOf(4.0f to "4.0+", 4.5f to "4.5+", 4.8f to "4.8+ ★").forEach { (valRating, label) ->
            val isSelected = rating == valRating
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = if (isSelected) CherishMint else Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CherishTeal else CherishBorder),
              modifier = Modifier
                .weight(1f)
                .clickable { rating = valRating }
            ) {
              Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(vertical = 10.dp)
              ) {
                Text(
                  text = label,
                  style = MaterialTheme.typography.labelMedium,
                  color = if (isSelected) CherishTeal else CherishNavy,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }
        }
      }

      // Action Buttons
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        OutlinedButton(
          onClick = {
            distance = 5f
            rating = 4.5f
            onReset()
          },
          shape = RoundedCornerShape(14.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text(text = "Clear All", color = CherishSlate)
        }

        Button(
          onClick = { onApply(distance, rating) },
          colors = ButtonDefaults.buttonColors(containerColor = CherishTeal),
          shape = RoundedCornerShape(14.dp),
          modifier = Modifier.weight(2f)
        ) {
          Text(text = "Apply Filters", color = Color.White, fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}

@Composable
fun CategorySideDrawerSheet(
  categories: List<ServiceCategory> = DataRepository.serviceCategories,
  selectedCategoryId: String?,
  onSelectCategory: (String) -> Unit,
  onClose: () -> Unit
) {
  ModalDrawerSheet(
    drawerContainerColor = Color.White,
    modifier = Modifier.width(310.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .navigationBarsPadding()
    ) {
      // Header
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(CherishSurfaceLight)
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(CherishTeal)
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(text = "CHERISHHOME", color = CherishMint, fontSize = 9.sp, fontWeight = FontWeight.Bold)
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(text = "Services", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = CherishNavy)
          Text(text = "Explore everything your home needs", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
        }
        IconButton(onClick = onClose) {
          Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = CherishSlate)
        }
      }

      // Categories List
      Column(
        modifier = Modifier
          .weight(1f)
          .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        categories.forEach { cat ->
          val isSelected = cat.id == selectedCategoryId
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(if (isSelected) CherishMint else Color.Transparent)
              .border(1.dp, if (isSelected) Color(0xFFC8E6DF) else Color.Transparent, RoundedCornerShape(12.dp))
              .clickable { onSelectCategory(cat.id) }
              .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Text(text = cat.iconEmoji, fontSize = 18.sp)
              Column {
                Text(
                  text = cat.name.uppercase(),
                  style = MaterialTheme.typography.labelMedium,
                  color = if (isSelected) CherishTeal else CherishNavy,
                  fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold
                )
                if (isSelected) {
                  Text(text = "Active Category", style = MaterialTheme.typography.labelSmall, color = CherishTeal)
                }
              }
            }

            Icon(
              imageVector = Icons.Default.KeyboardArrowRight,
              contentDescription = null,
              tint = if (isSelected) CherishTeal else Color(0xFFCBD5E1),
              modifier = Modifier.size(18.dp)
            )
          }
        }
      }

      // Footer
      Surface(
        color = CherishSurfaceLight,
        modifier = Modifier
          .fillMaxWidth()
          .padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder)
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(CherishCoralLight),
            contentAlignment = Alignment.Center
          ) {
            Text(text = "24/7", color = CherishCoral, fontSize = 10.sp, fontWeight = FontWeight.Bold)
          }
          Column {
            Text(text = "Need Custom Help?", style = MaterialTheme.typography.labelMedium, color = CherishNavy, fontWeight = FontWeight.Bold)
            Text(text = "Contact Cherish Concierge Desk", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
        }
      }
    }
  }
}
