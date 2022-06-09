package fr.sy.lebudgetduzero.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public class SpentItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "Income",
    val value: Float = 0F,
    val id_type: Int =0,
    val date: Int = 1640995200
)