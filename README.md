# Grafo con DFS y BFS

Este programa crea un grafo no dirigido usando una matriz de adyacencia. Un grafo es un conjunto de vertices conectados entre si. Como es no dirigido, cada conexion funciona en los dos sentidos: al conectar A con B, tambien queda registrada la conexion de B con A.

Cada vertice tiene una etiqueta y una marca llamada `visited`, que permite recordar si ya fue recorrido. El metodo `addEdge` guarda las conexiones en la matriz. Para empezar, el ejemplo de `main` crea seis vertices y los conecta.

El programa incluye dos formas de recorrer el grafo:

- **DFS** (busqueda en profundidad): visita un vertice y sigue por un vecino disponible hasta donde pueda. Cuando ya no encuentra vecinos nuevos, vuelve atras y continua con otra conexion. En el codigo se implementa con llamadas recursivas.
- **BFS** (busqueda en amplitud): visita primero el vertice inicial y luego sus vecinos cercanos, avanzando por niveles. Usa una cola para mantener el orden de visita.

Antes de cada recorrido se reinicia la marca `visited`, asi DFS y BFS pueden ejecutarse uno despues del otro sin interferirse. Ambos comienzan en el vertice indicado y recorren solo la parte conectada con ese inicio.

## Ejecutar

Desde una terminal ubicada en esta carpeta:

```text
javac taller.java
java taller
```

Con las conexiones de ejemplo, DFS imprime `A -> B -> D -> E -> C -> F` y BFS imprime `A -> B -> C -> D -> E -> F`.
