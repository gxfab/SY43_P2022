package com.example.noappnogain.model

class Budget {
    var montant = 0
    var category: String? = null
    var id: String? = null

    constructor(montant: Int, category: String?, id: String) {
        this.montant = montant
        this.category = category
        this.id = id
    }

    constructor()
}
