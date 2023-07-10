package com.builditcreative.mindmyscape.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.builditcreative.mindmyscape.data.Data
import com.builditcreative.mindmyscape.databinding.FragmentHomeBinding
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    private var dataList = mutableListOf<Data>()
    private lateinit var adapter: TherapiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()

        setObservers()

        viewModel.getTherapies()

    }

    private fun setListeners() {

        binding.searchBar.doOnTextChanged { text, start, before, count ->
            viewModel.searchTherapies(text.toString())
        }
    }

    private fun setObservers() {
        viewModel.getTherapiesResponse.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it.isLoading
            if (it.data.isEmpty()) {
                return@observe
            }
            dataList = it.data.toMutableList()
            setRecyclerView()
        }
    }

    private fun setRecyclerView() {
        binding.rvList.apply {
            binding.progressBar.isVisible = false
            layoutManager = GridLayoutManager(requireContext(),3)
            this@HomeFragment.adapter = TherapiesAdapter(dataList)
            adapter = this@HomeFragment.adapter
        }

    }

}
