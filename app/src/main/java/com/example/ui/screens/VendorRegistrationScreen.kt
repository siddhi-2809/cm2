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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import com.example.data.DataRepository
import com.example.ui.theme.*

@Composable
fun VendorRegistrationScreen(
  onBack: () -> Unit,
  onRegisterSuccess: (
    businessName: String,
    contactPerson: String,
    categoryId: String,
    phone: String,
    serviceTitle: String,
    price: Int,
    priceUnit: String,
    features: List<String>
  ) -> Unit
) {
  var businessName by remember { mutableStateOf("") }
  var contactPerson by remember { mutableStateOf("") }
  var selectedCategory by remember { mutableStateOf(DataRepository.serviceCategories.first().id) }
  var serviceTitle by remember { mutableStateOf("") }
  var phone by remember { mutableStateOf("") }
  var priceInput by remember { mutableStateOf("499") }
  var priceUnit by remember { mutableStateOf("/ visit") }
  var keyFeaturesInput by remember { mutableStateOf("Verified pro • Background checked • Fast response") }

  val canSubmit = businessName.isNotBlank() && contactPerson.isNotBlank() && serviceTitle.isNotBlank() && phone.isNotBlank()

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .background(CherishSurfaceLight)
      .statusBarsPadding()
      .navigationBarsPadding(),
    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        IconButton(
          onClick = onBack,
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, CherishBorder, CircleShape)
        ) {
          Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = CherishNavy)
        }

        Column {
          Text(
            text = "Vendor Registration",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = CherishNavy
          )
          Text(
            text = "Partner with CherishHome & serve verified residents",
            style = MaterialTheme.typography.bodySmall,
            color = CherishSlate
          )
        }
      }
    }

    // Trust Assurance Badge
    item {
      Surface(
        shape = RoundedCornerShape(18.dp),
        color = CherishMint,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFC8E6DF)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Icon(imageVector = Icons.Default.Shield, contentDescription = null, tint = CherishTeal)
          Column {
            Text(text = "Verified Professional Onboarding", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = CherishTeal)
            Text(text = "Join our network of background-verified home service experts in Pune.", style = MaterialTheme.typography.bodySmall, color = CherishSlate)
          }
        }
      }
    }

    // Form Fields Card
    item {
      Surface(
        shape = RoundedCornerShape(22.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, CherishBorder),
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          modifier = Modifier.padding(18.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          // Business Name
          Column {
            Text(text = "BUSINESS / AGENCY NAME", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
              value = businessName,
              onValueChange = { businessName = it },
              placeholder = { Text("e.g. Apex Home Care Solutions") },
              leadingIcon = { Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = CherishTeal) },
              shape = RoundedCornerShape(14.dp),
              modifier = Modifier.fillMaxWidth(),
              singleLine = true
            )
          }

          // Contact Person
          Column {
            Text(text = "CONTACT PERSON NAME", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
              value = contactPerson,
              onValueChange = { contactPerson = it },
              placeholder = { Text("e.g. Ramesh Kulkarni") },
              leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = CherishTeal) },
              shape = RoundedCornerShape(14.dp),
              modifier = Modifier.fillMaxWidth(),
              singleLine = true
            )
          }

          // Category Selector
          Column {
            Text(text = "SERVICE CATEGORY", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              items(DataRepository.serviceCategories) { cat ->
                val isSelected = selectedCategory == cat.id
                Surface(
                  shape = CircleShape,
                  color = if (isSelected) CherishTeal else CherishSurfaceLight,
                  border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) CherishTeal else CherishBorder),
                  modifier = Modifier.clickable { selectedCategory = cat.id }
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Text(text = cat.iconEmoji, fontSize = 12.sp)
                    Text(
                      text = cat.name,
                      color = if (isSelected) Color.White else CherishNavy,
                      style = MaterialTheme.typography.labelSmall,
                      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                  }
                }
              }
            }
          }

          // Service Offering Title
          Column {
            Text(text = "SERVICE TITLE", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
              value = serviceTitle,
              onValueChange = { serviceTitle = it },
              placeholder = { Text("e.g. Deep Cleaning & Sanitization") },
              shape = RoundedCornerShape(14.dp),
              modifier = Modifier.fillMaxWidth(),
              singleLine = true
            )
          }

          // Contact Phone
          Column {
            Text(text = "PHONE NUMBER", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
              value = phone,
              onValueChange = { phone = it },
              placeholder = { Text("+91 98765 00000") },
              leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = CherishTeal) },
              shape = RoundedCornerShape(14.dp),
              modifier = Modifier.fillMaxWidth(),
              singleLine = true
            )
          }

          // Pricing
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(text = "PRICE (₹)", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = priceInput,
                onValueChange = { priceInput = it },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
              )
            }

            Column(modifier = Modifier.weight(1f)) {
              Text(text = "BILLING UNIT", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.height(6.dp))
              OutlinedTextField(
                value = priceUnit,
                onValueChange = { priceUnit = it },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
              )
            }
          }

          // Key Service Features
          Column {
            Text(text = "SERVICE HIGHLIGHTS (BULLETS)", style = MaterialTheme.typography.labelSmall, color = CherishSlate, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
              value = keyFeaturesInput,
              onValueChange = { keyFeaturesInput = it },
              placeholder = { Text("Separate features with bullet • or comma") },
              shape = RoundedCornerShape(14.dp),
              modifier = Modifier.fillMaxWidth(),
              maxLines = 2
            )
          }

          Spacer(modifier = Modifier.height(4.dp))

          Button(
            onClick = {
              if (canSubmit) {
                val price = priceInput.toIntOrNull() ?: 499
                val features = keyFeaturesInput.split("•", ",").map { it.trim() }.filter { it.isNotBlank() }
                onRegisterSuccess(
                  businessName,
                  contactPerson,
                  selectedCategory,
                  phone,
                  serviceTitle,
                  price,
                  priceUnit,
                  if (features.isNotEmpty()) features else listOf("Verified service", "Prompt delivery")
                )
              }
            },
            enabled = canSubmit,
            colors = ButtonDefaults.buttonColors(containerColor = CherishCoral),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White)
              Text(text = "Register as Verified Vendor", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = Color.White)
            }
          }
        }
      }
    }
  }
}
