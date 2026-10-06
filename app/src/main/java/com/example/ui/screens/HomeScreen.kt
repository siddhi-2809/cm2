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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Spa
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
import com.example.data.DataRepository
import com.example.model.*
import com.example.ui.theme.*

@Composable
fun HomeScreen(
  userName: String,
  onNavigateToTab: (BottomTab) -> Unit,
  onNavigateToCategory: (String) -> Unit,
  onNavigateToMarketCategory: (String) -> Unit,
  onBookServiceDialog: (ServiceProvider) -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }

  val suggestionPills = listOf(
    "❄️" to "AC repair",
    "🥑" to "groceries",
    "✨" to "cleaning",
    "🛋️" to "home decor",
    "🅿️" to "parking"
  )

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    verticalArrangement = Arrangement.spacedBy(18.dp)
  ) {
    // Greeting Header
    item {
      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "Good evening, $userName",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = CherishTeal
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = "👋", fontSize = 22.sp)
        }
        Text(
          text = "What's on your mind today?",
          style = MaterialTheme.typography.bodySmall,
          color = CherishSlate
        )
      }
    }

    // Universal Search Bar
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(18.dp))
          .background(Color.White)
          .border(1.dp, CherishBorder, RoundedCornerShape(18.dp))
          .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = null,
          tint = CherishSlate,
          modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text(text = "Try searching for anything...", color = CherishMuted, fontSize = 13.sp) },
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
          ),
          singleLine = true,
          modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { }, modifier = Modifier.size(28.dp)) {
          Icon(imageVector = Icons.Default.Mic, contentDescription = "Voice", tint = CherishSlate, modifier = Modifier.size(18.dp))
        }
        Divider(modifier = Modifier.height(16.dp).width(1.dp), color = CherishBorder)
        IconButton(onClick = { onNavigateToTab(BottomTab.SERVICES) }, modifier = Modifier.size(28.dp)) {
          Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filters", tint = CherishSlate, modifier = Modifier.size(18.dp))
        }
      }
    }

    // Quick Suggestion Pills
    item {
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 2.dp)
      ) {
        items(suggestionPills) { (emoji, label) ->
          Surface(
            shape = CircleShape,
            color = Color.White,
            border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
            modifier = Modifier.clickable {
              if (label.contains("groceries")) onNavigateToMarketCategory("groceries")
              else if (label.contains("AC")) onNavigateToCategory("home_needs")
              else onNavigateToTab(BottomTab.SERVICES)
            }
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp),
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
              Text(text = emoji, fontSize = 12.sp)
              Text(text = label, style = MaterialTheme.typography.labelSmall, color = CherishNavy)
            }
          }
        }
      }
    }

    // "What do you need?" Core 3 Hero Feature Cards
    item {
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Text(
              text = "What do you need?",
              style = MaterialTheme.typography.titleLarge,
              fontWeight = FontWeight.Bold,
              color = CherishTeal
            )
            Text(text = "✨", fontSize = 16.sp)
          }
          Text(
            text = "CATEGORIES",
            style = MaterialTheme.typography.labelSmall,
            color = CherishSlate,
            letterSpacing = 1.sp
          )
        }

        // Card 1: SERVICES
        OrganicFeatureCard(
          tag = "SERVICES",
          title = "Need something done?",
          subtitle = "Cleaning • Repair • Care",
          badgeText = "Top Rated",
          badgeColor = CherishTeal,
          cardBgColor = CherishMintLight,
          borderColor = Color(0xFFC8E6DF),
          imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD0DX4vCyKwq7rOW9XfqPGSIKnKTGF_HnxNvjUNBwyWHUsGQV4CGkZtsvFBUycLsH2DWXewHnBDwqUJ_Mmmm7UBRO9sZKn9sx6S9oH6AnqS1htVVHY89QbMA057C3Nn2BOO1v5MtaIzCHgCMKH-f9dd-Qqx9ucEb4LFCKBHcSepO4r2ZFI662A4X5Q3mTFDvPpYJEd6ycP1zSq5AQww6Sy4qUlfqbpyTCl-6tEYQLH5kUWaClLEJeyDbw",
          arrowBtnColor = CherishTeal,
          onClick = { onNavigateToTab(BottomTab.SERVICES) }
        )

        // Card 2: MARKETPLACE
        OrganicFeatureCard(
          tag = "MARKETPLACE",
          title = "Need something for your home?",
          subtitle = "Groceries • Decor • Handmade",
          badgeText = "Fresh & Local",
          badgeColor = CherishCoral,
          cardBgColor = CherishPeach,
          borderColor = Color(0xFFFFD8CC),
          imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBvclN4ywN4zoijXV08yt4OsHcpytFs1qdE_A77phfRrSA_BC6qvyTjXOkwLkNY5Fm1s9GOHA9926WUo42dArkwxTH80NYrJwaJJKAeEUoUKfZX_71f7le3LB5C56WrnHfn69KLroiLhEavuLWpdOn7JT1wTNOTi0NzaP5QHcSHXbHXu5M5vsuGk0rCPNoIqlw3xmFMMM8uMKnFO9JByMMKWnFkp2i_16edcvoctOTgdwLE65IJidqCdA",
          arrowBtnColor = CherishCoral,
          onClick = { onNavigateToTab(BottomTab.MARKET) }
        )

        // Card 3: SOCIETY
        OrganicFeatureCard(
          tag = "SOCIETY",
          title = "Need help around your society?",
          subtitle = "Parking • Complaint • Notice",
          badgeText = "My Gated Community",
          badgeColor = Color(0xFF1E40AF),
          cardBgColor = Color(0xFFEFF6FF),
          borderColor = Color(0xFFBFDBFE),
          imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAm4DSHlSpT2sZg1ITn9V28RkrIsT5Bh4q04Xis2d2SpUo9OpYrh-hmiIVnJy3O_xyj8NahdwGSWJe9dvLWhe_mBwzfpB6ZgL2q1ZSwfq1pf964O9QE4vHhpL-_6Es-j1R_tAzpKzDqVWCp8uGpe-tDTruG41vjF_CofxP9-Bi5ZZN4CpqfFMvNBrVAEFggv-Ayibs41p_oQ_9lpPAvKU1VzxtZGHBS6xuf8lx2j4Z6Vh_TlZzyo37NCQ",
          arrowBtnColor = CherishTeal,
          onClick = { onNavigateToTab(BottomTab.SOCIETY) }
        )
      }
    }

    // "Just for you" Section
    item {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "Just for you", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishTeal)
          Text(
            text = "See all →",
            style = MaterialTheme.typography.labelSmall,
            color = CherishCoral,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onNavigateToTab(BottomTab.SERVICES) }
          )
        }

        // Just for you Item 1: AC
        Surface(
          shape = RoundedCornerShape(18.dp),
          color = Color.White,
          border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
          shadowElevation = 2.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Box(
              modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(14.dp))
            ) {
              AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDsA9seTWCCASwaiV8oPKqSiCGbDPwbNiVcwMrHrJHr5PDObq20TPYUqHp0CPP1XNxS_NhY7WPERP5HVC37gHsvrSVACTF_UMyoLRBYdRfGWtgNxHKuaDhNN10spvdlIcR0SbWCepRcGRjsG5FUZ-kn_tsXxBklz55x4zR2bJFcisrnxym1sIoEkTjezaVByGyeNYjhCrdghnFH1qzTng97Z7KkevzR4QYq5XrptIabOVlPi9z-_v_0Pw",
                contentDescription = "AC service",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
              )
            }
            Column(modifier = Modifier.weight(1f)) {
              Text(text = "Your AC service is due soon", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
              Text(text = "Keep your home cool and comfortable.", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
              Spacer(modifier = Modifier.height(6.dp))
              Button(
                onClick = {
                  val acProvider = DataRepository.serviceCategories.firstOrNull { it.id == "home_needs" }?.providers?.firstOrNull()
                  if (acProvider != null) {
                    onBookServiceDialog(acProvider)
                  } else {
                    onNavigateToCategory("home_needs")
                  }
                },
                colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
                shape = CircleShape,
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                modifier = Modifier.height(30.dp)
              ) {
                Text(text = "Book Now", style = MaterialTheme.typography.labelSmall, color = Color.White)
              }
            }
          }
        }

        // Just for you Item 2: Groceries
        Surface(
          shape = RoundedCornerShape(18.dp),
          color = Color.White,
          border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
          shadowElevation = 2.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Box(
              modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(14.dp))
            ) {
              AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDHkI7znKmxEq2nyvnyOchtMrc0GQRU_6Ot09reUojoZ4J-f53Yj6kviitkxbYWJ0xy0OiBabPug6gmthOnv_x9qv4LFbM750W3aIydPVwR5NA8M0nMYxOY0C6rNxRV7B3EA2FTY11VyFTK_IBgxOU39aJIog2YO36VzUwr2xxC65MJ6siJUp9znIRQHDwk2oyy4uoDV34xm0rfG8tcD7CMcg2Jv95bU2j4TnZQfJlenvdhJfouMdKwjQ",
                contentDescription = "Groceries",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
              )
            }
            Column(modifier = Modifier.weight(1f)) {
              Text(text = "Your usual groceries are running low", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishNavy)
              Text(text = "Restock your essentials in 30 mins.", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedButton(
                onClick = { onNavigateToMarketCategory("groceries") },
                shape = CircleShape,
                border = androidx.compose.foundation.BorderStroke(1.dp, CherishTeal),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                modifier = Modifier.height(30.dp)
              ) {
                Text(text = "Shop Now", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
              }
            }
          }
        }
      }
    }

    // "Your Home Today" Section
    item {
      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Your Home Today", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishTeal)
        Surface(
          shape = RoundedCornerShape(18.dp),
          color = CherishMintLight,
          border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
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
                  .background(Color.White),
                contentAlignment = Alignment.Center
              ) {
                Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = null, tint = CherishTeal, modifier = Modifier.size(20.dp))
              }
              Column {
                Text(text = "Nothing scheduled today.", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishTeal)
                Text(text = "Explore something for your home.", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
              }
            }

            OutlinedButton(
              onClick = { onNavigateToTab(BottomTab.SERVICES) },
              shape = CircleShape,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishTeal),
              modifier = Modifier.height(32.dp)
            ) {
              Text(text = "Explore", style = MaterialTheme.typography.labelSmall, color = CherishTeal, fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }

    // "Society Pulse" Section
    item {
      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "Society Pulse", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = CherishTeal)
          Text(
            text = "View all →",
            style = MaterialTheme.typography.labelSmall,
            color = CherishTeal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onNavigateToTab(BottomTab.SOCIETY) }
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          DataRepository.societyPulse.forEach { item ->
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = Color.White,
              border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
              shadowElevation = 1.dp,
              modifier = Modifier
                .weight(1f)
                .clickable { onNavigateToTab(BottomTab.SOCIETY) }
            ) {
              Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(CherishSurfaceLight),
                  contentAlignment = Alignment.Center
                ) {
                  Text(text = item.iconEmoji, fontSize = 14.sp)
                }
                Text(text = item.value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = CherishNavy)
                Text(text = item.label, style = MaterialTheme.typography.labelSmall, color = CherishSlate, maxLines = 2, overflow = TextOverflow.Ellipsis)
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun OrganicFeatureCard(
  tag: String,
  title: String,
  subtitle: String,
  badgeText: String,
  badgeColor: Color,
  cardBgColor: Color,
  borderColor: Color,
  imageUrl: String,
  arrowBtnColor: Color,
  onClick: () -> Unit
) {
  Surface(
    shape = RoundedCornerShape(26.dp),
    color = cardBgColor,
    border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
    shadowElevation = 3.dp,
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() }
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      // Banner Image with Top-Left Badge & Top-Rated Chip
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(130.dp)
          .clip(RoundedCornerShape(18.dp))
      ) {
        AsyncImage(
          model = imageUrl,
          contentDescription = title,
          contentScale = ContentScale.Crop,
          modifier = Modifier.fillMaxSize()
        )

        // Bottom right badge inside image
        Box(
          modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(8.dp)
            .clip(CircleShape)
            .background(badgeColor.copy(alpha = 0.9f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
          Text(text = badgeText, color = Color.White, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Bottom Info & Round Arrow Button
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(text = tag, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, color = badgeColor)
          Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = CherishNavy)
          Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = CherishSlate)
        }

        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(arrowBtnColor),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}
