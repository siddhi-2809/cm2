package com.example.data

import com.example.model.*

object DataRepository {

  val serviceCategories = listOf(
    ServiceCategory(
      id = "home_training",
      name = "Home Training",
      badge = "Mentoring",
      tagline = "Tutor, books, learning & smart mentoring",
      description = "Learn, grow and get expert guidance right from the comfort and safety of your home.",
      iconEmoji = "📚",
      heroImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCYOTjoLy_tFPtI6mpyUoDo7LFSQhqRtdlqoJNaWcrfeuQCxjRwJ_LRu88lzv6jWzW1ZkiokLZgP5iffHBHjWvKY05gtq_-w_Jq0o5wd0krFe9osMI_nooVnsxfsRInaXgBWxs7BJY0aMFcfJpv-kEzAmd01opNeAqEhtJlSmAE4QUIUgt7AEjqBTODbkFuys_xErvju95n6lKTy_k8bMKHTMqh0X4yYjvG7pQ9aAMzdiDCgtB_PhLn3g",
      popularChips = listOf("All Services", "Home Tutor", "Mathematics", "Physics", "Chemistry", "Coding & Robotics", "Exam Prep"),
      providers = emptyList()
    ),

    ServiceCategory(
      id = "home_needs",
      name = "Home Needs",
      badge = "Repairs",
      tagline = "Cleaning, essential repairs & daily maintenance",
      description = "Reliable help for the everyday needs of your home. Verified pros at your doorstep.",
      iconEmoji = "🧹",
      heroImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuAHX2Ku73ZrtW9T8_RtAMX50mLndB-jGC2ZQ7qej01qPRWKU8vxhbyP4KRtgO9X5Fr4m7Xx89sZZI0XxHA96pOz98bz5uJwGcwJeSD4ccHxN24RxmaoN_I3SdEud5rV6bc8TUJ5t6znhMre5pTBQ5iv90Mf-n-nYUHArAe6XsHkYiHr7p3ntrm4l7PVKIKplXYDoWepX1uJKjUThyPHDp7E4qk4DZnu-sH9S4JFXA7LVUWQFIENaDq1cw",
      popularChips = listOf("All Services", "Cleaning", "Electrical", "Appliance Repair", "Plumbing", "Carpentry", "Painting"),
      providers = emptyList()
    ),

    ServiceCategory(
      id = "home_care",
      name = "Home Care",
      badge = "Caregiver",
      tagline = "Caregiver, elderly care, nurse & assistance",
      description = "Trusted care and assistance for you and your loved ones, right at home.",
      iconEmoji = "❤️",
      heroImage = "https://lh3.googleusercontent.com/aida/AEtjO1Xx5Ok4p2EjtXfGUJ9Z1zwnO84nau-ujiogilOf6E02uNz_kaUyin4U4RcEzg4foWd0BYyA47vgKCpeMdz6ZEtXPNTTiSlBAKLlNvkgZyUbPNzh_niBfeN6EBTh2A1ERk393HLrbpvbp_vyWXytkDLrPP7Z6NTnpX-K8JENb3A2FXe9IXTmg-Tjt9SlQC84ysHl_29SJB_smKZ6xQQANS2erVEl2_O_phMFoCJs6fjxNP6GBg4ZmUBQXQ9C",
      popularChips = listOf("All Services", "Elder Care", "Patient & Post-Op", "Attendant Care", "Home Nursing", "Baby Care"),
      providers = emptyList()
    ),

    ServiceCategory(
      id = "kitchen_needs",
      name = "Kitchen Needs",
      badge = "Gourmet",
      tagline = "Gourmet cooking, cookware & meal prep",
      description = "Everything you need to keep your kitchen running smoothly, clean, and safe.",
      iconEmoji = "🍳",
      heroImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCaPHTfl1FdI5Yycx3ZlIKiSVD7t3IikobVZBFilMlRNelRqzPGDsf103EnYQU0wpUpeBhqAUrSRo3bxPwqhiYxZz5rfhZt9vvOQPnZqJKoYyeDIPhubH3cUw_J7X69XeRort203FV7BuOhvamJQzJJOux15UGoASIzKpveLbcTXcvvKCIjdwnPc09ic4qXqH-s0dsv8m7sPeN_kZAVwDVFFdvdA6GlhLykPF5Gx-kVMsv8j5Oty32osQ",
      popularChips = listOf("All Services", "Deep Cleaning", "Chimney Cleaning", "Appliance Repair", "RO & Purifier", "Gas Stove & Hob"),
      providers = emptyList()
    ),

    ServiceCategory(
      id = "home_security",
      name = "Home Security",
      badge = "Smart",
      tagline = "CCTV, smart biometric lock & digital safety",
      description = "Protect your home with trusted security solutions and verified technicians.",
      iconEmoji = "🔐",
      heroImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuB-7wMHcJZp9StX9gA79rZXV7H2iEv-gxMmdPV2EXGKHc5CUGWKrfsnYQZbh_7CjukjF9ZuWm2a0AzNZyIn1Mw4hbOUJG9NHgcOa0c6CS6r3HiobZEcc0VjFT2aeV1LoXevv1NnjsNvvOZGHU4zBOrimosI54cIfmV4pbEam5ji77LS6CSHUuOT73fC-wNfp7fFiKEZ3Fu4tuVKlHppGY0qgZ8IQuzWD5QF4EtnGJqjEGDHm26S2L3czA",
      popularChips = listOf("All Services", "CCTV Installation", "Smart Door Locks", "Alarm Systems", "Video Doorbells", "Security Inspection"),
      providers = emptyList()
    ),

    ServiceCategory(
      id = "society_needs",
      name = "Society Needs",
      badge = "Gated",
      tagline = "Apartment, security guard, clubhouse & repairs",
      description = "Make everyday society services, shared maintenance and community support easy.",
      iconEmoji = "🏢",
      heroImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCx5SpKJF8HVrDHL-mzygIvlt7dgPi9Zh_7jgKpnpW0k88_2hdDOF9uUE_ii8npme4B4Ls2uF9xXx93tP5-OELwwWm4Ed24sXNopxTqzOz9vr8kFM9LnRHbN6eHMfnc6u7CvakM0aVV5F2JWxWCjCgXSWrruSjj0l9DZhCnwWPr8f4mAV5q9SPWERkQIc_OpcZ7NyWoiRQ3jnvfU-SV0wqTj-HLhnLlUTLJhfCRyWwVY8db5T1eKv0D1w",
      popularChips = listOf("All Services", "Society Maintenance", "Plumbing & Tanks", "Common Electrical", "Landscape & Gardening", "Society Pest Control", "Clubhouse Care"),
      providers = emptyList()
    ),

    ServiceCategory(
      id = "home_entertainment",
      name = "Home Entertainment",
      badge = "Acoustics",
      tagline = "Smart TV, home theater, gaming & acoustics",
      description = "Bring entertainment, technology and acoustic comfort together at home.",
      iconEmoji = "📺",
      heroImage = "https://lh3.googleusercontent.com/aida-public/AB6AXuCzqMszzxOTpnAd4EcvyYv5KNMiwYSTKjTt1XD8i3qpHfvga7NV1GRrU1Mvc6XdYVINpSrWajf0a_0nU0tXUbvqRHkn-nksRepTjUqXo7YoPM97VZvGMab-chRZR58uNHFFbxlWhZBv7YQD9Rfo3s-da_PdSAi08o8kpCirg7CTIXsj5sI8COG_PzxArRVL3CgaJbHhWwG4mBX0QW7WbY9-QrLmSG98OWwZ2dLXSWKorqu19jsSPjQDqA",
      popularChips = listOf("All Services", "TV Installation", "Home Theatre Setup", "Smart TV Setup", "Speaker & Soundbar", "Gaming Setup"),
      providers = emptyList()
    )
  )

  val marketCategories = listOf(
    MarketCategory("groceries", "Daily Groceries", "Fresh produce, artisanal grains & pantry", "🛒"),
    MarketCategory("kitchen", "Kitchen Essentials", "Cookware, tools, tableware & jars", "🍳"),
    MarketCategory("homedecor", "Home Decor", "Lamps, vases, wall art & aesthetics", "🛋️"),
    MarketCategory("personalcare", "Personal Care", "Natural wellness, skin & hair care", "🌿"),
    MarketCategory("jewelry", "Handmade Jewelry", "Artisanal earrings, rings & chains", "💎"),
    MarketCategory("textiles", "Handmade Textiles", "Woven throws, linen & cushion covers", "🧵"),
    MarketCategory("pottery", "Pottery & Ceramics", "Hand-thrown mugs, plates & planters", "☕"),
    MarketCategory("bags", "Handwoven Bags", "Jute totes, wicker baskets & pouches", "👜"),
    MarketCategory("cleaning", "Cleaning & Household", "Eco liquids, scrubbers & organizers", "🧴"),
    MarketCategory("garden", "Garden & Plants", "Indoor greens, soil & ceramic pots", "🪴"),
    MarketCategory("wellness", "Organic & Wellness", "Herbal teas, pure honey & cold-pressed oils", "🍃"),
    MarketCategory("petcare", "Pet Care", "Nutritious treats, toys & grooming", "🐾")
  )

  val marketProducts = listOf(
    MarketProduct(
      id = "prod_matcha_mug",
      name = "Ceramic Matcha Mug",
      price = 649,
      originalPrice = 810,
      unit = "With textured saucer",
      category = "Pottery & Ceramics",
      discountPercent = 20,
      rating = 4.90,
      reviewCount = 128,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDWy5pQDBH3x20DnmERThA6BUVCtS0rkGOP9JOTQLFeE_pEe7I8PsnaHzx8lmZzzYNOx5nC5yEz4JQJITyqWH_roZMo3BMSOYU7HrvE0kevpSR7CsMle540glld-9B3xVQH5NYIrLcyB1hJ2nvwyZ2JCCdEKtvHmi-hXmzACUWksCuBrQ5ipj2MVR99lQt0eY7ldPb4zWowFP8gDPHyK6XuZAFK4wa7ByCid5sjwpD1lDyuZllXlMiOYw",
      isTrending = true
    ),
    MarketProduct(
      id = "prod_olive_oil",
      name = "Cold-Pressed Olive & Herb Oil",
      price = 480,
      unit = "Organic cold-pressed 500ml",
      category = "Organic & Wellness",
      rating = 4.85,
      reviewCount = 94,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBg5V2EAxTUxpjBP1pEXnrbjiZPMEIUjX9e4HdUFmxGIhtPPBTpq_8XKFrOaVC-JMXPfBuKssALfJLNNZbxallJQDbNXa7sUsJ0-nocd3LCZI2dFL05iWmaJIIVEGmUpfH1-_M2nDpGLra5bmC6KgzZ5HeOmsrVrMpVCzAyISe-jvfVFHVAmZaSOk-lavsaFn5_joQvHzTbjGLIXapBhJEuSuD1od2PP6C1vvqT1oOALI65HV3OTqjYYA",
      isTrending = true
    ),
    MarketProduct(
      id = "prod_linen_towel",
      name = "Handwoven Linen Towel Set",
      price = 420,
      unit = "100% European flax set",
      category = "Handmade Textiles",
      rating = 4.92,
      reviewCount = 76,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDs7U23ZXzezMA-qrsu0LM4u5B84gPVTa64GhPPpggGdHv_J_NyMhxl_v4VigAcB7Ndv5OiNsxK0O7grTbip314V93llh0s5X3YnPAgjZJZVbjr8If-j5vQOKFWEVyrEsZaOYnB8hyTYFrT-8b_rORsb7xXEIRNn3V8wsyrzJXSPv3koMF4XhAiQmAw8_i1weLA_w8g-Y6Fobdb7hnMOB2lX5V-JFlmGALSFiAOf_MapScTKEjqC1hTPg",
      isTrending = true,
      isPicked = true
    ),
    MarketProduct(
      id = "prod_terracotta_planter",
      name = "Terracotta Indoor Planter",
      price = 380,
      unit = "Air-purifying Jade succulent",
      category = "Garden & Plants",
      rating = 4.88,
      reviewCount = 64,
      imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBweAdgfgva5xCt1dvXETNEBu0pcRDSf068oAD4lcMJFXKF2sZN2KFvqFe-76AZ4mEYnxDwl3Za3BQjDKad1n0dhljy1JdksfCgS2mI8vCisFOGZurm3ENi93otlYqQA-XlaZAP_BdUUqINstXYysUQxY03vWkndGakJX7lJYMIVejcwSySTg0k12rK3Yruv5g_8lf1_Zw9JXgYoCqTtxLbatRJo_kIC3Luw3FSO5CFsNmgfrOWJevK4Q",
      isPicked = true
    ),
    MarketProduct(
      id = "groc_apples",
      name = "Organic Shimla Apples",
      price = 180,
      originalPrice = 210,
      unit = "4 pcs (approx. 500g)",
      category = "Daily Groceries",
      discountPercent = 15,
      rating = 4.90,
      reviewCount = 310,
      imageUrl = "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_spinach",
      name = "Baby Spinach (Cleaned)",
      price = 65,
      unit = "200 g pack",
      category = "Daily Groceries",
      rating = 4.80,
      reviewCount = 180,
      imageUrl = "https://images.unsplash.com/photo-1576045057995-568f588f82fb?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_milk",
      name = "Farm Fresh Cow Milk",
      price = 72,
      unit = "1 L Glass Bottle",
      category = "Daily Groceries",
      rating = 4.95,
      reviewCount = 920,
      imageUrl = "https://images.unsplash.com/photo-1550583724-b2692b85b150?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_mango",
      name = "Ratnagiri Alphonso Mango",
      price = 450,
      originalPrice = 500,
      unit = "Box of 6 pcs",
      category = "Daily Groceries",
      discountPercent = 10,
      rating = 4.90,
      reviewCount = 450,
      imageUrl = "https://images.unsplash.com/photo-1553279768-865429fa0078?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_atta",
      name = "Aashirvaad Shudh Chakki Atta",
      price = 245,
      originalPrice = 275,
      unit = "5 kg",
      category = "Daily Groceries",
      discountPercent = 11,
      rating = 4.80,
      reviewCount = 1200,
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_salt",
      name = "Tata Salt Vacuum Evaporated",
      price = 28,
      unit = "1 kg",
      category = "Daily Groceries",
      rating = 4.90,
      reviewCount = 840,
      imageUrl = "https://images.unsplash.com/photo-1518110925495-5fe2fda0442c?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_ghee",
      name = "Amul Pure Ghee Binnie Jar",
      price = 610,
      originalPrice = 650,
      unit = "1 L",
      category = "Daily Groceries",
      rating = 4.90,
      reviewCount = 2100,
      imageUrl = "https://images.unsplash.com/photo-1628088062854-d1870b4553da?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_oil",
      name = "Fortune Sunlite Sunflower Oil",
      price = 142,
      originalPrice = 165,
      unit = "1 L Pouch",
      category = "Daily Groceries",
      discountPercent = 14,
      rating = 4.70,
      reviewCount = 950,
      imageUrl = "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_dal",
      name = "Tata Sampann Toor Dal",
      price = 175,
      unit = "1 kg Unpolished",
      category = "Daily Groceries",
      rating = 4.80,
      reviewCount = 620,
      imageUrl = "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=400&auto=format&fit=crop"
    ),
    MarketProduct(
      id = "groc_rice",
      name = "Daawat Rozana Basmati Rice",
      price = 399,
      originalPrice = 460,
      unit = "5 kg",
      category = "Daily Groceries",
      discountPercent = 13,
      rating = 4.70,
      reviewCount = 510,
      imageUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=400&auto=format&fit=crop"
    )
  )

  val societyPulse = listOf(
    SocietyPulseItem("sp_1", "🚗", "12", "Visitor parking slots", "Available on Level B1"),
    SocietyPulseItem("sp_2", "📜", "1", "New notice", "Water tank clean on Wednesday"),
    SocietyPulseItem("sp_3", "🔧", "Maint.", "Due in 3 days", "₹3,450 Q3 Assessment")
  )

  val notices = listOf(
    NoticeItem("n_1", "Water Tank Cleaning Schedule", "Today, 10:00 AM", "Maintenance Committee", false, "Overhead tanks for Wings A & B will be sanitized between 10 AM to 2 PM. Water supply will resume normally by 3 PM."),
    NoticeItem("n_2", "Society AGM & Annual Budget Meeting", "Oct 12, 11:00 AM", "Managing Committee", true, "All resident owners are requested to attend the Annual General Meeting at Clubhouse Hall 1."),
    NoticeItem("n_3", "EV Charging Station Access Protocol", "Oct 02, 06:30 PM", "Green Energy Wing", false, "New 22kW fast chargers are now live in Parking Pod C. Reserve via app.")
  )

  val amenities = listOf(
    AmenityBooking("am_1", "Infinity Swimming Pool", "🏊", "06:00 AM - 09:00 PM", "Open Now", "Free for residents"),
    AmenityBooking("am_2", "Clubhouse Banquet Hall", "🎉", "Slots by reservation", "Book Ahead", "₹2,500 deposit"),
    AmenityBooking("am_3", "Floodlit Tennis Court", "🎾", "06:00 AM - 10:00 PM", "2 Slots Left Today", "Free"),
    AmenityBooking("am_4", "Gym & Yoga Studio", "🧘", "05:00 AM - 11:00 PM", "Open Now", "Included")
  )
}
