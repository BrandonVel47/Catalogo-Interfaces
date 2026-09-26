import 'package:flutter/material.dart';
import '../main.dart';

class Section2Screen extends StatefulWidget {
  const Section2Screen({super.key});

  @override
  State<Section2Screen> createState() => _Section2ScreenState();
}

class _Section2ScreenState extends State<Section2Screen> {
  bool _isLoading = false;
  Set<String> _segmentSelected = {'Opción 1'};

  void _mostrarMensaje(String mensaje) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(mensaje), duration: const Duration(seconds: 1)),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('2. Botones y Acciones')),
      drawer: const AppDrawer(),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text('Tipos de Botones Principales', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: () => _mostrarMensaje('Botón relleno pulsado'),
                  child: const Text('Relleno'),
                ),
                OutlinedButton(
                  onPressed: () => _mostrarMensaje('Contorno pulsado'),
                  child: const Text('Contorno'),
                ),
                TextButton(
                  onPressed: () => _mostrarMensaje('Texto pulsado'),
                  child: const Text('Texto'),
                ),
              ],
            ),
            const Divider(height: 32),

            const Text('Botones con Íconos', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                IconButton(
                  icon: const Icon(Icons.favorite),
                  color: Colors.red,
                  onPressed: () => _mostrarMensaje('Solo ícono pulsado'),
                ),
                ElevatedButton.icon(
                  onPressed: () => _mostrarMensaje('Ícono + Texto pulsado'),
                  icon: const Icon(Icons.send),
                  label: const Text('Enviar'),
                ),
              ],
            ),
            const Divider(height: 32),

            const Text('Botón de Acción Flotante (FAB)', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 16),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                FloatingActionButton(
                  heroTag: 'fab1',
                  onPressed: () => _mostrarMensaje('FAB Normal pulsado'),
                  child: const Icon(Icons.add),
                ),
                FloatingActionButton.extended(
                  heroTag: 'fab2',
                  onPressed: () => _mostrarMensaje('FAB Extendido pulsado'),
                  icon: const Icon(Icons.edit),
                  label: const Text('Redactar'),
                ),
              ],
            ),
            const Divider(height: 32),

            const Text('Selector Segmentado (Toggle)', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            Center(
              child: SegmentedButton<String>(
                segments: const [
                  ButtonSegment(value: 'Opción 1', label: Text('Opción 1')),
                  ButtonSegment(value: 'Opción 2', label: Text('Opción 2')),
                ],
                selected: _segmentSelected,
                onSelectionChanged: (Set<String> newSelection) {
                  setState(() => _segmentSelected = newSelection);
                  _mostrarMensaje('Seleccionaste: ${newSelection.first}');
                },
              ),
            ),
            const Divider(height: 32),

            const Text('Estados del Botón', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                const ElevatedButton(
                  onPressed: null,
                  child: Text('Deshabilitado'),
                ),
                ElevatedButton(
                  onPressed: _isLoading ? null : () {
                    setState(() => _isLoading = true);
                    _mostrarMensaje('Cargando...');
                    Future.delayed(const Duration(seconds: 2), () {
                      setState(() => _isLoading = false);
                    });
                  },
                  child: _isLoading
                      ? const SizedBox(width: 20, height: 20, child: CircularProgressIndicator(strokeWidth: 2))
                      : const Text('Iniciar Carga'),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}