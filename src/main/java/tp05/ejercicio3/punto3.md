# Respuestas sobre Heaps (Montículos)

## a) ¿Cuántos elementos hay, al menos, en una heap de altura $h$?

**Respuesta:** En una heap de altura $h$, la cantidad mínima de elementos es $2^h$ (asumiendo que la altura de la raíz es $h = 0$).

### Justificación:

Para minimizar la cantidad de elementos en una heap de altura $h$:

1. Los niveles desde el $0$ hasta el $h-1$ deben estar completamente llenos.

2. El último nivel (nivel $h$) debe contener el mínimo número de nodos posible para mantener esa altura, es decir, **exactamente** $1$ **nodo**.

La cantidad de elementos en un árbol binario completo de altura $h-1$ es:

$$2^h - 1$$

Al sumar el único elemento presente en el nivel $h$, obtenemos:

$$\text{Total mínimo} = (2^h - 1) + 1 = 2^h$$

*(Nota: Si la convención usada define que un árbol de un solo nodo tiene altura $h = 1$, la fórmula equivalente para la cantidad mínima de elementos es $2^{h-1}$).*

## b) ¿Dónde se encuentra ubicado el elemento mínimo en una max-heap?

**Respuesta:** El elemento mínimo se encuentra en **una de las hojas del árbol** (en un arreglo, esto corresponde a la segunda mitad del vector, desde el índice $\lfloor n/2 \rfloor$ hasta $n-1$).

### Justificación:

Por la propiedad fundamental de una **max-heap**, la clave de cada nodo es mayor o igual que las claves de sus nodos hijos ($A[\text{padre}] \ge A[\text{hijo}]$).

Esto implica que ningún nodo interno (nodo que posee al menos un hijo) puede ser el mínimo global, ya que sus hijos siempre tendrán valores menores o iguales a él. Por lo tanto, por descarte, el elemento con el valor mínimo no puede tener descendientes y **debe residir obligatoriamente en uno de los nodos hoja**.

## c) ¿El arreglo `[23, 17, 14, 6, 13, 10, 1, 5, 7, 12]` es una max-heap?

**Respuesta:** **No**, el arreglo no representa una max-heap.

### Justificación:

Para que un arreglo indexado de $0$ a $n-1$ represente una max-heap válida, se debe cumplir que para todo índice $i$, la clave del padre sea mayor o igual que la de sus hijos:

* **Hijo izquierdo:** $2i + 1$

* **Hijo derecho:** $2i + 2$

Evaluando los nodos del arreglo:

* **Índice 0 (valor 23):** Hijos en `A[1]=17` y `A[2]=14` $\rightarrow 23 \ge 17$ y $23 \ge 14$ *(Cumple)*.

* **Índice 1 (valor 17):** Hijos en `A[3]=6` y `A[4]=13` $\rightarrow 17 \ge 6$ y $17 \ge 13$ *(Cumple)*.

* **Índice 2 (valor 14):** Hijos en `A[5]=10` y `A[6]=1` $\rightarrow 14 \ge 10$ y $14 \ge 1$ *(Cumple)*.

* **Índice 3 (valor 6):** Hijos en `A[7]=5` y `A[8]=7`.

En el **índice 3**, la propiedad de max-heap se viola ya que el padre tiene valor $6$ y su hijo derecho (`A[8]`) tiene valor $7$ ($6 < 7$).