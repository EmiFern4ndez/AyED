package tp06.ejercicio2;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;
import tp01.ejercicio3.ColaGenerica;
import tp06.ejercicio1.Arista;
import tp06.ejercicio1.Grafo;
import tp06.ejercicio1.Vertice;

public class Recorridos<T> {


    public ListaGenerica<Vertice<T>> dfs(Grafo<T> grafo) {
        ListaGenerica<Vertice<T>> listaResultado = new ListaEnlazadaGenerica<>();
        if (grafo == null || grafo.esVacio()) {
            return listaResultado;
        }
        // Arreglo de marcas para saber qué vértices ya visitamos
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
        for (int i = 0; i < grafo.listaDeVertices().tamanio(); i++) {
            marca[i] = false;
        }

        ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
        vertices.comenzar();
        while (!vertices.fin()) {
            Vertice<T> puntoPartida = vertices.proximo();
            if (!marca[puntoPartida.getPosicion()]) {
                dfsInterno(puntoPartida, grafo, marca, listaResultado);
            }
        }
        return listaResultado;
    }

    private void dfsInterno(Vertice<T> inicio, Grafo<T> grafo, boolean[] marca, ListaGenerica<Vertice<T>> listaResultado) {
        //Marco como visitado y lo agrego en la lista resultado
        marca[inicio.getPosicion()] = true;
        listaResultado.agregarFinal(inicio);

        ListaGenerica<Arista<T>> listaAdyacentes = grafo.listaDeAdyacentes(inicio);
        listaAdyacentes.comenzar();
        while (!listaAdyacentes.fin()) {
            Arista<T> nuevArista = listaAdyacentes.proximo();
            Vertice<T> destino = nuevArista.verticeDestino();

            // Si el vecino no fue visitado, profundizamos en él recursivamente
            if (!marca[destino.getPosicion()]) {
                dfsInterno(destino, grafo, marca, listaResultado);
            }
        }
    }

    public ListaGenerica<Vertice<T>> bfs(Grafo<T> grafo) {
        ListaGenerica<Vertice<T>> listaResultado = new ListaEnlazadaGenerica<>();
        if (grafo == null || grafo.esVacio()) {
            return listaResultado;
        }
        // Arreglo de marcas para saber qué vértices ya visitamos
        boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()];
        for (int i = 0; i < grafo.listaDeVertices().tamanio(); i++) {
            marca[i] = false;
        }

        ListaGenerica<Vertice<T>> vertices = grafo.listaDeVertices();
        vertices.comenzar();
        while (!vertices.fin()) {
            Vertice<T> puntoPartida = vertices.proximo();
            if (!marca[puntoPartida.getPosicion()]) {
                bfsInterno(puntoPartida, grafo, marca, listaResultado);
            }
        }
        return listaResultado;
    }

    private void bfsInterno(Vertice<T> inicio, Grafo<T> grafo, boolean[] marca, ListaGenerica<Vertice<T>> listaResultado) {
        ColaGenerica<Vertice<T>> cola = new ColaGenerica<>();

        cola.encolar(inicio);
        marca[inicio.getPosicion()] = true; // Se marca al encolar para evitar duplicados en la cola

        while (!cola.esVacia()) {
            Vertice<T> vertice = cola.desencolar();
            listaResultado.agregarFinal(vertice); // Procesamos el vértice extraído de la cola

            // Obtenemos todos sus vecinos directos
            ListaGenerica<Arista<T>> adyacentes = grafo.listaDeAdyacentes(vertice);
            adyacentes.comenzar();
            while (!adyacentes.fin()) {
                Arista<T> arista = adyacentes.proximo();
                Vertice<T> destino = arista.verticeDestino();
                // Si el vecino no ha sido visitado ni encolado aún, lo marcamos y encolamos
                if (!marca[destino.getPosicion()]) {
                    marca[destino.getPosicion()] = true;
                    cola.encolar(destino);
                }
            }
        }
    }
}
