package com.example.yogiyo_clone.src.order

import android.os.Bundle
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseActivity
import com.example.yogiyo_clone.databinding.ActivityOrderBinding
import com.example.yogiyo_clone.src.order.menu.menuheader.MenuHeaderFragment

class OrderActivity : BaseActivity<ActivityOrderBinding>(ActivityOrderBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
//        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        super.onCreate(savedInstanceState)

        val storeidx=intent.getIntExtra("storeId",0)

        val newFragment = MenuHeaderFragment()

        val transaction = this.supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            newFragment.arguments = Bundle().apply {
                putInt("storeIdx",storeidx)
            }
            replace(R.id.order_frame, newFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit();
    }

}