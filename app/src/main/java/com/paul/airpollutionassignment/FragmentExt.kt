package com.paul.airpollutionassignment

import androidx.fragment.app.Fragment
import com.paul.airpollutionassignment.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as AirApplication).repository
    return ViewModelFactory(repository)
}