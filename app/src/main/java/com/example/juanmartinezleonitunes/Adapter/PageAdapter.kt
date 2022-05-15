package com.example.juanmartinezleonitunes.Adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.juanmartinezleonitunes.Fragments.MusicListFragment

class PageAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {

        //1 -> rock
        //2 -> classic
        //3 -> pop
        return when (position) {
            0 -> {
                MusicListFragment.newInstance(1)
            }
            1 -> {
                MusicListFragment.newInstance(2)
            }
            2 -> {
                MusicListFragment.newInstance(3)
            }
            else -> {
                throw Resources.NotFoundException("not found")
            }
        }
    }
}

