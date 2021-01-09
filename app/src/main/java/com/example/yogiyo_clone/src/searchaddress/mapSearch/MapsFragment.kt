package com.example.yogiyo_clone.src.searchaddress.mapSearch

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.yogiyo_clone.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng


class MapsFragment : OnMapReadyCallback,Fragment() {
    val locationRequestId = 100
    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var manager:LocationManager
    var currentLocation=LatLng(37.4,127.02)
    lateinit var mapFragment :SupportMapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_maps, container, false)

        mapFragment = (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment.getMapAsync(this);





    }

    fun getCurrentLocation(){
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient=FusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: android.location.Location? ->
                if (location != null) {
                    currentLocation = LatLng(location.latitude, location.longitude)
                    Log.d("getLocation", location.toString())
                    mapFragment?.getMapAsync { googleMap->
                        googleMap.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                currentLocation,
                                18F
                            )
                        )
                    }
                }

            }

    }

    val MY_PERMISSIONS_REQUEST_LOCATION = 99

//    private fun checkLocationPermissionWithRationale() {
//        if (ContextCompat.checkSelfPermission(
//                requireActivity(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    requireActivity(),
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                )
//            ) {
//                AlertDialog.Builder(requireContext())
//                    .setTitle("위치정보")
//                    .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다. 위치정보 접근을 허용하여 주세요.")
//                    .setPositiveButton("확인",
//                        DialogInterface.OnClickListener { dialogInterface, i ->
//                            requestPermissions(
//                                requireActivity(), arrayOf(
//                                    Manifest.permission.ACCESS_FINE_LOCATION
//                                ), MY_PERMISSIONS_REQUEST_LOCATION
//                            )
//                        }).create().show()
//            } else {
//                requestPermissions(
//                    requireActivity(),
//                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                    MY_PERMISSIONS_REQUEST_LOCATION
//                )
//            }
//        }
//    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap=map

        if (googleMap != null) {
            getCurrentLocation()

            googleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15F))

            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                googleMap!!.setMyLocationEnabled(true);
                currentLocation = googleMap!!.getProjection().getVisibleRegion().latLngBounds.getCenter()
                Log.d("currentLocation : ","Lat : ${currentLocation.latitude}, lng : ${currentLocation.longitude}")
            } else {
                askLocationPermission()
//                checkLocationPermissionWithRationale();
            }
        }
        if (googleMap != null) { // 이 설정은 onMapReady에서 설정해야 먹음.. 다른곳에서 하니 설정이안먹힘..
            googleMap!!.setOnCameraIdleListener(object :GoogleMap.OnCameraIdleListener{
                override fun onCameraIdle() {
                    currentLocation = googleMap!!.getProjection().getVisibleRegion().latLngBounds.getCenter()
                    Log.d("currentLocation : ","Lat : ${currentLocation.latitude}, lng : ${currentLocation.longitude}")
                    CameraUpdateFactory.newLatLngZoom(
                        currentLocation,
                        15F
                    )
                }

            })
        }

    }

    fun askLocationPermission(){
        ActivityCompat.requestPermissions(requireActivity(),arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),locationRequestId)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==locationRequestId){
            if(grantResults!=null && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //get addr
            }
        }
    }

}