package punto_1;

import tp01.ejercicio1.ListaDeEnterosConArreglos;

public class TestListaDeEnterosConArreglos {
    public static void main(String[] args) {
        //Punto 1.2
        ListaDeEnterosConArreglos lista = new ListaDeEnterosConArreglos();

        lista.agregarInicio(10);
        lista.agregarInicio(20);
        lista.agregarInicio(30);
        lista.agregarInicio(40);
        lista.agregarInicio(50);

        System.out.println("Forma iterativa\n");
        lista.comenzar();
        if (!lista.esVacia()) {
            for (int i = 0; i < lista.tamanio(); i++) {
                System.out.println("Elemento: " + lista.elemento(i) + " Pos: " + i);
            }
        }
    }
}
