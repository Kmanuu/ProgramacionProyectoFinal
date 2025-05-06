
---

## 💾 Base de datos

La base de datos `retrodrip` contiene las siguientes tablas:
- Cliente
- Producto
- Pedido
- PedidoProducto

Con relaciones:
- Un cliente puede tener varios pedidos (1:N)
- Un pedido puede incluir varios productos (N:M, resuelto con PedidoProducto)

---

## ✨ Funcionalidades

✅ Conexión a MySQL con configuración en `connection.xml`  
✅ Clases modelo (Cliente, Producto, Pedido, PedidoProducto)  
✅ DAOs con operaciones CRUD básicas  
✅ Pruebas en consola (`pruebas.java`)  
✅ Diseño basado en el patrón DAO  
✅ Diagrama ER documentado

---

## 💥 Próximas mejoras (opcional para nota extra)

- Validaciones de entrada (emails, stock positivo, etc.)  
- Interfaz gráfica (JavaFX)  
- Registro de logs de errores  
- Exportación de datos a XML/CSV

---



## 💻 Cómo ejecutar

1. Importar el proyecto en IntelliJ IDEA  
2. Configurar la base de datos en XAMPP (importar el script SQL)  
3. Configurar `connection.xml` con tus credenciales  
4. Ejecutar `pruebas.java` para hacer las pruebas

---

## 👨‍💻 Autor

Kmanuu  
GitHub: [github.com/Kmanuu](https://github.com/Kmanuu)
