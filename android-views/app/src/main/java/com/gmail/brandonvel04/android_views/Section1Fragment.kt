package com.gmail.brandonvel04.android_views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Section1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_section_one, container, false)

        // Configuración de validación de error
        val textInputLayoutError = view.findViewById<TextInputLayout>(R.id.textInputLayoutError)
        val editTextValidation = view.findViewById<TextInputEditText>(R.id.editTextValidation)

        editTextValidation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    textInputLayoutError.error = "Este campo no puede estar vacío"
                } else {
                    textInputLayoutError.error = null // Limpia el error
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Configuración del menú desplegable
        val options = listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, options)
        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteOptions)
        autoCompleteTextView.setAdapter(adapter)

        return view
    }
}