package com.vettons.sample.vtsample.di

import com.vettons.sample.vtsample.network.datasource.homelist.HomeListDS
import com.vettons.sample.vtsample.network.repository.HomeListRepo
import com.vettons.sample.vtsample.service.HomeListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HomeListModule {
    @Singleton
    @Provides
    fun provideHomeListService(retrofit: Retrofit): HomeListService =
        retrofit.create(HomeListService::class.java)

    @Singleton
    @Provides
    fun provideHomeListRemoteDataSource(homelistService: HomeListService) = HomeListDS(homelistService)

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: HomeListDS,
    ) = HomeListRepo(remoteDataSource)
}