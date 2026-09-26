package com.gmail.brandonvel04.android_views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Section2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_section_two, container, false)

        // Función de apoyo para mostrar mensajes (Toasts)
        fun showToast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        // 1. Botones base
        view.findViewById<MaterialButton>(R.id.btnFilled).setOnClickListener { showToast("Botón Relleno pulsado") }
        view.findViewById<MaterialButton>(R.id.btnOutlined).setOnClickListener { showToast("Botón Contorno pulsado") }
        view.findViewById<MaterialButton>(R.id.btnText).setOnClickListener { showToast("Botón de Texto pulsado") }

        // 2. Botones con ícono
        view.findViewById<MaterialButton>(R.id.btnIconText).setOnClickListener { showToast("Enviando mensaje...") }
        view.findViewById<MaterialButton>(R.id.btnIconOnly).setOnClickListener { showToast("Cámara abierta") }

        // 3. Botones Flotantes (FAB)
        view.findViewById<FloatingActionButton>(R.id.fabNormal).setOnClickListener { showToast("FAB Normal pulsado") }
        view.findViewById<ExtendedFloatingActionButton>(R.id.fabExtended).setOnClickListener { showToast("FAB Extendido pulsado") }

        // 4. Botón de Alternancia (Toggle)
        val toggleGroup = view.findViewById<MaterialButtonToggleGroup>(R.id.toggleGroup)
        toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                val button = view.findViewById<MaterialButton>(checkedId)
                showToast("Seleccionado: ${button.text}")
            }
        }

        // 5. Botón de estado de carga
        val btnLoading = view.findViewById<MaterialButton>(R.id.btnLoading)
        btnLoading.setOnClickListener {
            // Deshabilita el botón temporalmente para simular carga
            btnLoading.isEnabled = false
            btnLoading.text = "Cargando..."

            // Simula una tarea de 2 segundos
            Handler(Looper.getMainLooper()).postDelayed({
                btnLoading.isEnabled = true
                btnLoading.text = "Púlsame para cargar"
                showToast("¡Carga completada!")
            }, 2000)
        }

        return view
    }
}