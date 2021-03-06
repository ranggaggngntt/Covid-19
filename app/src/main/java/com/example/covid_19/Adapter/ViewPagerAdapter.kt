package com.example.covid_19.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.covid_19.Fragment.CountryFragment
import com.example.covid_19.Fragment.HomeFragment
import com.example.covid_19.Fragment.AboutFragment
import com.example.covid_19.Fragment.ProvinsiFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                CountryFragment()
            }
            2 -> {
                ProvinsiFragment()
            }
            3 -> {
                AboutFragment()
            }
            else -> {
                ProvinsiFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}