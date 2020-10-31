package com.aravi.shelftabssample

import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.aravi.shelftabssample.frament.TagsFragment
import com.aravi.shelftabssample.fragment.TagsListFragment
import com.partner.verticaltabdemo.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var adapter: FragmentPagerAdapter
    var fragments: MutableList<Fragment> = ArrayList<Fragment>()
    private var exitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        bvHomeNavigation.inflateMenu(R.menu.menu_cy_home_bottom_nav)
        bvHomeNavigation.setOnNavigationItemSelectedListener(this)
        fragments.add(HomeFragment())
        fragments.add(TagsFragment())
        fragments.add(TagsListFragment())

        adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(i: Int): Fragment {
                return fragments[i]
            }

            override fun getCount(): Int {
                return fragments.size
            }
        }

        vpHomePager.adapter = adapter
        vpHomePager.offscreenPageLimit = fragments.size
        vpHomePager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                bvHomeNavigation.selectedItemId = position
                bvHomeNavigation.menu.getItem(position).isChecked = true
            }

        })


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.action == KeyEvent.ACTION_DOWN
        ) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showToast(this, "Click again to close")
                exitTime = System.currentTimeMillis()
            } else {
                finish()
                exitProcess(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_index -> {
                vpHomePager.currentItem = 0
                return true
            }
            R.id.home_games -> {
                vpHomePager.currentItem = 1
                return true
            }
            R.id.home_me -> {
                vpHomePager.currentItem = 2
                return true
            }
            else -> {
            }
        }
        return false
    }


}