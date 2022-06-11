package com.example.noappnogain.ui.statistique

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.noappnogain.BalanceAnnuel
import com.example.noappnogain.BalanceMensuel
import com.example.noappnogain.databinding.FragmentStatistiqueBinding
import com.example.noappnogain.ui.budget.BudgetFragment

class StatistiqueFragment : Fragment() {

    private var _binding: FragmentStatistiqueBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStatistiqueBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnMensuel: Button = binding.buttonMensuel
        val btnAnnuel: Button = binding.buttonAnnuel
        val btnBudget: Button = binding.buttonBudget

        btnMensuel.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, BalanceMensuel::class.java)
            startActivity(intent)
        })

        btnAnnuel.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, BalanceAnnuel::class.java)
            startActivity(intent)
        })

        btnBudget.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, BudgetFragment::class.java)
            startActivity(intent)
        })

        btnBudget.setOnClickListener(View.OnClickListener {

        })


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


