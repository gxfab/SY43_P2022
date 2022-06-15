package com.example.noappnogain.ui.statistique

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.noappnogain.BalanceAnnuel
import com.example.noappnogain.BalanceMensuel
import com.example.noappnogain.R
import com.example.noappnogain.databinding.FragmentStatistiqueBinding
import com.example.noappnogain.model.Data
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
        val btnMensuel: Button = binding.buttonMensuel
        val btnAnnuel: Button = binding.buttonAnnuel
        val btnFiltre: Button = binding.buttonFiltre
        var pieArrayList: ArrayList<PieEntry>

        pieChart = binding.activityMainPiechart
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth?.currentUser

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
        spinnerAnnee?.setSelection(Adapter.NO_SELECTION, true)
        spinnerAnnee?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                annee = spinnerAnnee?.selectedItem.toString().trim { it <= ' ' }
                posAnnee = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                annee = "0"
            }
        }
        spinnerMois?.setSelection(Adapter.NO_SELECTION, true)
        spinnerMois?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                mois = position.toString()
                posMois = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                mois = "0"
            }
        }

        initPieChart()

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pieArrayList = ArrayList<PieEntry>()
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    val categorie_depense : Array<String> = arrayOf("Alimentation", "Animaux", "Cadeaux offerts", "Education", "Enfants",
                        "Epargne", "Habillement", "Impôts", "Intérêts dette", "Inventissement", "Loisirs", "Ménage", "Santé", "Transport", "Logement")
                    for(cat in categorie_depense){
                        var amount = 0
                        for (userSnapshot in snapshot.children) {
                            val data: Data? = userSnapshot.getValue(Data::class.java)
                            if (data != null) {
                                if (data.amount < 0) {
                                    if (data.type == cat) {
                                        amount -= data.amount
                                    }
                                }
                            }
                        }
                        if(amount != 0){
                            pieArrayList.add(PieEntry(abs(amount).toFloat(), cat))
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
                    data.setDrawValues(true)
                    data.setValueTextSize(10f)
                    pieChart.setExtraOffsets(5f, 0f, 5f, 5f)
                    pieChart.animateY(1400, Easing.EaseInOutQuad)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        mMouvementDatabase?.addListenerForSingleValueEvent(valueEventListener)


        btnFiltre.setOnClickListener({
            filtreData()
        })

        btnMensuel.setOnClickListener({
            val intent = Intent(activity, BalanceMensuel::class.java)
            val mDate: String
            mDate = "".plus(annee).plus("/").plus(mois)
            intent.putExtra("mDate", mDate )
            startActivity(intent)
        })

        btnAnnuel.setOnClickListener({
            val intent = Intent(activity, BalanceAnnuel::class.java)
            val mDate: String
            mDate = "".plus(annee)
            intent.putExtra("mDate", mDate )
            startActivity(intent)
        })

        return root
    }

    private fun initPieChart() {

        pieChart.setUsePercentValues(true)
        pieChart.description.text = ""
        //hollow pie chart
        pieChart.isDrawHoleEnabled = true
        pieChart.setTouchEnabled(true)
        //adding padding
        pieChart.setExtraOffsets(100f, 100f, 100f, 0f)
        pieChart.setUsePercentValues(true)
        pieChart.isRotationEnabled = true
        pieChart.setDrawEntryLabels(false)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.setEntryLabelTextSize(15f)
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
        pieChart.legend.isWordWrapEnabled = true
        pieChart.setHoleColor(Color.WHITE)
        pieChart.setDrawCenterText(true)
        pieChart.centerText = "Pourcentage de dépenses par catégorie"

    }


    fun filtreData(){

        initPieChart()
        var pieArrayList: ArrayList<PieEntry>

        var withMonth = false
        var onlyYear = false

        if(posAnnee == 0 && posMois == 0){
            onlyYear = false
            withMonth = false
        }
        if(posAnnee > 0 && posMois == 0){
            onlyYear = true
            withMonth = false
        }
        if(posAnnee == 0 && posMois > 0){
            onlyYear = false
            withMonth = false
        }
        if(posAnnee > 0 && posMois > 0){
            onlyYear = true
            withMonth = true
        }

        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pieArrayList = ArrayList<PieEntry>()
                mouvementArrayList = arrayListOf<Data>()
                if (snapshot.exists()) {
                    val categorie_depense : Array<String> = arrayOf("Alimentation", "Animaux", "Cadeaux offerts", "Education", "Enfants",
                        "Epargne", "Habillement", "Impôts", "Intérêts dette", "Inventissement", "Loisirs", "Ménage", "Santé", "Transport", "Logement")
                    for(cat in categorie_depense){
                        var amount = 0
                        for (userSnapshot in snapshot.children) {
                            val data: Data? = userSnapshot.getValue(Data::class.java)
                            if (data != null) {
                                if (data.amount < 0) {
                                    var mDate = ""
                                    if(onlyYear && withMonth){
                                        mDate = "".plus(annee).plus("/").plus(mois)
                                        println("mDate" + mDate)
                                    }
                                    if(onlyYear && !withMonth){
                                        mDate = "".plus(annee)
                                        println("mDate" + mDate)
                                    }
                                    if(onlyYear){
                                        if(withMonth){
                                            if(data.date!!.startsWith(mDate)){
                                                if (data.type == cat) {
                                                    amount -= data.amount
                                                }
                                            }
                                        }else{
                                            if(data.date!!.startsWith(mDate)){
                                                if (data.type == cat) {
                                                    amount -= data.amount
                                                }
                                            }
                                        }
                                    }else{
                                        if (data.type == cat) {
                                            amount -= data.amount
                                        }
                                    }
                                }
                            }
                        }
                        if(amount != 0){
                            pieArrayList.add(PieEntry(abs(amount).toFloat(), cat))
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
                    pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
                    data.setValueTextSize(15f)
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


