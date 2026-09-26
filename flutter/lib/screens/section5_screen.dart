import 'package:flutter/material.dart';
import '../main.dart';

class Section5Screen extends StatefulWidget {
  const Section5Screen({super.key});

  @override
  State<Section5Screen> createState() => _Section5ScreenState();
}

class _Section5ScreenState extends State<Section5Screen> {
  bool _isProgressActive = true;

  // Diálogo de confirmación
  void _showConfirmDialog(BuildContext context) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('Confirmación'),
          content: const Text('¿Estás seguro de realizar esta acción?'),
          actions: [
            TextButton(
              onPressed: () => Navigator.of(context).pop(),
              child: const Text('Cancelar'),
            ),
            FilledButton(
              onPressed: () {
                Navigator.of(context).pop();
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('Acción confirmada')),
                );
              },
              child: const Text('Aceptar'),
            ),
          ],
        );
      },
    );
  }

  // Hoja inferior (Bottom Sheet)
  void _showBottomSheet(BuildContext context) {
    showModalBottomSheet(
      context: context,
      builder: (BuildContext context) {
        return Container(
          padding: const EdgeInsets.all(24.0),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text('Hoja Inferior (Bottom Sheet)', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
              const SizedBox(height: 12),
              const Text('Este contenido aparece desde la parte inferior de la pantalla de manera flotante.'),
              const SizedBox(height: 20),
              ElevatedButton(
                onPressed: () => Navigator.pop(context),
                child: const Text('Cerrar Panel'),
              ),
            ],
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('5. Información y Retroalimentación')),
      drawer: const AppDrawer(),
      body: ListView(
        padding: const EdgeInsets.all(16.0),
        children: [
          // 1. Textos con distintos estilos y tamaños
          const Text('Jerarquía de Textos', style: TextStyle(fontWeight: FontWeight.bold)),
          const SizedBox(height: 8),
          const Text('Texto con estilo Headline (Grande)', style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold)),
          const Text('Texto secundario con énfasis atenuado.', style: TextStyle(color: Colors.grey, fontStyle: FontStyle.italic)),
          const Divider(height: 32),

          // 2. Imágenes local y de red
          const Text('Manejo de Imágenes', style: TextStyle(fontWeight: FontWeight.bold)),
          const SizedBox(height: 8),
          const Text('Imagen desde URL remota:'),
          const SizedBox(height: 8),
          ClipRRect(
            borderRadius: BorderRadius.circular(8),
            child: Image.network(
              'https://picsum.photos/400/200',
              height: 150,
              fit: BoxFit.cover,
              loadingBuilder: (context, child, loadingProgress) {
                if (loadingProgress == null) return child;
                return const Center(child: CircularProgressIndicator());
              },
            ),
          ),
          const Divider(height: 32),

          // 3. Indicadores de progreso
          const Text('Indicadores de Progreso', style: TextStyle(fontWeight: FontWeight.bold)),
          const SizedBox(height: 12),
          const Text('Lineal indeterminado:'),
          const SizedBox(height: 4),
          const LinearProgressIndicator(),
          const SizedBox(height: 16),
          const Text('Circular determinado (75%):'),
          const SizedBox(height: 8),
          const Center(child: CircularProgressIndicator(value: 0.75)),
          const Divider(height: 32),

          // 4. Mensajes emergentes (Snackbar con acción)
          const Text('Retroalimentación Emergente', style: TextStyle(fontWeight: FontWeight.bold)),
          const SizedBox(height: 8),
          ElevatedButton(
            onPressed: () {
              ScaffoldMessenger.of(context).showSnackBar(
                SnackBar(
                  content: const Text('Mensaje temporal con acción'),
                  action: SnackBarAction(
                    label: 'Deshacer',
                    onPressed: () {
                      // Acción al pulsar deshacer
                    },
                  ),
                ),
              );
            },
            child: const Text('Mostrar Snackbar con Acción'),
          ),
          const Divider(height: 32),

          // 5. Diálogos y Hojas Inferiores
          const Text('Diálogos y Paneles', style: TextStyle(fontWeight: FontWeight.bold)),
          const SizedBox(height: 8),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              OutlinedButton(
                onPressed: () => _showConfirmDialog(context),
                child: const Text('Abrir Diálogo'),
              ),
              OutlinedButton(
                onPressed: () => _showBottomSheet(context),
                child: const Text('Abrir Hoja Inferior'),
              ),
            ],
          ),
          const Divider(height: 32),

          // 6. Tarjetas, Separadores y Distintivos (Badges)
          const Text('Tarjetas y Distintivos', style: TextStyle(fontWeight: FontWeight.bold)),
          const SizedBox(height: 8),
          Card(
            elevation: 3,
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const Text('Notificaciones nuevas', style: TextStyle(fontSize: 16)),
                  Badge.count(
                    count: 5,
                    child: const Icon(Icons.notifications, size: 28),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}