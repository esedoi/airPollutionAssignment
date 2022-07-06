package com.paul.airpollutionassignment.data.source

import com.paul.airpollutionassignment.data.AirData
import com.paul.airpollutionassignment.data.Result

class DefaultAirRepository (private val airDataSource: AirDataSource) :
    AirRepository {

    override suspend fun getAirPollution(apiKey:String, limit:Int): Result<AirData> {
        return airDataSource.getAirPollution(apiKey, limit)
    }
}