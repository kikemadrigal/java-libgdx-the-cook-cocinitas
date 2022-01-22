package es.tipolisto.cocinitas;

import com.badlogic.gdx.Screen;

public class PantallaAbstracta implements Screen {
    public Juego juego;
    public PantallaAbstracta(Juego juego){
        this.juego=juego;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
