package com.example.model

enum class Screen {
  SPLASH,
  INTRO_UNIVERSE,
  AUTH,
  LOCATION_SETUP,
  VENDOR_REGISTRATION,
  MAIN_APP
}

enum class BottomTab(val label: String) {
  HOME("Home"),
  SERVICES("Services"),
  MARKET("Market"),
  SOCIETY("Society"),
  ACCOUNT("Account")
}

data class ServiceCategory(
  val id: String,
  val name: String,
  val badge: String,
  val tagline: String,
  val description: String,
  val iconEmoji: String,
  val heroImage: String,
  val popularChips: List<String>,
  val providers: List<ServiceProvider>
)

data class ServiceProvider(
  val id: String,
  val categoryId: String,
  val title: String,
  val providerName: String,
  val credentials: String,
  val rating: Double,
  val reviewCount: Int,
  val distanceKm: Double,
  val isVerified: Boolean = true,
  val highlightBadge: String = "",
  val price: Int,
  val priceUnit: String,
  val features: List<String>,
  val imageUrl: String = "",
  val initials: String = ""
)

data class MarketCategory(
  val id: String,
  val name: String,
  val description: String,
  val iconEmoji: String
)

data class MarketProduct(
  val id: String,
  val name: String,
  val price: Int,
  val originalPrice: Int? = null,
  val unit: String,
  val category: String,
  val discountPercent: Int? = null,
  val rating: Double = 4.8,
  val reviewCount: Int = 100,
  val imageUrl: String,
  val isTrending: Boolean = false,
  val isPicked: Boolean = false
)

data class CartItem(
  val product: MarketProduct,
  val quantity: Int
)

data class SocietyPulseItem(
  val id: String,
  val iconEmoji: String,
  val value: String,
  val label: String,
  val subtitle: String
)

data class NoticeItem(
  val id: String,
  val title: String,
  val date: String,
  val department: String,
  val isUrgent: Boolean,
  val summary: String
)

data class AmenityBooking(
  val id: String,
  val name: String,
  val iconEmoji: String,
  val timing: String,
  val status: String,
  val fee: String
)

data class VisitorPass(
  val id: String,
  val visitorName: String,
  val purpose: String,
  val time: String,
  val passCode: String,
  val status: String
)

data class BookingRecord(
  val id: String,
  val serviceTitle: String,
  val providerName: String,
  val date: String,
  val timeSlot: String,
  val price: Int,
  val status: String
)
