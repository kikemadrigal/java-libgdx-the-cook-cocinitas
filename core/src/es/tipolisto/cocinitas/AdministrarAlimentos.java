package es.tipolisto.cocinitas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import es.tipolisto.cocinitas.Beans.Alimento;
import es.tipolisto.cocinitas.actores.Mensaje;
import es.tipolisto.cocinitas.actores.Personaje;


public class AdministrarAlimentos extends PantallaAbstracta {
    Stage stage;
    Table table;
    Dialog dialog;
    Skin skin;
    Window window;
    InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos;
    ArrayList<Alimento> alimentos;
    private static final String TAG="MENSAJE";
    public AdministrarAlimentos(Juego juego, InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos) {
        super(juego);
        this.interfacePlataformaBaseDeDatos=interfacePlataformaBaseDeDatos;
        alimentos=new ArrayList<Alimento>();
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Bienvenido a la pantalla administrar alimentos (3)");
        //Inicializamos la base de datos
        interfacePlataformaBaseDeDatos.crear();
        //Obtenemos todos los datos de la base de datos:
        alimentos=interfacePlataformaBaseDeDatos.devolverTodosLosAlimentos();
        Gdx.app.log("Mensaje", "tienes "+String.valueOf(alimentos.size())+" alimentos");
        /*****************UI***************************************/
        skin= new Skin(Gdx.files.internal("skin/uiskin.json"));
        table=new Table();
        Label labelId=new Label("Id",skin);
        Label labelNombre=new Label("Nombre",skin);
        table.add(labelId).width(20);
        table.add(labelNombre).width(200);
        table.row();

        if(alimentos.size()==0){
            alimentos.add(new Alimento(0,"No hay alimentos"));
        }
        for (Alimento alimento: alimentos){
            TextField textFieldId=new TextField(String.valueOf(alimento.getId()), skin);
            TextField textFieldNombre=new TextField(alimento.getNombre(), skin);
            table.add(textFieldId).width(20);
            table.add(textFieldNombre).width(200);
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
        Personaje botonAnadirAlimento=new Personaje(new Texture(Gdx.files.internal("anadiralimento60ancho40alto.png")),0,0,60,40);
        botonAnadirAlimento.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                crearVentanaAnadirAlimento();
                //Gdx.app.log(TAG, "Has hecho click");
                stage.addActor(window);
            }
        });

        stage=new Stage();
        stage.addActor(new Mensaje("Administrar alimentos",0, Gdx.graphics.getHeight()-50));
        stage.addActor(table);
        stage.addActor(botonSalir);
        stage.addActor(botonAnadirAlimento);
        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

     public void crearVentanaAnadirAlimento(){
         final TextField textFieldNombreAlimento=new TextField(" ", skin);

         TextButton botonInsertar=new TextButton("Insertar", skin);
         TextButton botonCerrar=new TextButton("Cerrar", skin);
         window=new Window("Introduce un alimento", skin);
         window.add(new Label("Nombre", skin));
         window.add(textFieldNombreAlimento).row();

         botonInsertar.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                 String nombreAlimento="";
                 int piezas=0;
                 int gramos=0;
                 int idPlato=0;
                 nombreAlimento=textFieldNombreAlimento.getText().toString();
                 interfacePlataformaBaseDeDatos.anadirAlimento(nombreAlimento);
                 mostrarmensaje("Alimento: "+nombreAlimento+", añadido.");
                 juego.verPantalla(4);
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
