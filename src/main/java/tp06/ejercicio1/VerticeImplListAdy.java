package tp06.ejercicio1;

import tp01.ejercicio2.ListaGenerica;

public class VerticeImplListAdy<T> implements Vertice<T> {
    private T dato;
    private int posicion;
    private ListaGenerica<Arista<T>> adyacentes;

    public VerticeImplListAdy(T dato) {
        this.dato = dato;
        this.posicion = -1;
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

    public void conectar(Vertice<T> destino) {
        Arista<T> nuevaArista = new AristaImpl<>(destino, 1);
        if (nuevaArista != null) {
            if (!adyacentes.incluye(nuevaArista)) {
                adyacentes.agregarFinal(nuevaArista);
            }
        }
    }

    public void conectar(Vertice<T> destino, int peso) {
        Arista<T> nuevaArista = new AristaImpl<>(destino, peso);
        if (nuevaArista != null) {
            if (!adyacentes.incluye(nuevaArista)) {
                adyacentes.agregarFinal(nuevaArista);
            }
        }
    }

    public void desconectar(Vertice<T> destino) {
        Arista<T> aristaADesconectar = this.obtenerArista(destino);
        if (aristaADesconectar != null) {
            adyacentes.eliminar(aristaADesconectar);
        }
    }

    public ListaGenerica<Arista<T>> obtenerAdyacentes() {
        return this.adyacentes;
    }

    public boolean esAdyacente(Vertice destino) {
        return this.obtenerArista(destino) != null;
    }

    public int peso(Vertice destino) {
        return this.obtenerArista(destino).peso();
    }

    public Arista<T> obtenerArista(Vertice<T> destino) {
        adyacentes.comenzar();
        Arista<T> arista = null;
        while (!adyacentes.fin()) {
            arista = adyacentes.proximo();
            if (arista.verticeDestino().equals(destino)) {
                return arista;
            }
        }
        return arista;
    }
}
