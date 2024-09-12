package entity;

public class Persona {
    private final String nombre;
    private final String identificacion;

    public Persona(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public String obteneridentificacion() {
        return identificacion;
    }
}