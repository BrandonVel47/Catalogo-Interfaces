import 'package:flutter/material.dart';
import 'screens/section1_screen.dart';
import 'screens/section2_screen.dart';
import 'screens/section3_screen.dart';
import 'screens/section4_screen.dart';
import 'screens/section5_screen.dart';
import 'screens/section6_screen.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Catálogo UI - Flutter',
      // Configuración de tema claro y oscuro automático
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      darkTheme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue, brightness: Brightness.dark),
        useMaterial3: true,
      ),
      themeMode: ThemeMode.system,
      home: const HomeScreen(),
      debugShowCheckedModeBanner: false,
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Catálogo de UI - Flutter')),
      drawer: const AppDrawer(),
      body: const Center(
        child: Text(
          'Selecciona una sección en el menú lateral',
          style: TextStyle(fontSize: 18),
        ),
      ),
    );
  }
}

// Menú lateral reutilizable
class AppDrawer extends StatelessWidget {
  const AppDrawer({super.key});

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: [
          DrawerHeader(
            decoration: BoxDecoration(color: Theme.of(context).colorScheme.primary),
            child: const Text('Navegación', style: TextStyle(color: Colors.white, fontSize: 24)),
          ),
          ListTile(
            leading: const Icon(Icons.home),
            title: const Text('Pantalla Principal'),
            onTap: () => Navigator.pushReplacement(context, MaterialPageRoute(builder: (_) => const HomeScreen())),
          ),
          ListTile(
            leading: const Icon(Icons.text_fields),
            title: const Text('1. Entrada de texto'),
            onTap: () => Navigator.pushReplacement(context, MaterialPageRoute(builder: (_) => const Section1Screen())),
          ),
          ListTile(
            leading: const Icon(Icons.smart_button),
            title: const Text('2. Botones y acciones'),
            onTap: () => Navigator.pushReplacement(context, MaterialPageRoute(builder: (_) => const Section2Screen())),
          ),
          ListTile(
            leading: const Icon(Icons.check_box),
            title: const Text('3. Elementos de selección'),
            onTap: () => Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (_) => const Section3Screen())
            ),
          ),
        ],
      ),
    );
  }
}