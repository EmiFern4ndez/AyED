package tp03.ejercicio4;

import tp03.ejercicio3.ArbolBinario;

public class RedBinariaLlena {
    private ArbolBinario<Integer> arbol;

    public RedBinariaLlena(ArbolBinario<Integer> arbol) {
        this.arbol = arbol;
    }

    public int retardoReenvio() {
        return this.retardoReenvioHelper(this.arbol);
    }

    private int retardoReenvioHelper(ArbolBinario<Integer> arbol) {
        if (arbol.esVacio()) {
            return 0;
        }
        if (arbol.esHoja()) {
            return arbol.getDato();
        }
        int segundosIzq = 0;
        int segundosDer = 0;
        int segundos;
        if (arbol.tieneHijoIzquierdo()) {
            segundosIzq += arbol.getDato() + retardoReenvioHelper(arbol.getHijoIzquierdo());
        }
        if (arbol.tieneHijoDerecho()) {
            segundosDer += arbol.getDato() + retardoReenvioHelper(arbol.getHijoDerecho());
        }
        if (segundosIzq > segundosDer) {
            segundos = segundosIzq;
        } else
            segundos = segundosDer;
        return segundos;
    }
}
