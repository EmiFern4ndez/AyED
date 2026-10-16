package tp06.ejercicio1;

public class AristaImpl<T> implements Arista<T> {
    private Vertice<T> destino;
    private int peso;

    public AristaImpl(Vertice<T> destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    @Override
    public Vertice<T> verticeDestino() {
        return destino;
    }

    @Override
    public int peso() {
        return peso;
    }
}
