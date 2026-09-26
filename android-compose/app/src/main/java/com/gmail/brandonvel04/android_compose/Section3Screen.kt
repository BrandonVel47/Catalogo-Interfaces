package com.gmail.brandonvel04.android_compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section3Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Casillas de verificación (Checkbox)
        Text("Casillas de verificación", fontWeight = FontWeight.Bold)
        var checked by remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = checked, onCheckedChange = { checked = it })
            Text("Opción seleccionable")
        }
        var triState by remember { mutableStateOf(ToggleableState.Indeterminate) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            TriStateCheckbox(
                state = triState,
                onClick = {
                    triState = when (triState) {
                        ToggleableState.On -> ToggleableState.Off
                        ToggleableState.Off -> ToggleableState.Indeterminate
                        ToggleableState.Indeterminate -> ToggleableState.On
                    }
                }
            )
            Text("Estado indeterminado")
        }

        // 2. Grupo de botones de opción (Radio Buttons)
        Text("Botones de opción (Mutuamente excluyentes)", fontWeight = FontWeight.Bold)
        val radioOptions = listOf("Opción A", "Opción B")
        var selectedRadio by remember { mutableStateOf(radioOptions[0]) }
        Row {
            radioOptions.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 16.dp)
                ) {
                    RadioButton(
                        selected = (option == selectedRadio),
                        onClick = { selectedRadio = option }
                    )
                    Text(option)
                }
            }
        }

        // 3. Interruptor (Switch)
        Text("Interruptor (Switch)", fontWeight = FontWeight.Bold)
        var switchState by remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Switch(checked = switchState, onCheckedChange = { switchState = it })
            Spacer(modifier = Modifier.width(8.dp))
            Text(if (switchState) "Activado" else "Desactivado")
        }

        // 4. Deslizadores (Sliders)
        Text("Deslizadores", fontWeight = FontWeight.Bold)
        var sliderValue by remember { mutableStateOf(50f) }
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f
        )
        var rangeSliderValue by remember { mutableStateOf(20f..80f) }
        RangeSlider(
            value = rangeSliderValue,
            onValueChange = { rangeSliderValue = it },
            valueRange = 0f..100f
        )

        // 5. Lista desplegable (Dropdown)
        Text("Lista desplegable", fontWeight = FontWeight.Bold)
        var expanded by remember { mutableStateOf(false) }
        val items = listOf("Lunes", "Martes", "Miércoles", "Jueves")
        var selectedItem by remember { mutableStateOf(items[0]) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedItem = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        // 6. Selectores de Fecha y Hora (Pickers)
        Text("Selectores de Fecha y Hora", fontWeight = FontWeight.Bold)
        var showDatePicker by remember { mutableStateOf(false) }
        var showTimePicker by remember { mutableStateOf(false) }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { showDatePicker = true }) { Text("Elegir Fecha") }
            Button(onClick = { showTimePicker = true }) { Text("Elegir Hora") }
        }

        if (showDatePicker) {
            val datePickerState = rememberDatePickerState()
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { showDatePicker = false }) { Text("Aceptar") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (showTimePicker) {
            val timePickerState = rememberTimePickerState()
            AlertDialog(
                onDismissRequest = { showTimePicker = false },
                confirmButton = {
                    TextButton(onClick = { showTimePicker = false }) { Text("Aceptar") }
                },
                text = { TimePicker(state = timePickerState) }
            )
        }

        // 7. Chips de filtro
        Text("Chips de filtro seleccionables", fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            var chip1 by remember { mutableStateOf(false) }
            var chip2 by remember { mutableStateOf(false) }
            FilterChip(
                selected = chip1,
                onClick = { chip1 = !chip1 },
                label = { Text("Películas") }
            )
            FilterChip(
                selected = chip2,
                onClick = { chip2 = !chip2 },
                label = { Text("Series") }
            )
        }
    }
}