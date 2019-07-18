package com.example.apicallactivity

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.support.v4.view.ViewPager
import com.bumptech.glide.Glide
import com.example.apicallactivity.Dataclasses.Image
import java.util.*


class ViewPagerAdapter() : PagerAdapter() {

    lateinit var context: Context
    lateinit var imageList:ArrayList<Image>


    constructor(context: Context,imageList:ArrayList<Image>):this()
    {
        this.context=context
        this.imageList=imageList


    }
    override fun getCount(): Int {
       return imageList.size
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {

        return p0 === p1 as ImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        var imageView=ImageView(context)
        imageView.scaleType=ImageView.ScaleType.CENTER_CROP
        var p=imageList.get(position)
        Glide.with(context).load(p.imageloc)
            .into(imageView);
        (container as ViewPager).addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, i: Int, obj: Any) {
        (container as ViewPager).removeView(obj as ImageView)
    }
}
