package com.paul.airpollutionassignment.homefragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paul.airpollutionassignment.AirApplication
import com.paul.airpollutionassignment.data.Record
import com.paul.airpollutionassignment.data.Result
import com.paul.airpollutionassignment.data.source.AirRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val airRepository: AirRepository) : ViewModel() {

    var searchDisplayList = mutableListOf<Record>()

    var allPollutionData = mutableListOf<Record>()
    private var _allData = MutableLiveData<List<Record>>()
    val allData: LiveData<List<Record>>
        get() = _allData

    private var _upPollution = MutableLiveData<List<Record>>()
    val upPollution: LiveData<List<Record>>
        get() = _upPollution

    var downPollutionData = mutableListOf<Record>()
    private var _downPollution = MutableLiveData<List<Record>>()
    val downPollution: LiveData<List<Record>>
        get() = _downPollution

    private var _loadingComplete = MutableLiveData<Boolean>()
    val loadingComplete: LiveData<Boolean>
        get() = _loadingComplete


    fun getAirPollution(apiKey: String, limit: Int) {
        viewModelScope.launch {
            _loadingComplete.value = false

            when (val result = airRepository.getAirPollution(apiKey, limit)) {
                is Result.Success -> {
                    val data = result.data
                    val upList = mutableListOf<Record>()
                    val downList = mutableListOf<Record>()
                    _allData.value = data.records

                    for (i in data.records) {
                        try {
                            if (i.pmTwoPointFive.toInt() < AirApplication.THRESHOLD) {
                                upList.add(i)
                            } else {
                                downList.add(i)
                            }
                        } catch (e: Exception) {

                            Log.d("homeViewModel", "e = $e ")
                            Log.d("homeViewModel", "i = $i ")
                        }

                    }
                    _loadingComplete.value = true
                    _downPollution.value = downList
                    _upPollution.value = upList

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