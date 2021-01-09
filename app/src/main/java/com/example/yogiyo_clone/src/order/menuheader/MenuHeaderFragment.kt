package com.example.yogiyo_clone.src.order.menuheader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentMenuHeaderBinding
import com.example.yogiyo_clone.src.login.signupinfo.model.PostUserValicationResponse
import com.example.yogiyo_clone.src.order.menubottom.DetailFragment


class MenuHeaderFragment : BaseFragment<FragmentMenuHeaderBinding>(FragmentMenuHeaderBinding::bind, R.layout.fragment_menu_header),MenuHeaderFragmentView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_header, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val newFragment = DetailFragment()

        val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.frage, newFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit();
//
//        activity?.actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#33ffffff")))
//
//        activity?.actionBar?.apply {
//            this.title="BHC-군포금정역점"
////            setBackgroundDrawable()
////            setStackedBackgroundDrawable(ColorDrawable(Color.parseColor("#550000ff")));
//        }


    }

    override fun onPostLoadMenuSuccess(response: PostUserValicationResponse) {

    }

    override fun onPostLoadMenuFailure(message: String) {

    }
}