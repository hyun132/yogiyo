package com.example.yogiyo_clone.src.order.menu.info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentRestaurnatInfoBinding
import com.example.yogiyo_clone.src.order.menu.info.models.BottomInfoResponse
import com.example.yogiyo_clone.src.order.menu.menubottom.BottomMenuService


class RestaurnatInfoFragment : BaseFragment<FragmentRestaurnatInfoBinding>(FragmentRestaurnatInfoBinding::bind,
    R.layout.fragment_restaurnat_info), RestaurnatInfoFragmentView{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { RestaurantInfoService(this).tryGetRestaurnatInfo(it.getInt("storeidx")) }
    }

    override fun onGetRestaurantInfoSuccess(response: BottomInfoResponse) {
        response.result.address?.let { Log.d("getInfoSuccess : ", it) }

        binding.chefDescription.text=response.result.description
        binding.businessHourTextview.text=response.result.businessHour
        binding.addressTextview.text=response.result.address
        binding.limitChargeTextview.text=response.result.limitCharge
        binding.limitChargeTextview2.text=response.result.limitCharge
        binding.payMethodAreaTextview.text=response.result.paymentSystem
        binding.businessNameTextview.text=response.result.businessName
        binding.businessNumberTextview.text=response.result.businessNumber
        binding.originDescriptionTextview.text=response.result.countryOfOrigin

    }

    override fun onGetRestaurantInfoFailure(message: String) {

    }


}