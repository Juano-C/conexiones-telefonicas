# Trabajo Practico n2 de Programacion 3

TP de la universidad nacional general sarmiento

El objetivo del trabajo practico es implementar una aplicacion para planificar conexiones telefonicas entre localidades ubicadas en regiones despobladas.

Dado un conjunto de localidades, se debe proporcionar un arbol generador mınimo entre ellas e informar el costo de la solucion.

La aplicacion debe permitir al usuario registrar una serie de localidades, incluyendo el nombre, provincia, latitud y longitud de cada una. Ademas, se deben proporcionar los siguientes costos:

   • Costo en pesos por kilometro de una conexion entre dos localidades.
   
   • Porcentaje de aumento del costo si la conexion tiene mas de 300 km.
   
   • Costo fijo que se agrega si la conexion involucra localidades de dos provincias distintas.

Cuando el usuario solicita la planificacion, se debe resolver el problema de arbol generador mınimo sobre el grafo completo dado por todas las localidades, y cuyas aristas tienen costos calculados de acuerdo con los ıtems anteriores. Se debe presentar al usuario la solucion, especificando las conexiones telefonicas a construir y el costo total de las instalaciones.
