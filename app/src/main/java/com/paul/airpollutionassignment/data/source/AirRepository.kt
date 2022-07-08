package com.paul.airpollutionassignment.data.source

import com.paul.airpollutionassignment.data.AirData
import com.paul.airpollutionassignment.data.Result

interface AirRepository {

    suspend fun getAirPollution(apiKey:String, limit:Int): Result<AirData>
}