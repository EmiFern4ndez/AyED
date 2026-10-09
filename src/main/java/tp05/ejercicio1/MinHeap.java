package tp05.ejercicio1;

import tp01.ejercicio2.ListaGenerica;

public class MinHeap<T extends Comparable<T>> implements ColaPrioridades<T> {
    private T[] datos;
    private int cantidadElementos;

    public MinHeap() {
        this.datos = (T[]) new Comparable[100];
        this.cantidadElementos = 0;
    }

    public MinHeap(ListaGenerica<T> lista) {
        // Guardar la cantidad de elementos
        this.cantidadElementos = lista.tamanio();
        // Inicializar el arreglo de datos (cast necesario en Java para genéricos)
        this.datos = (T[]) new Comparable[this.cantidadElementos];
        // Copiar todos los elementos de la lista al arreglo interno
        lista.comenzar();
        int i = 0;
        while (!lista.fin()) {
            this.datos[i] = lista.proximo();
            i++;
        }
        // Acomodar los elementos para cumplir la propiedad de MaxHeap
        for (int p = (this.cantidadElementos / 2) - 1; p >= 0; p--) {
            this.percolate_down(p);
        }
    }

    @Override
    public boolean esVacia() {
        return this.cantidadElementos == 0;
    }

    @Override
    public void eliminar() {
        if (!this.esVacia()) {
            T elemento = this.datos[0];
            this.datos[0] = this.datos[this.cantidadElementos];
            this.cantidadElementos--;
            this.percolate_down(0);
        }
    }

    @Override
    public boolean agregar(T elemento) {
        boolean estado = false;
        if (this.cantidadElementos < 100) {
            this.datos[this.cantidadElementos] = elemento;
            this.cantidadElementos++;
            estado = true;
            if (this.cantidadElementos > 1) {
                this.percolate_up();
            }
        }
        return estado;
    }

    @Override
    public T tope() {
        if (this.esVacia()) {
            return null;
        }
        return this.datos[0];
    }

    private void percolate_up() {
        if (this.esVacia()) {
            return;
        }
        int pos = this.cantidadElementos - 1;
        T temp = this.datos[pos];

        // Mientras no sea la raíz y el elemento sea MENOR que su padre, sube
        while (pos > 0 && temp.compareTo(this.datos[(pos - 1) / 2]) < 0) {
            this.datos[pos] = this.datos[(pos - 1) / 2];
            pos = (pos - 1) / 2;
        }
        this.datos[pos] = temp;
    }

    private void percolate_down(int pos) {
        T candidato = this.datos[pos];
        boolean stop_percolate = false;

        // Mientras el nodo actual tenga al menos un hijo izquierdo
        while ((2 * pos + 1 < this.cantidadElementos) && (!stop_percolate)) {
            int h_min = 2 * pos + 1; // Por defecto, elegimos el hijo izquierdo

            // Si existe hijo derecho Y el hijo derecho es MENOR que el izquierdo
            if (h_min + 1 < this.cantidadElementos && this.datos[h_min + 1].compareTo(this.datos[h_min]) < 0) {
                h_min++; // El hijo derecho es el menor real
            }

            // Si el candidato es MAYOR que el menor de sus hijos, debe bajar
            if (candidato.compareTo(this.datos[h_min]) > 0) {
                this.datos[pos] = this.datos[h_min]; // Subimos al hijo menor
                pos = h_min;                         // Bajamos nuestra posición actual
            } else {
                stop_percolate = true;               // El candidato ya es menor o igual, paramos
            }
        }
        this.datos[pos] = candidato; // Colocamos al candidato en su lugar final
    }
}
