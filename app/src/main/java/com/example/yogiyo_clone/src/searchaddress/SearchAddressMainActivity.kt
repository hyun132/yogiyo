package com.example.yogiyo_clone.src.searchaddress

import android.os.Bundle
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseActivity
import com.example.yogiyo_clone.databinding.ActivitySearchAddressMainBinding
import com.example.yogiyo_clone.src.searchaddress.textSearch.SearchTextAddressFragment


class SearchAddressMainActivity :  BaseActivity<ActivitySearchAddressMainBinding>(ActivitySearchAddressMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newFragment = SearchTextAddressFragment()

        val transaction = this.supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.search_frame, newFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit();

    }
}