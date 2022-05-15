package com.example.juanmartinezleonitunes

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.juanmartinezleonitunes.Adapter.PageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tab_tablayout: TabLayout
    private lateinit var cordLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        var tab_viewpager = findViewById<ViewPager2>(R.id.tab_viewpager)
        tab_tablayout = findViewById(R.id.tab_tablayout)
        cordLayout = findViewById(R.id.cl_background)
        tab_viewpager.adapter = PageAdapter(this)
        changeTabColors("#000000", "#ce0015", "#fefefe", "#ce0015", R.color.rock_selector_icon, "#1c1200")
        TabLayoutMediator(tab_tablayout, tab_viewpager){ tab, index ->
            tab.text =  when(index) {
                0-> {"Rock"}
                1-> {"Classic"}
                2-> {"Pop"}
                else -> {throw Resources.NotFoundException("not found")}
            }
            tab.icon =  when(index) {
                0 -> { ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_foreground, null)}
                1 -> {
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_foreground, null)}
                2 -> {
                    ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_foreground, null)}
                else -> {throw Resources.NotFoundException("not found")}
            }


        }.attach()

        tab_tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0-> changeTabColors("#000000", "#ce0015", "#fefefe", "#ce0015", R.color.rock_selector_icon, "#1c1200")
                        1-> changeTabColors("#745435", "#000000", "#fefefe", "#000000", R.color.classic_selector_icon, "#cfa162")
                        2-> changeTabColors("#ff6fb5", "#a386da", "#fefefe", "#a386da", R.color.pop_selector_icon, "#ff92a3")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun changeTabColors(backgroundColor: String, unselectedColor: String, selectedColor: String, indicatorColor: String, selector: Int, listBackground: String) {

        tab_tablayout.setBackgroundColor(Color.parseColor(backgroundColor))
        tab_tablayout.setTabTextColors(Color.parseColor(selectedColor), Color.parseColor(unselectedColor))
        tab_tablayout.setSelectedTabIndicatorColor(Color.parseColor(indicatorColor))
        tab_tablayout.setTabIconTintResource(selector)
        cordLayout.setBackgroundColor(Color.parseColor(listBackground))
    }
}

