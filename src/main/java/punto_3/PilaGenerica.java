package punto_3;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;

public class PilaGenerica<T> {
    private ListaGenerica<T> datos;

    public PilaGenerica() {
        this.datos = new ListaEnlazadaGenerica<T>();
        this.datos.comenzar();
    }

    public void apilar(T elemento) throws RuntimeException {
        if (!this.datos.agregarInicio(elemento)) {
            new RuntimeException("Error al encolar el elemento");
        }
    }

    public T desapilar() throws RuntimeException {
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
