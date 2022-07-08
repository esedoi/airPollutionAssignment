package com.paul.airpollutionassignment.data.source.remote

import com.paul.airpollutionassignment.data.AirData
import com.paul.airpollutionassignment.data.Result
import com.paul.airpollutionassignment.data.source.AirDataSource
import com.paul.airpollutionassignment.network.AirApi

object AirRemoteDataSource: AirDataSource {


    override suspend fun getAirPollution(apiKey:String, limit:Int): Result<AirData> {

        return try {
            val result = AirApi.retrofitService.getAirPollution(apiKey, limit)
            Result.Success(result)

        }catch (e:Exception){
            Result.Error(e)
        }

    }
}