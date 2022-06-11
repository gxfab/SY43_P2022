package com.example.noappnogain.model

//import androidx.recyclerview.widget.RecyclerView;
class Data {
    //extends RecyclerView.ViewHolder
    var amount = 0
    var type: String? = null
    var note: String? = null
    var id: String? = null
    var date: String? = null

    constructor(amount: Int, type: String?, note: String?, id: String?, date: String?) {
        this.amount = amount
        this.type = type
        this.note = note
        this.id = id
        this.date = date
    }



    constructor()

    fun Data() {}


    fun getAmountData(): Int {
        return amount
    }

    fun setAmountData(amount: Int) {
        this.amount = amount
    }

    fun getTypeData(): String? {
        return type
    }

    fun setTypeData(type: String?) {
        this.type = type
    }

    fun getNoteData(): String? {
        return note
    }

    fun setNoteData(note: String?) {
        this.note = note
    }

    fun getIdData(): String? {
        return id
    }

    fun setIdData(id: String?) {
        this.id = id
    }

    fun getDateData(): String? {
        return date
    }

    fun setDateData(date: String?) {
        this.date = date
    }
}
