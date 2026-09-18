package tp01.punto_2;

public class Estudiante {
    String apellido;
    String nombre;
    String legajo;

    public Estudiante(String apellido, String nombre, String legajo) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.legajo = legajo;
    }

    public String misDatos() {
        return "Apellido: " + apellido + "\n" + "Nombre: " + nombre + "\n" + "Legajo: " + legajo;
    }
}
