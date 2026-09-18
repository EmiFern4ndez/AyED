package tp01.punto_2;

import tp01.ejercicio2.ListaEnlazadaGenerica;

public class TestListaEnlazadaGenerica {
    public static void main(String[] args) {
        ListaEnlazadaGenerica lista = new ListaEnlazadaGenerica();

        var estudiante1 = new Estudiante("Perez", "Juan", "12345");
        var estudiante2 = new Estudiante("Gomez", "Maria", "67890");
        var estudiante3 = new Estudiante("Lopez", "Carlos", "54321");
        var estudiante4 = new Estudiante("Fernandez", "Ana", "98765");

        lista.agregarFinal(estudiante1);
        lista.agregarFinal(estudiante2);
        lista.agregarFinal(estudiante3);
        lista.agregarFinal(estudiante4);

        System.out.println("Lista de estudiantes:\n");
        lista.comenzar();
        int pos = 1;
        while (!lista.fin()) {
            Estudiante elemento = (Estudiante) lista.elemento(pos);
            System.out.println(elemento.misDatos() + "\n");
            lista.proximo();
            pos++;
        }
    }
}
