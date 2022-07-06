package com.paul.airpollutionassignment.homefragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.airpollutionassignment.data.AirData
import com.paul.airpollutionassignment.data.Result
import com.paul.airpollutionassignment.data.source.AirRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val airRepository: AirRepository) : ViewModel() {

    private var _airPollution = MutableLiveData<AirData>()
    val airPollution: LiveData<AirData>
        get() = _airPollution


    fun getAirPollution(apiKey:String, limit:Int) {
        viewModelScope.launch {
            when (val result = airRepository.getAirPollution(apiKey, limit)) {
                is Result.Success -> {

                    _airPollution.value = result.data ?: return@launch

                    Log.d("homeViewModel", "news result = ${result.data}")
                }
                is Result.Error -> {
                    Log.d("homeViewModel", "news result = ${result.exception}")
                }
                is Result.Fail -> {
                    Log.d("homeViewModel", "news result = ${result.error}")
                }
            }

        }
    }

}