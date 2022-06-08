package fr.sy.lebudgetduzero

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public class IncomeItem (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "Income",
    val value: Float = 0F,
    val date: String = "01/01/2022")