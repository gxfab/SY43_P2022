package com.example.lafo_cheuse.material

import android.text.InputFilter
import android.text.Spanned

/**
 * Class to create a customize EditText which filter only emojis.
 * Every other symbols will not be printed.
 *
 */
private class EmojiFilter : InputFilter {

    /**
     * Function to filter only emojis when the user type in the EditText.
     *
     * @param source - [CharSequence] corresponding to the text entered by the user
     * @param start - [Int] index of the first symbol entered by user in the [source]
     * @param end - [Int] index of the last symbol entered by the user in the [source]
     * @return a [CharSequence] corresponding to what what was filtered
     */
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        for( i in start until end) {
            val type = Character.getType(source!![i]).toByte()
            if(type != Character.SURROGATE && type != Character.OTHER_SYMBOL) {
                return ""
            }
        }
        return null
    }
}