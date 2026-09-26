package com.gmail.brandonvel04.android_views // Recuerda verificar tu paquete exacto

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.net.URL
import kotlin.concurrent.thread

class Section5Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_section_five, container, false)

        // 1. Cargar imagen desde URL en un hilo secundario
        val ivUrlImage = view.findViewById<ImageView>(R.id.ivUrlImage)
        thread {
            try {
                val url = URL("https://picsum.photos/400/200") // Imagen aleatoria
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                requireActivity().runOnUiThread {
                    ivUrlImage.setImageBitmap(bmp)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        // 2. Snackbar
        view.findViewById<Button>(R.id.btnSnackbar).setOnClickListener {
            Snackbar.make(view, "Acción realizada con éxito", Snackbar.LENGTH_LONG)
                .setAction("Deshacer") {
                    Toast.makeText(requireContext(), "Deshecho", Toast.LENGTH_SHORT).show()
                }.show()
        }

        // 3. Diálogo de confirmación
        view.findViewById<Button>(R.id.btnDialog).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("¿Eliminar archivo?")
                .setMessage("Esta acción no se puede deshacer. ¿Deseas continuar?")
                .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
                .setPositiveButton("Aceptar") { dialog, _ ->
                    Toast.makeText(requireContext(), "Archivo eliminado", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        // 4. Hoja inferior (Bottom Sheet)
        view.findViewById<Button>(R.id.btnBottomSheet).setOnClickListener {
            val bottomSheet = BottomSheetDialog(requireContext())
            val bottomSheetView = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)

            bottomSheetView.findViewById<Button>(R.id.btnCloseSheet).setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.setContentView(bottomSheetView)
            bottomSheet.show()
        }

        return view
    }
}