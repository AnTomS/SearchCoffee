package com.atom.searchcoffe.ui.menu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.searchcoffe.domain.dto.CartItem
import com.atom.searchcoffe.domain.dto.Coffee
import com.atom.searchcoffe.domain.usecase.GetMenuUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val getMenuUseCase: GetMenuUseCase) : ViewModel() {

    private val _menuItems = MutableLiveData<List<Coffee>>().apply { value = emptyList() }
    val menuItems: LiveData<List<Coffee>> get() = _menuItems

    private val _updatedCartItem = MutableLiveData<Pair<Coffee, Int>>()
    val updatedCartItem: LiveData<Pair<Coffee, Int>> get() = _updatedCartItem


    private val _cartItems = MutableLiveData<List<CartItem>>().apply { value = emptyList() }
    val cartItems: LiveData<List<CartItem>> get() = _cartItems


    fun getMenu(locationId: Int) {
        viewModelScope.launch {
            val menu = getMenuUseCase.getMenu(locationId)
            // Изначально устанавливаем количество товара в корзине в 0
            _cartItems.value = menu.map { CartItem(it, 0) }
        }
    }


    fun addToCart(coffee: Coffee) {
        val existingItem = _cartItems.value?.find { it.coffee.id == coffee.id }
        existingItem?.let {
            increaseCoffeeQuantity(coffee)
        } ?: run {
            _cartItems.value = (_cartItems.value ?: emptyList()) + CartItem(coffee, 1)
        }
    }

    fun removeFromCart(coffee: Coffee) {
        _cartItems.value = _cartItems.value?.filterNot { it.coffee.id == coffee.id }
    }

    fun increaseCoffeeQuantity(coffee: Coffee) {
        val updatedCartItems = _cartItems.value?.map {
            if (it.coffee.id == coffee.id) {
                it.copy(quantity = it.quantity + 1)
            } else {
                it
            }
        }
        _cartItems.value = updatedCartItems
    }

    fun decreaseCoffeeQuantity(coffee: Coffee) {
        val updatedCartItems = _cartItems.value?.map {
            if (it.coffee.id == coffee.id && it.quantity > 0) {
                it.copy(quantity = it.quantity - 1)
            } else {
                it
            }
        }?.toMutableList()  // Удаляем элемент, если его количество стало 0
        _cartItems.value = updatedCartItems
    }
}
//    private fun updateMenuQuantity(menuItemId: Int, quantity: Int) {
//        val updatedMenu = _menuItems.value?.map {
//            if (it.id == menuItemId) {
//                it.quantity = quantity
//                it
//            } else {
//                it
//            }
//        }
//        _menuItems.value = updatedMenu
//        println("Updated menu with quantity: $updatedMenu")
//    }
//
//    fun getCartItems(): List<Pair<Coffee, Int>> {
//        return cartItems.map { it.coffee to it.quantity }
//    }



//    fun addToCart(menuItem: Coffee) {
//        val existingItem = _cartItems.value?.find { it.first.id == menuItem.id }
//        if (existingItem != null) {
//            val updatedItem = existingItem.copy(second = existingItem.second + 1)
//            val updatedList = _cartItems.value?.toMutableList() ?: mutableListOf()
//            updatedList[_cartItems.value?.indexOf(existingItem)!!] = updatedItem
//            _cartItems.value = updatedList
//            _updatedCartItem.value = updatedItem
//        } else {
//            val updatedList = _cartItems.value?.toMutableList() ?: mutableListOf()
//            updatedList.add(menuItem to 1)
//            _cartItems.value = updatedList
//            _updatedCartItem.value = menuItem to 1
//        }
//    }
//
//    fun removeFromCart(menuItem: Coffee) {
//        val existingItem = _cartItems.value?.find { it.first.id == menuItem.id }
//        if (existingItem != null && existingItem.second > 0) {
//            val updatedItem = existingItem.copy(second = existingItem.second - 1)
//            val updatedList = _cartItems.value?.toMutableList() ?: mutableListOf()
//            updatedList[_cartItems.value?.indexOf(existingItem)!!] = updatedItem
//            _cartItems.value = updatedList
//            _updatedCartItem.value = updatedItem
//        }
//    }
//
//    private fun updateMenuQuantity(menuItemId: Int, quantity: Int) {
//        val updatedMenu = _menuItems.value?.map {
//            if (it.id == menuItemId) {
//                it.copy(quantity = quantity)
//            } else {
//                it
//            }
//        }
//        _menuItems.value = updatedMenu
//    }
//
//    fun getCartItems(): List<Pair<Coffee, Int>> {
//        return _cartItems.value ?: emptyList()
//    }