package com.example.lafo_cheuse.material

import android.text.InputFilter
import android.text.Spanned

private class EmojiFilter : InputFilter {

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
                return "";
            }
        }
        return null;
    }
}