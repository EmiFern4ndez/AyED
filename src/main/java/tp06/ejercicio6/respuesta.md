# Resumen y Teoría de Grafos: Representaciones, Recorridos y Algoritmos

## 1. Introducción a las Representaciones de Grafos (Matriz vs. Lista de Adyacencia)

### a. ¿Cuándo usar Matriz de Adyacencias vs. Lista de Adyacencias?

* **Matriz de Adyacencias:**
  * **Condición principal:** Se prefiere en **grafos densos** (donde la cantidad de aristas $E$ está muy cerca del máximo posible, $E \approx V^2$) o cuando el tamaño del grafo es pequeño y fijo.
  * **Fundamentación:** Ofrece acceso directo en tiempo $O(1)$ para consultar si existe una arista entre dos vértices específicos (`esAdyacente(u, v)`). Su desventaja es el alto desperdicio de memoria en grafos dispersos al reservar un espacio fijo de tamaño $V \times V$.

* **Lista de Adyacencias:**
  * **Condición principal:** Se prefiere en **grafos dispersos** (la gran mayoría de los casos reales, donde $E$ es mucho menor que $V^2$).
  * **Fundamentación:** Optimiza el uso de memoria a un orden de $O(V + E)$, almacenando únicamente las aristas que existen de manera dinámica. Además, acelera considerablemente los recorridos de vecinos por vértice.

### b. Parámetros para la estimación del orden de ejecución

* **Para grafos densos:**
  * **Parámetro principal:** El número de vértices ($V$).
  * **Fundamentación:** Como las aristas $E$ son proporcionales a $V^2$, los términos dependientes de $E$ quedan dominados por $V^2$ (por ejemplo, complejidades de orden $O(V^2)$).
* **Para grafos dispersos:**
  * **Parámetros principales:** Tanto el número de vértices ($V$) como el número de aristas ($E$).
  * **Fundamentación:** Dado que $E$ puede ser muy pequeño frente a $V^2$, el rendimiento real del algoritmo depende estrechamente de cuántas aristas se procesan (ej. algoritmos como Dijkstra con colas de prioridad con complejidad $O((V + E) \log V)$).

### c. Matriz resultante en un grafo no dirigido

* **Característica principal:** La matriz de adyacencia resultante es **simétrica** con respecto a su diagonal principal ($M[i][j] == M[j][i]$).
* **Fundamentación:** En un grafo no dirigido, una arista entre $i$ y $j$ representa una conexión bidireccional mutua. Por lo tanto, cualquier valor registrado en la salida de $i$ hacia $j$ debe verse reflejado idénticamente en la celda simétrica de $j$ hacia $i$.

---

## 2. Conceptos Clave de Recorridos y Algoritmos

### DFS (Depth First Search / Recorrido en Profundidad)
* **Lógica:** Explora lo más profundo posible por un camino antes de retroceder.
* **Estructura mental:** Funciona mediante una **Pila (LIFO)**, ya sea simulada explícitamente o mediante la pila de llamadas de la recursividad.
* **Característica:** Utiliza un arreglo de marcas (`boolean[]`) para evitar bucles infinitos y un bucle externo para asegurar la visita en grafos disconexos.

### BFS (Breadth First Search / Recorrido en Amplitud)
* **Lógica:** Explora el grafo por capas u ondas concéntricas (primero todos los vecinos directos a 1 paso, luego a 2, etc.).
* **Estructura mental:** Utiliza estrictamente una **Cola (FIFO)**.
* **Propiedad en no ponderados:** El primer camino que encuentra BFS al llegar al destino es garantizadamente el camino más corto en cantidad de pasos.

### Orden Topológico (DAG)
* **Definición:** Es una asignación de orden lineal para los vértices de un Grafo Dirigido Acíclico (DAG) de modo que si existe una arista $(v, w)$, el vértice $v$ aparece estrictamente antes que $w$ en la lista.
* **Regla práctica:** Solo se pueden "poner" o procesar aquellos vértices que no tengan dependencias pendientes (es decir, ninguna flecha apuntando hacia ellos en ese momento).

### Backtracking (Búsqueda con Retroceso)
* **Aplicación:** Ideal para problemas con restricciones complejas (como el paseo en bicicleta en Oslo).
* **Funcionamiento:** Avanza probando rutas, marcando los nodos visitados en el camino actual. Si llega a un callejón sin salida o infringe una restricción (tiempo máximo, lugares prohibidos), **desmarca el vértice** (`marca = false`) al retroceder para liberar el camino y permitir que otras rutas alternativas puedan utilizarlo posteriormente.

### Camino Mínimo: Algoritmo de Dijkstra
* **Aplicación:** Grafos ponderados con costos o tiempos distintos.
* **Funcionamiento:** Utiliza un enfoque avaro (greedy) manteniendo arreglos de distancias mínimas y nodos previos. En cada paso selecciona el vértice no visitado con menor distancia acumulada, "relaja" las aristas de sus vecinos y, al finalizar, reconstruye el camino mínimo paso a paso desde el destino hacia el origen.