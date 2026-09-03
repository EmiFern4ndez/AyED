package tp02.ejercicio3;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp02.ejercicio1.ArbolGeneral;

public class Main {
    public static void main(String[] args) {
        //Hojas
        ArbolGeneral<Integer> a = new ArbolGeneral<>(4);
        ArbolGeneral<Integer> b = new ArbolGeneral<>(7);
        ArbolGeneral<Integer> c = new ArbolGeneral<>(5);
        ListaEnlazadaGenerica<ArbolGeneral<Integer>> listaHojas1 = new ListaEnlazadaGenerica<>();
        listaHojas1.agregarFinal(a);
        listaHojas1.agregarFinal(b);
        listaHojas1.agregarFinal(c);

        ListaEnlazadaGenerica<ArbolGeneral<Integer>> listaHojas2 = new ListaEnlazadaGenerica<>();
        ArbolGeneral<Integer> d = new ArbolGeneral<>(6);
        ArbolGeneral<Integer> e = new ArbolGeneral<>(10);
        ArbolGeneral<Integer> f = new ArbolGeneral<>(18);
        listaHojas2.agregarFinal(d);
        listaHojas2.agregarFinal(e);
        listaHojas2.agregarFinal(f);

        ListaEnlazadaGenerica<ArbolGeneral<Integer>> listaHojas3 = new ListaEnlazadaGenerica<>();
        ArbolGeneral<Integer> g = new ArbolGeneral<>(9);
        ArbolGeneral<Integer> h = new ArbolGeneral<>(12);
        ArbolGeneral<Integer> i = new ArbolGeneral<>(19);
        listaHojas3.agregarFinal(g);
        listaHojas3.agregarFinal(h);
        listaHojas3.agregarFinal(i);

        //Nodos internos
        ListaEnlazadaGenerica<ArbolGeneral<Integer>> listaInternos = new ListaEnlazadaGenerica<>();
        ArbolGeneral<Integer> j = new ArbolGeneral<>(13, listaHojas1);
        ArbolGeneral<Integer> k = new ArbolGeneral<>(25, listaHojas2);
        ArbolGeneral<Integer> l = new ArbolGeneral<>(10, listaHojas3);
        listaInternos.agregarFinal(j);
        listaInternos.agregarFinal(k);
        listaInternos.agregarFinal(l);

        //Raíz
        ArbolGeneral<Integer> arbol = new ArbolGeneral<>(14, listaInternos);

        System.out.println("      SE HA CREADO EL ÁRBOL GENERAL DE ENTEROS      ");

        /* TESTER */
        System.out.println("La altura del árbol es: " + arbol.altura() +
                "\nEl ancho del árbol es: " + arbol.ancho() +
                "\nEl nivel del dato 10 es: " + arbol.nivel(10));

        System.out.println("      RECORRIDOS DEL ÁRBOL GENERAL      ");

        //Preorden
        System.out.println("\n--- Preorden ---");
        ListaEnlazadaGenerica<Integer> listaPreOrden = (ListaEnlazadaGenerica<Integer>) arbol.preOrden();
        listaPreOrden.comenzar();
        while (!listaPreOrden.fin()) {
            System.out.println("Elemento: " + listaPreOrden.proximo());
        }

        //Inorden
        System.out.println("\n--- Inorden ---");
        ListaEnlazadaGenerica<Integer> listaInOrden = (ListaEnlazadaGenerica<Integer>) arbol.inOrden();
        listaPreOrden.comenzar();
        while (!listaInOrden.fin()) {
            System.out.println("Elemento: " + listaInOrden.proximo());
        }

        //Postorden
        System.out.println("\n--- Postorden ---");
        ListaEnlazadaGenerica<Integer> listaPostOrden = (ListaEnlazadaGenerica<Integer>) arbol.postOrden();
        listaPostOrden.comenzar();
        while (!listaPostOrden.fin()) {
            System.out.println("Elemento: " + listaPostOrden.proximo());
        }

        //Por niveles
        System.out.println("\n--- Por Nivel ---");
        ListaEnlazadaGenerica<Integer> listaPorNiveles = (ListaEnlazadaGenerica<Integer>) arbol.porNiveles();
        listaPorNiveles.comenzar();
        while (!listaPorNiveles.fin()) {
            System.out.println("Elemento: " + listaPorNiveles.proximo());
        }

        //impares mayores que n
        ListaEnlazadaGenerica<Integer> listaImparesMayores;
        RecorridosAG recorridos = new RecorridosAG();
        Integer n = 10;

        System.out.println("\n      NUMEROS IMPARES MAYORES QUE " + n + " EN EL ÁRBOL GENERAL CON DIFERENTES RECORRIDOS      ");

        //Preorden
        System.out.println("\n--- Preorden ---");
        listaImparesMayores = (ListaEnlazadaGenerica<Integer>) recorridos.numerosImparesMayoresQuePreOrden(arbol, n);
        listaImparesMayores.comenzar();
        while (!listaImparesMayores.fin()) {
            System.out.println("Elemento: " + listaImparesMayores.proximo());
        }

        //Inorden
        System.out.println("\n--- Inorden ---");
        listaImparesMayores = (ListaEnlazadaGenerica<Integer>) recorridos.numerosImparesMayoresQueInOrden(arbol, n);
        listaImparesMayores.comenzar();
        while (!listaImparesMayores.fin()) {
            System.out.println("Elemento: " + listaImparesMayores.proximo());
        }

        //Postorden
        System.out.println("\n--- Postorden ---");
        listaImparesMayores = (ListaEnlazadaGenerica<Integer>) recorridos.numerosImparesMayoresQuePostOrden(arbol, n);
        listaImparesMayores.comenzar();
        while (!listaImparesMayores.fin()) {
            System.out.println("Elemento: " + listaImparesMayores.proximo());
        }

        //Por Niveles
        System.out.println("\n--- Por Niveles ---");
        listaImparesMayores = (ListaEnlazadaGenerica<Integer>) recorridos.numerosImparesMayoresQuePorNiveles(arbol, n);
        listaImparesMayores.comenzar();
        while (!listaImparesMayores.fin()) {
            System.out.println("Elemento: " + listaImparesMayores.proximo());
        }
    }
}