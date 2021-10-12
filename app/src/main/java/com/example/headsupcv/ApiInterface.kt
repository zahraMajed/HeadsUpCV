package com.example.headsupcv

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    //get request: get -read- data from API
    @Headers("Content-Type: application/json")
    @GET("/celebrities/")
    fun getDate(): Call<List<myData.celeb>>?
}