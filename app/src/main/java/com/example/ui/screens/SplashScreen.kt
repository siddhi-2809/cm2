package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
  onProceed: () -> Unit
) {
  var progress by remember { mutableFloatStateOf(0.3f) }

  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.95f,
    targetValue = 1.05f,
    animationSpec = infiniteRepeatable(
      animation = tween(2000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "glowScale"
  )

  LaunchedEffect(Unit) {
    while (progress < 1.0f) {
      delay(200)
      progress += 0.15f
    }
    delay(400)
    onProceed()
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        Brush.radialGradient(
          colors = listOf(
            Color(0xFF0D383B),
            Color(0xFF08282A),
            Color(0xFF001718)
          )
        )
      )
      .statusBarsPadding()
      .navigationBarsPadding()
      .padding(24.dp)
  ) {
    // Ambient Glow Effect in Background
    Box(
      modifier = Modifier
        .align(Alignment.Center)
        .size((280 * pulseScale).dp)
        .clip(CircleShape)
        .background(
          Brush.radialGradient(
            colors = listOf(
              Color(0x332DD4BF),
              Color(0x150E3E41),
              Color.Transparent
            )
          )
        )
    )

    // Top Encrypted Badge
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.TopCenter),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Surface(
        shape = CircleShape,
        color = Color(0x660C2F31),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3382D4DA))
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(CherishMintBright)
          )
          Text(
            text = "ENCRYPTED",
            color = Color(0xFFC7E9EB),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
          )
        }
      }

      Icon(
        imageVector = Icons.Default.Shield,
        contentDescription = "Shield",
        tint = CherishMintBright.copy(alpha = 0.75f),
        modifier = Modifier.size(18.dp)
      )
    }

    // Center Logo & Title
    Column(
      modifier = Modifier
        .align(Alignment.Center)
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Modern Home Icon Badge
      Box(
        modifier = Modifier
          .size(80.dp)
          .clip(RoundedCornerShape(24.dp))
          .background(
            Brush.linearGradient(
              listOf(Color(0xFF0E3E41), Color(0xFF14565A))
            )
          )
          .border(1.5.dp, Color(0x442DD4BF), RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
      ) {
        Text(text = "🏡", fontSize = 38.sp)
      }

      Spacer(modifier = Modifier.height(24.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "CherishHome",
          color = Color(0xFFC7E9EB),
          style = MaterialTheme.typography.headlineLarge,
          fontWeight = FontWeight.Bold,
          letterSpacing = (-0.5).sp
        )
        Spacer(modifier = Modifier.width(6.dp))
        Box(
          modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(CherishCoral)
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "COMFORT. CARE. CHERISH.",
        color = Color(0xFF98EAF0),
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Bold,
        letterSpacing = 2.sp
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "Everything your home needs, in one place.",
        color = Color(0xFF8A9293),
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
      )
    }

    // Bottom Loading Bar & Tag
    Column(
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Progress Bar
      Box(
        modifier = Modifier
          .width(160.dp)
          .height(4.dp)
          .clip(CircleShape)
          .background(Color(0xFF193A3C))
      ) {
        Box(
          modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(progress.coerceIn(0f, 1f))
            .clip(CircleShape)
            .background(
              Brush.horizontalGradient(
                listOf(CherishMintBright, CherishTealMedium, CherishCoral)
              )
            )
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(CherishMintBright))
        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(CherishMintBright.copy(alpha = 0.6f)))
        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(CherishCoral))
      }

      Spacer(modifier = Modifier.height(14.dp))

      Text(
        text = "V1.0 • SECURE HOME LIVING",
        color = Color(0xFF8A9293).copy(alpha = 0.7f),
        style = MaterialTheme.typography.labelSmall,
        letterSpacing = 1.sp
      )

      Spacer(modifier = Modifier.height(12.dp))

      Button(
        onClick = onProceed,
        colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.1f)),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.height(36.dp)
      ) {
        Text(text = "Get Started →", color = Color(0xFFC7E9EB), style = MaterialTheme.typography.labelMedium)
      }
    }
  }
}
