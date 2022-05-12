package fr.hplovers.moneysaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.hplovers.moneysaver.fragments.HomeFragment
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //injection du fragment dans la partie qui change (fragment_container)
        val trans = supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragment_container,HomeFragment())
        trans.addToBackStack(null)
        trans.commit()
    }
}