# Ready2Ref

## Descripción
**Ready2Ref** es un sistema de gestión para árbitros, donde se pueden registrar, editar información personal, administrar entrenamientos y gestionar pagos. La plataforma distingue entre diferentes roles de usuario:
- **ADMIN**: Puede crear árbitros, editar información de cualquier árbitro y gestionar entrenamientos.
- **USER**: Puede editar su propia información, registrar asistencias y realizar pagos.
- **ENTRENADOR**: Puede subir entrenamientos y gestionar su propio perfil.

### Clonar el repositorio
```bash
 git clone https://github.com/tuusuario/Ready2Ref.git
 cd Ready2Ref
```

## Endpoints Principales

### Autenticación
- **POST /auth/login** → Inicia sesión y devuelve un token.
- **POST /auth/refresh/token** → Refresca el token de sesión.

### Gestión de Árbitros
- **POST /arbitro/create/admin** → Crea un nuevo árbitro (**ADMIN**).
- **POST /arbitro/create/user** → Crea un nuevo árbitro (**USER**).
- **PUT /arbitro/edit/user/me** → Permite a un **USER** editar su perfil.
- **PUT /arbitro/edit/admin/me** → Permite a un **ADMIN** editar su propio perfil.
- **PUT /arbitro/edit/admin/{username}** → Un **ADMIN** edita a cualquier árbitro.
- **GET /arbitro/search** → Buscar árbitros.
- **DELETE /delete/{username}** → Elimina un árbitro (**ADMIN**).

### Gestión de Entrenadores
- **POST /entrenador/create** → Crea un entrenador.
- **PUT /entrenador/edit/me** → Edita su perfil.
- **POST /upload** → Sube entrenamientos.
- **GET /download/{id_entreno}** → Descarga entrenamientos.

### Mensajería
- **POST /mensaje/create/admin** → Un **ADMIN** puede enviar mensajes.
- **GET /mensaje/search** → Un usuario puede buscar mensajes.

### Seguridad
- **PUT /edit/contrasenia** → Cambio de contraseña para cualquier usuario.

## Pruebas con Postman
Puedes importar la colección de Postman ubicada en este repositorio para probar los endpoints.
1. Abre Postman
2. Ve a **Importar** > **Subir Archivo** y selecciona `Ready2Ref.postman_collection.json`
3. Configura las variables globales en Postman con los tokens necesarios.

## To be continued
- Hay que decir que el proyecto no esta finalizado y esta en proceso de mejora y de añadir nuevos aspectos

