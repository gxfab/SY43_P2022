package net.yolopix.moneyz.adapters.pager

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import net.yolopix.moneyz.R

class PrevisionStepsAdapter : PagerAdapter() {

    companion object {
        /** The list of views to display as pages */
        val viewList: List<Int> = listOf(
            R.id.layout_salary,
            R.id.layout_bills,
            R.id.layout_envelopes,
            R.id.layout_sinking_funds,
            R.id.layout_extra_debt,
            R.id.layout_extra_savings,
            R.id.layout_end_previsions
        )
    }

    /**
     * Create the page for the given position.  The adapter is responsible
     * for adding the view to the container given here, although it only
     * must ensure this is done by the time it returns from
     * [.finishUpdate].
     *
     * @param container The containing View in which the page will be shown.
     * @param position The page position to be instantiated.
     * @return Returns an Object representing the new page.  This does not
     * need to be a View, but can be some other container of the page.
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return container.findViewById(viewList[position])
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        return viewList.size
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

    /**
     * Remove a page for the given position.  The adapter is responsible
     * for removing the view from its container, although it only must ensure
     * this is done by the time it returns from [.finishUpdate].
     *
     * @param container The containing View from which the page will be removed.
     * @param position The page position to be removed.
     * @param object The same object that was returned by
     * [.instantiateItem].
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }
}