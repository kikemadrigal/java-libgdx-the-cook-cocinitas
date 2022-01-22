package es.tipolisto.cocinitas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

import es.tipolisto.cocinitas.Beans.Plato;
import es.tipolisto.cocinitas.actores.Mensaje;
import es.tipolisto.cocinitas.actores.Personaje;


public class Olla extends PantallaAbstracta {
    Stage stage;
    Personaje holla,botonSalir;
    ArrayList<Plato> platos;
    InterfacePlataformaBaseDeDatos interfacePlataforma;
    public Olla(Juego juego, InterfacePlataformaBaseDeDatos interfacePlataforma) {
        super(juego);
        this.interfacePlataforma=interfacePlataforma;
        //this.interfacePlataforma.crear();
    }

    @Override
    public void show() {
        Gdx.app.log("Mensaje", "Estas en la pantalla holla");
        stage=new Stage(new ScreenViewport());
        holla=new Personaje(new Texture("holla.png"), Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()/2,100,100);
        final TextButton buttonSupermercado=new TextButton("Hacer la compra", new Skin(Gdx.files.internal("skin/uiskin.json")), "default");
        buttonSupermercado.setPosition(Gdx.graphics.getHeight(), 10);
        botonSalir=new Personaje(new Texture(Gdx.files.internal("salir.png")), Gdx.graphics.getWidth()-60, Gdx.graphics.getHeight()-60,60,60);
        botonSalir.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Gdx.app.log("Mensaje", "Has hecho click");
                juego.verPantalla(0);
            }
        });
        buttonSupermercado.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // super.clicked(event, x, y);
                juego.verPantalla(1);
            }
        });
        Gdx.input.setInputProcessor(stage);
        stage.addActor(new Mensaje("Cesta de la compra",10, Gdx.graphics.getHeight()-50));
        //Obtenemos todoslos platos y elegimos uno al hazar

        platos=interfacePlataforma.devolverTodosLosPlatos();
        int numeroAleatorio=(int) MathUtils.random(0,platos.size());
        stage.addActor(new Mensaje("Hoy vamos a preparar..."+interfacePlataforma.obtenerUnPlato(numeroAleatorio).getNombre(),10, Gdx.graphics.getHeight()-250));

        stage.addActor(buttonSupermercado);
        stage.addActor(botonSalir);
        stage.addActor(holla);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) interfacePlataforma.mostrarDialogo();

        stage.act(delta);
        stage.draw();
    }
}
