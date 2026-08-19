package tp01.ejercicio1;

public class ListaDeEnterosEnlazada extends ListaDeEnteros {
    private NodoEntero inicio;
    private NodoEntero actual;
    private NodoEntero fin;
    private int tamanio = 0;

    @Override
    public void comenzar() {
        actual = inicio;
    }

    @Override
    public Integer proximo() {
        Integer elem = actual.getDato();
        actual = actual.getSiguiente();
        return elem;
    }

    @Override
    public boolean fin() {
        return actual == null;
    }

    @Override
    public Integer elemento(int pos) {
        if (pos < 0 || pos >= tamanio) return null;
        NodoEntero aux = inicio;
        for (int i = 0; i < pos; i++) aux = aux.getSiguiente();
        return aux.getDato();
    }

    @Override
    public boolean agregarEn(Integer elem, int pos) {
        if (pos < 0 || pos > tamanio) return false;
        NodoEntero nuevo = new NodoEntero();
        nuevo.setDato(elem);
        if (pos == 0) {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
            if (fin == null) fin = nuevo;
        } else {
            NodoEntero aux = inicio;
            for (int i = 0; i < pos - 1; i++) aux = aux.getSiguiente();
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            if (nuevo.getSiguiente() == null) fin = nuevo;
        }
        tamanio++;
        return true;
    }

    @Override
    public boolean agregarInicio(Integer elem) {
        return agregarEn(elem, 0);
    }

    @Override
    public boolean agregarFinal(Integer elem) {
        return agregarEn(elem, tamanio);
    }

    @Override
    public boolean eliminar(Integer elem) {
        NodoEntero aux = inicio, ant = null;
        while (aux != null && !aux.getDato().equals(elem)) {
            ant = aux;
            aux = aux.getSiguiente();
        }
        if (aux == null) return false;
        if (ant == null) inicio = aux.getSiguiente();
        else ant.setSiguiente(aux.getSiguiente());
        if (aux == fin) fin = ant;
        tamanio--;
        return true;
    }

    @Override
    public boolean eliminarEn(int pos) {
        if (pos < 0 || pos >= tamanio) return false;
        if (pos == 0) {
            inicio = inicio.getSiguiente();
            if (inicio == null) fin = null;
        } else {
            NodoEntero aux = inicio;
            for (int i = 0; i < pos - 1; i++) aux = aux.getSiguiente();
            aux.setSiguiente(aux.getSiguiente().getSiguiente());
            if (aux.getSiguiente() == null) fin = aux;
        }
        tamanio--;
        return true;
    }

    @Override
    public boolean incluye(Integer elem) {
        NodoEntero aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(elem)) return true;
            aux = aux.getSiguiente();
        }
        return false;
    }

    @Override
    public boolean esVacia() {
        return tamanio == 0;
    }

    @Override
    public int tamanio() {
        return tamanio;
    }
}
