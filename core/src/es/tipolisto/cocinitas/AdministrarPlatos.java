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

import es.tipolisto.cocinitas.Beans.Plato;
import es.tipolisto.cocinitas.actores.Mensaje;
import es.tipolisto.cocinitas.actores.Personaje;


public class AdministrarPlatos extends PantallaAbstracta {
    Stage stage;
    Table table;
    Dialog dialog;
    Skin skin;
    Window window;
    InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos;
    ArrayList<Plato> platos;
    private static final String TAG="MENSAJE";
    public AdministrarPlatos(Juego juego, InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos) {
        super(juego);
        this.interfacePlataformaBaseDeDatos=interfacePlataformaBaseDeDatos;
        platos=new ArrayList<Plato>();
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Bienvenido a la pantalla administrar platos(5)");
        //Inicializamos la base de datos
        interfacePlataformaBaseDeDatos.crear();
        //Obtenemos todos los datos de la base de datos:
        platos=interfacePlataformaBaseDeDatos.devolverTodosLosPlatos();
        Gdx.app.log("Mensaje", "tienes "+String.valueOf(platos.size())+" platos");
        /*****************UI***************************************/
        skin= new Skin(Gdx.files.internal("skin/uiskin.json"));


        table=new Table();
        Label labelId=new Label("Id",skin);
        Label labelNombre=new Label("Nombre",skin);
        Label labelAccionEliminar=new Label("elimi",skin);
        Label labelAccionActualizar=new Label("Act",skin);
        table.add(labelId).width(20);
        table.add(labelNombre).width(200);
        table.add(labelAccionEliminar).width(50);
        table.add(labelAccionActualizar).width(50);

        table.row();

        if(platos.size()==0){
            platos.add(new Plato(0,"No hay platos"));
        }
        for (final Plato plato: platos){
            TextField textFieldId=new TextField(String.valueOf(plato.getId()), skin);
            TextButton textButtonNombre=new TextButton(plato.getNombre(), skin);
            textButtonNombre.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    juego.verLosIngredientesDeUnPlato(plato.getId());
                }
            });
            ImageButton imageButtonEliminar=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("eliminar.png")))));
            imageButtonEliminar.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    interfacePlataformaBaseDeDatos.eliminarPlato(plato.getId());
                    juego.verPantalla(5);
                }
            });
            ImageButton imageButtonActualizar=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("actualizar.png")))));
            table.add(textFieldId).width(20);
            table.add(textButtonNombre).width(200);
            table.add(imageButtonActualizar).width(50);
            table.add(imageButtonEliminar).width(50);
            table.row();
        }

        table.setFillParent(true);

        //table.debug();
        /******************Fin de UI***********************************/
        Personaje botonSalir=new Personaje(new Texture(Gdx.files.internal("salir.png")), Gdx.graphics.getWidth()-60, Gdx.graphics.getHeight()-60,60,60);
        botonSalir.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Gdx.app.log(TAG, "Has hecho click");
                juego.verPantalla(0);
            }
        });
        Personaje botonAnadirPlato=new Personaje(new Texture(Gdx.files.internal("anadirplato60ancho50alto.png")),0,0,60,40);
        botonAnadirPlato.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                crearVentanaAnadirPlato();
                //Gdx.app.log(TAG, "Has hecho click");
                stage.addActor(window);
            }
        });

        stage=new Stage();
        stage.addActor(new Mensaje("Administrar platos",0, Gdx.graphics.getHeight()-50));
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

     public void crearVentanaAnadirPlato(){
         final TextField textFieldNombrePlato=new TextField(" ", skin);
         TextButton botonInsertar=new TextButton("Insertar", skin);
         TextButton botonCerrar=new TextButton("Cerrar", skin);
         window=new Window("Introduce un plato", skin);
         window.add(new Label("Nombre", skin));
         window.add(textFieldNombrePlato).row();

         botonInsertar.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                 String nombrePlato="";
                 int piezas=0;
                 int gramos=0;
                 int idPlato=0;
                 nombrePlato=textFieldNombrePlato.getText().toString();
                 interfacePlataformaBaseDeDatos.anadirPlato(nombrePlato);
                 mostrarmensaje("Plato: "+nombrePlato+", añadido.");
                 juego.verPantalla(5);
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
