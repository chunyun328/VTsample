package com.vettons.sample.vtsample.view.homelist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.vettons.sample.vtsample.network.entitymodel.HomeListModel
import com.vettons.sample.vtsample.network.entitymodel.ItemList
import com.vettons.sample.vtsample.network.repository.HomeListRepo
import com.vettons.sample.vtsample.util.Resource
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class HomeListViewModel @ViewModelInject constructor(
    private val repository: HomeListRepo,
) : ViewModel() {
    var homeList = MutableLiveData<Resource<HomeListModel>>()
    lateinit var selectedHomeItem: ItemList

    init {
        viewModelScope.launch {
            homeList.value = repository.getHomeList()
        }
    }
}