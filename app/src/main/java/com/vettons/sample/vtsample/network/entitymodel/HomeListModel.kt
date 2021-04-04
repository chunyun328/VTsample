package com.vettons.sample.vtsample.network.entitymodel

import kotlinx.serialization.Serializable

@Serializable
data class HomeListModel (
    val itemList: List<ItemList>
)

@Serializable
data class ItemList (
    val id: Long,
    val name: String,
    val brand_logo: String,
    val photo_url: List<String>,

    val ori_price: Double,
    val discount_rate: Double
)
