package fr.sy.lebudgetduzero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_overview)
    }

    fun setHomeView(view: View){
        setContentView(R.layout.view_overview)
    }

    fun setBalanceView(view: View){
        setContentView(R.layout.view_balance)
    }

    fun setEntreeView(view: View){
        setContentView(R.layout.view_entrees)
    }

    fun setDepenseView(view: View){
        setContentView(R.layout.view_depense)
    }

}