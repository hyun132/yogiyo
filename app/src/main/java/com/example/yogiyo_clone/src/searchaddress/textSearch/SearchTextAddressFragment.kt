package com.example.yogiyo_clone.src.searchaddress.textSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSearchTextAddressBinding
import com.example.yogiyo_clone.src.login.login.model.LogInResult
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse

class SearchTextAddressFragment : BaseFragment<FragmentSearchTextAddressBinding>(FragmentSearchTextAddressBinding::bind, R.layout.fragment_login),
SearchTextAddressFragmentView {
    override fun onPostAddressSearchSuccess(textSearchResult: SearchAddressResponse) {
        TODO("Not yet implemented")
    }

    override fun onPostAddressSearchFailure(message: String) {
        TODO("Not yet implemented")
    }

}