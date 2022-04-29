package com.example.sy43_p2022.database.dao


// TODO: Add your own imports from MySQL database

class CategoriesDAO {
    constructor() {}

    fun getSubCategories(): Array<String> {
        // make request
        return arrayOf("Food", "Services", "Car", "Lodging", "Social Security", "Health", "Hobbies", "Personal", "Various", "Savings", "Events", "Holidays", "Debts")
    }

    fun getSubSubCategories(subCategory: String): Array<String> {
        when (subCategory) {
            "Food" -> return arrayOf("Grocery Shopping", "Restaurants", "Bars", "Uber Eats/Deliveroo")
            "Services" -> return arrayOf("Mobile Phone", "Internet", "Streaming")
            "Car" -> return arrayOf("Insurance", "Gasoline", "Reparations", "Parking")
            "Lodging" -> return arrayOf("Rent", "Insurance", "Electricity", "Local Taxes")
            "Social Security" -> return arrayOf("Life Insurance", "Disability Insurance", "Retirement", "Pension")
            "Health" -> return arrayOf("Doctor", "Drugstore")
            "Hobbies" -> return arrayOf("Sports", "Movie Theater")
            "Personal" -> return arrayOf("Books", "Video Games")
            "Various" -> return arrayOf("Unexpected Expenses")
            "Savings" -> return arrayOf("Christmas", "Gifts")
            "Events" -> return arrayOf("Birthday", "Parties")
            "Holidays" -> return arrayOf("Travel", "Week-ends")
            "Debts" -> return arrayOf("Loans")
            else -> return arrayOf("???")
        }


    }
}