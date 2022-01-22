package es.tipolisto.cocinitas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import es.tipolisto.cocinitas.actores.Mensaje;

public class Administrar extends PantallaAbstracta {
    Stage stage;
    private Skin skin;
    Juego juego;
    public Administrar(Juego juego) {
        super(juego);
        this.juego=juego;
    }

    @Override
    public void show() {
        super.show();
        stage=new Stage(new ScreenViewport());
        Table table=new Table();
        skin=new Skin(Gdx.files.internal("skin/uiskin.json"));


        final TextButton textButtonAdministrarAlimentos=new TextButton("Alimentos", skin, "default");
        final TextButton textButtonAdministrarPlatos=new TextButton("Platos", skin, "default");
        final TextButton textButtonAdministrarRecetas=new TextButton("Recetas", skin, "default");
        table.add(textButtonAdministrarAlimentos).width(200f).height(40).space(20).row();
        table.add(textButtonAdministrarPlatos).width(200f).height(40).space(20).row();
        table.add(textButtonAdministrarRecetas).width(200f).height(40).space(20).row();
        table.setFillParent(true);



        textButtonAdministrarAlimentos.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.verPantalla(4);
            }
        });
        textButtonAdministrarPlatos.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.verPantalla(5);
            }
        });
        textButtonAdministrarRecetas.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.verPantalla(6);
            }
        });



        stage.addActor(new Mensaje("Administrar",0));
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }
}
