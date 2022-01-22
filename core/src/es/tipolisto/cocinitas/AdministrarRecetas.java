package es.tipolisto.cocinitas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import es.tipolisto.cocinitas.Beans.Receta;
import es.tipolisto.cocinitas.actores.Mensaje;
import es.tipolisto.cocinitas.actores.Personaje;


public class AdministrarRecetas extends PantallaAbstracta {
    Stage stage;
    Table table;
    Dialog dialog;
    Skin skin;
    Window window;
    InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos;
    ArrayList<Receta> recetas;
    private static final String TAG="MENSAJE";
    public AdministrarRecetas(Juego juego, InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos) {
        super(juego);
        this.interfacePlataformaBaseDeDatos=interfacePlataformaBaseDeDatos;
        recetas=new ArrayList<Receta>();
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Bienvenido a la pantalla administrar recetas (6)");
        //Inicializamos la base de datos
        interfacePlataformaBaseDeDatos.crear();
        //Obtenemos todos los datos de la base de datos:
        recetas=interfacePlataformaBaseDeDatos.devolverTodosLasRecetas();
        Gdx.app.log("Mensaje", "tienes "+String.valueOf(recetas.size())+" recetas");
        /*****************UI***************************************/
        skin= new Skin(Gdx.files.internal("skin/uiskin.json"));
        table=new Table();
        Label labelId=new Label("Id",skin);
        Label labelNombre=new Label("Nombre",skin);
        Label labelIdPlato=new Label("IdPlato",skin);
        table.add(labelId);
        table.add(labelNombre);
        table.add(labelIdPlato);
        table.row();

        if(recetas.size()==0){
            recetas.add(new Receta(0,"No hay recetas",0));
        }
        for (Receta receta: recetas){
            TextField textFieldId=new TextField(String.valueOf(receta.getId()), skin);
            TextField textFieldNombre=new TextField(receta.getNombre(), skin);
            TextField textFieldIdPlato=new TextField(String.valueOf(receta.getIdPlato()), skin);
            table.add(textFieldId);
            table.add(textFieldNombre);
            table.add(textFieldIdPlato);
            table.row();
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
                crearVentanaAnadirReceta();
                //Gdx.app.log(TAG, "Has hecho click");
                stage.addActor(window);
            }
        });

        stage=new Stage();
        stage.addActor(new Mensaje("Administrar Recetas",0, Gdx.graphics.getHeight()-50));
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

     public void crearVentanaAnadirReceta(){
         final TextField textFieldNombreReceta=new TextField(" ", skin);
         TextButton botonInsertar=new TextButton("Insertar", skin);
         TextButton botonCerrar=new TextButton("Cerrar", skin);
         window=new Window("Introduce una receta", skin);
         window.add(new Label("Nombre", skin));
         window.add(textFieldNombreReceta).row();
         window.add(new Label("Elige un plato", skin));
         final SelectBox<String> selectBoxPlatos=new SelectBox<String>(skin);
         selectBoxPlatos.setItems("1", "2","3","25");
         selectBoxPlatos.setWidth(200);
         window.add(selectBoxPlatos).row();

         botonInsertar.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                 String nombreReceta="";
                 int idPlato=0;
                 nombreReceta=textFieldNombreReceta.getText().toString();
                 idPlato=Integer.parseInt(selectBoxPlatos.getSelected());
                 interfacePlataformaBaseDeDatos.anadirReceta(nombreReceta,idPlato);
                 mostrarmensaje("Receta: "+nombreReceta+", añadido.");
                 juego.verPantalla(6);
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
