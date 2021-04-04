package com.vettons.sample.vtsample.service

import com.vettons.sample.vtsample.network.entitymodel.HomeListModel
import retrofit2.Response
import retrofit2.http.GET

interface HomeListService {
    @GET("3a91c119-3650-40c4-aa06-a1f01f73a33c")
    suspend fun getHomeList(
    ): Response<HomeListModel>
}