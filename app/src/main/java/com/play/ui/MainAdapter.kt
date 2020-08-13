package com.play.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.play.ui.dashboard.DashboardFragment
import com.play.ui.detail.DetailFragment
import com.play.ui.login.LoginFragment

class MainAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    private var pageCount: Int = 0

    fun setCount(count: Int){
        pageCount = count;
    }

    override fun getItemCount(): Int = pageCount

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return LoginFragment()
            }
            1 -> {
                return DashboardFragment()
            }
            2 -> {
                return DetailFragment()
            }
        }

        return LoginFragment();
    }
}