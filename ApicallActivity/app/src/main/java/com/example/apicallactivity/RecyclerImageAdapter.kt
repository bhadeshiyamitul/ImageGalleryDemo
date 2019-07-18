package com.example.apicallactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.apicallactivity.Dataclasses.Image

class RecyclerImageAdapter() : RecyclerView.Adapter<RecyclerImageAdapter.MyViewHolder>() {

    lateinit var context: Context
    lateinit var arrayList: ArrayList<Image>


    constructor(context: Context, arrayList: ArrayList<Image>) : this() {
        this.context = context
        this.arrayList = arrayList
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        var i = arrayList.get(p1)

        var d = i.imageDimension
        var dim = d!!.split(":")

        var width = dim[0].toInt()
        var height = dim[1].toInt()

        p0.imageView.setOnClickListener {
            var intent = Intent(context, FullScreenViewActivity::class.java).setFlags(FLAG_ACTIVITY_NEW_TASK)
            var bundle=Bundle()
            bundle.putParcelableArrayList("Array",arrayList as java.util.ArrayList<out Parcelable>)
            bundle.putInt("Position",p1)
            intent.putExtra("data",bundle)
            context.startActivity(intent)
            Toast.makeText(context, "image clicked" + p1, Toast.LENGTH_LONG).show()
        }
        var requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_launcher_background)
        requestOptions.error(R.drawable.ic_launcher_background)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(i.imageloc).override(width, height)
            .into(p0.imageView);

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        var v = LayoutInflater.from(context).inflate(R.layout.custom_grid_layout, p0, false)
        var myViewHolder = MyViewHolder(v)
        return myViewHolder

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var imageView = view.findViewById<ImageView>(R.id.grid_imag)

    }

    /*class OnItemClickListener() : View.OnClickListener {
        lateinit var activity:DetailActivity
        var position = 0

        constructor(position: Int) : this() {
            this.position = position
        }

        override fun onClick(v: View?) {
            var intent = Intent(activity, FullScreenViewActivity::class.java)
            intent.putExtra("Position", position)
            activity.startActivity(intent)
        }
    }*/
}