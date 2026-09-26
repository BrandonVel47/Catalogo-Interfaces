package com.gmail.brandonvel04.android_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section6Screen() {
    // Estado para la barra de navegación inferior
    var selectedBottomTab by remember { mutableStateOf(0) }

    // El Scaffold nos provee la estructura básica de una pantalla Material
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("6. Contenedores") },
                actions = {
                    IconButton(onClick = { /* Simula una acción */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = { /* Simula un menú */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Opciones")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    selected = selectedBottomTab == 0,
                    onClick = { selectedBottomTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Star, contentDescription = "Destacados") },
                    label = { Text("Destacados") },
                    selected = selectedBottomTab == 1,
                    onClick = { selectedBottomTab = 1 }
                )
            }
        }
    ) { paddingValues ->
        // Contenedor con desplazamiento vertical
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Respeta el espacio del topBar y bottomBar
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 1. Distribución en Fila y Columna
            Column {
                Text("Distribución en Fila y Columna", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                Text("Organiza los elementos horizontal (Row) y verticalmente (Column).", color = Color.Gray, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(bottom = 12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Text("Perfil")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Settings, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Text("Ajustes")
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Share, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Text("Compartir")
                    }
                }
            }

            HorizontalDivider()

            // 2. Superposición (Box)
            Column {
                Text("Distribución superpuesta (Box)", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                Text("Permite apilar elementos uno encima de otro y alinearlos libremente.", color = Color.Gray, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(bottom = 12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.medium)
                ) {
                    Text(
                        "Capa inferior (Fondo)",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    SmallFloatingActionButton(
                        onClick = { /* Acción */ },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Agregar")
                    }
                    Badge(modifier = Modifier.align(Alignment.TopStart).padding(8.dp)) { Text("Nuevo") }
                }
            }

            HorizontalDivider()

            // 3. Pesos Proporcionales
            Column {
                Text("Pesos Proporcionales (Weight)", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
                Text("Distribuye el espacio disponible asignando porcentajes a los contenedores.", color = Color.Gray, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(bottom = 12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f) // 25% del espacio
                            .fillMaxHeight()
                            .background(Color(0xFFEF5350), shape = MaterialTheme.shapes.small),
                        contentAlignment = Alignment.Center
                    ) { Text("25%", color = Color.White, fontWeight = FontWeight.Bold) }

                    Box(
                        modifier = Modifier
                            .weight(2f) // 50% del espacio
                            .fillMaxHeight()
                            .background(Color(0xFF42A5F5), shape = MaterialTheme.shapes.small),
                        contentAlignment = Alignment.Center
                    ) { Text("50%", color = Color.White, fontWeight = FontWeight.Bold) }

                    Box(
                        modifier = Modifier
                            .weight(1f) // 25% del espacio
                            .fillMaxHeight()
                            .background(Color(0xFF66BB6A), shape = MaterialTheme.shapes.small),
                        contentAlignment = Alignment.Center
                    ) { Text("25%", color = Color.White, fontWeight = FontWeight.Bold) }
                }
            }
        }
    }
}