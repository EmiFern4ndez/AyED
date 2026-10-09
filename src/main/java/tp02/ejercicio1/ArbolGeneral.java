package tp02.ejercicio1;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;
import tp01.ejercicio3.ColaGenerica;

public class ArbolGeneral<T> {
    private T dato;
    private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

    public ArbolGeneral(T dato) {
        this.dato = dato;
    }

    public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
        this(dato);
        if (hijos == null)
            this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
        else
            this.hijos = hijos;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public ListaGenerica<ArbolGeneral<T>> getHijos() {
        return this.hijos;
    }

    public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
        if (hijos == null)
            this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
        else
            this.hijos = hijos;
    }

    public void agregarHijo(ArbolGeneral<T> unHijo) {
        this.getHijos().agregarFinal(unHijo);
    }

    public boolean esHoja() {
        return !this.tieneHijos();
    }

    public boolean tieneHijos() {
        return !this.hijos.esVacia();
    }

    public boolean esVacio() {
        return this.dato == null && !this.tieneHijos();
    }

    public void eliminarHijo(ArbolGeneral<T> hijo) {
        if (this.tieneHijos()) {
            ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
            if (hijos.incluye(hijo))
                hijos.eliminar(hijo);
        }
    }

    public ListaEnlazadaGenerica<T> preOrden() {
        ListaEnlazadaGenerica<T> lista = new ListaEnlazadaGenerica<T>();
        this.preOrdenRecursivo(lista);
        return lista;
    }

    private void preOrdenRecursivo(ListaEnlazadaGenerica<T> lista) {
        lista.agregarFinal(this.getDato());
        if (this.tieneHijos()) {
            ListaGenerica<ArbolGeneral<T>> listaHijos = this.getHijos();
            listaHijos.comenzar();
            while (!listaHijos.fin()) {
                ArbolGeneral<T> hijo = listaHijos.proximo();
                hijo.preOrdenRecursivo(lista);
            }
        }
    }

    public ListaEnlazadaGenerica<T> inOrden() {
        ListaEnlazadaGenerica<T> lista = new ListaEnlazadaGenerica<T>();
        this.inOrdenRecursivo(lista);
        return lista;
    }

    private void inOrdenRecursivo(ListaEnlazadaGenerica<T> lista) {
        ListaGenerica<ArbolGeneral<T>> listaHijos = this.getHijos();
        if (this.tieneHijos()) {
            listaHijos.comenzar();
            if (!listaHijos.fin()) {
                ArbolGeneral<T> primerHijo = listaHijos.proximo();
                primerHijo.inOrdenRecursivo(lista);
            }
        }
        lista.agregarFinal(this.getDato());
        if (this.tieneHijos()) {
            while (!listaHijos.fin()) {
                ArbolGeneral<T> restoHijo = listaHijos.proximo();
                restoHijo.inOrdenRecursivo(lista);
            }
        }
    }

    public ListaEnlazadaGenerica<T> postOrden() {
        ListaEnlazadaGenerica<T> lista = new ListaEnlazadaGenerica<T>();
        this.postOrdenRecursivo(lista);
        return lista;
    }

    private void postOrdenRecursivo(ListaEnlazadaGenerica<T> lista) {
        if (this.tieneHijos()) {
            ListaGenerica<ArbolGeneral<T>> listaHijos = this.getHijos();
            listaHijos.comenzar();
            while (!listaHijos.fin()) {
                ArbolGeneral<T> hijo = listaHijos.proximo();
                hijo.postOrdenRecursivo(lista);
            }
        }
        lista.agregarFinal(this.getDato());
    }

    public ListaEnlazadaGenerica<T> porNiveles() {
        ListaEnlazadaGenerica<T> resultado = new ListaEnlazadaGenerica<T>();
        if (this.esVacio()) {
            return resultado;
        }
        ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
        cola.encolar(this);
        while (!cola.esVacia()) {
            ArbolGeneral<T> actual = cola.desencolar();
            resultado.agregarFinal(actual.getDato());

            if (actual.tieneHijos()) {
                ListaGenerica<ArbolGeneral<T>> hijos = actual.getHijos();
                hijos.comenzar();
                while (!hijos.fin()) {
                    cola.encolar(hijos.proximo());
                }
            }
        }
        return resultado;
    }

    public Integer altura() {
        int maxAlt = 0;
        if (this.esVacio()) {
            return -1;
        }
        if (this.esHoja()) {
            return maxAlt;
        }
        ListaGenerica<ArbolGeneral<T>> listaHijos = this.getHijos();
        listaHijos.comenzar();
        while (!listaHijos.fin()) {
            int altHijo = listaHijos.proximo().altura();
            if (altHijo > maxAlt) {
                maxAlt = altHijo;
            }
        }
        return maxAlt + 1;
    }

    public Integer nivel(T dato) {
        if (this.esVacio()) return -1;
        ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
        cola.encolar(this);
        cola.encolar(null);

        int nivelActual = 0;

        while (!cola.esVacia()) {
            ArbolGeneral<T> actual = cola.desencolar();
            if (actual != null) {
                if (actual.getDato().equals(dato)) {
                    return nivelActual;
                }
                if (actual.tieneHijos()) {
                    ListaGenerica<ArbolGeneral<T>> hijos = actual.getHijos();
                    hijos.comenzar();
                    while (!hijos.fin()) {
                        cola.encolar(hijos.proximo());
                    }
                }
            } else if (!cola.esVacia()) {
                nivelActual++;
                cola.encolar(null);
            }
        }
        return -1;
    }

    public Integer ancho() {
        if (this.esVacio()) return 0;
        ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
        cola.encolar(this);
        cola.encolar(null);

        int maxAncho = 0;
        int nodosEnNivel = 0;

        while (!cola.esVacia()) {
            ArbolGeneral<T> actual = cola.desencolar();
            if (actual != null) {
                nodosEnNivel++;
                if (actual.tieneHijos()) {
                    ListaGenerica<ArbolGeneral<T>> hijos = actual.getHijos();
                    hijos.comenzar();
                    while (!hijos.fin()) {
                        cola.encolar(hijos.proximo());
                    }
                }
            } else {
                if (nodosEnNivel > maxAncho) {
                    maxAncho = nodosEnNivel;
                }
                nodosEnNivel = 0;
                if (!cola.esVacia()) {
                    cola.encolar(null);
                }
            }
        }
        return maxAncho;
    }
}