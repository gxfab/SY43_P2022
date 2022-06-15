package com.example.lafo_cheuse.material

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter to implement a ViewPager2 on the MainActivity menu.
 *
 *
 * @param list - a list [Fragment] to indicates which fragment the viewPager should make focus on
 * @param fm - [FragmentManager]
 * @param lifecycle - [Lifecycle] that the adapter should follow
 */
class ViewPagerAdapter(
    list: ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm,lifecycle) {

    private val fragmentList : ArrayList<Fragment> = list

    /**
     * Callback function called to know the number of item to switch between
     *
     * @return
     */
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    /**
     * Callback function called to create a fragment in the list of fragment (create = display it)
     *
     * @param position - [Int] position of the fragment in [fragmentList]
     * @return a [Fragment] at position [position] in [fragmentList]
     */
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}