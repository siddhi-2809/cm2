package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun OnboardingLocationScreen(
  initialName: String,
  initialLocation: String,
  onBack: () -> Unit,
  onContinue: (name: String, location: String) -> Unit
) {
  var name by remember { mutableStateOf(initialName) }
  var location by remember { mutableStateOf(initialLocation) }
  var isLocationActive by remember { mutableStateOf(true) }
  var showManualInput by remember { mutableStateOf(false) }
  var manualInputText by remember { mutableStateOf("") }

  val canProceed = name.trim().length >= 2

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight)
      .statusBarsPadding()
      .navigationBarsPadding()
      .padding(horizontal = 20.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    // Top Bar & Progress
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = onBack,
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, CherishBorder, CircleShape)
        ) {
          Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = CherishTeal)
        }

        // Brand logo
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier
              .size(26.dp)
              .clip(RoundedCornerShape(8.dp))
              .background(CherishTeal),
            contentAlignment = Alignment.Center
          ) {
            Text(text = "CH", color = CherishMint, fontSize = 10.sp, fontWeight = FontWeight.Black)
          }
          Text(text = "CherishHome", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishTeal)
        }

        Surface(
          shape = CircleShape,
          color = CherishMint,
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF))
        ) {
          Text(
            text = "STEP 2",
            color = CherishTeal,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Minimal Progress Bar (Step 2 of 3)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Box(modifier = Modifier.weight(1f).height(4.dp).clip(CircleShape).background(CherishTeal))
        Box(modifier = Modifier.weight(1f).height(4.dp).clip(CircleShape).background(CherishTeal))
        Box(modifier = Modifier.weight(1f).height(4.dp).clip(CircleShape).background(Color(0xFFE2E8F0)))
      }

      Row(
        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(text = "Account Setup", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
        Text(text = "Almost ready", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
      }
    }

    // Main Form Body
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
      Column {
        Text(
          text = "Let's make CherishHome yours.",
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.ExtraBold,
          color = CherishNavy
        )
        Text(
          text = "Just a couple of details to get your personalized home experience started.",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate,
          modifier = Modifier.padding(top = 4.dp)
        )
      }

      // Name Input
      Column {
        Text(
          text = "WHAT'S YOUR NAME?",
          style = MaterialTheme.typography.labelSmall,
          color = CherishTeal,
          fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextField(
          value = name,
          onValueChange = { name = it },
          placeholder = { Text(text = "Enter your full name", color = CherishSlate) },
          leadingIcon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = CherishTeal)
          },
          trailingIcon = {
            if (name.trim().length >= 2) {
              Box(
                modifier = Modifier
                  .size(20.dp)
                  .clip(CircleShape)
                  .background(Color(0xFFD1FAE5)),
                contentAlignment = Alignment.Center
              ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color(0xFF059669), modifier = Modifier.size(12.dp))
              }
            }
          },
          shape = RoundedCornerShape(16.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = CherishTeal,
            unfocusedBorderColor = Color(0xFFCBD5E1),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
          ),
          textStyle = MaterialTheme.typography.titleMedium.copy(color = CherishNavy, fontWeight = FontWeight.SemiBold),
          modifier = Modifier.fillMaxWidth()
        )
      }

      // Location Section
      Column {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "WHERE ARE YOU LOCATED?",
            style = MaterialTheme.typography.labelSmall,
            color = CherishTeal,
            fontWeight = FontWeight.Bold
          )
          Surface(
            shape = CircleShape,
            color = Color(0xFFF1F5F9)
          ) {
            Text(
              text = "Recommended",
              color = CherishSlate,
              style = MaterialTheme.typography.labelSmall,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
          }
        }

        Text(
          text = "Allow location access to discover nearby verified services, curated home products and your neighborhood society.",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate,
          modifier = Modifier.padding(top = 4.dp, bottom = 10.dp)
        )

        // Location Card
        Surface(
          shape = RoundedCornerShape(20.dp),
          color = if (isLocationActive) Color(0xFFF0FAF8) else Color.White,
          border = androidx.compose.foundation.BorderStroke(
            1.5.dp,
            if (isLocationActive) Color(0xFF6EE7B7) else Color(0xFFCBD5E1)
          ),
          shadowElevation = 2.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            if (isLocationActive) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(10.dp),
                  modifier = Modifier.weight(1f)
                ) {
                  Box(
                    modifier = Modifier
                      .size(36.dp)
                      .clip(RoundedCornerShape(10.dp))
                      .background(Color(0xFFD1FAE5)),
                    contentAlignment = Alignment.Center
                  ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color(0xFF059669), modifier = Modifier.size(20.dp))
                  }
                  Column {
                    Row(
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                      Text(text = "Location enabled", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFF065F46))
                      Box(
                        modifier = Modifier
                          .clip(RoundedCornerShape(4.dp))
                          .background(Color(0xFFBBF7D0))
                          .padding(horizontal = 6.dp, vertical = 1.dp)
                      ) {
                        Text(text = "GPS Active", color = Color(0xFF065F46), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                      }
                    }
                    Text(text = location, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold, color = CherishNavy)
                  }
                }

                Text(
                  text = "Change",
                  style = MaterialTheme.typography.labelSmall,
                  fontWeight = FontWeight.Bold,
                  color = CherishTeal,
                  modifier = Modifier
                    .clip(CircleShape)
                    .clickable { showManualInput = true }
                    .padding(6.dp)
                )
              }
            } else {
              Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                  Box(
                    modifier = Modifier
                      .size(44.dp)
                      .clip(RoundedCornerShape(12.dp))
                      .background(CherishMint),
                    contentAlignment = Alignment.Center
                  ) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = CherishTeal)
                  }
                  Column {
                    Text(text = "Use my current location", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishNavy)
                    Text(text = "Find trusted services and products near you automatically", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
                  }
                }

                Button(
                  onClick = { isLocationActive = true },
                  colors = ButtonDefaults.buttonColors(containerColor = CherishTeal),
                  shape = RoundedCornerShape(12.dp),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Text(text = "Allow Location Access →", color = Color.White, fontWeight = FontWeight.Bold)
                }
              }
            }

            AnimatedVisibility(visible = showManualInput) {
              Column(modifier = Modifier.padding(top = 10.dp)) {
                OutlinedTextField(
                  value = manualInputText,
                  onValueChange = { manualInputText = it },
                  placeholder = { Text(text = "Enter Pincode, Society or Area", fontSize = 12.sp) },
                  trailingIcon = {
                    TextButton(onClick = {
                      if (manualInputText.isNotBlank()) {
                        location = manualInputText
                        isLocationActive = true
                        showManualInput = false
                      }
                    }) {
                      Text(text = "Save", color = CherishTeal, fontWeight = FontWeight.Bold)
                    }
                  },
                  shape = RoundedCornerShape(10.dp),
                  modifier = Modifier.fillMaxWidth()
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "✏️ Enter location manually",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = CherishTeal,
            modifier = Modifier.clickable { showManualInput = !showManualInput }
          )
          Text(text = "Optional for now", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
        }
      }
    }

    // Bottom Actions
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.fillMaxWidth()
    ) {
      Button(
        onClick = { onContinue(name, location) },
        enabled = canProceed,
        colors = ButtonDefaults.buttonColors(
          containerColor = CherishCoral,
          disabledContainerColor = Color(0xFFCBD5E1)
        ),
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
            text = "Continue",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = CherishTeal, modifier = Modifier.size(14.dp))
        Text(
          text = "Your location is only used to personalize your CherishHome experience.",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate,
          fontSize = 11.sp
        )
      }
    }
  }
}
