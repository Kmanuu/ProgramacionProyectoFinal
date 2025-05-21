# RetroDrip – Sistema de Pedidos y Gestión de Productos Retro

Proyecto final de Programación (1º DAM) desarrollado en Java usando JavaFX, MySQL y JDBC.

## 🧠 Descripción

**RetroDrip** es una aplicación de escritorio que simula una tienda de ropa retro, como camisetas de fútbol vintage y zapatillas. Hay dos tipos de usuarios: **clientes** y **vendedores**. 

- Los **clientes** pueden ver productos y hacer pedidos.
- Los **vendedores** pueden añadir, editar, eliminar y ver productos.

La aplicación se conecta a una base de datos MySQL y utiliza XML + JAXB para gestionar las propiedades de conexión. Todo está estructurado con POO, DAO y Maven.

---

## ⚙️ Tecnologías utilizadas

- Java 17  
- JavaFX  
- JDBC (MySQL)  
- JAXB (para configuración XML)  
- XAMPP (MySQL)  
- Maven  
- IntelliJ IDEA

---

## 📦 Funcionalidades principales

### Cliente:
- Iniciar sesión o registrarse
- Ver productos disponibles
- Hacer pedidos (con validación de stock)
- Cerrar sesión

### Vendedor:
- Iniciar sesión o registrarse
- Ver productos propios
- Añadir producto
- Editar producto
- Eliminar producto
- Cerrar sesión

---

## 🗃️ Base de Datos

- Tablas: `usuario`, `cliente`, `vendedor`, `producto`, `pedido`, `pedido_producto`
- Relación 1:N → Cliente - Pedido
- Relación N:M → Pedido - Producto (gestionada con tabla intermedia `pedido_producto`)
- Conexión externa configurada con archivo `connection.xml` cargado por JAXB.

---

## 🧱 Estructura del proyecto

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── baseDatos/
│   │   ├── model/
│   │   ├── DAO/
│   │   └── ui/
│   └── resources/
│       └── connection.xml
📝 Notas del autor
Este proyecto lo he ido mejorando poco a poco, enfrentándome a muchos errores, especialmente con GitHub y el manejo de sesiones. Aun así, he conseguido hacer funcionar todo y estructurarlo de forma clara. Me siento orgulloso del resultado final. 🚀

💾 Cómo ejecutar
Abre el proyecto en IntelliJ IDEA como proyecto Maven.

Asegúrate de tener XAMPP encendido y MySQL corriendo.

Verifica que el archivo connection.xml en la carpeta /resources/ tenga los datos correctos de tu conexión.

Abre la configuración llamada JavaFXLogin (arriba a la derecha en IntelliJ).

Ejecuta esa configuración para lanzar el programa desde Main.java.

📌 Autor
Manuel Laguna Prieto
1º DAM – IES Francisco de los Ríos



