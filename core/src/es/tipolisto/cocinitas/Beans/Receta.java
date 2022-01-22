package es.tipolisto.cocinitas.Beans;


public class Receta {
    private int id;
    private String nombre;
    private int idPlato;

    public Receta(int id, String nombre, int idPlato) {
        this.id = id;
        this.nombre = nombre;
        this.idPlato = idPlato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idPlato=" + idPlato +
                '}';
    }
}
