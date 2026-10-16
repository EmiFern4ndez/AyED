package tp06.ejercicio1;

public class VerticeImplMatrizAdy<T> implements Vertice<T> {
    private T dato;
    private int posicion;

    private VerticeImplMatrizAdy(T dato, int posicion) {
        this.dato = dato;
        this.posicion = posicion;
    }

    @Override
    public T dato() {
        return this.dato;
    }

    @Override
    public void setDato(T dato) {
        if (dato != null) {
            this.dato = dato;
        }
    }

    @Override
    public int getPosicion() {
        return this.posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
