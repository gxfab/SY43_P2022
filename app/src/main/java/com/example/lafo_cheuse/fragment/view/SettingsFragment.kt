package com.example.lafo_cheuse.fragment.view

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.example.lafo_cheuse.R
import com.example.lafo_cheuse.models.Option
import com.example.lafo_cheuse.models.OptionField
import com.example.lafo_cheuse.viewmodels.OptionViewModel

/**
 * Fragment where the options and references are displayed.
 * We store this options in our database to retrieve them on launch.
 * It is not completely functional.
 *
 * @property optionViewModel - An instance of [OptionViewModel]
 * @property optionTheme - [Option] where the current theme of graphical theme (dark, light, system) is stored
 * @property optionNotification - [Option] where the current notifications chosen are stored
 * @property optionNotificationSum - [Option] where the current sum of one of the notification is stored
 * @property optionBudget - [Option] where the day to start the new budget is stored
 *
 */
class SettingsFragment : Fragment() {
    val optionViewModel : OptionViewModel by viewModels()
    var optionTheme : Option? = null
    var optionNotification : Option? = null
    var optionNotificationSum : Option? = null
    var optionBudget : Option? = null

    /**
     * Initialization of the view. We will link our widgets to the database using this function.
     * Then, widgets are initialized in other functions.
     *
     * @param inflater - [LayoutInflater] which contained our layout file
     * @param container
     * @param savedInstanceState
     * @return a [View] : the fragment view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view : View = inflater.inflate(R.layout.fragment_settings, container, false)
        val optionIcon : TextView = view.findViewById(R.id.optionIcon)
        val optionGithub : TextView = view.findViewById(R.id.optionGithub)
        val optionSpinner : Spinner = view.findViewById(R.id.option_spinner)
        val themeSetter : RadioGroup = view.findViewById(R.id.option_radiobutton)
        val optionNextIncomeNotification : CheckBox = view.findViewById(R.id.option_next_income_notification)
        val optionBelowSumNotification : CheckBox = view.findViewById(R.id.option_below_sum_notification)

        optionViewModel.getOptions()?.observe(viewLifecycleOwner) { optionList ->
            for(option in optionList) {
                when(option.optionDescription) {
                    "option_theme" -> {
                        optionTheme = option
                    }
                    "option_notifications" -> {
                        optionNotification = option
                    }
                    "option_notification_sum" -> {
                        optionNotificationSum = option
                    }
                    "option_budget" -> {
                        optionBudget = option
                    }
                }
            }
            setupSpinner(optionSpinner, optionBudget!!)
            setupThemeSetter(themeSetter,optionTheme!!)
            setupNotificationSetter(optionNextIncomeNotification,optionBelowSumNotification, optionNotification!!)
        }

        optionIcon.movementMethod = LinkMovementMethod.getInstance()
        optionGithub.movementMethod = LinkMovementMethod.getInstance()


        return view
    }

    /**
     * Function to setup the spinner button.
     * This spinner allows the user to choose a date to start a new budget each month
     *
     * @param spinner - a [Spinner] which contains integer from 1 to 31
     * @param option - an [Option] to link with the [spinner]. Here it is the [optionBudget]
     */
    private fun setupSpinner(spinner : Spinner, option : Option) {
        val day_list : Array<Int> = (1..31).toList().toTypedArray()
        val adapter : ArrayAdapter<Int> = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item,
            day_list)
        spinner.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        optionViewModel.getOptionFields(option)?.observe(viewLifecycleOwner) { fields ->
            val valueChosen = fields[0]
            val spinnerListener = SpinnerAdapter(optionViewModel,valueChosen)
            spinner.setSelection(valueChosen.fieldValue!!.toInt()-1)

            spinner.onItemSelectedListener = spinnerListener
        }

    }

    /**
     * Initialization of radio group which manage the theme of our application
     *
     * @param radioGroup - a [RadioGroup] with the possibilities light, dark or system
     * @param option - The [Option] the [radioGroup] will be linked with. Here it is the [optionTheme]
     */
    private fun setupThemeSetter(radioGroup : RadioGroup, option : Option) {
        var lightField : OptionField? = null
        var darkField : OptionField? = null
        var systemField : OptionField? = null
        val light : RadioButton = radioGroup.findViewById(R.id.option_light_theme)
        val dark : RadioButton = radioGroup.findViewById(R.id.option_dark_theme)
        val system : RadioButton = radioGroup.findViewById(R.id.option_system_theme)
        Log.d("option", option.optionDescription)
        optionViewModel.getOptionFields(option)?.observe(viewLifecycleOwner) { optionFields ->
            Log.d("field", optionFields.toString())
            for(field in optionFields) {

                when(field.fieldValue) {
                    "light_theme" -> {
                        lightField = field
                        if(field.chosen) {

                            light.isChecked = true
                            changeApplicationTheme(AppCompatDelegate.MODE_NIGHT_NO)
                        }
                    }
                    "dark_theme" -> {
                        darkField = field
                        if(field.chosen) {
                            dark.isChecked = true
                            changeApplicationTheme(AppCompatDelegate.MODE_NIGHT_YES)
                        }
                    }
                    "system_theme" -> {
                        systemField = field
                        if(field.chosen) {
                            system.isChecked = true
                            changeApplicationTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        }
                    }
                }
            }

            radioGroup.setOnCheckedChangeListener { _, i ->
                Log.d("i value", i.toString()+ " "+R.id.option_light_theme+" "+R.id.option_dark_theme)
                //(activity as AppCompatActivity).delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                when(i) {
                    R.id.option_light_theme -> {
                        lightField?.chosen = true
                        darkField?.chosen = false
                        systemField?.chosen = false
                        changeApplicationTheme(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    R.id.option_dark_theme -> {
                        lightField?.chosen = false
                        darkField?.chosen = true
                        systemField?.chosen = false
                        changeApplicationTheme(AppCompatDelegate.MODE_NIGHT_YES)
                    }

                    R.id.option_system_theme -> {
                        lightField?.chosen = false
                        darkField?.chosen = false
                        systemField?.chosen = true
                        changeApplicationTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                }
                optionViewModel.updateOptionField(lightField!!)
                optionViewModel.updateOptionField(darkField!!)
                optionViewModel.updateOptionField(systemField!!)
            }
        }
    }

    /**
     * Initialization of the notification setter.
     *
     * @param optionNextIncomeNotification - [CheckBox] which contains the next income notification
     * @param optionBelowSumNotification - [CheckBox] which contains the below a certain sum notification
     * @param optionNotification - an [Option] which the 2 checkboxes will be linked with. Here it is the [optionNotification]
     */
    private fun setupNotificationSetter(
        optionNextIncomeNotification : CheckBox,
        optionBelowSumNotification : CheckBox,
        optionNotification : Option,
    ) {
        optionViewModel.getOptionFields(optionNotification)?.observe(viewLifecycleOwner) { notifications ->
            var nextIncomeAlert : OptionField? = null
            var belowSumAlert : OptionField? = null
            for(notification in notifications) {
                when(notification.fieldValue) {
                    "next_income_alert" -> {
                        nextIncomeAlert = notification
                        if(notification.chosen == true) {
                            optionNextIncomeNotification.isChecked = true
                        }
                    }
                    "below_sum_alert" -> {
                        belowSumAlert = notification
                        if(notification.chosen == true) {
                            optionBelowSumNotification.isChecked = true
                        }
                    }
                }
            }
            optionNextIncomeNotification.setOnClickListener {
                nextIncomeAlert!!.chosen = !nextIncomeAlert.chosen
                optionViewModel.updateOptionField(nextIncomeAlert)
            }
            optionBelowSumNotification.setOnClickListener {
                belowSumAlert!!.chosen = !belowSumAlert.chosen
                optionViewModel.updateOptionField(belowSumAlert)
            }
        }
    }

    /**
     * Function to change application theme.
     *
     * @param mode - must be [AppCompatDelegate.MODE_NIGHT_NO], [AppCompatDelegate.MODE_NIGHT_YES] or [AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM]
     */
    private fun changeApplicationTheme(mode : Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
        (activity as AppCompatActivity).delegate.localNightMode = mode
    }

    /**
     * A listener for the [optionBudget] field linked to the spinner.
     *
     * @property optionViewModel - An instance of [OptionViewModel]
     * @property field - an [OptionField] which contains the current selected item in the spinner
     */
    private class SpinnerAdapter(val optionViewModel: OptionViewModel, val field: OptionField) : AdapterView.OnItemSelectedListener {
        /**
         * Callback action when the user select an item int the spinner
         *
         * @param parent - the parent view (the spinner)
         * @param view
         * @param pos - the position of the item selected in [parent]
         * @param id - the id of the selected item
         */
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
            val selected = parent?.getItemAtPosition(pos)
            field.fieldValue = selected.toString()
            optionViewModel.updateOptionField(field)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

    }

}