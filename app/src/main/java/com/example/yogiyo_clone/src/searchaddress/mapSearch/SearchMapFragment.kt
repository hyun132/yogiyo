package com.example.yogiyo_clone.src.searchaddress.mapSearch

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.currentLatLng
import com.example.yogiyo_clone.config.ApplicationClass.Companion.roadAddress
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSearchMapBinding
import com.example.yogiyo_clone.src.login.login.LoginFragmentView
import com.example.yogiyo_clone.src.login.login.model.LogInResult
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.PostSetMyAddressRequest
import com.example.yogiyo_clone.src.searchaddress.mapSearch.model.SetAddressResponse
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse


////                    val projection = map.projection.visibleRegion.latLngBounds.center
////                    Log.d("current location: ", "${projection.latitude}, ${projection.longitude}")
class SearchMapFragment : BaseFragment<FragmentSearchMapBinding>(FragmentSearchMapBinding::bind, R.layout.fragment_search_map),
    SearchMapFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newFragment = MapsFragment()
        val arg = arguments?.getString("latlng")
        Log.d("SearchMapFragment : ", "arg : ${arguments?.getString("latlng")}")
        newFragment.arguments=Bundle().apply {
            putString("latlng",arg)
        }
        val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back



            replace(R.id.map_frame, newFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit();

        roadAddress.observe(this.viewLifecycleOwner, Observer {
            Log.d("observe","$it")
            binding.streetNameAddressTextview.text="[도로명] $it"
            binding.dongAddressTextview.text=it
        })

        binding.setAddressButton.setOnClickListener {
            if(binding.detailAddressEdittext.text.isNotEmpty()){
                var postSetMyAddressRequest =PostSetMyAddressRequest(currentLatLng.latitude.toFloat(),
                    currentLatLng.longitude.toFloat(), roadAddress.value.toString(),roadAddress.value.toString(),binding.detailAddressEdittext.text.toString())
                SetAddressService(this).setAddress(postSetMyAddressRequest)
            }
        }
    }

    override fun onPostSetMapAddressSuccess(setAddressResponse: SetAddressResponse) {
        Log.d("SetMapAddress : ", "success")


        activity?.finish()
    }


    override fun onPostSetMapAddressFailure(message: String) {
    }


}