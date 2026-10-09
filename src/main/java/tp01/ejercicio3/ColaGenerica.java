package tp01.ejercicio3;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;

public class ColaGenerica<T> {
    private ListaGenerica<T> datos;

    public ColaGenerica() {
        this.datos = new ListaEnlazadaGenerica<T>();
    }

    public void encolar(T elemento) {
        if (!this.datos.agregarFinal(elemento)) {
            throw new RuntimeException("Error al encolar el elemento");
        }
    }

    public T desencolar() {
        if (this.esVacia()) {
            throw new RuntimeException("La cola está vacía");
        }
        T elemento = this.datos.elemento(1); // El frente siempre está en la posición 1
        this.datos.eliminarEn(1);           // Se elimina por posición, no por valor
        return elemento;
    }

    public T tope() {
        if (this.esVacia()) {
            throw new RuntimeException("La cola está vacía");
        }
        return this.datos.elemento(1); // El frente de la cola es la posición 1
    }

    public boolean esVacia() {
        return this.datos.esVacia();
    }
}
