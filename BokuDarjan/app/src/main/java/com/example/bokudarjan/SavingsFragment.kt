package com.example.bokudarjan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bokudarjan.category.ListAdapterCategory
import com.example.bokudarjan.envelope.EnvelopeViewModel
import com.example.bokudarjan.saving.ListAdapterSaving
import com.example.bokudarjan.saving.Saving
import com.example.bokudarjan.saving.SavingViewModel
import kotlinx.android.synthetic.main.fragment_savings.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SavingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var savingViewModel : SavingViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        savingViewModel = ViewModelProvider(this).get(SavingViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_savings, container, false)

        var test = listOf<Saving>(Saving("Acheter une voiture", 1000f), Saving("Maison", 100f));

        var savingAdapter = ListAdapterSaving()
        view.recylclerSavings.adapter = savingAdapter;
        view.recylclerSavings.layoutManager = LinearLayoutManager(requireContext())

        savingViewModel.readAllData.observe(viewLifecycleOwner, Observer { saving ->
            savingAdapter.setData(saving)
        })

        view.expandButton3.setOnClickListener {
            var dialog = AddSavingDialog()
            dialog.show(childFragmentManager, "")
        }

        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SavingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}