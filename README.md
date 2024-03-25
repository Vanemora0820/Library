Biblioteca en Java
Este repositorio contiene la implementación de un sistema de gestión de biblioteca en Java. El sistema es capaz de realizar las siguientes operaciones:

Agregar un nuevo libro al catálogo de la biblioteca con su título, autor y año de publicación.
Prestar un libro a un usuario.
Devolver un libro prestado.
Mostrar el catálogo completo de la biblioteca.
Mostrar los libros prestados actualmente.
Requerimientos Técnicos
Debes implementar las siguientes clases: Libro, Biblioteca, Usuario, Main.
La clase Libro debe tener atributos para el título, autor y año de publicación.
La clase Biblioteca debe tener una lista de libros disponibles, una lista de libros prestados y métodos para agregar un libro al catálogo, prestar un libro, devolver un libro, mostrar el catálogo completo y mostrar los libros prestados.
La clase Usuario debe tener un nombre y un identificador único.
El programa debe ser interactivo, permitiendo al usuario seleccionar las diferentes operaciones disponibles.
Utiliza estructuras de datos adecuadas para almacenar los libros disponibles y prestados.
Implementa manejo de excepciones donde sea necesario.

Configuración de la Base de Datos
Este sistema está conectado a una base de datos MySQL. A continuación se muestran las credenciales y la configuración utilizadas:
URL: jdbc:mysql://localhost:3306/BOOKSTORE
Usuario: root
Contraseña: admin
Driver: com.mysql.cj.jdbc.Driver
Modo de actualización de la base de datos: update

Instrucciones de Uso
Clona este repositorio en tu máquina local.
Asegúrate de tener configurada una base de datos MySQL con las credenciales mencionadas.
Ejecuta el programa Java desde el archivo Main.java.
Sigue las instrucciones proporcionadas por el programa para interactuar con el sistema de gestión de biblioteca.
