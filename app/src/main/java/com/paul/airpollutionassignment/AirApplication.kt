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
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}