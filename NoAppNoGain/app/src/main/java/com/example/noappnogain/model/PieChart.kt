package com.example.noappnogain.model

class PieChart {
    var montant = 0
    var category: String? = null

    constructor(montant: Int, category: String?) {
        this.montant = montant
        this.category = category
    }

    constructor()
}