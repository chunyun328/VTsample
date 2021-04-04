package com.vettons.sample.vtsample.network.repository

import com.vettons.sample.vtsample.network.datasource.homelist.HomeListDS
import com.vettons.sample.vtsample.util.performGetOperation
import javax.inject.Inject

class HomeListRepo @Inject constructor(
    private val remoteDataSource: HomeListDS,
) {
    suspend fun getHomeList() = performGetOperation(
        networkCall = { remoteDataSource.getHomeList() }
    )

}