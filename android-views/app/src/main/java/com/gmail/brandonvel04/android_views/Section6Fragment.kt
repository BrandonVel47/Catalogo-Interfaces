package com.gmail.brandonvel04.android_views // Revisa tu paquete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class Section6Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_section_six, container, false)

        // Configurar Barra Superior
        val topToolbar = view.findViewById<MaterialToolbar>(R.id.topToolbar)
        topToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    Toast.makeText(requireContext(), "Búsqueda seleccionada", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_settings -> {
                    Toast.makeText(requireContext(), "Ajustes seleccionados", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Configurar Barra Inferior
        val bottomNav = view.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_bot_home -> Toast.makeText(requireContext(), "Pestaña: Inicio", Toast.LENGTH_SHORT).show()
                R.id.nav_bot_profile -> Toast.makeText(requireContext(), "Pestaña: Perfil", Toast.LENGTH_SHORT).show()
                R.id.nav_bot_config -> Toast.makeText(requireContext(), "Pestaña: Configuración", Toast.LENGTH_SHORT).show()
            }
            true
        }

        return view
    }
}