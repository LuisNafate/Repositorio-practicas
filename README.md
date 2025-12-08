# F1 App - Aplicación Android de Fórmula 1

Aplicación Android desarrollada en Kotlin con Jetpack Compose que consume la **jolpica-f1 API** para mostrar información actualizada de Fórmula 1.

## Características Principales

### Arquitectura MVVM
- **ViewModel** para gestión de estados
- **Repository** para abstracción de fuentes de datos
- **Room Database** para persistencia offline
- **Retrofit** para consumo de API REST
- **Corrutinas** para operaciones asíncronas

### Almacenamiento Offline
- Room Database con dos tablas:
  - `favorite_races` - Carreras favoritas
  - `favorite_drivers` - Pilotos favoritos
- DAO con operaciones CRUD
- Flow para actualizaciones reactivas
- Mappers para conversión de entidades

### Navegación
- Navigation Compose
- Bottom Navigation Bar con 3 secciones:
  - Carreras
  - Búsqueda
  - Favoritos

## Cumplimiento de Requisitos

 **Arquitectura MVVM**: Implementada con ViewModels, Repository y estados
 **Retrofit**: Configurado con OkHttp y Gson para consumo de API
 **4 Pantallas**: Splash, Lista, Detalle, Búsqueda + Favoritos
 **Buscador Personalizado**: Diseño único con filtrado en tiempo real
 **Room y Favoritos Offline**: Base de datos local completamente funcional
 **Corrutinas**: Operaciones asíncronas en todos los ViewModels
 **Manejo de Estados**: Loading, Success, Error en todas las pantallas
 **Material Design 3**: Tema personalizado con colores de F1

