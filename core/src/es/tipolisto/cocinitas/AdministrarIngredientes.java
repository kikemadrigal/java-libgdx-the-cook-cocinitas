package es.tipolisto.cocinitas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import es.tipolisto.cocinitas.Beans.Alimento;
import es.tipolisto.cocinitas.Beans.Ingrediente;
import es.tipolisto.cocinitas.actores.Mensaje;
import es.tipolisto.cocinitas.actores.Personaje;


public class AdministrarIngredientes extends PantallaAbstracta {
    final int idPlato;
    Stage stage;
    Table table;
    Dialog dialog;
    Skin skin;
    Window window;
    InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos;
    ArrayList<Ingrediente> ingredientes;
    private static final String TAG="MENSAJE";
    public AdministrarIngredientes(Juego juego, InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos, int idPlato) {
        super(juego);
        this.interfacePlataformaBaseDeDatos=interfacePlataformaBaseDeDatos;
        ingredientes=new ArrayList<Ingrediente>();
        this.idPlato=idPlato;
    }

    @Override
    public void show() {
        //Inicializamos la base de datos
        interfacePlataformaBaseDeDatos.crear();
        //Obtenemos todos los datos de la base de datos:
        ingredientes=interfacePlataformaBaseDeDatos.devolverTodosLosIngredientesDeUnPlato(this.idPlato);
        Gdx.app.log("Adm alimentos", "hay "+ingredientes.size()+" ingredientes");
        stage=new Stage();
        /*****************UI***************************************/
        skin= new Skin(Gdx.files.internal("skin/uiskin.json"));
        table=new Table();
        Label labelNombre=new Label("Nombre",skin);
        Label labelCantidad=new Label("Cantidad",skin);
        Label labelEliminar=new Label(" ",skin);
        table.add(labelNombre).width(200);
        table.add(labelCantidad).width(50);
        table.row();

        if(ingredientes.size()==0){
            ingredientes.add(new Ingrediente(0,"Sin ingredientes",0,0,0));
            //mostrarmensaje("No hay ingredientes");
        }else{
            for (final Ingrediente ingrediente: ingredientes){
                TextField textFieldNombre=new TextField(interfacePlataformaBaseDeDatos.obtenerUnAlimento(ingrediente.getIdAlimento()).getNombre(), skin);
                TextField textFieldCantidad=new TextField(String.valueOf(ingrediente.getIdCantidad()), skin);
                ImageButton imageButtonEliminar=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("eliminar.png")))));
                imageButtonEliminar.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {
                        interfacePlataformaBaseDeDatos.eliminarIngrediente(ingrediente.getId());
                        Gdx.app.log("Click", "Ingediente: "+String.valueOf(ingrediente.getId())+" borrado");
                        juego.verLosIngredientesDeUnPlato(idPlato);
                    }
                });
                table.add(textFieldNombre).width(200);
                table.add(textFieldCantidad).width(50);
                table.add(imageButtonEliminar);
                table.row();
            }
        }


        table.setFillParent(true);
        //table.debug();
        /******************Fin de UI***********************************/
        Personaje botonSalir=new Personaje(new Texture(Gdx.files.internal("salir.png")), Gdx.graphics.getWidth()-60, Gdx.graphics.getHeight()-60,60,60);
        botonSalir.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log(TAG, "Has hecho click");
                juego.verPantalla(0);
            }
        });
        Personaje botonAnadirPlato=new Personaje(new Texture(Gdx.files.internal("anadirreceta60ancho50alto.png")),0,0,60,40);
        botonAnadirPlato.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                crearUnIngredienteEnEstePlato();
                //Gdx.app.log(TAG, "Has hecho click");
                stage.addActor(window);
            }
        });


        stage.addActor(new Mensaje("Ingredientes del plato: "+interfacePlataformaBaseDeDatos.obtenerUnPlato(this.idPlato).getNombre(),0, Gdx.graphics.getHeight()-50));
        stage.addActor(table);
        stage.addActor(botonSalir);
        stage.addActor(botonAnadirPlato);
        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

     public void crearUnIngredienteEnEstePlato(){
         final TextField textFieldCantidad =new TextField("",skin);
         final ArrayList<Alimento> alimentos=interfacePlataformaBaseDeDatos.devolverTodosLosAlimentos();
         String[] arrayAlimentos=new String[alimentos.size()];
         TextButton botonInsertar=new TextButton("Insertar", skin);
         TextButton botonCerrar=new TextButton("Cerrar", skin);


         window=new Window("Elige un alimento", skin);
         window.add(new Label("Ingrediente", skin));
         final SelectBox<String> selectBoxIngrediente=new SelectBox<String>(skin);

         for (int contador=0;contador<alimentos.size();contador++){
             arrayAlimentos[contador]=alimentos.get(contador).getNombre();
         }
         selectBoxIngrediente.setItems(arrayAlimentos);
         selectBoxIngrediente.setWidth(200);
         window.add(selectBoxIngrediente).row();
         window.add(new Label("Cantidad", skin));
         window.add(textFieldCantidad);
         window.add().row();

         botonInsertar.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                 int idAlimento=0;
                 int idCantidad=0;
                 try{
                     idCantidad=Integer.parseInt(textFieldCantidad.getText());
                 }catch (NumberFormatException ex){
                     Gdx.app.log("Mensaje", "numberformat exception con "+textFieldCantidad.getText());
                     idCantidad=0;
                 }
                 for(Alimento alimento: alimentos){
                     if(alimento.getNombre().contentEquals(selectBoxIngrediente.getSelected())) idAlimento=alimento.getId();
                 }
                 interfacePlataformaBaseDeDatos.anadirIngrediente(selectBoxIngrediente.getSelected(),idAlimento, idCantidad, idPlato);
                 juego.verLosIngredientesDeUnPlato(idPlato);
             }
         });
         botonCerrar.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                     stage.getRoot().removeActor(window);
             }
         });
         window.add(botonInsertar);
         window.add(botonCerrar);
         window.setSize(320,180);
         window.setPosition((Gdx.graphics.getHeight()/2)-60, Gdx.graphics.getHeight()/2);
         window.layout();

     }

    private void mostrarmensaje(String mensaje){
        final Dialog dialog=new Dialog("Mensaje", skin);
        dialog.text(mensaje);
        dialog.show(stage);
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                dialog.hide();
            }
        },5);
    }

}
