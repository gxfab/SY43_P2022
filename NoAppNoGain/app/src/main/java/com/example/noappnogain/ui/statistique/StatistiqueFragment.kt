package com.example.noappnogain.ui.statistique

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.noappnogain.BalanceAnnuel
import com.example.noappnogain.BalanceMensuel
import com.example.noappnogain.databinding.FragmentStatistiqueBinding
import com.example.noappnogain.model.Data
import com.example.noappnogain.ui.budget.BudgetFragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class StatistiqueFragment : Fragment() {

    private var _binding: FragmentStatistiqueBinding? = null
    private var mAuth: FirebaseAuth? = null
    private var mMouvementDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null

    private lateinit var pieArrayList: ArrayList<PieEntry>
    private lateinit var mouvementArrayList: java.util.ArrayList<Data>
    private lateinit var pieChart : PieChart

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

        pieChart = binding.activityMainPiechart
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

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

        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mMouvementDatabase =
                FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)

        }

        mMouvementDatabase?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data: Data? = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount < 0) {
                                mouvementArrayList.add(data!!)
                            }
                        }
                    }
                    // call PieChart
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        initPieChart()
        setDataToPieChart()

        return root
    }

    private fun initPieChart() {
        pieChart.setUsePercentValues(true)
        pieChart.description.text = "Here"
        //hollow pie chart
        pieChart.isDrawHoleEnabled = false
        pieChart.setTouchEnabled(false)
        pieChart.setDrawEntryLabels(false)
        //adding padding
        pieChart.setExtraOffsets(100f, 0f, 100f, 100f)
        pieChart.setUsePercentValues(true)
        pieChart.isRotationEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.isWordWrapEnabled = true

    }

    private fun setDataToPieChart() {

        pieChart.setUsePercentValues(true)

        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(72f, "Android"))
        dataEntries.add(PieEntry(26f, "Ios"))
        dataEntries.add(PieEntry(2f, "Other"))

        //add color
        val colors = java.util.ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        pieChart.data = data
        data.setValueTextSize(15f)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        pieChart.animateY(1400, Easing.EaseInOutQuad)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


