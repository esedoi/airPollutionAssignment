package com.paul.airpollutionassignment.homefragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.airpollutionassignment.AirApplication
import com.paul.airpollutionassignment.R
import com.paul.airpollutionassignment.data.Record
import com.paul.airpollutionassignment.databinding.FragmentHomeBinding
import com.paul.airpollutionassignment.getVmFactory

class HomeFragment : Fragment() {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private var allPollutionData = mutableListOf<Record>()
    var displayList = mutableListOf<Record>()
    private var downPollutionData = mutableListOf<Record>()


    //vertical
    private lateinit var verticalAdapter: VerticalAdapter
    private var verticalManager: RecyclerView.LayoutManager? = null

    //horizontal
    private lateinit var horizontalAdapter: HorizontalAdapter
    private var horizontalManager: RecyclerView.LayoutManager? = null


    private val homeViewModel by viewModels<HomeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.getAirPollution(AirApplication.APIKEY, AirApplication.LIMIT)

        horizontalAdapter = HorizontalAdapter()
        horizontalManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.horizontalRecycler.layoutManager = horizontalManager
        binding.horizontalRecycler.adapter = horizontalAdapter

        verticalAdapter = VerticalAdapter()
        verticalManager = LinearLayoutManager(this.context)
        binding.verticalRecycler.layoutManager = verticalManager
        binding.verticalRecycler.adapter = verticalAdapter

        homeViewModel.loadingComplete.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.VISIBLE
            }

        }


        homeViewModel.allData.observe(viewLifecycleOwner) {
            allPollutionData.addAll(it)
        }

        homeViewModel.upPollution.observe(viewLifecycleOwner) {
            horizontalAdapter.submitList(it)
        }

        homeViewModel.downPollution.observe(viewLifecycleOwner) {
            downPollutionData.clear()
            downPollutionData.addAll(it)
            updateVerticalRecycler(it)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_main, menu)

        val searchItem = menu.findItem(R.id.action_search)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            val editText =
                searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            editText.hint = getString(R.string.find_site_name_hint)

            searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                    binding.horizontalRecycler.visibility = View.GONE
                    showHint()
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    editText.text.clear()
                    closeSearch()
                    return true
                }
            })


            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText != null && newText.isNotEmpty()) {
                        displayList.clear()
                        addToDisplayList(newText)
                        checkDisplayList(newText)
                        updateVerticalRecycler(displayList)

                    } else {
                        showHint()
                    }

                    return true
                }
            })
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun checkDisplayList(newText: String) {
        if (displayList.isEmpty()) {
            binding.hintText.visibility = View.VISIBLE
            binding.hintText.text = getString(R.string.find_nothing, newText)
        } else {
            binding.hintText.visibility = View.GONE
        }
    }

    private fun addToDisplayList(newText: String) {
        allPollutionData.forEach {
            if (it.siteName.contains(newText)) {
                displayList.add(it)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateVerticalRecycler(list: List<Record>) {
        verticalAdapter.submitList(list)
        verticalAdapter.notifyDataSetChanged()
    }


    private fun closeSearch() {
        binding.hintText.visibility = View.GONE
        binding.verticalRecycler.visibility = View.VISIBLE
        binding.horizontalRecycler.visibility = View.VISIBLE
        updateVerticalRecycler(downPollutionData)
    }


    private fun showHint() {
        displayList.clear()
        updateVerticalRecycler(displayList)
        binding.hintText.visibility = View.VISIBLE
        binding.hintText.text = getString(R.string.enter_site_name)
    }
}