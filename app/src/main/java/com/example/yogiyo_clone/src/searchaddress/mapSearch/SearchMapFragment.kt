package com.example.yogiyo_clone.src.searchaddress.mapSearch

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.ApplicationClass.Companion.roadAddress
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSearchMapBinding
import com.example.yogiyo_clone.src.login.login.LoginFragmentView
import com.example.yogiyo_clone.src.login.login.model.LogInResult


////                    val projection = map.projection.visibleRegion.latLngBounds.center
////                    Log.d("current location: ", "${projection.latitude}, ${projection.longitude}")
class SearchMapFragment : BaseFragment<FragmentSearchMapBinding>(FragmentSearchMapBinding::bind, R.layout.fragment_search_map),
    LoginFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newFragment = MapsFragment()

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
        })
    }

//    fun setAddress(address:String){
//        binding.streetNameAddressTextview.text=address
//    }

    override fun onPostLogInSuccess(logInResult: LogInResult) {
        TODO("Not yet implemented")
    }

    override fun onPostLogInFailure(message: String) {
        TODO("Not yet implemented")
    }

}