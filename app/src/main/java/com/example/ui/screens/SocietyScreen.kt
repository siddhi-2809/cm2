package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DataRepository
import com.example.model.AmenityBooking
import com.example.model.NoticeItem
import com.example.model.VisitorPass
import com.example.ui.theme.*

@Composable
fun SocietyScreen(
  societyName: String,
  flatNumber: String,
  visitorPasses: List<VisitorPass>,
  isMaintenancePaid: Boolean,
  onPayMaintenance: () -> Unit,
  onCreateVisitorPass: (name: String, purpose: String) -> Unit
) {
  var showNewVisitorDialog by remember { mutableStateOf(false) }
  var visitorNameInput by remember { mutableStateOf("") }
  var visitorPurposeInput by remember { mutableStateOf("Guest") }
  var selectedAmenity by remember { mutableStateOf<AmenityBooking?>(null) }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header
    item {
      Column {
        Text(
          text = "Society Living",
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.ExtraBold,
          color = CherishNavy
        )
        Text(
          text = "$societyName • $flatNumber",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate
        )
      }
    }

    // Digital Access Key (Quick-Pass) Card
    item {
      Surface(
        shape = RoundedCornerShape(26.dp),
        color = CherishTeal,
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(18.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(RoundedCornerShape(10.dp))
                  .background(CherishMint.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
              ) {
                Icon(imageVector = Icons.Default.QrCode, contentDescription = null, tint = CherishMintBright)
              }
              Column {
                Text(text = "RESIDENT PASS", style = MaterialTheme.typography.labelSmall, color = CherishMint, letterSpacing = 1.sp)
                Text(text = "Main Gate Auto-Access", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
              }
            }

            Surface(
              shape = CircleShape,
              color = Color(0xFF10B981).copy(alpha = 0.2f),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF10B981))
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(Color(0xFF10B981)))
                Text(text = "Active RFID", color = Color(0xFFD1FAE5), fontSize = 10.sp, fontWeight = FontWeight.Bold)
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Simulated QR Display
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(Color.White)
              .padding(14.dp),
            contentAlignment = Alignment.Center
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(text = "FLAT $flatNumber", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = CherishNavy)
                Text(text = "Access Code: 8904-CH-2026", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
                Text(text = "Tap to scan at turnstile", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
              }
              Box(
                modifier = Modifier
                  .size(54.dp)
                  .clip(RoundedCornerShape(8.dp))
                  .background(CherishSurfaceLight)
                  .border(1.dp, CherishBorder),
                contentAlignment = Alignment.Center
              ) {
                Text(text = "🏁", fontSize = 28.sp)
              }
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          // 1-Tap Intercom Button
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = CherishMint, modifier = Modifier.size(14.dp))
              Text(text = "Security Cabin 1: Intercom 101", style = MaterialTheme.typography.bodySmall, color = CherishMint)
            }

            Surface(
              shape = CircleShape,
              color = CherishCoral,
              modifier = Modifier.clickable { }
            ) {
              Text(
                text = "Call Gate",
                color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
              )
            }
          }
        }
      }
    }

    // Maintenance Due Card
    item {
      Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(text = "Q3 Maintenance Due", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
              Text(
                text = if (isMaintenancePaid) "Paid on Oct 5, 2026 ✓" else "Due in 3 days (Oct 8)",
                style = MaterialTheme.typography.bodySmall,
                color = if (isMaintenancePaid) Color(0xFF059669) else CherishCoral,
                fontWeight = FontWeight.SemiBold
              )
            }

            Text(
              text = "₹3,450",
              style = MaterialTheme.typography.headlineSmall,
              fontWeight = FontWeight.ExtraBold,
              color = CherishTeal
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          if (isMaintenancePaid) {
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = Color(0xFFD1FAE5),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color(0xFF059669))
                Text(text = "Paid successfully! Tax receipt available in documents.", color = Color(0xFF065F46), style = MaterialTheme.typography.bodySmall)
              }
            }
          } else {
            Button(
              onClick = onPayMaintenance,
              colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
              shape = RoundedCornerShape(12.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Text(text = "Pay Now via UPI / Netbanking →", color = Color.White, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }

    // Visitor Management Section
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(text = "Visitor Passes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
            Text(text = "Pre-approve entry for guests & deliveries", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
          Button(
            onClick = { showNewVisitorDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = CherishTeal),
            shape = CircleShape,
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            modifier = Modifier.height(30.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
              Text(text = "New Pass", style = MaterialTheme.typography.labelSmall, color = Color.White)
            }
          }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          visitorPasses.forEach { pass ->
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                  Box(
                    modifier = Modifier
                      .size(38.dp)
                      .clip(CircleShape)
                      .background(if (pass.purpose == "Guest") CherishMint else Color(0xFFFEF3C7)),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(text = if (pass.purpose == "Guest") "👤" else "📦", fontSize = 16.sp)
                  }
                  Column {
                    Text(text = pass.visitorName, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
                    Text(text = "${pass.purpose} • ${pass.time}", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
                  }
                }

                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = CherishSurfaceLight,
                  border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder)
                ) {
                  Text(
                    text = pass.passCode,
                    color = CherishTeal,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                  )
                }
              }
            }
          }
        }
      }
    }

    // Community Amenities
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Column {
          Text(text = "Amenities & Club Booking", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
          Text(text = "Reserve clubhouse, sports courts & pool access", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          DataRepository.amenities.forEach { amenity ->
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                  Text(text = amenity.iconEmoji, fontSize = 22.sp)
                  Column {
                    Text(text = amenity.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
                    Text(text = amenity.timing, style = MaterialTheme.typography.bodySmall, color = CherishSlate)
                  }
                }

                Button(
                  onClick = { selectedAmenity = amenity },
                  colors = ButtonDefaults.buttonColors(containerColor = CherishMint),
                  shape = CircleShape,
                  contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                  modifier = Modifier.height(28.dp)
                ) {
                  Text(text = "Reserve", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
                }
              }
            }
          }
        }
      }
    }

    // Notices & Circulars
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(text = "Society Circulars", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
        DataRepository.notices.forEach { notice ->
          Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(
              modifier = Modifier.padding(14.dp),
              verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(text = notice.department.uppercase(), style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold, fontSize = 9.sp)
                Text(text = notice.date, style = MaterialTheme.typography.bodySmall, color = CherishSlate, fontSize = 10.sp)
              }
              Text(text = notice.title, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
              Text(text = notice.summary, style = MaterialTheme.typography.bodySmall, color = CherishSlate)
            }
          }
        }
      }
    }
  }

  // New Visitor Pass Modal Dialog
  if (showNewVisitorDialog) {
    AlertDialog(
      onDismissRequest = { showNewVisitorDialog = false },
      containerColor = Color.White,
      shape = RoundedCornerShape(24.dp),
      title = {
        Text(text = "Create Digital Gate Pass", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = CherishNavy)
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Text(text = "Enter visitor details for automatic barrier gate clearance.", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          OutlinedTextField(
            value = visitorNameInput,
            onValueChange = { visitorNameInput = it },
            label = { Text("Visitor Name") },
            placeholder = { Text("e.g. Ramesh K. (Guest)") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          )
          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Guest", "Cab / Taxi", "Delivery").forEach { p ->
              val isSel = visitorPurposeInput == p
              Surface(
                shape = CircleShape,
                color = if (isSel) CherishTeal else CherishSurfaceLight,
                border = androidx.compose.foundation.BorderStroke(1.dp, if (isSel) CherishTeal else CherishBorder),
                modifier = Modifier.clickable { visitorPurposeInput = p }
              ) {
                Text(
                  text = p,
                  color = if (isSel) Color.White else CherishNavy,
                  style = MaterialTheme.typography.labelSmall,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
              }
            }
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            if (visitorNameInput.isNotBlank()) {
              onCreateVisitorPass(visitorNameInput, visitorPurposeInput)
              showNewVisitorDialog = false
              visitorNameInput = ""
            }
          },
          colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text(text = "Generate Pass", color = Color.White, fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        TextButton(onClick = { showNewVisitorDialog = false }) {
          Text(text = "Cancel", color = CherishSlate)
        }
      }
    )
  }

  // Amenity Reservation Dialog
  selectedAmenity?.let { am ->
    AlertDialog(
      onDismissRequest = { selectedAmenity = null },
      containerColor = Color.White,
      shape = RoundedCornerShape(24.dp),
      title = {
        Text(text = "Reserve ${am.name}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = CherishNavy)
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(text = "Timing: ${am.timing}", style = MaterialTheme.typography.bodyMedium, color = CherishNavy)
          Text(text = "Fee: ${am.fee}", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          Text(text = "Slot reserved for Flat $flatNumber. QR key will be shared with security.", style = MaterialTheme.typography.bodySmall, color = CherishTeal)
        }
      },
      confirmButton = {
        Button(
          onClick = { selectedAmenity = null },
          colors = ButtonDefaults.buttonColors(containerColor = CherishTeal),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text(text = "Confirm Slot", color = Color.White, fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        TextButton(onClick = { selectedAmenity = null }) {
          Text(text = "Cancel", color = CherishSlate)
        }
      }
    )
  }
}
