package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun AuthScreen(
  onLoginSuccess: (phoneNumber: String) -> Unit,
  onExploreAsGuest: () -> Unit
) {
  var isSignUp by remember { mutableStateOf(false) }
  var phoneNumber by remember { mutableStateOf("9876543210") }
  val otpDigits = remember { mutableStateListOf("4", "8", "2", "9", "", "") }
  var isOtpSent by remember { mutableStateOf(true) }
  var isVerifying by remember { mutableStateOf(false) }

  val otpFocusRequesters = remember { List(6) { FocusRequester() } }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(Color(0xFFF3F7F7), Color(0xFFFAFBFB), Color(0xFFFFFFFF))
        )
      )
      .statusBarsPadding()
      .navigationBarsPadding()
      .padding(horizontal = 20.dp, vertical = 12.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      // Top Logo & Header
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 10.dp)
      ) {
        Box(
          modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(CherishTeal),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "🏡", fontSize = 28.sp)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = "Welcome to CherishHome",
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.Bold,
          color = CherishTeal
        )
        Text(
          text = "Everything your home needs, all in one place.",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate
        )
      }

      // Card Container
      Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = Color.White,
        shadowElevation = 8.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF))
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          // Sign Up / Log In Toggle
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(16.dp))
              .background(Color(0xFFF1F5F9))
              .padding(4.dp)
          ) {
            Box(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSignUp) CherishTeal else Color.Transparent)
                .clickable { isSignUp = true }
                .padding(vertical = 8.dp),
              contentAlignment = Alignment.Center
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Sign Up",
                  color = if (isSignUp) Color.White else CherishSlate,
                  style = MaterialTheme.typography.labelMedium,
                  fontWeight = FontWeight.Bold
                )
                if (isSignUp) {
                  Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(CherishMintBright))
                }
              }
            }

            Box(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .background(if (!isSignUp) CherishTeal else Color.Transparent)
                .clickable { isSignUp = false }
                .padding(vertical = 8.dp),
              contentAlignment = Alignment.Center
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Text(
                  text = "Log In",
                  color = if (!isSignUp) Color.White else CherishSlate,
                  style = MaterialTheme.typography.labelMedium,
                  fontWeight = FontWeight.Bold
                )
                if (!isSignUp) {
                  Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(CherishMintBright))
                }
              }
            }
          }

          Column {
            Text(
              text = if (isSignUp) "Create your account" else "Welcome back",
              style = MaterialTheme.typography.titleMedium,
              fontWeight = FontWeight.Bold,
              color = CherishNavy
            )
            Text(
              text = if (isSignUp) "Enter your mobile number to get started with CherishHome." else "Enter your mobile number and OTP to continue.",
              style = MaterialTheme.typography.bodySmall,
              color = CherishSlate
            )
          }

          // Phone Number Input
          Column {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(
                text = "MOBILE NUMBER",
                style = MaterialTheme.typography.labelSmall,
                color = CherishSlate,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "Use Email instead",
                style = MaterialTheme.typography.labelSmall,
                color = CherishTeal,
                fontWeight = FontWeight.Bold
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(Color(0xFFF8FAFC))
                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(14.dp))
                .padding(horizontal = 12.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = "🇮🇳 +91", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = CherishNavy)
              Spacer(modifier = Modifier.width(10.dp))
              Divider(
                modifier = Modifier
                  .height(18.dp)
                  .width(1.dp),
                color = Color(0xFFCBD5E1)
              )
              Spacer(modifier = Modifier.width(10.dp))

              OutlinedTextField(
                value = phoneNumber,
                onValueChange = { if (it.length <= 10) phoneNumber = it },
                modifier = Modifier.weight(1f),
                colors = OutlinedTextFieldDefaults.colors(
                  focusedBorderColor = Color.Transparent,
                  unfocusedBorderColor = Color.Transparent,
                  cursorColor = CherishTeal
                ),
                textStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = CherishNavy),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
              )

              Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = null,
                tint = if (phoneNumber.length == 10) CherishTeal else CherishSlate,
                modifier = Modifier.size(18.dp)
              )
            }

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(text = "OTP sent to +91 98765 43210", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
              Text(text = "${phoneNumber.length}/10", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
            }
          }

          // OTP 6-digit grid
          Column {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(text = "ENTER OTP", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
              Text(text = "Resend in 00:28", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              for (i in 0 until 6) {
                OutlinedTextField(
                  value = otpDigits[i],
                  onValueChange = { v ->
                    if (v.length <= 1) {
                      otpDigits[i] = v
                      if (v.isNotEmpty() && i < 5) {
                        otpFocusRequesters[i + 1].requestFocus()
                      }
                    }
                  },
                  modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .focusRequester(otpFocusRequesters[i]),
                  shape = RoundedCornerShape(10.dp),
                  colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = CherishTeal,
                    unfocusedBorderColor = Color(0xFFE2E8F0),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color(0xFFF8FAFC)
                  ),
                  textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CherishTeal,
                    textAlign = TextAlign.Center
                  ),
                  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                  singleLine = true
                )
              }
            }

            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Text(text = "Didn't receive code?", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
              Text(
                text = "Resend via SMS",
                style = MaterialTheme.typography.labelSmall,
                color = CherishCoral,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { }
              )
            }
          }

          // Primary CTA Button
          Button(
            onClick = {
              isVerifying = true
              onLoginSuccess(phoneNumber)
            },
            colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(50.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(
                text = if (isSignUp) "Verify & Create Account" else "Verify & Log In",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
              Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            }
          }

          // OR Divider
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Divider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
            Text(
              text = "OR",
              style = MaterialTheme.typography.labelSmall,
              color = CherishSlate,
              modifier = Modifier.padding(horizontal = 8.dp)
            )
            Divider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
          }

          // Continue with Google
          OutlinedButton(
            onClick = { onLoginSuccess(phoneNumber) },
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(46.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1))
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(text = "🌐", fontSize = 16.sp)
              Text(
                text = "Continue with Google",
                style = MaterialTheme.typography.titleSmall,
                color = CherishNavy,
                fontWeight = FontWeight.SemiBold
              )
            }
          }

          // Explore as Guest
          Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = "Explore as Guest >",
              style = MaterialTheme.typography.labelMedium,
              color = CherishTeal,
              fontWeight = FontWeight.Bold,
              modifier = Modifier
                .clickable { onExploreAsGuest() }
                .padding(4.dp)
            )
          }
        }
      }

      // Security Badges Footer
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(bottom = 8.dp)
      ) {
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = CherishTeal, modifier = Modifier.size(14.dp))
            Text(text = "100% Secure Login", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
          Text(text = "•", color = CherishSlate)
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = CherishTeal, modifier = Modifier.size(14.dp))
            Text(text = "End-to-End Encrypted", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
        }
        Text(
          text = "By continuing, you agree to our Terms of Service and Privacy Policy.",
          style = MaterialTheme.typography.bodySmall,
          color = CherishMuted,
          textAlign = TextAlign.Center
        )
      }
    }
  }
}
