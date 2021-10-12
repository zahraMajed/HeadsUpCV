package com.example.headsupcv

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClint {
    var retrofitBuilder: Retrofit?= null

    //getClient() is just a method name you can rename it
    fun getClient(): Retrofit? {
        val interceptor= HttpLoggingInterceptor()
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        val client= OkHttpClient.Builder().addInterceptor(interceptor).build()
        //build retrofit
        retrofitBuilder = Retrofit.Builder()
            //do not use full url (full but except last part -endpoint-)
            .baseUrl("https://dojo-recipes.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofitBuilder
    }
}