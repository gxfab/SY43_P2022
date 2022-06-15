package net.yolopix.moneyz

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class PrevisionStepsAdapter : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        return collection.findViewById(
            when (position) {
                0 -> R.id.layout_salary
                1 -> R.id.layout_bills
                2 -> R.id.layout_end_previsions
                else -> 0
            }
        )
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        return 3
    }

    /**
     * Determines whether a page View is associated with a specific key object
     * as returned by [.instantiateItem]. This method is
     * required for a PagerAdapter to function properly.
     *
     * @param view Page View to check for association with `object`
     * @param object Object to check for association with `view`
     * @return true if `view` is associated with the key object `object`
     */
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }
}