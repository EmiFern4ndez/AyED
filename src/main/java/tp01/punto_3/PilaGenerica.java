package tp01.punto_3;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;

public class PilaGenerica<T> {
    private ListaGenerica<T> datos;

    public PilaGenerica() {
        this.datos = new ListaEnlazadaGenerica<T>();
    }

    public void apilar(T elemento) {
        if (!this.datos.agregarFinal(elemento)) {
            throw new RuntimeException("Error al apilar el elemento");
        }
    }

    public T desapilar() {
        if (this.esVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        int posTope = this.datos.tamanio(); // El tope es la última posición de la lista
        T elemento = this.datos.elemento(posTope);
        this.datos.eliminarEn(posTope);      // Se elimina el último por posición
        return elemento;
    }

    public T tope() {
        if (this.esVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return this.datos.elemento(this.datos.tamanio()); // La última posición válida es tamanio()
    }

    public boolean esVacia() {
        return this.datos.esVacia();
    }
}