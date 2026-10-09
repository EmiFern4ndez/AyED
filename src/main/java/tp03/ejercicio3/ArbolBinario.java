package tp03.ejercicio3;

import tp01.ejercicio3.ColaGenerica;

public class ArbolBinario<T> {
    private T dato;
    private ArbolBinario<T> hijoIzquierdo;
    private ArbolBinario<T> hijoDerecho;


    public ArbolBinario() {
        super();
    }

    public ArbolBinario(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Preguntar antes de invocar si tieneHijoIzquierdo()
     *
     * @return
     */
    public ArbolBinario<T> getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public ArbolBinario<T> getHijoDerecho() {
        return this.hijoDerecho;

    }

    public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
        this.hijoIzquierdo = hijo;
    }

    public void agregarHijoDerecho(ArbolBinario<T> hijo) {
        this.hijoDerecho = hijo;
    }

    public void eliminarHijoIzquierdo() {
        this.hijoIzquierdo = null;
    }

    public void eliminarHijoDerecho() {
        this.hijoDerecho = null;
    }

    public boolean esVacio() {
        return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
    }

    public boolean esHoja() {
        return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());

    }

    @Override
    public String toString() {
        return this.getDato().toString();
    }


    public boolean tieneHijoIzquierdo() {
        return this.hijoIzquierdo != null;
    }


    public boolean tieneHijoDerecho() {
        return this.hijoDerecho != null;
    }

    public int contarHojas() {
        if (this.esVacio()) {
            return 0;
        }
        if (this.esHoja()) {
            return 1;
        }
        int hojas = 0;
        if (this.tieneHijoIzquierdo()) {
            hojas += this.getHijoIzquierdo().contarHojas();
        }
        if (this.tieneHijoDerecho()) {
            hojas += this.getHijoDerecho().contarHojas();
        }
        return hojas;
    }

    public ArbolBinario<T> espejo() {
        if (this.esVacio()) {
            return null;
        }
        ArbolBinario<T> nuevoArbol = new ArbolBinario<T>(this.getDato());
        if (this.tieneHijoIzquierdo()) {
            nuevoArbol.agregarHijoDerecho(this.getHijoIzquierdo().espejo());
        }
        if (this.tieneHijoDerecho()) {
            nuevoArbol.agregarHijoIzquierdo(this.getHijoDerecho().espejo());
        }
        return nuevoArbol;
    }


    public void entreNiveles(int n, int m) {
        if (this.esVacio() || n >= m) {
            System.out.println("No se puede recorrer entre niveles");
        } else {
            ColaGenerica cola = new ColaGenerica();
            int nivelActual = 0;

            while (!cola.esVacia() && nivelActual <= m) {
                ArbolBinario<T> nodo = (ArbolBinario<T>) cola.desencolar();

                if (nodo != null) {
                    if (nivelActual >= n) {
                        System.out.print(nodo.getDato() + " ");
                    }
                    if (nodo.tieneHijoIzquierdo()) {
                        cola.encolar(nodo.getHijoIzquierdo());
                    }
                    if (nodo.tieneHijoDerecho()) {
                        cola.encolar(nodo.getHijoDerecho());
                    }
                } else {
                    if (nivelActual >= n && nivelActual <= m) {
                        System.out.println(); // Salto de línea al terminar el nivel
                    }
                    nivelActual++;
                    if (!cola.esVacia()) {
                        cola.encolar(null); // Marcador para el siguiente nivel
                    }
                }
            }
        }
    }


}
