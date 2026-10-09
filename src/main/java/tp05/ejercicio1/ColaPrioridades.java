package tp05.ejercicio1;

public interface ColaPrioridades<T> {
    boolean esVacia();

    void eliminar();

    boolean agregar(T elemento);

    T tope();
}
