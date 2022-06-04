package net.yolopix.moneyz

import android.text.InputFilter
import android.text.Spanned

/**
 * This is an input filter to restrict numeric values (decimal or integer) to a [min, max] range
 * @param min the lower bound
 * @param max the upper bound, can be null to disable the max limit
 */
class NumberRangeInputFilter(private var min: Number = 0, private var max: Number) :
    InputFilter {

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
            val minFloat = min.toFloat()
            val maxFloat = max.toFloat()
            // If the number is in range
            if (input in minFloat..maxFloat)
                return null
            // Allow user to type while the min has not been reached
            // (only 1-digit numbers can be reached in a single character)
            if (dest.length < min.toInt().toString().length && min.toInt().toString().length > 1)
                return null
        } catch (e: NumberFormatException) {
        }
        return ""
    }
}