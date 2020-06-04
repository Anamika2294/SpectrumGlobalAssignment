package com.example.letsgetcheckedassignment

import com.example.spectrumglobalassignment.Model.RespnseDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @GET(".")
    fun getData(): Call<List<RespnseDataItem>>
}