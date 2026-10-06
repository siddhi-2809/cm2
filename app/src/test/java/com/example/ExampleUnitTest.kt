package com.example

import com.example.data.DataRepository
import com.example.model.*
import com.example.viewmodel.CherishHomeViewModel
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun testServiceCategoriesCount() {
    assertEquals(7, DataRepository.serviceCategories.size)
    val names = DataRepository.serviceCategories.map { it.name }
    assertTrue(names.contains("Home Training"))
    assertTrue(names.contains("Home Needs"))
    assertTrue(names.contains("Home Care"))
    assertTrue(names.contains("Kitchen Needs"))
    assertTrue(names.contains("Home Security"))
    assertTrue(names.contains("Society Needs"))
    assertTrue(names.contains("Home Entertainment"))
  }

  @Test
  fun testMarketCategoriesCount() {
    assertEquals(12, DataRepository.marketCategories.size)
  }

  @Test
  fun testViewModelCartOperations() {
    val vm = CherishHomeViewModel()
    val product = DataRepository.marketProducts.first()
    val initialCount = vm.uiState.value.cart.values.sumOf { it.quantity }
    vm.addToCart(product)
    val updatedCount = vm.uiState.value.cart.values.sumOf { it.quantity }
    assertEquals(initialCount + 1, updatedCount)
  }
}
