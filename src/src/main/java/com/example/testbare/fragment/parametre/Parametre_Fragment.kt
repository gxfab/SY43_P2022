package com.example.testbare.fragment.parametre

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.preference.*
import com.example.testbare.database.AppDatabase
import com.example.testbare.database.entities.Utilisateur
import kotlinx.coroutines.launch
import com.example.testbare.R


class Parametre_Fragment :  PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.teste,rootKey)

        val sw_mdp: SwitchPreference? = findPreference("sw_mdp")
        val et_mdp: EditTextPreference? = findPreference("et_mdp")
        val sw_theme : SwitchPreference? = findPreference("sw_theme")

        if (sw_mdp?.isChecked == true) {
            if (et_mdp != null) {
                et_mdp.isVisible = true
            }
        }
        sw_mdp?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { pref, newValue ->
                if (newValue as Boolean) {
                    if (et_mdp != null) {
                        et_mdp.isVisible = true
                        lifecycleScope.launch {
                            val utilisateurDao = AppDatabase.getDatabase(requireContext()).UtilisateurDao()
                            if(utilisateurDao.existUser() > 0)
                                utilisateurDao.updateMdpActif(true)
                        }
                    }
                } else {
                    if (et_mdp != null) {
                        et_mdp.isVisible = false
                        lifecycleScope.launch {
                            val utilisateurDao = AppDatabase.getDatabase(requireContext()).UtilisateurDao()
                            if(utilisateurDao.existUser() > 0)
                                utilisateurDao.updateMdpActif(false)
                        }
                    }
                }
                true
            }
        et_mdp?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { pref, newValue ->
                val utilisateurDao = AppDatabase.getDatabase(requireContext()).UtilisateurDao()
                lifecycleScope.launch {
                    if (utilisateurDao.existUser() != 0)
                        utilisateurDao.updateMdp(newValue as String)
                    else
                        utilisateurDao.insertUser(Utilisateur("mail", newValue as String, true))
                    Toast.makeText(requireContext(), "Mot de passe mis à jour", Toast.LENGTH_SHORT).show()
                }
                true
            }
        sw_theme?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener{pref, newValue ->
                var newConfig : Configuration = Configuration()
                newConfig.setToDefaults()
                if(newValue as Boolean){
                    newConfig.uiMode = Configuration.UI_MODE_NIGHT_YES
                    Log.e("Thème : ", "changé en dark")
                }else{
                    newConfig.uiMode = Configuration.UI_MODE_NIGHT_NO
                    Log.e("Thème : ", "changé en clair")
                }
                super.onConfigurationChanged(newConfig)

                true
            }

    }
}