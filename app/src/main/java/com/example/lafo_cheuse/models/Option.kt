package com.example.lafo_cheuse.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Option {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var optionId: Int = 0

    @ColumnInfo(name = "description")
    var optionDescription: String = ""

    @ColumnInfo(name = "type")
    var type: OptionType = OptionType.CHECKBOX

    @ColumnInfo(name = "values")
    var values: ArrayList<OptionField>? = null

    fun optionChosen(): OptionField? {
        var chosenOne : OptionField? = null
        for(opt in values!!) {
            if(opt.chosen) {
                chosenOne = opt
                break
            }
        }
        return chosenOne
    }
}