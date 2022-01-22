package es.tipolisto.cocinitas.desktop;
import com.badlogic.gdx.Gdx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.tipolisto.cocinitas.Beans.Alimento;
import es.tipolisto.cocinitas.Beans.Ingrediente;
import es.tipolisto.cocinitas.Beans.Plato;
import es.tipolisto.cocinitas.Beans.Receta;
import es.tipolisto.cocinitas.InterfacePlataformaBaseDeDatos;


public class DesktopInterfacePlataformaBaseDeDatos implements InterfacePlataformaBaseDeDatos {
    private static Connection conexion;
    Statement statement;
    ResultSet resultSet;


    /*@Override
    public void mostrarDialogo() {
        JOptionPane.showMessageDialog(null, "este es el titulo", "Este es el mensaje", JOptionPane.INFORMATION_MESSAGE);
    }*/

    @Override
    public void crear() {
        //System.out.println("estas dentro");
        Statement statement = null;

        //Conexion
        try {
            Class.forName("org.sqlite.JDBC");
            //conexion = DriverManager.getConnection("jdbc:sqlite:src/databases/cocina.db");
            conexion = DriverManager.getConnection("jdbc:sqlite:databases/cocina.db");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("no se pudo conectar clase no encontrada");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("no se pudo conectar sql exception");
        }
        if (conexion == null) {
            try {
                conexion.close();
                System.out.println("no se pudo conexion null");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**************************
     * Alimentos
     ***********************************************/
    @Override
    public Alimento obtenerUnAlimento(int idAlimento) {
        Alimento alimento = null;
        int id = 0;
        String nombre = "";
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("Select * from alimentos where id='" + idAlimento + "'");
            if (resultSet == null) alimento = new Alimento(0, "Sin alimento");
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                nombre = resultSet.getString(2);
                alimento = new Alimento(id, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alimento;
    }

    @Override
    public void anadirAlimento(String nombre) {
        Statement statement2 = null;
        PreparedStatement preparedStatement = null;
        try {
            statement2 = conexion.createStatement();
            preparedStatement = conexion.prepareStatement("INSERT INTO alimentos (nombre) values (?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.execute();
            System.out.println("Datos insertdos");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudieron insertar los datos");
        }
    }

    @Override
    public ArrayList<Alimento> devolverTodosLosAlimentos() {
        ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
        int id = 0;

        String nombre = "vacio";
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("Select * from alimentos");
            if (resultSet == null) alimentos.add(new Alimento(0, "cocina vacía"));
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                nombre = resultSet.getString(2);

                Alimento alimento = new Alimento(id, nombre);
                alimentos.add(alimento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alimentos;
    }
    /****************************Fin de alimentos*********************************************/


    /**************************
     * Platos
     ***********************************************/

    @Override
    public Plato obtenerUnPlato(int idPlato) {
        Gdx.app.log("Plato", "----->" + String.valueOf(idPlato));
        Plato plato = null;
        int id = 0;
        String nombre = "";
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("Select * from platos where id='" + idPlato + "'");
            if (resultSet == null) plato = new Plato(0, "Plato vacío");
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                nombre = resultSet.getString(2);
                plato = new Plato(id, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plato;
    }


    @Override
    public void anadirPlato(String nombre) {
        Statement statement2 = null;
        PreparedStatement preparedStatement = null;
        try {
            statement2 = conexion.createStatement();
            preparedStatement = conexion.prepareStatement("INSERT INTO platos (nombre) values (?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.execute();
            System.out.println("Plato insertado");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudieron insertar los datos");
        }
    }


    @Override
    public ArrayList<Plato> devolverTodosLosPlatos() {
        ArrayList<Plato> platos = new ArrayList<Plato>();
        Plato plato=null;
        int id = 0;
        String nombre = "vacio";
        try {
            statement = conexion.createStatement();
            resultSet= statement.executeQuery("Select * from platos");
            if (resultSet == null) platos.add(new Plato(0, "Sin platos"));
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                nombre = resultSet.getString(2);
                plato= new Plato(id, nombre);
                platos.add(plato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platos;
    }

    @Override
    public int eliminarPlato(int idPlato){
        try {
            Statement statement = conexion.createStatement();
            statement.execute("delete from platos where id='" + idPlato + "'");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /****************************Fin de platos*********************************************/






















    /************************** Recetas  ***********************************************/

    @Override
    public Receta obtenerUnaReceta(int idReceta) {
        Receta receta = null;
        int id = 0;
        String nombre = "";
        int idPlato = 0;
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("Select * from recetas where id='" + idReceta + "'");
            if (resultSet == null) receta = new Receta(0, "Sin receta", 0);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                nombre = resultSet.getString(2);
                idPlato = resultSet.getInt(3);
                receta = new Receta(id, nombre, idPlato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receta;
    }


    @Override
    public void anadirReceta(String nombre, int idPlato) {
        Statement statement2 = null;
        PreparedStatement preparedStatement = null;
        try {
            statement2 = conexion.createStatement();
            preparedStatement = conexion.prepareStatement("INSERT INTO recetas (nombre, idplato) values (?,?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, idPlato);
            preparedStatement.execute();
            System.out.println("Receta insertada.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudieron insertar los datos");
        }
    }

    @Override
    public ArrayList<Receta> devolverTodosLasRecetas() {
        ArrayList<Receta> recetas = new ArrayList<Receta>();
        int id = 0;
        String nombre = "vacio";
        int idPlato = 0;
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("Select * from recetas");
            if (resultSet == null) recetas.add(new Receta(0, "Sin recetas", 0));
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                nombre = resultSet.getString(2);
                idPlato = resultSet.getInt(3);
                Receta receta = new Receta(id, nombre, idPlato);
                recetas.add(receta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recetas;
    }
    /****************************Fin de alimentos*********************************************/













    /***************************** Ingredientes  *********************************************/

    @Override
    public int anadirIngrediente(String nombre, int idAlimento, int cantidad, int idPlato) {
        Statement statement2 = null;
        PreparedStatement preparedStatement = null;
        try {
            statement2 = conexion.createStatement();
            preparedStatement = conexion.prepareStatement("INSERT INTO ingredientes (nombre, idalimento, cantidad, idplato) values (?,?,?,?)");
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, idAlimento);
            preparedStatement.setInt(3, cantidad);
            preparedStatement.setInt(4, idPlato);
            preparedStatement.execute();
            System.out.println("Ingrediente insertado.");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No se pudieron insertar los datos");
            return 0;
        }
    }


    @Override
    public ArrayList<Ingrediente> devolverTodosLosIngredientesDeUnPlato(int idPlato) {
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        int id = 0;
        String nombre="";
        int idAlimento = 0;
        int idCantidad = 0;
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("Select * from ingredientes where idplato='" + idPlato + "'");
            if (resultSet == null) ingredientes.add(new Ingrediente(0,"Sin ingredientes", 0, 0, 0));
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                nombre = resultSet.getString(2);
                idAlimento = resultSet.getInt(3);
                idCantidad = resultSet.getInt(4);
                //idPlato= resultSet.getInt(4);
                Ingrediente ingrediente = new Ingrediente(id, nombre, idAlimento, idCantidad, idPlato);
                ingredientes.add(ingrediente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientes;
    }





    public int eliminarIngrediente(int idIngrediente){
        try {
            Statement statement = conexion.createStatement();
            statement.execute("delete from ingredientes where id='" + idIngrediente + "'");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /************************Fin de ingredientes*********************************************/


}