package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
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

private data class IntroPill(
  val title: String,
  val icon: String,
  val bgColor: Color,
  val textColor: Color,
  val borderColor: Color,
  val offsetX: Float,
  val offsetY: Float,
  val delayMs: Long
)

@Composable
fun IntroUniverseScreen(
  onProceed: () -> Unit
) {
  var animationStep by remember { mutableIntStateOf(0) }

  val pills = remember {
    listOf(
      IntroPill("Home Needs", "🟢", Color(0xFFECFDF5), Color(0xFF094747), Color(0xFF6EE7B7), -100f, 60f, 150L),
      IntroPill("Daily Groceries", "🛍️", Color(0xFFFFF2ED), Color(0xFFC84323), Color(0xFFFFAB91), 100f, 40f, 300L),
      IntroPill("Society", "🏢", Color(0xFFF0F9FF), Color(0xFF0369A1), Color(0xFF7DD3FC), -90f, 140f, 450L),
      IntroPill("Handmade Textiles", "🟠", Color(0xFFFFF7ED), Color(0xFF9A3412), Color(0xFFFDBA74), 90f, 120f, 600L),
      IntroPill("Home Care", "✓", CherishTeal, Color.White, CherishTeal, -80f, 220f, 750L),
      IntroPill("Kitchen Essentials", "🍳", Color.White, Color(0xFFC84323), Color(0xFFFF7A59), 90f, 210f, 900L),
      IntroPill("Security", "🛡️", Color.White, CherishTeal, Color(0x330E3E41), -80f, 300f, 1050L),
      IntroPill("Home Decor", "🟠", Color(0xFFFFF5EE), Color(0xFFD9532F), Color(0xFFFF8A65), 85f, 290f, 1200L),
      IntroPill("Kitchen Needs", "⚡", Color(0xFFECFDF5), Color(0xFF065F46), Color(0xFF6EE7B7), -90f, 380f, 1350L),
      IntroPill("Maintenance", "🟢", Color.White, CherishTeal, Color(0x4D0E3E41), 90f, 370f, 1500L),
      IntroPill("Organic & Wellness", "🍃", Color(0xFFF0FDF4), Color(0xFF166534), Color(0xFF86EFAC), -70f, 450f, 1650L),
      IntroPill("Pottery & Ceramics", "🏺", Color(0xFFFEF2F2), Color(0xFF991B1B), Color(0xFFFCA5A5), 70f, 440f, 1800L)
    )
  }

  val infiniteTransition = rememberInfiniteTransition(label = "floatAnim")
  val floatOffset by infiniteTransition.animateFloat(
    initialValue = -4f,
    targetValue = 4f,
    animationSpec = infiniteRepeatable(
      animation = tween(2400, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "float"
  )

  LaunchedEffect(Unit) {
    delay(200)
    animationStep = 1
  }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        Brush.verticalGradient(
          colors = listOf(
            Color(0xFFFAF8F5),
            Color(0xFFF8F9F8),
            Color(0xFFEFF5F5)
          )
        )
      )
      .statusBarsPadding()
      .navigationBarsPadding()
      .padding(16.dp)
  ) {
    // Header
    Column(
      modifier = Modifier
        .align(Alignment.TopCenter)
        .padding(top = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Box(
          modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(CherishTeal),
          contentAlignment = Alignment.Center
        ) {
          Text(text = "CH", color = CherishMint, fontSize = 10.sp, fontWeight = FontWeight.Black)
        }
        Text(
          text = "CherishHome",
          style = MaterialTheme.typography.titleLarge,
          fontWeight = FontWeight.ExtraBold,
          color = CherishTeal
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Dots Indicator
      Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(CherishTeal.copy(alpha = 0.3f)))
        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(CherishTeal.copy(alpha = 0.3f)))
        Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(CherishTeal.copy(alpha = 0.3f)))
        Box(modifier = Modifier.width(18.dp).height(5.dp).clip(CircleShape).background(CherishTeal))
      }
    }

    // Floating Interactive Category Universe Box
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(490.dp)
        .align(Alignment.Center)
    ) {
      pills.forEachIndexed { index, pill ->
        var isVisible by remember { mutableStateOf(false) }

        LaunchedEffect(animationStep) {
          if (animationStep == 1) {
            delay(pill.delayMs)
            isVisible = true
          }
        }

        AnimatedVisibility(
          visible = isVisible,
          enter = fadeIn(tween(400)) + scaleIn(tween(500)),
          modifier = Modifier
            .align(Alignment.TopCenter)
            .offset(x = pill.offsetX.dp, y = (pill.offsetY + if (index % 2 == 0) floatOffset else -floatOffset).dp)
        ) {
          Surface(
            shape = CircleShape,
            color = pill.bgColor,
            border = androidx.compose.foundation.BorderStroke(1.dp, pill.borderColor),
            shadowElevation = 3.dp,
            modifier = Modifier.clickable { onProceed() }
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(5.dp),
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
              Text(text = pill.icon, fontSize = 11.sp)
              Text(
                text = pill.title.uppercase(),
                color = pill.textColor,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
              )
            }
          }
        }
      }
    }

    // Bottom Statement & CTA
    Column(
      modifier = Modifier
        .align(Alignment.BottomCenter)
        .fillMaxWidth()
        .padding(bottom = 8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Top accent bar
      Box(
        modifier = Modifier
          .width(44.dp)
          .height(3.dp)
          .clip(CircleShape)
          .background(
            Brush.horizontalGradient(
              listOf(CherishMintBright, CherishTeal, CherishCoral)
            )
          )
      )

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = "EVERYTHING UNDER",
        style = MaterialTheme.typography.labelSmall,
        color = Color(0xFF64748B),
        letterSpacing = 1.sp,
        fontWeight = FontWeight.SemiBold
      )

      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "ONE PLACE",
          style = MaterialTheme.typography.headlineLarge,
          fontWeight = FontWeight.ExtraBold,
          color = CherishTeal,
          letterSpacing = (-0.5).sp
        )
        Text(
          text = ".",
          style = MaterialTheme.typography.headlineLarge,
          fontWeight = FontWeight.ExtraBold,
          color = CherishCoral
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Text(text = "Services", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
        Text(text = "•", color = CherishMintBright, fontWeight = FontWeight.Bold)
        Text(text = "Marketplace", style = MaterialTheme.typography.labelSmall, color = CherishCoral, fontWeight = FontWeight.Bold)
        Text(text = "•", color = CherishMintBright, fontWeight = FontWeight.Bold)
        Text(text = "Society", style = MaterialTheme.typography.labelSmall, color = Color(0xFF0369A1), fontWeight = FontWeight.Bold)
      }

      Spacer(modifier = Modifier.height(16.dp))

      Button(
        onClick = onProceed,
        colors = ButtonDefaults.buttonColors(containerColor = CherishTeal),
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
            text = "Login or Sign Up",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = CherishMintBright,
            modifier = Modifier.size(18.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        modifier = Modifier
          .clip(CircleShape)
          .clickable {
            animationStep = 0
            isVisibleGlobalReset()
          }
          .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, tint = CherishSlate, modifier = Modifier.size(12.dp))
        Text(text = "Replay intro", color = CherishSlate, style = MaterialTheme.typography.bodySmall)
      }
    }
  }
}

private fun isVisibleGlobalReset() {}
