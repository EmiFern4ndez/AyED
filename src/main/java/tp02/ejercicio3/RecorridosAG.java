package tp02.ejercicio3;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;
import tp02.ejercicio1.ArbolGeneral;

public class RecorridosAG {

    private boolean imparMayorQue(Integer n, Integer numero) {
        return numero % 2 != 0 && numero > n;
    }

    public ListaGenerica<Integer> numerosImparesMayoresQuePreOrden(ArbolGeneral<Integer> a, Integer n) {
        ListaEnlazadaGenerica<Integer> listaImparesMayores = new ListaEnlazadaGenerica<>();
        if (a != null && !a.esVacio()) {
            ListaGenerica<Integer> listaRecorrido = a.preOrden();
            listaRecorrido.comenzar();
            while (!listaRecorrido.fin()) {
                Integer numero = listaRecorrido.proximo();
                if (imparMayorQue(n, numero)) {
                    listaImparesMayores.agregarFinal(numero);
                }
            }
        }
        return listaImparesMayores;
    }

    public ListaGenerica<Integer> numerosImparesMayoresQueInOrden(ArbolGeneral<Integer> a, Integer n) {
        ListaEnlazadaGenerica<Integer> listaImparesMayores = new ListaEnlazadaGenerica<>();
        if (a != null && !a.esVacio()) {
            ListaGenerica<Integer> listaRecorrido = a.inOrden();
            listaRecorrido.comenzar();
            while (!listaRecorrido.fin()) {
                Integer numero = listaRecorrido.proximo();
                if (imparMayorQue(n, numero)) {
                    listaImparesMayores.agregarFinal(numero);
                }
            }
        }
        return listaImparesMayores;
    }

    public ListaGenerica<Integer> numerosImparesMayoresQuePostOrden(ArbolGeneral<Integer> a, Integer n) {
        ListaEnlazadaGenerica<Integer> listaImparesMayores = new ListaEnlazadaGenerica<>();
        if (a != null && !a.esVacio()) {
            ListaGenerica<Integer> listaRecorrido = a.postOrden();
            listaRecorrido.comenzar();
            while (!listaRecorrido.fin()) {
                Integer numero = listaRecorrido.proximo();
                if (imparMayorQue(n, numero)) {
                    listaImparesMayores.agregarFinal(numero);
                }
            }
        }
        return listaImparesMayores;
    }

    public ListaGenerica<Integer> numerosImparesMayoresQuePorNiveles(ArbolGeneral<Integer> a, Integer n) {
        ListaEnlazadaGenerica<Integer> listaImparesMayores = new ListaEnlazadaGenerica<>();
        if (a != null && !a.esVacio()) {
            ListaGenerica<Integer> listaRecorrido = a.porNiveles();
            listaRecorrido.comenzar();
            while (!listaRecorrido.fin()) {
                Integer numero = listaRecorrido.proximo();
                if (imparMayorQue(n, numero)) {
                    listaImparesMayores.agregarFinal(numero);
                }
            }
        }
        return listaImparesMayores;
    }
}