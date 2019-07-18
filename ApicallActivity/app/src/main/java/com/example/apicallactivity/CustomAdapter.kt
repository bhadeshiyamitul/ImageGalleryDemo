package com.example.apicallactivity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.apicallactivity.Dataclasses.Datum

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    lateinit var context: Context
    lateinit var array_list: ArrayList<Datum>

    constructor(context: Context, array_list: ArrayList<Datum>) : this() {
        this.context = context
        this.array_list = array_list

    }

    override fun getItemCount(): Int {
            return array_list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {

        var d=array_list.get(p1)
        p0.tvId.setText("ID::  "+d.mandirId)
        p0.tvTag.setText("Tag::  "+d.mandirtag)
        p0.tvTitle.setText("Title::  "+d.title)
        p0.tvDate.setText("Date::  "+d.createdat)
        p0.tvUrl.setText("Urls::  "+d.urls)
        p0.itemView.setOnClickListener {


            var intent= Intent(context,DetailActivity::class.java)
            intent.putExtra("Id",p0.tvId.text.toString())
            intent.putExtra("Tag",p0.tvTag.text.toString())
            intent.putExtra("Title",p0.tvTitle.text.toString())
            intent.putExtra("Date",p0.tvDate.text.toString())
            intent.putExtra("Url",p0.tvUrl.text.toString())
            context.startActivity(intent)


        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        var v=LayoutInflater.from(context).inflate(R.layout.custom_list,p0,false)
        var myViewHolder=MyViewHolder(v)
        return myViewHolder

    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvId=view.findViewById(R.id.txtid) as TextView
        var tvTag=view.findViewById(R.id.txttag) as TextView
        var tvTitle=view.findViewById(R.id.txttitle) as TextView
        var tvDate=view.findViewById(R.id.txtdate) as TextView
        var tvUrl=view.findViewById(R.id.txturl) as TextView



    }
}