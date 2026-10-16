package tp06.ejercicio1;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;

public class GrafoImplMatrizAdy<T> implements Grafo<T> {
    private int[][] matriz;
    private int capacidadMax;
    private ListaGenerica<Vertice<T>> vertices;

    public GrafoImplMatrizAdy(int capacidadMax) {
        this.capacidadMax = capacidadMax;
        this.matriz = new int[capacidadMax][capacidadMax];
        this.vertices = new ListaEnlazadaGenerica<>();

        // Inicializamos la matriz en 0 (sin conexiones)
        for (int i = 0; i < capacidadMax; i++) {
            for (int j = 0; j < capacidadMax; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    @Override
    public void agregarVertice(Vertice<T> v) {
        if (!vertices.incluye(v) && vertices.tamanio() < capacidadMax) {
            // Asignamos la posición libre actual al vértice
            int nuevaPos = vertices.tamanio();
            ((VerticeImplMatrizAdy<T>) v).setPosicion(nuevaPos);
            vertices.agregarFinal(v);
        }
    }

    @Override
    public void eliminarVertice(Vertice<T> v) {
        if (vertices.incluye(v)) {
            int pos = ((VerticeImplMatrizAdy<T>) v).getPosicion();
            if (pos >= 0 && pos < vertices.tamanio()) {
                // 1. Remover el vértice de la lista de vértices primero
                vertices.eliminar(v);
                int totalVerticesRestantes = vertices.tamanio();
                // 2. Desplazar las filas de la matriz hacia arriba (desde 'pos' en adelante)
                for (int i = pos; i < totalVerticesRestantes; i++) {
                    for (int j = 0; j <= totalVerticesRestantes; j++) {
                        matriz[i][j] = matriz[i + 1][j];
                    }
                }
                // 3. Desplazar las columnas de la matriz hacia la izquierda (desde 'pos' en adelante)
                for (int i = 0; i <= totalVerticesRestantes; i++) {
                    for (int j = pos; j < totalVerticesRestantes; j++) {
                        matriz[i][j] = matriz[i][j + 1];
                    }
                }
                // 4. Limpiar la última fila y columna sobrante que quedó duplicada/sucia al final
                for (int i = 0; i <= totalVerticesRestantes; i++) {
                    matriz[totalVerticesRestantes][i] = 0;
                    matriz[i][totalVerticesRestantes] = 0;
                }
                // 5. Actualizar las posiciones de todos los vértices afectados en la lista
                for (int i = 0; i < totalVerticesRestantes; i++) {
                    VerticeImplMatrizAdy<T> verticeActual = (VerticeImplMatrizAdy<T>) vertices.elemento(i); // Ojo: si tu lista arranca en 0 o en 1 según tu implementación de ListaGenerica
                    if (verticeActual.getPosicion() > pos) {
                        verticeActual.setPosicion(verticeActual.getPosicion() - 1);
                    }
                }
            }
        }

    }

    @Override
    public void conectar(Vertice<T> origen, Vertice<T> destino) {
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            matriz[origen.getPosicion()][destino.getPosicion()] = 1;
        }
    }

    @Override
    public void conectar(Vertice<T> origen, Vertice<T> destino, int peso) {
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            matriz[origen.getPosicion()][destino.getPosicion()] = peso;
        }
    }

    @Override
    public void desConectar(Vertice<T> origen, Vertice<T> destino) {
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            matriz[origen.getPosicion()][destino.getPosicion()] = 0;
        }
    }

    @Override
    public boolean esAdyacente(Vertice<T> origen, Vertice<T> destino) {
        boolean esAdyacente = false;
        if (vertices.incluye(origen) && vertices.incluye(destino)) {
            if (matriz[origen.getPosicion()][destino.getPosicion()] != 0) {
                esAdyacente = true;
                return esAdyacente;
            }
        }
        return esAdyacente;
    }

    @Override
    public int peso(Vertice<T> origen, Vertice<T> destino) {
        int peso = 0;
        if (esAdyacente(origen, destino)) {
            peso = matriz[origen.getPosicion()][destino.getPosicion()];
        }
        return peso;
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
    public ListaGenerica<Arista<T>> listaDeAdyacentes(Vertice<T> v) {
        ListaGenerica<Arista<T>> adyacentes = new ListaEnlazadaGenerica<>();
        if (vertices.incluye(v)) {
            int posOrigen = v.getPosicion();
            // Recorremos la fila completa del vértice origen en la matriz
            for (int i = 0; i < vertices.tamanio(); i++) {
                int pesoArista = matriz[posOrigen][i];
                if (pesoArista != 0) {
                    Vertice<T> verticeDestino = vertices.elemento(i);
                    Arista<T> arista = new AristaImpl<>(verticeDestino, pesoArista);
                    adyacentes.agregarFinal(arista);
                }
            }
        }
        return adyacentes;
    }

    @Override
    public Vertice<T> vetice(int posicion) {
        if (posicion >= 0 && posicion < vertices.tamanio()) {
            return vertices.elemento(posicion);
        }
        return null;
    }
}
