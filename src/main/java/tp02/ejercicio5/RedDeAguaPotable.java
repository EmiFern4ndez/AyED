package tp02.ejercicio5;

import tp01.ejercicio2.ListaGenerica;
import tp02.ejercicio1.ArbolGeneral;

public class RedDeAguaPotable {

    private ArbolGeneral<Double> red; // Representa la red de caños

    public RedDeAguaPotable(ArbolGeneral<Double> red) {
        this.red = red;
    }

    public double minimoCaudal(double caudal) {
        if (this.red == null || this.red.esVacio()) {
            return 0.0;
        }
        return minimoCaudalHelper(this.red, caudal);
    }

    private double minimoCaudalHelper(ArbolGeneral<Double> nodo, double caudalActual) {
        // Caso base: Si es una hoja (casa), este es su caudal final
        if (nodo.esHoja()) {
            return caudalActual;
        }

        // 1. Obtenemos los hijos y contamos cuántas bifurcaciones hay
        ListaGenerica<ArbolGeneral<Double>> hijos = nodo.getHijos();
        int cantidadHijos = hijos.tamanio();

        // 2. Calculamos el caudal que le toca a cada bifurcación
        double caudalDividido = caudalActual / cantidadHijos;
        double minCaudal = 9999999;

        // 3. Recorremos los hijos y buscamos el mínimo caudal que llega a las casas
        hijos.comenzar();
        while (!hijos.fin()) {
            ArbolGeneral<Double> hijoActual = hijos.proximo();
            double caudalHijo = minimoCaudalHelper(hijoActual, caudalDividido);
            if (caudalHijo < minCaudal) {
                minCaudal = caudalHijo;
            }
        }

        return minCaudal;
    }
}
