package com.gmail.brandonvel04.android_compose

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section5Screen() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Estados para alertas
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Tipografía
            Text("Tipografía (Textos)", fontWeight = FontWeight.Bold)
            Text("Texto principal (Headline)", style = MaterialTheme.typography.headlineSmall)
            Text("Texto secundario con énfasis medio.", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

            // 2. Tarjeta y Separador
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Tarjeta de Información", fontWeight = FontWeight.Bold)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Las tarjetas agrupan información relacionada y los separadores dividen el contenido.")
                }
            }

            // 3. Imágenes (Local y URL nativa)
            Text("Imágenes", fontWeight = FontWeight.Bold)
            Row(modifier = Modifier.height(120.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Imagen local
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background), // Recurso por defecto
                    contentDescription = "Local",
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )

                // Carga de imagen URL en hilo secundario (Nativo)
                var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }
                LaunchedEffect(Unit) {
                    withContext(Dispatchers.IO) {
                        try {
                            val url = URL("https://picsum.photos/200")
                            val stream = url.openConnection().getInputStream()
                            bitmap = BitmapFactory.decodeStream(stream).asImageBitmap()
                        } catch (e: Exception) { e.printStackTrace() }
                    }
                }

                if (bitmap != null) {
                    Image(
                        bitmap = bitmap!!,
                        contentDescription = "URL",
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Box(
                        modifier = Modifier.weight(1f).fillMaxHeight().background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) { CircularProgressIndicator() }
                }
            }

            // 4. Indicadores de progreso
            Text("Indicadores de Progreso", fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
                LinearProgressIndicator(modifier = Modifier.weight(1f))
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                CircularProgressIndicator(progress = { 0.7f }, modifier = Modifier.size(24.dp))
                LinearProgressIndicator(progress = { 0.7f }, modifier = Modifier.weight(1f))
            }

            // 5. Distintivo (Badge)
            Text("Distintivo Numérico (Badge)", fontWeight = FontWeight.Bold)
            BadgedBox(
                badge = { Badge { Text("3") } },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(Icons.Default.Email, contentDescription = "Correo", modifier = Modifier.size(32.dp))
            }

            // 6. Alertas
            Text("Retroalimentación y Alertas", fontWeight = FontWeight.Bold)
            Button(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Acción realizada con éxito", actionLabel = "Deshacer")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Mostrar Snackbar") }

            Button(
                onClick = { showDialog = true },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Mostrar Diálogo") }

            Button(
                onClick = { showBottomSheet = true },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Mostrar Hoja Inferior (Bottom Sheet)") }
        }

        // --- Componentes Emergentes ---

        // Diálogo
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("¿Eliminar archivo?") },
                text = { Text("Esta acción no se puede deshacer. ¿Deseas continuar?") },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        Toast.makeText(context, "Archivo eliminado", Toast.LENGTH_SHORT).show()
                    }) { Text("Aceptar") }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
                }
            )
        }

        // Bottom Sheet
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Hoja Inferior (Bottom Sheet)", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Este panel se desliza desde abajo para mostrar contenido secundario.")
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = {
                        coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) showBottomSheet = false
                        }
                    }) { Text("Cerrar") }
                }
            }
        }
    }
}