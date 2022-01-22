package es.tipolisto.cocinitas.Beans;


public class Ingrediente {
    private int id;
    private String nombre;
    private int idAlimento;
    private int idCantidad;
    private int idPlato;

    public Ingrediente(int id, String nombre, int idAlimento, int idCantidad, int idPlato) {
        this.id = id;
        this.nombre=nombre;
        this.idAlimento = idAlimento;
        this.idCantidad = idCantidad;
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

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public int getIdCantidad() {
        return idCantidad;
    }

    public void setIdCantidad(int idCantidad) {
        this.idCantidad = idCantidad;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idAlimento=" + idAlimento +
                ", idCantidad=" + idCantidad +
                ", idPlato=" + idPlato +
                '}';
    }
}
