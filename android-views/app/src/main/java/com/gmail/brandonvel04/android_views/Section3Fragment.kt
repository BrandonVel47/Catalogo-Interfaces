package com.gmail.brandonvel04.android_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.slider.RangeSlider
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Section3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_section_three, container, false)

        // 1. Configurar valores iniciales del RangeSlider
        val rangeSlider = view.findViewById<RangeSlider>(R.id.rangeSlider)
        rangeSlider.values = listOf(20f, 80f) // Asigna rango del 20 al 80

        // 2. Configurar Lista desplegable (Spinner)
        val spinner = view.findViewById<Spinner>(R.id.spinnerSelection)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listOf("Selecciona una opción...", "Lunes", "Martes", "Miércoles")
        )
        spinner.adapter = adapter

        // 3. Configurar Selector de Fecha (Date Picker)
        view.findViewById<MaterialButton>(R.id.btnDatePicker).setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selecciona una fecha")
                .build()

            datePicker.addOnPositiveButtonClickListener { selection ->
                val dateStr = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(selection))
                Toast.makeText(requireContext(), "Fecha: $dateStr", Toast.LENGTH_SHORT).show()
            }
            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }

        // 4. Configurar Selector de Hora (Time Picker)
        view.findViewById<MaterialButton>(R.id.btnTimePicker).setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Selecciona una hora")
                .build()

            timePicker.addOnPositiveButtonClickListener {
                Toast.makeText(requireContext(), "Hora: ${timePicker.hour}:${timePicker.minute}", Toast.LENGTH_SHORT).show()
            }
            timePicker.show(parentFragmentManager, "TIME_PICKER")
        }

        return view
    }
}