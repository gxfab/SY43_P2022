package com.example.sy43_p2022.database.dao


// TODO: Add your own imports from MySQL database

class CategoriesDAO {
    constructor() {}

    fun getSubCategoriesOfSpendings(): Array<String> {
        // make request
        return arrayOf("Food", "Services", "Car", "Lodging", "Social Security", "Health", "Hobbies", "Personnal", "Various", "Savings", "Events", "Holidays", "Debts")
    }
}