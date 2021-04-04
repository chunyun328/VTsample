package com.vettons.sample.vtsample.view.homelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vettons.sample.vtsample.databinding.HomeListViewholderBinding
import com.vettons.sample.vtsample.network.entitymodel.HomeListModel
import com.vettons.sample.vtsample.network.entitymodel.ItemList
import java.util.*
import kotlin.collections.ArrayList

class HomeListAdapter(private val listener: HomeListItemListener) : RecyclerView.Adapter<HomeListViewHolder>() {

    interface HomeListItemListener {
        fun onClickViewHolder(action: String, item:ItemList)
    }

    private var items = ArrayList<ItemList>()

    fun resetData(newItems: ArrayList<ItemList>) {
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun addData(newItems: ArrayList<ItemList>) {
        var size = this.items.size
        this.items.addAll(newItems)
        var sizeNew = this.items.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListViewHolder {
        val binding: HomeListViewholderBinding = HomeListViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HomeListViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) = holder.bind(items[position])


}