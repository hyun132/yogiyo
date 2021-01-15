package com.example.yogiyo_clone.config

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.yogiyo_clone.src.order.menu.ShoppingCart
import com.example.yogiyo_clone.src.order.menu.menuheader.model.MenuHeaderResult
import com.google.android.gms.maps.model.LatLng
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

//앱 처음 실행되는 때 딱 1번만 실행됨.
class ApplicationClass:Application() {

    val API_URL = "https://www.heedong.dev/"

    // 테스트 서버 주소
    // val API_URL = "http://dev-api.test.com/"

    // 실 서버 주소
    // val API_URL = "http://api.test.com/"

    // 코틀린의 전역변수 문법
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences

        // JWT Token Header 키 값
        val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"
        var SAVE_TOKEN=false
        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.`
        lateinit var sRetrofit: Retrofit

        var roadAddress= MutableLiveData<String>().apply { postValue("서울특별시 강남구 역삼동 강남대로 438") }
        var address= MutableLiveData<String>().apply { postValue("서울 강남구 역삼동 814-6") }
        var currentLatLng=LatLng(37.400000, 127.0210000)
        var detailAddress=""
        var userPhone=""
        var currentOrderList: ShoppingCart?=null

        var currentStore : MenuHeaderResult?=null

        fun addComma(price:Int):String{
            val dec = DecimalFormat("#,###")
            return dec.format(price)
        }
    }

    // 앱이 처음 생성되는 순간, SP를 새로 만들어주고, 레트로핏 인스턴스를 생성합니다.
    override fun onCreate() {
        super.onCreate()
        sSharedPreferences =
            applicationContext.getSharedPreferences(X_ACCESS_TOKEN, MODE_PRIVATE)
        // 레트로핏 인스턴스 생성
        initRetrofitInstance()
    }

    // 레트로핏 인스턴스를 생성하고, 레트로핏에 각종 설정값들을 지정해줍니다.
    // 연결 타임아웃시간은 5초로 지정이 되어있고, HttpLoggingInterceptor를 붙여서 어떤 요청이 나가고 들어오는지를 보여줍니다.
    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor { message: String ->
                Log.d(
                    "network_info",
                    message
                )
            }.setLevel(HttpLoggingInterceptor.Level.BODY)) // API Response 로그 작성용
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onTerminate() {
        super.onTerminate()

        if (!SAVE_TOKEN){
            val editor = sSharedPreferences.edit()
            editor.remove(X_ACCESS_TOKEN)
            editor.apply()
        }
    }

}