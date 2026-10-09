package tp05.ejercicio1;

import tp01.ejercicio2.ListaGenerica;

public class MaxHeap<T extends Comparable<T>> implements ColaPrioridades<T> {
    private T[] datos;
    private int cantidadElementos;

    public MaxHeap() {
        this.datos = (T[]) new Comparable[100];
        this.cantidadElementos = 0;
    }

    public MaxHeap(ListaGenerica<T> lista) {
        // 1. Guardar la cantidad de elementos
        this.cantidadElementos = lista.tamanio();
        // 2. Inicializar el arreglo de datos (cast necesario en Java para genéricos)
        this.datos = (T[]) new Comparable[this.cantidadElementos];
        // 3. Copiar todos los elementos de la lista al arreglo interno
        lista.comenzar();
        int i = 0;
        while (!lista.fin()) {
            this.datos[i] = lista.proximo();
            i++;
        }
    }

    @Override
    public boolean esVacia() {
        return this.cantidadElementos == 0;
    }

    @Override
    public void eliminar() {
        if (!this.esVacia()) {
            T elemento = this.datos[0]; //
            this.datos[0] = this.datos[this.cantidadElementos - 1];
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
        return this.datos[0];
    }

    private void percolate_up() {
        if (this.esVacia()) {
            return;
        }
        int pos = this.cantidadElementos - 1;
        T temp = this.datos[pos];
        while ((((pos) / 2) > this.cantidadElementos) && (this.datos[pos / 2].compareTo(temp) > 0)) {
            this.datos[pos] = this.datos[pos / 2];
            pos = (pos / 2);
        }
        this.datos[pos] = temp;
    }

    private void percolate_down(int pos) {
        T candidato = this.datos[pos];
        boolean stop_percolate = false;
        // Mientras el nodo actual tenga al menos un hijo izquierdo
        while ((2 * pos + 1 < this.cantidadElementos) && (!stop_percolate)) {
            int h_max = 2 * pos + 1; // Por defecto, elegimos el hijo izquierdo
            // Si existe hijo derecho Y el hijo derecho es MAYOR que el izquierdo
            if (h_max + 1 < this.cantidadElementos && this.datos[h_max + 1].compareTo(this.datos[h_max]) > 0) {
                h_max++; // El hijo derecho es el mayor real
            }
            // Si el candidato es MENOR que el mayor de sus hijos, debe bajar
            if (candidato.compareTo(this.datos[h_max]) < 0) {
                this.datos[pos] = this.datos[h_max]; // Subimos al hijo mayor
                pos = h_max;                         // Bajamos nuestra posición actual
            } else {
                stop_percolate = true;               // El candidato ya es mayor o igual, paramos
            }
        }
        this.datos[pos] = candidato; // Colocamos al candidato en su lugar final
    }

}
