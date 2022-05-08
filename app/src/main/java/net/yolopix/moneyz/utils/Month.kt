package net.yolopix.moneyz.utils

import java.util.Calendar

class Month() : Calendar() {
    fun equals(other: Calendar): Boolean {
        return other.get(Calendar.MONTH) == this.get(Calendar.MONTH)
    }

    override fun computeTime() {
        TODO("Not yet implemented")
    }

    override fun computeFields() {
        TODO("Not yet implemented")
    }

    override fun add(p0: Int, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun roll(p0: Int, p1: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getMinimum(p0: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getMaximum(p0: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getGreatestMinimum(p0: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getLeastMaximum(p0: Int): Int {
        TODO("Not yet implemented")
    }

}