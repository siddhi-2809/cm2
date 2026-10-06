package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
  primary = CherishMintBright,
  onPrimary = CherishTealDeep,
  primaryContainer = CherishTeal,
  onPrimaryContainer = CherishMint,
  secondary = CherishMint,
  onSecondary = CherishTealDeep,
  secondaryContainer = CherishTealMedium,
  onSecondaryContainer = CherishMintLight,
  tertiary = CherishCoral,
  onTertiary = Color.White,
  background = CherishTealDeep,
  onBackground = Color(0xFFC7E9EB),
  surface = CherishTealDeep,
  onSurface = Color(0xFFC7E9EB),
  surfaceVariant = CherishSurfaceHigh,
  onSurfaceVariant = Color(0xFFC0C8C8)
)

private val LightColorScheme = lightColorScheme(
  primary = CherishTeal,
  onPrimary = Color.White,
  primaryContainer = CherishMint,
  onPrimaryContainer = CherishTeal,
  secondary = CherishMint,
  onSecondary = CherishNavy,
  secondaryContainer = CherishMintLight,
  onSecondaryContainer = CherishTeal,
  tertiary = CherishCoral,
  onTertiary = Color.White,
  tertiaryContainer = CherishCoralLight,
  onTertiaryContainer = CherishCoralHover,
  background = CherishSurfaceLight,
  onBackground = CherishNavy,
  surface = CherishSurfaceLight,
  onSurface = CherishNavy,
  surfaceVariant = Color.White,
  onSurfaceVariant = CherishSlate,
  outline = CherishBorder
)

@Composable
fun CherishHomeTheme(
  darkTheme: Boolean = false, // Default to light luxury theme per mockups, while supporting dark
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}
