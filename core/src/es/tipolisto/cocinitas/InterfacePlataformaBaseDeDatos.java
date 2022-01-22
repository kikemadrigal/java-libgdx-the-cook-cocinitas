package es.tipolisto.cocinitas;


import java.util.ArrayList;

import es.tipolisto.cocinitas.Beans.Alimento;
import es.tipolisto.cocinitas.Beans.Ingrediente;
import es.tipolisto.cocinitas.Beans.Plato;
import es.tipolisto.cocinitas.Beans.Receta;

public interface InterfacePlataformaBaseDeDatos {

    void crear();
   /******************Alimentos*****************/
    void anadirAlimento(String nombreAlimento);
    ArrayList<Alimento> devolverTodosLosAlimentos();
    Alimento obtenerUnAlimento(int idAlimento);
   /****************Fin de alimentos**************/



   /**************Platos****************************/
    void anadirPlato(String nombrePlato);
    int eliminarPlato(int idPlato);
    Plato obtenerUnPlato(int idPlato);
    ArrayList<Plato> devolverTodosLosPlatos();
   /**************Fin de platos***************************/






    /******************Ingredientes*****************************/
    int anadirIngrediente(String nombre, int idAlimento, int cantidad, int idPlato);
    int eliminarIngrediente(int idIngrediente);
    ArrayList<Ingrediente> devolverTodosLosIngredientesDeUnPlato(int idPlato);
    /*******************Final de ingredientes***********************/


   /******************Recetas*****************************/
    void anadirReceta(String nombreAlimento, int idPlato);
    ArrayList<Receta> devolverTodosLasRecetas();
    Receta obtenerUnaReceta(int idReceta);
   /******************Fin de recetas*****************************/
   // void execute(String sql);
   // void executeUpdate();
   // ArrayList<Alimento> obtenerAlimentos();
   // void insertarAlimento(String nombre, int piezas, int gramos);
}

