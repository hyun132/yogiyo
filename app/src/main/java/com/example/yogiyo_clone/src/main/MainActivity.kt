package com.example.yogiyo_clone.src.main

import android.os.Bundle
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseActivity
import com.example.yogiyo_clone.databinding.ActivityMainBinding
import com.example.yogiyo_clone.src.main.category.CategoryFragment
import com.example.yogiyo_clone.src.main.home.HomeFragment
import com.example.yogiyo_clone.src.main.myyogiyo.MyYogiyoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.temp->{
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, CategoryFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_my_page -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyYogiyoFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }

                }
                false
            })


    }
}