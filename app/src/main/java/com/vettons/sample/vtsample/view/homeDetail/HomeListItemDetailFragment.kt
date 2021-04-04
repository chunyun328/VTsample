package com.vettons.sample.vtsample.view.homeDetail

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.vettons.sample.vtsample.util.autoCleared
import com.vettons.sample.vtsample.R
import com.vettons.sample.vtsample.databinding.FragmentHomeListItemDetailBinding
import com.vettons.sample.vtsample.network.entitymodel.ItemList
import com.vettons.sample.vtsample.view.homelist.HomeListViewModel


class HomeListItemDetailFragment : Fragment() {
    private var binding: FragmentHomeListItemDetailBinding by autoCleared()
    private val liveViewModel: HomeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeListItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        val homeItem : ItemList = liveViewModel.selectedHomeItem



        binding.tvProductName.text = homeItem.name

        binding.layoutPricing.tvPriceOri.text = String.format("RM%.2f", homeItem.ori_price)
        if(homeItem.discount_rate > 0){
            binding.layoutPricing.tvPriceOri.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.layoutPricing.tvPriceDiscount.text = String.format("RM%.2f", homeItem.ori_price*(1-homeItem.discount_rate))
            binding.tvDiscountRate.text = String.format("%.0f%%", homeItem.discount_rate * 100)
        }else{
            binding.layoutPricing.tvPriceDiscount.visibility = View.INVISIBLE
            binding.tvDiscountRate.visibility = View.INVISIBLE
        }

        if(homeItem.photo_url.isNotEmpty()){
            Glide.with(binding.root)
                .load(homeItem.photo_url[0])
                .placeholder(R.drawable.default_no_image)
                .into(binding.ivProductImage)

            addButton(homeItem.photo_url)
        }

    }

    private fun addButton(imageUrlList: List<String>){
        binding.layoutDynamicPhotoItem.removeAllViews()
        for(imageUrl: String in imageUrlList){
            binding.layoutDynamicPhotoItem.addView(createButton(imageUrl))
        }
    }

    private fun createButton(imageUrl: String): ImageView {
        val context = binding.root.context
        val button = ImageView(context)
        var linearLayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        linearLayoutParams.setMargins(10,10,10,10)
        button.layoutParams = linearLayoutParams
        button.setPadding(10,10,10,10)
        button.textAlignment = View.TEXT_ALIGNMENT_CENTER

        Glide.with(binding.root)
            .load(imageUrl)
            .placeholder(R.drawable.default_no_image)
            .into(button)

        return button
    }
}