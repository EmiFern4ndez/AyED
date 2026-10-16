package tp06.ejercicio1;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;

public class GrafoImplListAdy<T> implements Grafo<T> {
    private ListaGenerica<Vertice<T>> vertices;

    public GrafoImplListAdy() {
        this.vertices = new ListaEnlazadaGenerica<>();
    }

    @Override
    public void agregarVertice(Vertice v) {
        if (v != null) {
            if (!vertices.incluye(v)) {
                int nuevaPos = vertices.tamanio();
                ((VerticeImplListAdy<T>) v).setPosicion(nuevaPos);
                vertices.agregarFinal(v);
            }
        }
    }

    @Override
    public void eliminarVertice(Vertice v) {

    }

    @Override
    public void conectar(Vertice origen, Vertice destino) {
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            ((VerticeImplListAdy<T>) origen).conectar(destino);
        }
    }

    @Override
    public void conectar(Vertice origen, Vertice destino, int peso) {
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            ((VerticeImplListAdy<T>) origen).conectar(destino, peso);
        }
    }

    @Override
    public void desConectar(Vertice origen, Vertice destino) {
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            ((VerticeImplListAdy<T>) origen).desconectar(destino);
        }
    }

    @Override
    public boolean esAdyacente(Vertice origen, Vertice destino) {
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            return ((VerticeImplListAdy<T>) origen).esAdyacente(destino);
        }
        return false;
    }

    @Override
    public int peso(Vertice origen, Vertice destino) {
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            return ((VerticeImplListAdy<T>) origen).peso(destino);
        }
        return 0;
    }

    @Override
    public boolean esVacio() {
        return vertices.esVacia();
    }

    @Override
    public ListaGenerica<Vertice<T>> listaDeVertices() {
        return vertices;
    }

    @Override
    public ListaGenerica<Arista<T>> listaDeAdyacentes(Vertice v) {
        if (vertices.incluye(v)) {
            return ((VerticeImplListAdy<T>) v).obtenerAdyacentes();
        }
        return new ListaEnlazadaGenerica<>();
    }

    @Override
    public Vertice<T> vetice(int posicion) {
        if (posicion >= 0 && posicion < vertices.tamanio()) {
            return vertices.elemento(posicion);
        }
        return null;
    }
}
