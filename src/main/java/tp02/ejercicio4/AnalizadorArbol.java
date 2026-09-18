package tp02.ejercicio4;

import tp01.ejercicio2.ListaEnlazadaGenerica;
import tp01.ejercicio2.ListaGenerica;
import tp02.ejercicio1.ArbolGeneral;

public int devolverMaximoPromedio(ArbolGeneral<Empresa> arbol) {
    if (arbol == null || arbol.esVacio()) {
        return 0;
    }
    int maxProm = 0;
    // Usamos una ListaGenerica como cola
    ListaGenerica<ArbolGeneral<Empresa>> cola = new ListaEnlazadaGenerica<ArbolGeneral<Empresa>>();
    cola.agregarFinal(arbol);
    while (!cola.esVacia()) {
        int nodosEnNivel = cola.tamanio(); // Obtenemos el tamaño real del nivel
        int sumaNivel = 0;

        for (int i = 0; i < nodosEnNivel; i++) {
            ArbolGeneral<Empresa> actual = cola.elemento(1);
            cola.eliminarEn(1);
            sumaNivel += actual.getDato().tiempo();
            // Encolamos los hijos para el nivel siguiente al final de la lista
            if (actual.tieneHijos()) {
                ListaGenerica<ArbolGeneral<Empresa>> hijos = actual.getHijos();
                hijos.comenzar();
                while (!hijos.fin()) {
                    cola.agregarFinal(hijos.proximo());
                }
            }
        }

        // Calculamos el promedio del nivel actual
        int promActual = sumaNivel / nodosEnNivel;

        if (promActual > maxProm) {
            maxProm = promActual;
        }
    }

    return maxProm;
}

void main() {

}
