package com.example.lafo_cheuse.material

import android.content.Context
import android.util.AttributeSet

public class FilteredEditText : androidx.appcompat.widget.AppCompatEditText {

    constructor(context: Context, attrs: AttributeSet?, defStyle:Int):super(context,attrs,defStyle)
    {
    }

    constructor(context:Context, attrs: AttributeSet?):super(context,attrs)
    {
    }

    constructor(context:Context) : super(context) {
    }

    init{
        filters = arrayOf(EmojiFilter())
    }

}