package com.vettons.sample.vtsample.network.datasource.homelist

import com.vettons.sample.vtsample.network.datasource.BaseDataSource
import com.vettons.sample.vtsample.service.HomeListService
import javax.inject.Inject

class HomeListDS @Inject constructor(
    private val homelistService: HomeListService
) : BaseDataSource() {
    suspend fun getHomeList() = getResult { homelistService.getHomeList() }

}