import 'package:flutter/material.dart';
import '../main.dart';

class Section6Screen extends StatefulWidget {
  const Section6Screen({super.key});

  @override
  State<Section6Screen> createState() => _Section6ScreenState();
}

class _Section6ScreenState extends State<Section6Screen> {
  int _bottomNavIndex = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('6. Contenedores y Estructura'),
      ),
      drawer: const AppDrawer(),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // 1. Distribución en Fila (Row) y Columna (Column)
            const Text('Distribución en Fila y Columna', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Container(padding: const EdgeInsets.all(12), color: Colors.blue[100], child: const Text('Fila 1')),
                Container(padding: const EdgeInsets.all(12), color: Colors.blue[200], child: const Text('Fila 2')),
                Container(padding: const EdgeInsets.all(12), color: Colors.blue[300], child: const Text('Fila 3')),
              ],
            ),
            const Divider(height: 32),

            // 2. Distribución Superpuesta (Stack)
            const Text('Distribución Superpuesta (Stack)', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            Center(
              child: Stack(
                alignment: Alignment.center,
                children: [
                  Container(width: 150, height: 100, color: Colors.amber[200]),
                  Container(width: 100, height: 60, color: Colors.orange[400]),
                  const Text('Superpuestos', style: TextStyle(fontWeight: FontWeight.bold)),
                ],
              ),
            ),
            const Divider(height: 32),

            // 3. Distribución con Pesos Proporcionales (Expanded)
            const Text('Distribución Proporcional (Expanded)', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            Row(
              children: [
                Expanded(
                  flex: 2,
                  child: Container(
                    padding: const EdgeInsets.all(12),
                    color: Colors.green[200],
                    child: const Text('Ocupa 2 partes', textAlign: TextAlign.center),
                  ),
                ),
                Expanded(
                  flex: 1,
                  child: Container(
                    padding: const EdgeInsets.all(12),
                    color: Colors.green[400],
                    child: const Text('1 parte', textAlign: TextAlign.center, style: TextStyle(color: Colors.white)),
                  ),
                ),
              ],
            ),
            const Divider(height: 32),

            // 4. Ejemplo de Barra de Navegación Inferior interna
            const Text('Menú de Navegación Inferior (Ejemplo visual)', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 8),
            Container(
              decoration: BoxDecoration(
                border: Border.all(color: Colors.grey.shade300),
                borderRadius: BorderRadius.circular(8),
              ),
              child: NavigationBar(
                selectedIndex: _bottomNavIndex,
                onDestinationSelected: (int index) {
                  setState(() {
                    _bottomNavIndex = index;
                  });
                },
                destinations: const [
                  NavigationDestination(icon: Icon(Icons.explore), label: 'Explorar'),
                  NavigationDestination(icon: Icon(Icons.commute), label: 'Viajes'),
                  NavigationDestination(icon: Icon(Icons.bookmark), label: 'Guardados'),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}