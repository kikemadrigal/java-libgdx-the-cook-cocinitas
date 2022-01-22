package es.tipolisto.cocinitas.client;

import java.util.ArrayList;

import es.tipolisto.cocinitas.Beans.Alimento;
import es.tipolisto.cocinitas.Beans.Ingrediente;
import es.tipolisto.cocinitas.Beans.Plato;
import es.tipolisto.cocinitas.Beans.Receta;
import es.tipolisto.cocinitas.InterfacePlataformaBaseDeDatos;


public class HtmlInterfacePlataformaBaseDeDatos implements InterfacePlataformaBaseDeDatos {
    //private final MiServicioAsincrono miServicioAsincrono= GWT.create(MiServicioSincronoRemoteService.class);
    @Override
    public void crear() {
        
    }

    @Override
    public void anadirAlimento(String nombreAlimento) {
    }

    @Override
    public ArrayList<Alimento> devolverTodosLosAlimentos() {
        ArrayList<Alimento> alimentos=new ArrayList<Alimento>();
        alimentos.add(new Alimento(0,"Pera"));
        alimentos.add(new Alimento(1,"Platano"));
        alimentos.add(new Alimento(0,"Manzana"));
        alimentos.add(new Alimento(0,"Uva"));
        return alimentos;
    }

    @Override
    public Alimento obtenerUnAlimento(int idAlimento) {
        return null;
    }

    @Override
    public void anadirPlato(String nombrePlato) {

    }

    @Override
    public int eliminarPlato(int idPlato) {
        return 0;
    }

    @Override
    public Plato obtenerUnPlato(int idPlato) {
       /*miServicioAsincrono.getSaludar(1,2,new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Hubo un fallor"+caught.getMessage());
            }

            @Override
            public void onSuccess(String result) {
                System.out.println("Se hizo bien la comunicacion "+result );
            }
        });*/

        return new Plato(1,"Plato 1");
    }

    @Override
    public ArrayList<Plato> devolverTodosLosPlatos() {
        ArrayList<Plato> platos=new ArrayList<Plato>();
        platos.add(new Plato(0,"Plato 0"));
        platos.add(new Plato(1,"Plato 1"));
        platos.add(new Plato(2,"Plato 2"));
        platos.add(new Plato(3,"Plato 3"));
        return platos;
    }

    @Override
    public int anadirIngrediente(String nombre, int idAlimento, int cantidad, int idPlato) {
        return 0;
    }

    @Override
    public int eliminarIngrediente(int idIngrediente) {
        return 0;
    }

    @Override
    public ArrayList<Ingrediente> devolverTodosLosIngredientesDeUnPlato(int idPlato) {
        return null;
    }

    @Override
    public void anadirReceta(String nombreAlimento, int idPlato) {

    }

    @Override
    public ArrayList<Receta> devolverTodosLasRecetas() {
        return null;
    }

    @Override
    public Receta obtenerUnaReceta(int idReceta) {
        return null;
    }
}
