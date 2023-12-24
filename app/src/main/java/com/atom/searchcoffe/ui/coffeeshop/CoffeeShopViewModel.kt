package com.atom.searchcoffe.ui.coffeeshop

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


    fun loadLocations() {
        viewModelScope.launch {

            _locations.value = ResponseState.Loading()
            _locations.value = try {
                ResponseState.Success(
                    data = getLocationUseCase.getCoffeeShops()
                )
            } catch (e: Exception) {
                ResponseState.Error()
            }

        }
    }
}