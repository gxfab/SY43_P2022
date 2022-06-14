package net.yolopix.moneyz.utils

import android.text.InputFilter
import android.text.Spanned

/**
 * This is an input filter to restrict numeric values (decimal or integer) to a [min, max] range
 * @param max the upper bound, can be null to disable the max limit
 */
class NumberMaxInputFilter(private var max: Number) : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = (dest.toString() + source.toString()).toFloat()
            val maxFloat = max.toFloat()
            // If the number is in range
            if (input <= maxFloat) return null
        } catch (e: NumberFormatException) {
        }
        return ""
    }

}