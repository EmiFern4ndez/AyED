package tp05.ejercicio8;

import tp01.ejercicio2.ListaGenerica;
import tp05.ejercicio1.MaxHeap;

public class TestPunto8 {
    public static int buscarKesimoMasGrande(ListaGenerica lista, int k) {
        MaxHeap heap = new MaxHeap(lista);
        int elemento;
        while (k > 1) {
            heap.eliminar();
            k--;
        }
        return (int) heap.tope();
    }


    public static void main(String[] args) {

    }
}
