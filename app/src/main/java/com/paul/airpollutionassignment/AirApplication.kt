package com.paul.airpollutionassignment

import android.app.Application
import com.paul.airpollutionassignment.data.source.AirRepository
import com.paul.airpollutionassignment.util.ServiceLocator
import kotlin.properties.Delegates

class AirApplication : Application() {

    val repository: AirRepository
        get() = ServiceLocator.provideRepository(this)

    companion object {
        var instance: AirApplication by Delegates.notNull()
        const val LIMIT = 1000
        const val APIKEY = "cebebe84-e17d-4022-a28f-81097fda5896"
        const val THRESHOLD = 10
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}