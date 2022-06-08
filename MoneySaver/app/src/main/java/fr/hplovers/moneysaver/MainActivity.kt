package fr.hplovers.moneysaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.hplovers.moneysaver.fragments.HomeFragment
import java.sql.Types.NULL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Charger notre MouvementFinancierRepo
        val mouvRepo = MouvementFinancierRepo()

        // Mise à jour des données
        mouvRepo.updateData{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }
}