package com.example.lafo_cheuse.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HasFields {
    @PrimaryKey
    @Embedded(prefix = "option_")
    var option: Option? = null

    @PrimaryKey
    @Embedded(prefix = "field_")
    var field: OptionField? = null

    constructor(option : Option?, field : OptionField?) {
        this.option = option
        this.field = field
    }
}