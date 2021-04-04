package com.vettons.sample.vtsample.view.homelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vettons.sample.vtsample.util.autoCleared
import com.vettons.sample.vtsample.databinding.HomeListFragmentBinding
import com.vettons.sample.vtsample.network.entitymodel.ItemList
import com.vettons.sample.vtsample.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeListFragment : Fragment(), HomeListAdapter.HomeListItemListener {

    private var binding: HomeListFragmentBinding by autoCleared()
    private val viewModel: HomeListViewModel by activityViewModels()

    private lateinit var adapter: HomeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        adapter = HomeListAdapter(this)
        binding.rvFragmentHomeList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentHomeList.adapter = adapter
        binding.root.requestFocus()
    }

    private fun setupObservers() {
        viewModel.homeList.observe(viewLifecycleOwner,  {
            when (it.status) {
                Resource.Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE
                    if (!it.data?.itemList.isNullOrEmpty()) {
                            adapter.resetData(it.data?.itemList as ArrayList<ItemList>)
                        }else{
                            adapter.addData(it.data?.itemList as ArrayList<ItemList>)
                        }
                    }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()

                }
                }
        })
    }

    override fun onClickViewHolder(action: String, item: ItemList) {
        viewModel.selectedHomeItem = item
        when (action) {
            "HomeDetail" -> {
                val action =
                    HomeListFragmentDirections.actionNavHomeListToHomeListItemDetail(
                    )
                findNavController().navigate(action)
            }
        }
    }
}