package com.paul.airpollutionassignment.data.source

import com.paul.airpollutionassignment.data.AirData
import com.paul.airpollutionassignment.data.Result

interface AirDataSource {

    suspend fun getAirPollution(apiKey:String, limit:Int): Result<AirData>
}