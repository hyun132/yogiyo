package com.example.yogiyo_clone.src.searchaddress.textSearch

import android.net.Uri.encode
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yogiyo_clone.R
import com.example.yogiyo_clone.config.BaseFragment
import com.example.yogiyo_clone.databinding.FragmentSearchTextAddressBinding
import com.example.yogiyo_clone.src.searchaddress.SearchAddressService
import com.example.yogiyo_clone.src.searchaddress.mapSearch.SearchMapFragment
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.AddressResult
import com.example.yogiyo_clone.src.searchaddress.textSearch.model.SearchAddressResponse
import java.util.*

class SearchTextAddressFragment : BaseFragment<FragmentSearchTextAddressBinding>(FragmentSearchTextAddressBinding::bind, R.layout.fragment_search_text_address),
SearchTextAddressFragmentView {

    lateinit var adapter:AddressAdapter
    var searchQuery:String=""
    lateinit var resultAddressList:LiveData<AddressResult>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= AddressAdapter()

        binding.addressRecyclerView.adapter=adapter
        binding.addressRecyclerView.layoutManager=LinearLayoutManager(context)

        binding.addressSearchview.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchQuery= text.toString()
                SearchAddressService(this@SearchTextAddressFragment).trySearchAddressByText(encode(text.toString(), "UTF-8"))
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        binding.searchMyLocationButton.setOnClickListener {
            val newFragment = SearchMapFragment()

            val transaction = requireActivity().supportFragmentManager.beginTransaction().apply {
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                replace(R.id.search_frame, newFragment)
                addToBackStack(null)
            }
            // Commit the transaction
            transaction.commit();
        }
    }

    override fun onPostAddressSearchSuccess(addressResults: List<AddressResult>) {
//        resultAddressList.observe(this,{list->
//
//        })
        adapter.differ.submitList(addressResults)

    }

    override fun onPostAddressSearchFailure(message: String) {
    }

    inner class AddressAdapter:RecyclerView.Adapter<AddressAdapter.SimpleAddressItemViewHolder>(){

        inner class SimpleAddressItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(item:AddressResult){
                if (item.jibunAddr.contains(searchQuery)){
                    itemView.findViewById<TextView>(R.id.address_item_textview).text=item.jibunAddr
                }else{
                    itemView.findViewById<TextView>(R.id.address_item_textview).text=item.roadAddr
                }

            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): SimpleAddressItemViewHolder {
            val view=LayoutInflater.from(context).inflate(R.layout.simple_address_item,parent,false)
            return SimpleAddressItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: SimpleAddressItemViewHolder, position: Int) {
            holder.bind(differ.currentList[position])
        }

        override fun getItemCount(): Int {
            return differ.currentList.size
        }

        private val differCallback = object : DiffUtil.ItemCallback<AddressResult>() {
            //둘이 같은 객체인지
            override fun areItemsTheSame(oldItem: AddressResult, newItem: AddressResult): Boolean {
                return oldItem.jibunAddr == newItem.jibunAddr
            }

            //둘이 같은 아이템인지
            override fun areContentsTheSame(oldItem: AddressResult, newItem: AddressResult): Boolean {
                return oldItem == newItem
            }

        }

        val differ = AsyncListDiffer(this, differCallback)

    }


}