package com.example.lafo_cheuse.material

import java.util.*

/**
 * Data class to embed a date
 *
 * @property year - [Int] corresponding to the year of the date
 * @property month - [Int] corresponding to the year of the date (0 to 11)
 * @property day - [Int] corresponding to the year of the date (0 to 30)
 */
data class DatabaseDate(
    val year : Int,
    val month : Int,
    val day : Int
) {}