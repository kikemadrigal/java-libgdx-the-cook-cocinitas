package es.tipolisto.cocinitas.actores;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Mensaje extends Actor {
    BitmapFont bitmapFont;
    String texto;
    float ancho, alto, posicionX, posicionY;
    public Mensaje(String texto, int tipo){
        bitmapFont=new BitmapFont();
        this.texto=texto;
        this.posicionX=(Gdx.graphics.getHeight()/2)-getHeight();
        this.posicionY= Gdx.graphics.getHeight();
        if(tipo==1){
            bitmapFont.getData().setScale(getScaleX()*2,getScaleY()*2);
            bitmapFont.setColor(Color.RED);
        }
    }
    public Mensaje(String texto, int posicionX, int posicionY){
        bitmapFont=new BitmapFont();
        this.texto=texto;
        this.posicionX=posicionX;
        this.posicionY=posicionY;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
       bitmapFont.draw(batch, texto,posicionX, posicionY);
    }
}
