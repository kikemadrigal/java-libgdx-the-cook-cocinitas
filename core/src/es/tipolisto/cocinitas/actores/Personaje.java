package es.tipolisto.cocinitas.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Personaje extends Actor {
    Texture texture;
    float x,y,width,height;

    public Personaje(Texture texture, float x, float y, float with, float height){
        this.texture=texture;
        this.setX(x);
        this.setY(y);
        this.setWidth(with);
        this.setHeight(height);
    }
   /* public void cambiarAnchoYAlto(float ancho, float alto){
        this.setWidth(ancho);
        this.setHeight(alto);
    }*/
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }
    /*public void cambiarPosicion(float x, float y){
        this.setPosition(x,y);
    }*/

}
