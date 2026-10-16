package tp06.ejercicio4;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;
import tp06.ejercicio1.Arista;
import tp06.ejercicio1.Grafo;
import tp06.ejercicio1.Vertice;

public class VisitaOslo<T> {


    public ListaGenerica<String> paseoEnBici(Grafo<String> lugares, String destino, int maxTiempo, ListaGenerica<String> lugaresRestringidos) {
        ListaGenerica<Vertice<String>> caminoActual = new ListaEnlazadaGenerica<>();
        ListaGenerica<String> mejorCamino = new ListaEnlazadaGenerica<>();

        if (lugares == null || lugares.esVacio()) {
            return mejorCamino; // Retorna lista vacía si el grafo es nulo o vacío
        }

        // 1. Buscar el vértice "Ayuntamiento" antes de comenzar
        Vertice<String> origen = null;
        ListaGenerica<Vertice<String>> listaVertices = lugares.listaDeVertices();
        listaVertices.comenzar();
        while (!listaVertices.fin()) {
            Vertice<String> v = listaVertices.proximo();
            if (v.dato().equals("Ayuntamiento")) {
                origen = v;
                break;
            }
        }
        // Si no encontramos el nodo origen "Ayuntamiento"
        if (origen == null) {
            return mejorCamino;
        }

        //Lista de marcas para no pasar por el mismo lugar
        boolean[] marcas = new boolean[lugares.listaDeVertices().tamanio()];
        for (int i = 0; i < lugares.listaDeVertices().tamanio(); i++) {
            marcas[i] = false;
        }

        buscarCamino(origen, destino, maxTiempo, 0, lugaresRestringidos, lugares, marcas, caminoActual, mejorCamino);
        return mejorCamino;
    }

    private boolean buscarCamino(Vertice<String> actual, String destino, int maxTiempo, int tiempoActual, ListaGenerica<String> lugaresRestringidos, Grafo<String> lugares, boolean[] marca, ListaGenerica<Vertice<String>> caminoActual, ListaGenerica<String> mejorCamino) {
        marca[actual.getPosicion()] = true;
        caminoActual.agregarFinal(actual);

        //Caso base: encontre el destino
        if (actual.dato().equals(destino)) {
            clonarLista(caminoActual, mejorCamino);
            return false;
        }

        //Busco los adyacentes del vertice en el que estoy parado
        ListaGenerica<Arista<String>> listaAristas = lugares.listaDeAdyacentes(actual);
        listaAristas.comenzar();
        while (!listaAristas.fin()) {
            Arista<String> aristaActual = listaAristas.proximo();
            Vertice<String> verticeActual = aristaActual.verticeDestino();
            if (!marca[verticeActual.getPosicion()] &&
                    (tiempoActual + aristaActual.peso() <= maxTiempo) && (!lugaresRestringidos.incluye(verticeActual.dato()))) {

                boolean encontrado = buscarCamino(verticeActual, destino, maxTiempo, tiempoActual + aristaActual.peso(),
                        lugaresRestringidos, lugares, marca, caminoActual, mejorCamino);
                if (encontrado) {
                    return true; // Propaga el corte inmediato
                }
            }
        }
        marca[actual.getPosicion()] = false;
        caminoActual.eliminarEn(caminoActual.tamanio());
        return false;
    }

    private void clonarLista(ListaGenerica<Vertice<String>> caminoActual, ListaGenerica<String> mejorCamino) {
        caminoActual.comenzar();
        while (!caminoActual.fin()) {
            Vertice<String> v = caminoActual.proximo();
            mejorCamino.agregarFinal(v.dato());
        }
    }

}
