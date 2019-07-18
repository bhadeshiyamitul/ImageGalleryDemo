package com.example.apicallactivity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.apicallactivity.Dataclasses.Image

class FullScreenViewActivity : AppCompatActivity() {

    var p = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_view)

        supportActionBar!!.hide()

        var viewPager = findViewById<ViewPager>(R.id.view_pager)
        var btnNext = findViewById<ImageView>(R.id.img_next)
        var btnPrevious = findViewById<ImageView>(R.id.img_previous)

        var intent = intent
        var extras = intent.extras

        if (extras != null) {
            var data = extras.getBundle("data")
            p = data.getInt("Position")
            var arraylist = data.getParcelableArrayList<Image>("Array")

            var viewPagerAdapter = ViewPagerAdapter(this@FullScreenViewActivity, arraylist)
            viewPager.adapter = viewPagerAdapter
            viewPager.setCurrentItem(p, true)
            Log.e("arraylist image", "$arraylist")
        }

        btnNext.setOnClickListener {

            viewPager.setCurrentItem(viewPager.getCurrentItem()+1, true);


        }
        btnPrevious.setOnClickListener {
            viewPager.setCurrentItem(viewPager.getCurrentItem()-1, true);
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {


                    if (p0 < viewPager.adapter!!.count - 1) {
                        btnNext.visibility = View.VISIBLE
                    } else {
                        btnNext.visibility = View.INVISIBLE
                    }

                    if (p0 == 0) {
                        btnPrevious.visibility = View.INVISIBLE
                    } else {
                        btnPrevious.visibility = View.VISIBLE
                    }



            }
        })

    }
}
