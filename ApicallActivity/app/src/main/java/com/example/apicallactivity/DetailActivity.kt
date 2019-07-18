package com.example.apicallactivity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.GridView
import com.example.apicallactivity.Dataclasses.Image
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {

    var arrayList=ArrayList<Image>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var recyclerView=findViewById<RecyclerView>(R.id.detail_recycler_view)

        var staggeredGridLayoutManager=StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)

        recyclerView.layoutManager=staggeredGridLayoutManager


        var intent = getIntent()
        var extras = intent.extras

        var url = ""
        var retrofit: Retrofit

        if (extras != null) {
            var id = extras.getString("Id")
            var title = extras.getString("Title")
            var tag = extras.getString("Tag")
            var date = extras.getString("Date")
            url = extras.getString("Url")

        }
        var apiSplit = url.toString()
        var s = apiSplit.split("/")
        var s5 = s[4]

        Log.e("Split data", "$s5")
        retrofit = Retrofit.Builder().baseUrl(" http://swaminarayanwales.org.uk/DailyDarshan/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
        var api = retrofit.create(Api::class.java)

        var call1 = api.getImages(s5)
        var progressDoalog: ProgressDialog;
        progressDoalog = ProgressDialog(this@DetailActivity);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        call1.enqueue(object : Callback<JsonObject> {

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {

                if (response.isSuccessful && response.body() != null) {

                    try {
                        var data = response.body().toString()

                        var jsonObject = JSONObject(data)

                        var images = jsonObject.getJSONArray("images")

                        for (i in 0 until images.length()) {
                            var img = images.getJSONObject(i)

                            var imgLoc = img.getString("imageloc")
                            var imgDimension = img.getString("imageDimension")

                            var d=Image()
                            d.imageloc=imgLoc
                            d.imageDimension=imgDimension
                            arrayList.add(d)
                            progressDoalog.dismiss()
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    var recyclerImageAdapter=RecyclerImageAdapter(applicationContext,arrayList)
                    recyclerView.adapter=recyclerImageAdapter


                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                progressDoalog.dismiss()
            }

        })


    }
}
