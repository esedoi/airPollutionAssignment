package com.paul.airpollutionassignment.homefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.paul.airpollutionassignment.R
import com.paul.airpollutionassignment.databinding.FragmentFirstBinding
import com.paul.airpollutionassignment.getVmFactory

const val LIMIT = 1000
const val APIKEY = "cebebe84-e17d-4022-a28f-81097fda5896"

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        homeViewModel.getAirPollution(APIKEY, LIMIT)


        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}