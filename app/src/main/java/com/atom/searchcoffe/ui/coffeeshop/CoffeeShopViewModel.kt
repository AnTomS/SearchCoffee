package com.atom.searchcoffe.ui.coffeeshop

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.searchcoffe.data.network.ResponseState
import com.atom.searchcoffe.domain.dto.LocationRespondItem
import com.atom.searchcoffe.domain.usecase.GetLocationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoffeeShopViewModel @Inject constructor(private val getLocationUseCase: GetLocationUseCase) :
    ViewModel() {

    private val _locations = MutableLiveData<ResponseState<List<LocationRespondItem>>>()
    val locations: LiveData<ResponseState<List<LocationRespondItem>>> get() = _locations

    private var selectedLocationId: Int? = null

    fun loadLocations() {
        viewModelScope.launch {
            _locations.value = ResponseState.Loading()

            try {
                val coffeeShops = getLocationUseCase.getCoffeeShops()
                _locations.value = ResponseState.Success(data = coffeeShops)
                Log.d("CoffeeShopViewModel", "Successfully loaded coffee shops: $coffeeShops")
            } catch (e: Exception) {
                _locations.value = ResponseState.Error()
                Log.e("CoffeeShopViewModel", "Error loading coffee shops", e)
            }
        }
    }

    fun setSelectedLocationId(locationId: Int) {
        selectedLocationId = locationId
    }

    fun getSelectedLocationId(): Int? {
        return selectedLocationId
    }
}