package com.atom.searchcoffe.ui.menu

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.searchcoffe.Converter
import com.atom.searchcoffe.domain.dto.CartItem
import com.atom.searchcoffe.domain.dto.Coffee
import com.atom.searchcoffe.domain.usecase.GetMenuUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val getMenuUseCase: GetMenuUseCase) : ViewModel(),
    LifecycleObserver {


    private val converter = Converter()


    private val _updatedCartItem = MutableLiveData<Pair<Coffee, Int>>()
    val updatedCartItem: LiveData<Pair<Coffee, Int>> get() = _updatedCartItem


    private val _cartItems = MutableLiveData<List<CartItem>>().apply { value = emptyList() }
    val cartItems: LiveData<List<CartItem>> get() = _cartItems


    private val _orderList = MutableLiveData<List<CartItem>>()
    val orderList: LiveData<List<CartItem>> get() = _orderList

    fun setOrderListJson(json: String?) {
        _orderList.value = converter.parseOrderJson(json.orEmpty())
    }

    fun getOrderListJson(): String {
        val currentOrderList = _cartItems.value?.filter { it.quantity > 0 } ?: emptyList()
        return converter.convertOrderListToJson(currentOrderList)
    }


    fun getMenu(locationId: Int) {
        viewModelScope.launch {
            val menu = getMenuUseCase.getMenu(locationId)
            val cartItems = menu.map { CartItem(it, 0) }
            _cartItems.value = cartItems
            Log.e("MenuViewModel", "getMenu: $cartItems")
        }
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
        Log.e("MenuViewModel", "increaseCoffeeQuantity: $updatedCartItems")
    }

    fun decreaseCoffeeQuantity(coffee: Coffee) {
        val updatedCartItems = _cartItems.value?.map {
            if (it.coffee.id == coffee.id && it.quantity > 0) {
                it.copy(quantity = it.quantity - 1)
            } else {
                it
            }
        }?.toMutableList()
        _cartItems.value = updatedCartItems
        Log.e("MenuViewModel", "decreaseCoffeeQuantity: $updatedCartItems")
    }

}