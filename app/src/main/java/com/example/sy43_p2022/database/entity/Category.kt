package com.example.sy43_p2022.database.entity

class Category {
    private var subCategories = emptyList<Category>();
    private var name = "";
    private var objectiveAmount = 0;

    constructor(name: String) {
        this.name = name;
    }

    fun setObjectiveAmount(amount: Int) {
        this.objectiveAmount = amount;
    }

    fun addSubCategory(category: Category) {
        subCategories = subCategories.plus(category);
    }

    fun setSubCategories(subCategories: List<Category>) {
        this.subCategories = subCategories;
    }

    fun getSubCategories(position: Int): Category {
        return subCategories.elementAt(position);
    }

    fun getGlobalObjectiveAmount(): Int {
        var amount = 0;
        for (subCategory in subCategories) {
            amount += subCategory.getGlobalObjectiveAmount();
        }
        return amount;
    }

    fun getName(): String {
        return name;
    }
}