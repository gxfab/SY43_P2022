package fr.sy.lebudgetduzero.item

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
public class TypeItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "Default",
    val value_for_month: Int =0,
    val value_for_lastmonth: Int=0
)