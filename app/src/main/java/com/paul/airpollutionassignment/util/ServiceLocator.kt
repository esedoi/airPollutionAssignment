package com.paul.airpollutionassignment.util

import android.content.Context
import com.paul.airpollutionassignment.data.source.AirRepository
import com.paul.airpollutionassignment.data.source.DefaultAirRepository
import com.paul.airpollutionassignment.data.source.remote.AirRemoteDataSource

object ServiceLocator {

    private var repository: AirRepository? = null

    fun provideRepository(context: Context): AirRepository {
        synchronized(this) {
            return repository
                ?: createWeatherRepository(context)
        }
    }

    private fun createWeatherRepository(context: Context): AirRepository {
        return DefaultAirRepository(
            AirRemoteDataSource
        )
    }
}