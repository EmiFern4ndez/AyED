package punto_3;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;

public class ColaGenerica<T> {
    private ListaGenerica<T> datos;

    public ColaGenerica() {
        this.datos = new ListaEnlazadaGenerica<T>();
        this.datos.comenzar();
    }

    public void encolar(T elemento) throws RuntimeException {
        if (!this.datos.agregarFinal(elemento)) {
            new RuntimeException("Error al encolar el elemento");
        }
    }

    public T desencolar() throws RuntimeException {
        T elemento = (T) this.datos.elemento(this.datos.tamanio() - 1);
        if (!this.datos.eliminar(elemento)) {
            new RuntimeException("Error al desencolar el elemento");
        }
        return elemento;
    }

    public T tope() {
        return (T) this.datos.elemento(this.datos.tamanio() - 1);
    }

    public boolean esVacia(){
        return this.datos.esVacia();
    }
}
