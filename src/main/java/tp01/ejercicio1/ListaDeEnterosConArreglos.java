package tp01.ejercicio1;

public class ListaDeEnterosConArreglos extends ListaDeEnteros {
    private int tamanio = 0;
    private Integer[] datos = new Integer[100];
    private int actual = 0;

    @Override
    public void comenzar() {
        actual = 0;
    }

    @Override
    public Integer proximo() {
        return datos[actual++];
    }

    @Override
    public boolean fin() {
        return actual >= tamanio;
    }

    @Override
    public Integer elemento(int pos) {
        return (pos >= 0 && pos < tamanio) ? datos[pos] : null;
    }

    @Override
    public boolean agregarEn(Integer elem, int pos) {
        if (pos < 0 || pos > tamanio || tamanio >= datos.length) return false;
        for (int i = tamanio; i > pos; i--) datos[i] = datos[i - 1];
        datos[pos] = elem;
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
        for (int i = 0; i < tamanio; i++) {
            if (datos[i].equals(elem)) return eliminarEn(i);
        }
        return false;
    }

    @Override
    public boolean eliminarEn(int pos) {
        if (pos < 0 || pos >= tamanio) return false;
        for (int i = pos; i < tamanio - 1; i++) datos[i] = datos[i + 1];
        datos[--tamanio] = null;
        return true;
    }

    @Override
    public boolean incluye(Integer elem) {
        for (int i = 0; i < tamanio; i++) if (datos[i].equals(elem)) return true;
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
