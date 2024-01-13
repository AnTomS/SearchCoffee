package com.atom.searchcoffe.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.searchcoffe.domain.dto.Coffee
import com.atom.searchcoffe.domain.usecase.GetMenuUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val getMenuUseCase: GetMenuUseCase) : ViewModel() {

    private val _menuItems = MutableLiveData<List<Coffee>>().apply { value = emptyList() }
    val menuItems: LiveData<List<Coffee>> get() = _menuItems


    fun getMenu(locationId: Int) {
        viewModelScope.launch {
            val menu = getMenuUseCase.getMenu(locationId)
            _menuItems.value = menu
        }
    }

    fun addToCart(menuItem: Coffee) {
        // Логика добавления в корзину
        // Обновление _menuItems
    }

    fun removeFromCart(menuItem: Coffee) {
        // Логика удаления из корзины
        // Обновление _menuItems
    }
}