package com.example.yogiyo_clone.src.order.menu.review

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentBottomReviewBinding
import com.example.yogiyo_clone.src.order.menu.review.models.ReviewResponse


class BottomReviewFragment : BaseFragment<FragmentBottomReviewBinding>(FragmentBottomReviewBinding::bind,R.layout.fragment_bottom_review),
    BottomReviewFragmentView{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { GetBottomReviewService(this).tryGetBottomReview(it.getInt("storeidx",0),0) }
//        arguments?.let { GetBottomReviewService(this).tryGetBottomMenu(1) }
//        GetBottomReviewService(view).trySearchAddressByText(arguments.getInt(""))

    }


    override fun onGetBottomReviewSuccess(response: ReviewResponse) {
//        response.reviewResult.reviewRates

        binding.totalReviewRatingbar.rating= response.result.rates.totalRate.toFloat()
        binding.tasteReviewRatingbar.rating= response.result.rates.tasteRate.toFloat()
        binding.amountReviewRatingbar.rating= response.result.rates.amountRate.toFloat()
        binding.deliveryReviewTextview.text= response.result.rates.deliveryRate.toString()
        binding.tasteReviewTextview.text= response.result.rates.tasteRate.toString()
        binding.amountReviewTextview.text= response.result.rates.amountRate.toString()
        binding.deliveryReviewRatingbar.rating= response.result.rates.deliveryRate.toFloat()
        binding.totalReivewTextview.text= response.result.rates.totalRate.toString()
        binding.photoReviewTextview.text= response.result.photos.size.toString()
        //recyclerview


//        binding.reviewImageRecyclerview.adapter=



        val reviewAdapter =ReviewVerticalAdapter()
        reviewAdapter.differ.submitList(response.result.entire)
        binding.reviewRecyclerview.adapter = reviewAdapter
        binding.reviewRecyclerview.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        binding.totalReviewNumberTextview.text = response.result.basic.reviewCount.toString()
    }

    override fun onGetBottomReviewFailure(message: String) {

    }

}