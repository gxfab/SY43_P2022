package com.example.testbare

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.testbare.database.AppDatabase
import com.example.testbare.fragment.parametre.Parametre_Fragment
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import android.text.TextWatcher as TextWatcher1


class Login : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var mdpStocke: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login);
        val mSettingsFragment = Parametre_Fragment()
        var mMDP_ET: EditText = findViewById(R.id.et_password)
        var mMDP_BTN: Button = findViewById(R.id.btn_submit)
        var mMDP_Forgot_BTN : Button = findViewById(R.id.btn_forgot)
        var MMDP_Message_TV : TextView = findViewById(R.id.message_tv)
        MMDP_Message_TV.isVisible = false

        get_MDP()




        mMDP_BTN.setOnClickListener {
            if(mMDP_ET.text.toString().equals(mdpStocke) && !mdpStocke.equals("null") ){
                finish()
                MMDP_Message_TV.isVisible = false
            }else{
                MMDP_Message_TV.text = "Mauvais Mot De Passe "
                MMDP_Message_TV.isVisible = true
            }
        }
        mMDP_Forgot_BTN.setOnClickListener {
            MMDP_Message_TV.text = "Email Envoyé"
            MMDP_Message_TV.isVisible = true
        }


        mMDP_ET.addTextChangedListener(object : TextWatcher1 {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    if("$errString" == "No fingerprints enrolled."){

                    }else{
                        Toast.makeText(applicationContext,
                            "Authentication error : $errString", Toast.LENGTH_SHORT)
                            .show()
                    }

                    Log.e("authen", "$errString")
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentication Réussi !", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication Echoué",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authentification Biométrique")
            .setSubtitle(" ")
            .setNegativeButtonText(" ")
            .build()

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.



    }
    override fun onBackPressed() {  //permet d'empcercher le retour
    }

    fun get_MDP() {
        val utilisateurDao = AppDatabase.getDatabase(MainActivity()).UtilisateurDao()
        lifecycleScope.launch {
            if (utilisateurDao.existUser() != 0) {
                if(!utilisateurDao.isMdpActif())
                    finish()
                else{
                    mdpStocke = utilisateurDao.getMdp()
                    if (mdpStocke.equals(""))
                        finish()
                    else
                        biometricPrompt.authenticate(promptInfo)
                }
            }else
                finish()
        }
    }
}