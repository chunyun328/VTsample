package com.vettons.sample.vtsample.view.homelist

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.opengl.Visibility
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vettons.sample.vtsample.R
import com.vettons.sample.vtsample.databinding.HomeListViewholderBinding
import com.vettons.sample.vtsample.network.entitymodel.HomeListModel
import com.vettons.sample.vtsample.network.entitymodel.ItemList
import java.text.SimpleDateFormat


class HomeListViewHolder(private val itemBinding: HomeListViewholderBinding, private val listener: HomeListAdapter.HomeListItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var homeItem: ItemList

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: ItemList) {
        this.homeItem = item

        Glide.with(itemBinding.root)
            .load(item.brand_logo)
            .placeholder(R.drawable.brand_default)
            .into(itemBinding.ivBrandLogo)

        itemBinding.tvProductName.text = item.name

        if(homeItem.photo_url.isNotEmpty()){
            Glide.with(itemBinding.root)
                .load(homeItem.photo_url[0])
                .placeholder(R.drawable.default_no_image)
                .into(itemBinding.ivProductCore)
        }

        itemBinding.layoutPricing.tvPriceOri.text = String.format("RM%.2f", item.ori_price)
        if(item.discount_rate > 0){
            itemBinding.layoutPricing.tvPriceOri.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            itemBinding.layoutPricing.tvPriceDiscount.text = String.format("RM%.2f", item.ori_price*(1-item.discount_rate))
            itemBinding.tvDiscountRate.text = String.format("%.0f%%", item.discount_rate * 100)
        }else{
            itemBinding.layoutPricing.tvPriceDiscount.visibility = INVISIBLE
            itemBinding.tvDiscountRate.visibility = INVISIBLE
        }

        itemBinding.root.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        listener.onClickViewHolder("HomeDetail", this.homeItem)
    }
}