package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.BookingRecord
import com.example.ui.theme.*

@Composable
fun AccountScreen(
  userName: String,
  userPhone: String,
  userSociety: String,
  userFlat: String,
  bookings: List<BookingRecord>,
  onOpenVendorRegistration: () -> Unit = {},
  onLogout: () -> Unit
) {
  var selectedTab by remember { mutableStateOf("Bookings") }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // User Card
    item {
      Surface(
        shape = RoundedCornerShape(26.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            Box(
              modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(CherishTeal),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "SK",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
              )
            }

            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = "$userName K.",
                  style = MaterialTheme.typography.titleLarge,
                  fontWeight = FontWeight.ExtraBold,
                  color = CherishNavy
                )
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFD1FAE5))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(text = "Verified Resident", color = Color(0xFF065F46), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
              }

              Text(text = "+91 $userPhone", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
              Text(text = "$userFlat, $userSociety", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
            }
          }
        }
      }
    }

    // Cherish Assurance Protection Card
    item {
      Surface(
        shape = RoundedCornerShape(20.dp),
        color = CherishMint,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF)),
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(16.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(CherishTeal),
            contentAlignment = Alignment.Center
          ) {
            Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = CherishMint, modifier = Modifier.size(24.dp))
          }
          Column(modifier = Modifier.weight(1f)) {
            Text(text = "CherishHome Assurance Active", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishTeal)
            Text(text = "₹10,000 comprehensive property damage cover & verified contractor warranty enabled.", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
        }
      }
    }

    // Tabs: Bookings | Addresses | Support
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(16.dp))
          .background(Color.White)
          .border(1.dp, CherishBorder, RoundedCornerShape(16.dp))
          .padding(4.dp)
      ) {
        listOf("Bookings", "Saved Addresses", "Concierge Help").forEach { tab ->
          val isSel = selectedTab == tab
          Box(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(12.dp))
              .background(if (isSel) CherishTeal else Color.Transparent)
              .clickable { selectedTab = tab }
              .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = tab,
              color = if (isSel) Color.White else CherishSlate,
              style = MaterialTheme.typography.labelSmall,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }

    // Content based on tab
    when (selectedTab) {
      "Bookings" -> {
        item {
          Text(text = "Active & Past Bookings (${bookings.size})", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
        }
        if (bookings.isEmpty()) {
          item {
            Surface(
              shape = RoundedCornerShape(18.dp),
              color = Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
              ) {
                Text(
                  text = "No active bookings",
                  style = MaterialTheme.typography.titleMedium,
                  fontWeight = FontWeight.Bold,
                  color = CherishNavy
                )
                Text(
                  text = "Your confirmed service appointments will appear here.",
                  style = MaterialTheme.typography.bodySmall,
                  color = CherishSlate,
                  modifier = Modifier.padding(top = 4.dp)
                )
              }
            }
          }
        } else {
          items(bookings) { b ->
            Surface(
              shape = RoundedCornerShape(18.dp),
              color = Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
              shadowElevation = 1.dp,
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(text = b.serviceTitle, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
                  Surface(
                    shape = CircleShape,
                    color = if (b.status == "Confirmed") Color(0xFFD1FAE5) else CherishMint
                  ) {
                    Text(text = b.status, color = if (b.status == "Confirmed") Color(0xFF065F46) else CherishTeal, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                  }
                }
                Text(text = b.providerName, style = MaterialTheme.typography.bodySmall, color = CherishSlate)
                Divider(color = CherishBorder)
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(text = "📅 ${b.date} • ${b.timeSlot}", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
                  Text(text = "₹${b.price}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = CherishTeal)
                }
              }
            }
          }
        }
      }

      "Saved Addresses" -> {
        item {
          Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                  Text(text = "🏡 Primary Residence", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
                  Box(modifier = Modifier.clip(CircleShape).background(CherishMint).padding(horizontal = 6.dp, vertical = 1.dp)) {
                    Text(text = "DEFAULT", color = CherishTeal, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                  }
                }
                Text(text = "$userFlat, $userSociety", style = MaterialTheme.typography.bodySmall, color = CherishNavy)
                Text(text = "Near Eon Free Zone, Kharadi, Pune - 411014", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
              }
            }
          }
        }
      }

      "Concierge Help" -> {
        item {
          Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "CherishHome Resident Helpline", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
                Text(text = "Toll Free: 1800-CHERISH (1800-243-7474)", style = MaterialTheme.typography.bodyMedium, color = CherishTeal, fontWeight = FontWeight.Bold)
                Text(text = "Email: concierge@cherishhome.com", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
                Text(text = "Emergency Response Desk: 24/7 Available for Society Gated Access & Security issues.", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
              }
            }
          }
        }
      }
    }

    // Logout / Restart Intro Button
    item {
      OutlinedButton(
        onClick = onLogout,
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFCA5A5)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(text = "Log Out of CherishHome", color = Color(0xFFDC2626), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
      }
    }
  }
}
