import 'package:flutter/material.dart';
import '../main.dart';

class Section3Screen extends StatefulWidget {
  const Section3Screen({super.key});

  @override
  State<Section3Screen> createState() => _Section3ScreenState();
}

class _Section3ScreenState extends State<Section3Screen> {
  // Estados de los elementos
  bool? _checkboxValue = false; // Permite null para estado indeterminado
  int _radioValue = 1;
  bool _switchValue = false;
  double _sliderValue = 50;
  RangeValues _rangeValues = const RangeValues(20, 80);
  String _dropdownValue = 'Opción 1';
  final List<String> _dropdownOptions = ['Opción 1', 'Opción 2', 'Opción 3'];

  DateTime? _selectedDate;
  TimeOfDay? _selectedTime;

  final Set<String> _selectedFilters = {};
  final List<String> _filterOptions = ['Filtro A', 'Filtro B', 'Filtro C'];

  // Selectores de Fecha y Hora
  Future<void> _selectDate(BuildContext context) async {
    final DateTime? picked = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2000),
      lastDate: DateTime(2101),
    );
    if (picked != null && picked != _selectedDate) {
      setState(() => _selectedDate = picked);
    }
  }

  Future<void> _selectTime(BuildContext context) async {
    final TimeOfDay? picked = await showTimePicker(
      context: context,
      initialTime: TimeOfDay.now(),
    );
    if (picked != null && picked != _selectedTime) {
      setState(() => _selectedTime = picked);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('3. Elementos de Selección')),
      drawer: const AppDrawer(),
      body: ListView(
        padding: const EdgeInsets.all(16.0),
        children: [
          // 1. Casilla de verificación (con estado indeterminado)
          const Text('Casilla de verificación (Tristate)', style: TextStyle(fontWeight: FontWeight.bold)),
          CheckboxListTile(
            title: const Text('Aceptar términos'),
            subtitle: const Text('Soporta seleccionado, no seleccionado e indeterminado.'),
            tristate: true,
            value: _checkboxValue,
            onChanged: (bool? newValue) {
              setState(() => _checkboxValue = newValue);
            },
          ),
          const Divider(),

          // 2. Botones de opción (Radio)
          const Text('Grupo de botones de opción', style: TextStyle(fontWeight: FontWeight.bold)),
          const Text('Selección mutuamente excluyente.'),
          Row(
            children: [
              Radio<int>(value: 1, groupValue: _radioValue, onChanged: (val) => setState(() => _radioValue = val!)),
              const Text('Uno'),
              Radio<int>(value: 2, groupValue: _radioValue, onChanged: (val) => setState(() => _radioValue = val!)),
              const Text('Dos'),
              Radio<int>(value: 3, groupValue: _radioValue, onChanged: (val) => setState(() => _radioValue = val!)),
              const Text('Tres'),
            ],
          ),
          const Divider(),

          // 3. Interruptor (Switch)
          const Text('Interruptor (Switch)', style: TextStyle(fontWeight: FontWeight.bold)),
          SwitchListTile(
            title: const Text('Activar notificaciones'),
            subtitle: const Text('Alterna entre encendido y apagado.'),
            value: _switchValue,
            onChanged: (bool value) => setState(() => _switchValue = value),
          ),
          const Divider(),

          // 4. Deslizadores
          const Text('Deslizador (Valor único y Rango)', style: TextStyle(fontWeight: FontWeight.bold)),
          Slider(
            value: _sliderValue,
            min: 0,
            max: 100,
            divisions: 10,
            label: _sliderValue.round().toString(),
            onChanged: (double value) => setState(() => _sliderValue = value),
          ),
          RangeSlider(
            values: _rangeValues,
            min: 0,
            max: 100,
            divisions: 10,
            labels: RangeLabels(_rangeValues.start.round().toString(), _rangeValues.end.round().toString()),
            onChanged: (RangeValues values) => setState(() => _rangeValues = values),
          ),
          const Divider(),

          // 5. Lista desplegable
          const Text('Lista Desplegable', style: TextStyle(fontWeight: FontWeight.bold)),
          DropdownButton<String>(
            value: _dropdownValue,
            isExpanded: true,
            onChanged: (String? newValue) => setState(() => _dropdownValue = newValue!),
            items: _dropdownOptions.map<DropdownMenuItem<String>>((String value) {
              return DropdownMenuItem<String>(
                value: value,
                child: Text(value),
              );
            }).toList(),
          ),
          const Divider(),

          // 6. Selectores de Fecha y Hora
          const Text('Selectores de Fecha y Hora', style: TextStyle(fontWeight: FontWeight.bold)),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              ElevatedButton.icon(
                icon: const Icon(Icons.calendar_today),
                label: Text(_selectedDate == null ? 'Elegir Fecha' : '${_selectedDate!.toLocal()}'.split(' ')[0]),
                onPressed: () => _selectDate(context),
              ),
              ElevatedButton.icon(
                icon: const Icon(Icons.access_time),
                label: Text(_selectedTime == null ? 'Elegir Hora' : _selectedTime!.format(context)),
                onPressed: () => _selectTime(context),
              ),
            ],
          ),
          const Divider(),

          // 7. Chips de filtro
          const Text('Chips de Filtro', style: TextStyle(fontWeight: FontWeight.bold)),
          Wrap(
            spacing: 8.0,
            children: _filterOptions.map((String option) {
              return FilterChip(
                label: Text(option),
                selected: _selectedFilters.contains(option),
                onSelected: (bool selected) {
                  setState(() {
                    if (selected) {
                      _selectedFilters.add(option);
                    } else {
                      _selectedFilters.remove(option);
                    }
                  });
                },
              );
            }).toList(),
          ),
        ],
      ),
    );
  }
}