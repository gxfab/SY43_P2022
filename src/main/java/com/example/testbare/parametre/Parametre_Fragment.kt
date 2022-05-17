package com.example.testbare.parametre

import android.os.Bundle
import android.preference.PreferenceFragment
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.preference.*
import com.example.testbare.R


class Parametre_Fragment :  PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.teste, rootKey)

        val sw_mdp: SwitchPreference? = findPreference("sw_mdp")
        val et_mdp: EditTextPreference? = findPreference("et_mdp")

        if(sw_mdp?.isChecked == true){
            if (et_mdp != null) {
                et_mdp.isVisible = true
            }
        }
        sw_mdp?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener{pref,newValue ->
                Log.e("teste" , newValue.toString())

                    if (newValue as Boolean) {
                        if (et_mdp != null) {
                            et_mdp.isVisible = true
                        }
                    }else{
                        if (et_mdp != null) {
                            et_mdp.isVisible = false
                        }
                    }

                true


            }
    }
}