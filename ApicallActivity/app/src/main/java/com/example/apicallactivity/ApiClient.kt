package com.example.apicallactivity

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClient {


    var BASE_URL = "http://developerclouds.com/apps/dailydarshan/"

    lateinit var retrofit: Retrofit


    fun getClient(): Retrofit {

        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
          .build()

        return retrofit
    }
}