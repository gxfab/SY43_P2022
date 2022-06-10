package com.example.lafo_cheuse.fragment.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.lafo_cheuse.R
import org.w3c.dom.Text


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_settings, container, false)
        val optionIcon : TextView = view.findViewById(R.id.optionIcon)
        val optionGithub : TextView = view.findViewById(R.id.optionGithub)

        optionIcon.movementMethod = LinkMovementMethod.getInstance()
        optionGithub.movementMethod = LinkMovementMethod.getInstance()

        return view
    }
}