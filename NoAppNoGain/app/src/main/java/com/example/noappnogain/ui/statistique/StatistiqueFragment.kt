package com.example.noappnogain.ui.statistique

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.noappnogain.BalanceAnnuel
import com.example.noappnogain.BalanceMensuel
import com.example.noappnogain.R
import com.example.noappnogain.adapter.HomeAdapter
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
import java.lang.Math.abs

class StatistiqueFragment : Fragment() {

    private var _binding: FragmentStatistiqueBinding? = null
    private var mAuth: FirebaseAuth? = null
    private var mMouvementDatabase: DatabaseReference? = null
    private var mUser: FirebaseUser? = null

    private lateinit var mouvementArrayList: java.util.ArrayList<Data>
    private lateinit var pieArrayList : ArrayList<PieEntry>
    private lateinit var pieChart : PieChart

    private var spinnerAnnee : Spinner? = null
    private var spinnerMois : Spinner? = null

    var annee = "0"
    var mois =  "0"
    var categorie = "0"
    var posAnnee = 0
    var posMois = 0


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

        btnMensuel.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, BalanceMensuel::class.java)
            startActivity(intent)
        })

        btnAnnuel.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, BalanceAnnuel::class.java)
            startActivity(intent)
        })


        if (mAuth!!.currentUser != null) {
            val uid = mUser!!.uid
            mMouvementDatabase =
                FirebaseDatabase.getInstance().reference.child("MouvementData").child(uid)
        }

        spinnerAnnee = binding.spinnerAnnee
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.annee,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAnnee!!.adapter = adapter
        }
        spinnerMois = binding.spinnerMois
        ArrayAdapter.createFromResource(
            activity!!,
            R.array.mois,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMois!!.adapter = adapter
        }

        var category = ArrayList<String>()
        initPieChart()
        val pieArrayList = ArrayList<PieEntry>()
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data: Data? = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount < 0) {
                                pieArrayList!!.add(PieEntry(abs(data.amount).toFloat(), data.type.toString()))
                            }
                        }
                    }
                    val colors = java.util.ArrayList<Int>()
                    for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
                    for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
                    for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
                    for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
                    for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

                    val dataSet = PieDataSet(pieArrayList, "")
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
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        mMouvementDatabase?.addListenerForSingleValueEvent(valueEventListener)


        val btnFiltre: Button = binding.buttonFiltre
        btnFiltre.setOnClickListener(View.OnClickListener {
            filtreData()
        })

        return root
    }

    private fun initPieChart() {

        pieChart.setUsePercentValues(true)
        pieChart.description.text = ""
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


    fun filtreData(){

        initPieChart()
        val pieArrayList = ArrayList<PieEntry>()

        spinnerAnnee?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                annee = spinnerAnnee!!.selectedItem.toString().trim { it <= ' ' }
                posAnnee = position
                println("posAnnee" + position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                annee = "0"
            }
        })

        spinnerMois?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                mois = position.toString()
                posMois = position
                println("posMois" + position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                mois = "0"
            }
        })


        var tous : Boolean = false
        var month : Boolean = false
        var year : Boolean = false

        if(posAnnee == 0 && posMois == 0){
            tous = true
            year = true
            month = true
        }
        if(posAnnee > 0 && posMois == 0){
            tous = true
            year = false
            month = true
        }
        if(posAnnee == 0 && posMois > 0){
            tous = true
            year = true
            month = false
        }
        if(posAnnee > 0 && posMois > 0){
            tous = false
            year = false
            month = false
        }

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val data: Data? = userSnapshot.getValue(Data::class.java)
                        if (data != null) {
                            if (data.amount < 0) {
                                var mDate = ""
                                if(!year && !month){
                                    mDate = "/".plus(mois).plus("/").plus(annee)
                                    println("mDate" + mDate)
                                }
                                if(!tous){
                                    if(!year){
                                        if(!month){
                                            if(data.date!!.endsWith(mDate)){
                                                pieArrayList!!.add(PieEntry(abs(data.amount).toFloat(), data.type.toString()))
                                            }
                                        }
                                    }else{
                                        if(data.date!!.endsWith(annee)){
                                            pieArrayList!!.add(PieEntry(abs(data.amount).toFloat(), data.type.toString()))
                                        }
                                    }
                                }else{
                                    pieArrayList!!.add(PieEntry(abs(data.amount).toFloat(), data.type.toString()))
                                }
                            }
                        }
                    }
                    val colors = java.util.ArrayList<Int>()
                    for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
                    for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
                    for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
                    for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
                    for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)

                    val dataSet = PieDataSet(pieArrayList, "")
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
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        mMouvementDatabase?.addListenerForSingleValueEvent(valueEventListener)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


