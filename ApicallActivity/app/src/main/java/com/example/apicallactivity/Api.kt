package com.example.apicallactivity

import com.example.apicallactivity.Dataclasses.Pojo
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {


  @GET("index.php?action=madirlist")
    fun getData():Call<JsonObject>

  @GET
  fun getImages(@Url url:String):Call<JsonObject>



}