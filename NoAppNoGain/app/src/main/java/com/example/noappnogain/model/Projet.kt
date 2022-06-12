package com.example.noappnogain.model

class Projet {

    var actualAmount = 0
    var totalAmount = 0
    var isFinished: Boolean? = false
    var name: String? = null
    var id: String? = null
    var date: String? = null

    constructor(actualAmount: Int, totalAmount: Int, isFinished: Boolean, name: String?, id: String?, date: String?) {
        this.actualAmount = actualAmount
        this.totalAmount = totalAmount
        this.isFinished = isFinished
        this.name = name
        this.id = id
        this.date = date
    }

    constructor()

}