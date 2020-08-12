package com.pws.identificationofanimalnames

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager


/**
 * Created by PwS
 */

class Play : AppCompatActivity() {
    lateinit var pager: ViewPager
    internal var name =
        arrayOf("swipeLeft", "Bunglon", "Gajah", "Kambing", "Iguana", "Komodo", "Monyet", "Singa")
    internal var image = intArrayOf(
        R.drawable.swipeleft,
        R.drawable.chameleon,
        R.drawable.elephant,
        R.drawable.goat,
        R.drawable.iguanas,
        R.drawable.komodo,
        R.drawable.monkey,
        R.drawable.lion
    )

    /*Credit Sound : https://www.bensound.com/royalty-free-music/track/instinct*/
    internal var voice = intArrayOf(
        R.raw.bensoundinstinct,
        R.raw.bensoundinstinct,
        R.raw.elephant,
        R.raw.goat,
        R.raw.bensoundinstinct,
        R.raw.bensoundinstinct,
        R.raw.monkey,
        R.raw.lion
    )
    lateinit var adapter: PagerAdapter
    internal var mp: MediaPlayer? = null

    var onPage:
            ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            if (mp != null) {
                //not-null assertion operator !!
                mp!!.reset()
                mp!!.release()
            }
            mp = MediaPlayer.create(this@Play, voice[position])
            mp!!.start()
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_layout)
        getSupportActionBar()?.setTitle("IdentificationofAnimalNames");
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        pager = findViewById(R.id.viewPagerPlay) as ViewPager
        adapter = ImageAdapter(this, image, name)
        pager.adapter = adapter
        pager.setOnPageChangeListener(onPage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val backHome = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backHome);
        return super.onOptionsItemSelected(item);
    }

    private inner class ImageAdapter(
        play: Play,
        internal var image: IntArray,
        internal var name: Array<String>
    ) : PagerAdapter() {
        lateinit var inflater: LayoutInflater
        internal var activity: Activity

        init {
            this.activity = play
        }

        override fun getCount(): Int {
            return image.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object` as ScrollView
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            inflater = activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val show = inflater.inflate(R.layout.item_view_pager, container, false)
            val img = show.findViewById(R.id.img_Animal) as ImageView
            val text = show.findViewById(R.id.txt_Animal) as TextView
            if (position == 0) {
                img.setImageResource(image[position])
                text.setVisibility(TextView.INVISIBLE);
                (container as ViewPager).addView(show)
                return show
            } else {
                img.setImageResource(image[position])
                text.text = name[position]
                (container as ViewPager).addView(show)
                return show
            }
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            (container as ViewPager).removeView(`object` as ScrollView)
        }
    }

}

