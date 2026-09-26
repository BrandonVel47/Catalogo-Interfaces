import 'package:flutter/material.dart';
import '../main.dart'; // Importa el menú lateral

class Section1Screen extends StatefulWidget {
  const Section1Screen({super.key});

  @override
  State<Section1Screen> createState() => _Section1ScreenState();
}

class _Section1ScreenState extends State<Section1Screen> {
  final _formKey = GlobalKey<FormState>();
  bool _obscureText = true;
  final List<String> _opciones = ['Opción 1', 'Opción 2', 'Opción 3', 'Opción 4'];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('1. Entrada de texto')),
      drawer: const AppDrawer(),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // 1. Campo simple
              const Text('Campo de texto simple', style: TextStyle(fontWeight: FontWeight.bold)),
              const Text('Entrada básica de texto con una etiqueta (hint).', style: TextStyle(color: Colors.grey)),
              const SizedBox(height: 8),
              const TextField(
                decoration: InputDecoration(labelText: 'Ingresa tu nombre', border: OutlineInputBorder()),
              ),
              const SizedBox(height: 24),

              // 2. Validación
              const Text('Campo con validación', style: TextStyle(fontWeight: FontWeight.bold)),
              const Text('Muestra un mensaje de error si está vacío al interactuar.', style: TextStyle(color: Colors.grey)),
              const SizedBox(height: 8),
              TextFormField(
                decoration: const InputDecoration(labelText: 'Campo obligatorio', border: OutlineInputBorder()),
                validator: (value) => (value == null || value.isEmpty) ? 'Este campo no puede estar vacío' : null,
                onChanged: (_) => _formKey.currentState?.validate(),
              ),
              const SizedBox(height: 24),

              // 3. Contraseña
              const Text('Campo de contraseña', style: TextStyle(fontWeight: FontWeight.bold)),
              const Text('Oculta los caracteres y proporciona un ícono para alternar su visibilidad.', style: TextStyle(color: Colors.grey)),
              const SizedBox(height: 8),
              TextField(
                obscureText: _obscureText,
                decoration: InputDecoration(
                  labelText: 'Contraseña',
                  border: const OutlineInputBorder(),
                  suffixIcon: IconButton(
                    icon: Icon(_obscureText ? Icons.visibility : Icons.visibility_off),
                    onPressed: () => setState(() => _obscureText = !_obscureText),
                  ),
                ),
              ),
              const SizedBox(height: 24),

              // 4. Teclados Específicos
              const Text('Teclados específicos', style: TextStyle(fontWeight: FontWeight.bold)),
              const Text('Adaptan el teclado del sistema al tipo de dato esperado.', style: TextStyle(color: Colors.grey)),
              const SizedBox(height: 8),
              const TextField(
                keyboardType: TextInputType.number,
                decoration: InputDecoration(labelText: 'Edad (Numérico)', border: OutlineInputBorder()),
              ),
              const SizedBox(height: 8),
              const TextField(
                keyboardType: TextInputType.emailAddress,
                decoration: InputDecoration(labelText: 'Correo electrónico', border: OutlineInputBorder()),
              ),
              const SizedBox(height: 8),
              const TextField(
                keyboardType: TextInputType.phone,
                decoration: InputDecoration(labelText: 'Teléfono', border: OutlineInputBorder()),
              ),
              const SizedBox(height: 24),

              // 5. Multilínea
              const Text('Campo multilínea', style: TextStyle(fontWeight: FontWeight.bold)),
              const Text('Permite ingresar textos largos expandiéndose verticalmente.', style: TextStyle(color: Colors.grey)),
              const SizedBox(height: 8),
              const TextField(
                maxLines: 3,
                decoration: InputDecoration(labelText: 'Escribe un comentario', border: OutlineInputBorder()),
              ),
              const SizedBox(height: 24),

              // 6. Desplegable
              const Text('Menú desplegable', style: TextStyle(fontWeight: FontWeight.bold)),
              const Text('Muestra una lista de opciones predefinidas.', style: TextStyle(color: Colors.grey)),
              const SizedBox(height: 8),
              DropdownMenu<String>(
                label: const Text('Selecciona una opción'),
                width: MediaQuery.of(context).size.width - 32,
                dropdownMenuEntries: _opciones.map((e) => DropdownMenuEntry(value: e, label: e)).toList(),
              ),
              const SizedBox(height: 24),

              // 7. Búsqueda
              const Text('Barra de búsqueda', style: TextStyle(fontWeight: FontWeight.bold)),
              const Text('Componente optimizado para realizar consultas con botón para limpiar.', style: TextStyle(color: Colors.grey)),
              const SizedBox(height: 8),
              SearchBar(
                hintText: 'Buscar Delemento...',
                leading: const Icon(Icons.search),
                trailing: [IconButton(icon: const Icon(Icons.clear), onPressed: () {})],
              ),
              const SizedBox(height: 32),
            ],
          ),
        ),
      ),
    );
  }
}