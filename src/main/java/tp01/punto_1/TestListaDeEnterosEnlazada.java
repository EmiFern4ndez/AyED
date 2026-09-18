package tp01.punto_1;

import tp01.ejercicio1.ListaDeEnterosEnlazada;

public class TestListaDeEnterosEnlazada {
    public static void main(String[] args) {
        ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();

        //1.3
        lista.agregarInicio(10);
        lista.agregarInicio(20);
        lista.agregarInicio(30);
        lista.agregarInicio(40);
        lista.agregarInicio(50);

        System.out.println("Forma iterativa\n");
        lista.comenzar();
        int pos = 0;
        while (!lista.fin()) {
            System.out.println("Elemento:" + lista.proximo() + " Pos:" + pos);
            pos++;
        }

        //1.5
        System.out.println("\nForma recursiva (a la inversa)\n");
        lista.comenzar();
        imprimierALaInversa(lista);
    }

    public static void imprimierALaInversa(ListaDeEnterosEnlazada lista) {
        imprimirRecursivo(lista, 0);
    }

    public static void imprimirRecursivo(ListaDeEnterosEnlazada lista, int pos) {
        if (!lista.fin()) {
            Integer elem = lista.proximo(); // Avanza el puntero interno y guarda el valor
            imprimirRecursivo(lista, pos + 1);
            System.out.println("Elemento: " + elem + " Pos: " + pos);
        }
    }
}
