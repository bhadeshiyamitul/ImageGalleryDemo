package com.example.apicallactivity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.apicallactivity.Dataclasses.Datum
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    var arrayList = ArrayList<Datum>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById(R.id.recycler_view) as RecyclerView


        var linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = linearLayoutManager
        var api = ApiClient().getClient().create(Api::class.java)

        var call = api.getData()
         var progressDoalog:ProgressDialog ;
        progressDoalog = ProgressDialog(this@MainActivity);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {


                if (response.isSuccessful && response.body() != null) {


                    var data = response.body().toString()
                    Log.e("data", "$data")
                    try {
                        var jsonObject = JSONObject(data)
                        var status = jsonObject.getString("status")
                        var msg = jsonObject.getString("msg")

                        var data = jsonObject.getJSONArray("data")

                        for (i in 0 until data.length()) {
                            var d = data.getJSONObject(i)

                            var id = d.getString("mandirId")
                            var title = d.getString("title")
                            var tag = d.getString("mandirtag")
                            var date = d.getString("createdat")
                            var urls = d.getString("urls")


                            var datum=Datum()
                            datum.mandirId=id
                            datum.mandirtag=tag
                            datum.title=title
                            datum.createdat=date
                            datum.urls=urls

                            arrayList.add(datum)

                            Log.e("Api list data", "$id \n $title \n $tag \n $date \n $urls")

                        }
                        progressDoalog.dismiss()
                        Log.e("array","$arrayList")
                        Log.e("Api data", "$status and $msg")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }


                }

                var customAdapter = CustomAdapter(this@MainActivity, arrayList)

                recyclerView.adapter = customAdapter
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                progressDoalog.dismiss()
            }


        })

        Log.e("array11111","$arrayList")

    }
}
