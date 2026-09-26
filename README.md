# Catálogo Interactivo de Elementos de Interfaz de Usuario en Dispositivos Móviles

## Datos de Identificación
* **Materia**: Desarrollo de Aplicaciones Móviles Nativas
* **Profesor**: Hurtado Avilés Gabriel
* **Alumno**: Velazquez Beltran Brandon
* **Boleta**: 2023630925
* **Grupo**: 7CV4

---

## Descripción de la Aplicación y Tecnologías Utilizadas
Esta aplicación consiste en un catálogo interactivo de componentes de interfaz de usuario para dispositivos móviles, desarrollado e implementado en tres tecnologías distintas con el fin de comparar sus enfoques de construcción, componentes básicos y equivalencias entre plataformas:
1. **Android Nativo con Views y XML** (Kotlin + layouts XML)
2. **Android Nativo con Jetpack Compose** (Kotlin + funciones Composable)
3. **Flutter** (Dart, ejecutándose en emulador y en el **navegador Google Chrome** debido a dificultades técnicas iniciales con la vinculación inalámbrica del dispositivo físico).

---

## Instrucciones de Compilación y Ejecución

* **Android Views (`android-views/`)**: Abrir el proyecto en Android Studio, esperar la sincronización de Gradle y ejecutar sobre un dispositivo virtual o físico compatible con Views.
* **Jetpack Compose (`android-compose/`)**: Abrir la carpeta en Android Studio, verificar la correcta sincronización de las dependencias de Material 3 e iniciar la aplicación mediante el botón de ejecución (Run).
* **Flutter (`flutter/`)**: Abrir el directorio en Visual Studio Code o Android Studio, ejecutar `flutter pub get` para descargar dependencias y compilar la aplicación. *Nota importante:* Ante problemas de compatibilidad y conexión ADB con el teléfono físico durante las pruebas, se optó por configurar y cambiar la ejecución principal hacia el **navegador Google Chrome**, garantizando la validación fluida de toda la interfaz.

---

## Tabla de Equivalencias entre Tecnologías

| Elemento / Componente | Views / XML | Jetpack Compose | Flutter |
| :--- | :--- | :--- | :--- |
| **Campo de texto simple** | `EditText` / `TextInputLayout` | `TextField` | `TextField` |
| **Campo con validación** | `TextInputLayout` con error | `OutlinedTextField` con estado | `TextField` con validación local |
| **Campo de contraseña** | `TextInputLayout` (passwordToggle) | `TextField` (`visualTransformation`) | `TextField` (`obscureText`) |
| **Teclados específicos** | `inputType="..."` | `KeyboardOptions` | `TextInputType` |
| **Campo multilínea** | `inputType="textMultiLine"` | `maxLines` múltiples | `maxLines` múltiples |
| **Desplegable / Sugerencias** | `AutoCompleteTextView` | `ExposedDropdownMenuBox` | `DropdownButton` |
| **Barra de búsqueda** | `SearchView` | `SearchBar` / Campo personalizado | `TextField` con ícono de búsqueda |
| **Botones básicos** | `Button`, `OutlinedButton`, `TextView` | `Button`, `OutlinedButton`, `TextButton` | `ElevatedButton`, `OutlinedButton`, `TextButton` |
| **Botón con ícono** | `Button` con `app:icon` | `Button` con `Icon` | `ElevatedButton.icon` |
| **Botón flotante (FAB)** | `FloatingActionButton` | `FloatingActionButton` | `FloatingActionButton` |
| **Botón de alternancia** | `ToggleButton` / `Chip` | `IconToggleButton` / `FilterChip` | `ChoiceChip` |
| **Estado de carga / Deshabilitado**| `setEnabled(false)` / `ProgressBar` | Parámetro `enabled = false` | Parámetro `onPressed: null` |
| **Casilla de verificación** | `CheckBox` (tristate support) | `TriStateCheckbox` | `Checkbox` (`tristate: true`) |
| **Botones de opción** | `RadioGroup` y `RadioButton` | `RadioButton` agrupado | `Radio` agrupado |
| **Interruptor** | `Switch` | `Switch` | `Switch` |
| **Deslizador** | `SeekBar` / `RangeSlider` | `Slider` / `RangeSlider` | `Slider` / `RangeSlider` |
| **Selector de Fecha/Hora** | `DatePickerDialog` / `TimePickerDialog` | `DatePicker` / `TimePicker` nativos | `showDatePicker` / `showTimePicker` |
| **Chips de filtro** | `ChipGroup` con `Chip` | `FilterChip` | `FilterChip` |
| **Lista vertical** | `RecyclerView` | `LazyColumn` | `ListView.builder` |
| **Cuadrícula** | `RecyclerView` (GridLayoutManager) | `LazyVerticalGrid` | `GridView.builder` |
| **Lista con secciones** | `RecyclerView` con adaptadores múltiples | `LazyColumn` con secciones | `ListView` con encabezados |
| **Deslizar para eliminar** | `ItemTouchHelper` | Implementación de gestos personalizados | `Dismissible` |
| **Actualizar (Swipe to refresh)** | `SwipeRefreshLayout` | `PullToRefreshContainer` | `RefreshIndicator` |
| **Estado vacío** | Vista condicional de layout | Composable condicional | Vista condicional con icono y texto |
| **Pestañas deslizables** | `ViewPager2` con `TabLayout` | `HorizontalPager` | `DefaultTabController` / `TabBarView` |
| **Tipografía / Textos** | `TextView` con estilos | `Text` con `TextStyle` | `Text` con estilos personalizados |
| **Imágenes** | `ImageView` | `Image` / `AsyncImage` | `Image.asset` / `Image.network` |
| **Progreso** | `ProgressBar` (Linear/Circular) | `LinearProgressIndicator` / `CircularProgressIndicator` | `LinearProgressIndicator` / `CircularProgressIndicator` |
| **Mensajes (Toast / Snackbar)** | `Toast` / `Snackbar` | `SnackbarHostState` | `ScaffoldMessenger` (SnackBar) |
| **Diálogo / Hoja inferior** | `AlertDialog` / `BottomSheetDialogFragment` | `AlertDialog` / `ModalBottomSheet` | `showDialog` / `showModalBottomSheet` |
| **Tarjetas y distintivos** | `MaterialCardView` / `BadgeDrawable` | `Card` / `BadgedBox` | `Card` / `Badge.count` |
| **Contenedores estruturales** | `LinearLayout` / `ConstraintLayout` | `Row`, `Column`, `Box` | `Row`, `Column`, `Stack` |
| **Desplazamiento** | `ScrollView` | `verticalScroll` | `SingleChildScrollView` |
| **Barra superior / inferior** | `Toolbar` / `BottomNavigationView` | `TopAppBar` / `NavigationBar` | `AppBar` / `NavigationBar` |

---

## Capturas de Pantalla
A continuación se muestran las capturas correspondientes a las pantallas y secciones almacenadas en la carpeta `docs/`:

### Pantallas Principales
* ![Pantalla Principal 1](docs/Pantalla_principal_1.jpg)
* ![Pantalla Principal 2](docs/Pantalla_principal_2.jpg)
* ![Pantalla Principal 3](docs/Pantalla_principal_3.png)

### Sección 1
* ![Sección 1.1.1](docs/Seccion_1.1.1.jpg)
* ![Sección 1.2.1](docs/Seccion_1.2.1.jpg)
* ![Sección 1.2.2](docs/Seccion_1.2.2.jpg)
* ![Sección 1.3.1](docs/Seccion_1.3.1.jpg)
* ![Sección 1.3.2](docs/Seccion_1.3.2.jpg)
* ![Sección 1.4.1](docs/Seccion_1.4.1.jpg)
* ![Sección 1.4.2](docs/Seccion_1.4.2.jpg)

### Sección 2
* ![Sección 2.1](docs/Seccion_2.1.jpg)
* ![Sección 2.2](docs/Seccion_2.2.jpg)
* ![Sección 2.3](docs/Seccion_2.3.jpg)
* ![Sección 2.4.1](docs/Seccion_2.4.1.jpg)
* ![Sección 2.4.2](docs/Seccion_2.4.2.jpg)
* ![Sección 2.4.3](docs/Seccion_2.4.3.jpg)
* ![Sección 2.5](docs/Seccion_2.5.jpg)
* ![Sección 2.6](docs/Seccion_2.6.jpg)

### Sección 3
* ![Sección 3.1](docs/Seccion_3.1.png)
* ![Sección 3.2](docs/Seccion_3.2.png)
* ![Sección 3.3](docs/Seccion_3.3.png)
* ![Sección 3.4.1](docs/Seccion_3.4.1.png)
* ![Sección 3.4.2](docs/Seccion_3.4.2.png)
* ![Sección 3.4.3](docs/Seccion_3.4.3.png)
* ![Sección 3.5](docs/Seccion_3.5.png)
* ![Sección 3.6](docs/Seccion_3.6.png)

---

## Reflexión Final
* **Tecnología más rápida de construcción**: Jetpack Compose resultó ser la opción más ágil para estructurar interfaces gracias a su paradigma declarativo, reduciendo considerablemente la cantidad de código y archivos de configuración en comparación con Views.
* **Código más legible**: Jetpack Compose y Flutter proporcionan una legibilidad sobresaliente, ya que la jerarquía de los componentes se define directamente en la estructura del código. En contraste, Android Views requiere una separación estricta entre archivos XML y controladores en Kotlin/Java.
* **Dificultades encontradas**: 
  * En *Android Views*, el mantenimiento de adaptadores y estados manuales genera código repetitivo.
  * En *Jetpack Compose*, fue necesario ajustar versiones específicas del SDK para integrar componentes recientes de Material 3.
  * En *Flutter*, se presentó un problema de compatibilidad e intermitencia con la depuración remota en el teléfono físico, por lo que se solucionó cambiando la ejecución al **navegador Google Chrome**, lo que permitió validar todas las vistas web y móviles de forma exitosa.
* **Preferencia de trabajo**: Se prefiere trabajar con Jetpack Compose para desarrollos nativos en Android debido a su perfecta integración, aunque Flutter destaca enormemente por su versatilidad multiplataforma.

---

## Referencias Consultadas
* Android Developers. (2026). *Jetpack Compose documentation*. Recuperado de https://developer.android.com/jetpack/compose
* Flutter.dev. (2026). *Flutter documentation - Build apps for any screen*. Recuperado de https://flutter.dev/docs
* Google. (2026). *Material Design 3 guidelines and components*. Recuperado de https://m3.material.io/