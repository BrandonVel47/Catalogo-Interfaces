package com.gmail.brandonvel04.android_compose

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Section2Screen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Botones Base
        Text("Tipos de Botones Base", fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { Toast.makeText(context, "Botón Relleno", Toast.LENGTH_SHORT).show() }) {
                Text("Relleno")
            }
            OutlinedButton(onClick = { Toast.makeText(context, "Botón Contorno", Toast.LENGTH_SHORT).show() }) {
                Text("Contorno")
            }
            TextButton(onClick = { Toast.makeText(context, "Botón Texto", Toast.LENGTH_SHORT).show() }) {
                Text("Texto")
            }
        }

        // 2. Botones con Ícono
        Text("Botones con Íconos", fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { Toast.makeText(context, "Enviar pulsado", Toast.LENGTH_SHORT).show() }) {
                Icon(Icons.Default.Send, contentDescription = "Enviar", modifier = Modifier.size(ButtonDefaults.IconSize))
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Enviar")
            }
            IconButton(onClick = { Toast.makeText(context, "Cámara pulsada", Toast.LENGTH_SHORT).show() }) {
                Icon(Icons.Default.CameraAlt, contentDescription = "Cámara")
            }
        }

        // 3. Botones Flotantes (FAB)
        Text("Botones de Acción Flotante (FAB)", fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            FloatingActionButton(onClick = { Toast.makeText(context, "FAB Normal", Toast.LENGTH_SHORT).show() }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
            ExtendedFloatingActionButton(
                onClick = { Toast.makeText(context, "FAB Extendido", Toast.LENGTH_SHORT).show() },
                icon = { Icon(Icons.Default.Add, contentDescription = "Agregar") },
                text = { Text("Agregar nuevo") }
            )
        }

        // 4. Botón de Alternancia (Toggle)
        var selectedOption by remember { mutableStateOf(0) }
        val options = listOf("Izq", "Centro", "Der")
        Text("Botón de Alternancia (Toggle)", fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
            options.forEachIndexed { index, text ->
                val isSelected = selectedOption == index
                OutlinedButton(
                    onClick = {
                        selectedOption = index
                        Toast.makeText(context, "Seleccionado: $text", Toast.LENGTH_SHORT).show()
                    },
                    colors = if (isSelected) ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer) else ButtonDefaults.outlinedButtonColors()
                ) {
                    Text(text)
                }
            }
        }

        // 5. Estados: Deshabilitado y Carga
        var isLoading by remember { mutableStateOf(false) }
        Text("Estados: Deshabilitado y Carga", fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { }, enabled = false) {
                Text("No disponible")
            }
            Button(
                onClick = {
                    isLoading = true
                    coroutineScope.launch {
                        delay(2000) // Simula tarea de 2 segundos
                        isLoading = false
                        Toast.makeText(context, "¡Carga completada!", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
                    Spacer(Modifier.size(8.dp))
                    Text("Cargando...")
                } else {
                    Text("Púlsame para cargar")
                }
            }
        }
    }
}