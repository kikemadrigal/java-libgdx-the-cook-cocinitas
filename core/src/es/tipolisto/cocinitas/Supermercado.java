package es.tipolisto.cocinitas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import es.tipolisto.cocinitas.actores.Personaje;


public class Supermercado extends PantallaAbstracta {
    private static final String TAG="MENSAJE";
    Stage stage;
    Personaje actorFondoSupermercado, actorCocinero, botonSalir,botonIquierda,botonderecha
            ,botonarriba,botonAbajo;
    boolean moverDerecha, moverIzquiera, moverArriba, moverAbajo;
    Actor cocinero;
    public Supermercado(Juego juego) {
        super(juego);
    }

    @Override
    public void show() {
        stage=new Stage(new ScreenViewport());
        //Mandos
        botonSalir=new Personaje(new Texture(Gdx.files.internal("salir.png")), Gdx.graphics.getWidth()-60, Gdx.graphics.getHeight()-60,60,60);
        botonIquierda=new Personaje(new Texture(Gdx.files.internal("flechaizquierda.png")),0,60,60,60);
        botonderecha=new Personaje(new Texture(Gdx.files.internal("flechaderecha.png")),120,60,60,60);
        botonarriba=new Personaje(new Texture(Gdx.files.internal("flechaarriba.png")),60,120,60,60);
        botonAbajo=new Personaje(new Texture(Gdx.files.internal("flechaabajo.png")),60,0,60,60);
        moverDerecha=false;
        moverIzquiera=false;
        moverDerecha=false;
        moverIzquiera=false;
        //Añadimos los eventos a los mandos
        botonSalir.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.verPantalla(0);
            }
        });
        movimientoConBotonesPantallaCocinero();






        //Fondo
        actorFondoSupermercado=new Personaje(new Texture(Gdx.files.internal("supermercado.png")),0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Personaje
        actorCocinero=new Personaje(new Texture(Gdx.files.internal("cocinero.png")),0,0,30,50);
        actorCocinero.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        //Añadimos los actores a la escena
        stage.addActor(actorFondoSupermercado);
        stage.addActor(actorCocinero);
        stage.addActor(botonSalir);
        stage.addActor(botonIquierda);
        stage.addActor(botonderecha);
        stage.addActor(botonarriba);
        stage.addActor(botonAbajo);


        Gdx.input.setInputProcessor(stage);

        Gdx.app.log(TAG, "bienvenido a pantalla nivel 1");

    }

    @Override
    public void render(float delta) {
        //eventosBotones();
        movimientoConTecladoCocinero(actorCocinero);
        moverPersonaje(actorCocinero);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        stage.act(delta);
        stage.draw();
    }























    private void movimientoConBotonesPantallaCocinero(){
        botonIquierda.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Boton", "Has hecho click derecha");
                moverIzquiera=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moverIzquiera=false;
            }
        });
        botonderecha.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Boton", "Has hecho click derecha");
                moverDerecha=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moverDerecha=false;
            }
        });
        botonarriba.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Boton", "Has hecho click arriba");
                moverArriba=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moverArriba=false;
            }
        });
        botonAbajo.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Boton", "Has hecho click abajo");
                moverAbajo=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                moverAbajo=false;
            }
        });
    }

    private void movimientoConTecladoCocinero(Personaje personaje) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            actorCocinero.setX(personaje.getX()-5);
            //Gdx.app.log(TAG, "presionaste la tecla de izquierda");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            actorCocinero.setX(personaje.getX()+5);
            //Gdx.app.log(TAG, "presionaste la tecla derecha");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            actorCocinero.setY(personaje.getY()+5);
            //Gdx.app.log(TAG, "presionaste la tecla arriba");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            //Gdx.app.log(TAG, "presionaste la tecla abajo");
            actorCocinero.setY(personaje.getY()-5);
        }

    }
    public void moverPersonaje(Personaje personaje){
        if(moverDerecha)actorCocinero.setX(personaje.getX()+5);
        if(moverIzquiera)actorCocinero.setX(personaje.getX()-5);
        if(moverArriba) actorCocinero.setY(personaje.getY()+5);
        if(moverAbajo) actorCocinero.setY(personaje.getY()-5);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
