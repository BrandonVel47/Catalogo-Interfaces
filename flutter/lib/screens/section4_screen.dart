import 'package:flutter/material.dart';
import '../main.dart';

class Section4Screen extends StatefulWidget {
  const Section4Screen({super.key});

  @override
  State<Section4Screen> createState() => _Section4ScreenState();
}

class _Section4ScreenState extends State<Section4Screen> {
  // Genera una lista de 15 elementos
  List<String> _items = List.generate(15, (index) => 'Elemento ${index + 1}');

  // Simula la recarga de datos arrastrando hacia abajo
  Future<void> _refreshList() async {
    await Future.delayed(const Duration(seconds: 1));
    setState(() {
      _items = List.generate(15, (index) => 'Elemento Recargado ${index + 1}');
    });
  }

  // Muestra el detalle de un elemento en un diálogo
  void _showDetail(String item) {
    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text('Detalle de $item'),
        content: const Text('Esta es la vista de información extendida del elemento seleccionado.'),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(context),
            child: const Text('Cerrar'),
          )
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Scaffold(
        appBar: AppBar(
          title: const Text('4. Colecciones'),
          actions: [
            IconButton(
              icon: const Icon(Icons.delete_sweep),
              tooltip: 'Vaciar lista',
              onPressed: () => setState(() => _items.clear()), // Facilita probar el estado vacío
            )
          ],
          bottom: const TabBar(
            tabs: [
              Tab(text: 'Lista', icon: Icon(Icons.list)),
              Tab(text: 'Cuadrícula', icon: Icon(Icons.grid_view)),
              Tab(text: 'Secciones', icon: Icon(Icons.view_agenda)),
            ],
          ),
        ),
        drawer: const AppDrawer(),
        body: TabBarView(
          children: [
            // PESTAÑA 1: Lista interactiva (Refrescar, Eliminar, Detalles, Estado vacío)
            RefreshIndicator(
              onRefresh: _refreshList,
              child: _items.isEmpty
              // Estado vacío con ilustración
                  ? ListView(
                children: const [
                  SizedBox(height: 150),
                  Icon(Icons.inbox_outlined, size: 100, color: Colors.grey),
                  SizedBox(height: 16),
                  Center(
                    child: Text(
                      'No hay elementos.\nDesliza hacia abajo para recargar.',
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Colors.grey, fontSize: 16),
                    ),
                  ),
                ],
              )
              // Lista normal
                  : ListView.builder(
                itemCount: _items.length,
                itemBuilder: (context, index) {
                  final item = _items[index];
                  return Dismissible(
                    key: Key(item),
                    direction: DismissDirection.endToStart,
                    background: Container(
                      color: Colors.red,
                      alignment: Alignment.centerRight,
                      padding: const EdgeInsets.only(right: 20),
                      child: const Icon(Icons.delete, color: Colors.white),
                    ),
                    onDismissed: (direction) {
                      setState(() => _items.removeAt(index));
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('$item eliminado')),
                      );
                    },
                    child: ListTile(
                      leading: CircleAvatar(child: Text('${index + 1}')),
                      title: Text(item),
                      subtitle: const Text('Toca para detalles, desliza para borrar'),
                      onTap: () => _showDetail(item),
                    ),
                  );
                },
              ),
            ),

            // PESTAÑA 2: Cuadrícula (Grid)
            GridView.builder(
              padding: const EdgeInsets.all(8),
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 3, // 3 columnas
                crossAxisSpacing: 8,
                mainAxisSpacing: 8,
              ),
              itemCount: 21,
              itemBuilder: (context, index) {
                return Container(
                  decoration: BoxDecoration(
                    color: Theme.of(context).colorScheme.primaryContainer,
                    borderRadius: BorderRadius.circular(8),
                  ),
                  alignment: Alignment.center,
                  child: Text('Grid ${index + 1}'),
                );
              },
            ),

            // PESTAÑA 3: Lista con encabezados de sección
            ListView(
              children: const [
                Padding(
                  padding: EdgeInsets.all(16.0),
                  child: Text('Contactos Recientes', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.blue)),
                ),
                ListTile(leading: Icon(Icons.person), title: Text('Ana García')),
                ListTile(leading: Icon(Icons.person), title: Text('Luis Pérez')),
                Padding(
                  padding: EdgeInsets.all(16.0),
                  child: Text('Contactos Antiguos', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold, color: Colors.grey)),
                ),
                ListTile(leading: Icon(Icons.person_outline), title: Text('Roberto Torres')),
                ListTile(leading: Icon(Icons.person_outline), title: Text('María López')),
              ],
            ),
          ],
        ),
      ),
    );
  }
}