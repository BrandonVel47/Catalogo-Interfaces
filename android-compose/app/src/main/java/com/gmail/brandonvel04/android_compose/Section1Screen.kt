package com.gmail.brandonvel04.android_compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section1Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Campo de texto simple
        var simpleText by remember { mutableStateOf("") }
        Text("Campo de texto simple", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = simpleText,
            onValueChange = { simpleText = it },
            label = { Text("Ingresa tu nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        // 2. Campo con validación
        var validText by remember { mutableStateOf("") }
        val isError = validText.isEmpty()
        Text("Campo con validación", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = validText,
            onValueChange = { validText = it },
            label = { Text("Campo obligatorio") },
            isError = isError,
            supportingText = { if (isError) Text("Este campo no puede estar vacío") },
            modifier = Modifier.fillMaxWidth()
        )

        // 3. Campo de contraseña
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        Text("Campo de contraseña", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = { passwordVisible = !passwordVisible }) {
                    Text(if (passwordVisible) "Ocultar" else "Ver")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // 4. Teclados específicos
        var numText by remember { mutableStateOf("") }
        var emailText by remember { mutableStateOf("") }
        var phoneText by remember { mutableStateOf("") }
        Text("Teclados específicos", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = numText, onValueChange = { numText = it },
            label = { Text("Edad (Numérico)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = emailText, onValueChange = { emailText = it },
            label = { Text("Correo electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = phoneText, onValueChange = { phoneText = it },
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        // 5. Campo multilínea
        var multiText by remember { mutableStateOf("") }
        Text("Campo multilínea", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = multiText,
            onValueChange = { multiText = it },
            label = { Text("Escribe un comentario") },
            minLines = 3,
            maxLines = 5,
            modifier = Modifier.fillMaxWidth()
        )

        // 6. Menú desplegable
        val options = listOf("Opción 1", "Opción 2", "Opción 3", "Opción 4")
        var expanded by remember { mutableStateOf(false) }
        var selectedOption by remember { mutableStateOf(options[0]) }
        Text("Menú desplegable", fontWeight = FontWeight.Bold)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = selectedOption,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona una opción") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOption = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        // 7. Barra de búsqueda
        var searchText by remember { mutableStateOf("") }
        Text("Barra de búsqueda", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Buscar elemento...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = { searchText = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Limpiar")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}