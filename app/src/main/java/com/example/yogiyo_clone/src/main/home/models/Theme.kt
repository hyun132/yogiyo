package com.example.yogiyo_clone.src.main.home.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Theme(
    val stores: List<Store>,
    val themeIdx: Int,
    val themeName: String
    ):Serializable