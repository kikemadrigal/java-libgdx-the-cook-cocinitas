package es.tipolisto.cocinitas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import es.tipolisto.cocinitas.actores.Personaje;

public class Menu extends PantallaAbstracta {
    Stage stage;
    private Skin skin;
    Juego juego;
    

    public Menu(Juego juego) {
    	super(juego);
        this.juego=juego;
	}

	@Override
    public void show() {
        super.show();
        stage=new Stage(new ScreenViewport());
        
       Table table=new Table();
	     skin=new Skin(Gdx.files.internal("skin/uiskin.json"));
	     
	    
	     final Label labelTitle=new Label("The Cook", skin);
	     final Label labelDescription1=new Label("Eres un cocinero de alto nivel y tus comensales te encargan unos platos.", skin);
	     final Label labelDescription2=new Label("Encuentra los ingredientes en un tiempo determinado.", skin);

	     final TextButton textButtonMarket=new TextButton("Market", skin, "default");
	     final TextButton textButtonHighScore=new TextButton("High score", skin, "default");
	     final TextButton textButtonSettings=new TextButton("Settings", skin, "default");
	
	     //table.add(labelTitle).width(200f).height(40).space(20).row();
	     //table.add(labelDescription).width(200f).height(40).space(20).row();
	     //table.row();
	     table.add(textButtonMarket).width(200f).height(40).space(20).row();
	     table.add(textButtonHighScore).width(200f).height(40).space(20).row();
	     table.add(textButtonSettings).width(200f).height(40).space(20).row();
	     table.setFillParent(true);



	     textButtonMarket.addListener(new ClickListener(){
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	              juego.verPantalla(1);
	         }
	     });

        Personaje personajeAdministrar=new Personaje(new Texture(Gdx.files.internal("llaveinglesa50ancho70alto.png")),5,5,50,70);
        personajeAdministrar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Mensaje", "Vamos a la pantalla 4, administrar...");
                juego.verPantalla(3);
            }
        });
        
        stage.addActor(labelTitle);
        labelTitle.setPosition((Gdx.graphics.getWidth()/2)-20, Gdx.graphics.getHeight()-50);
        labelDescription1.setPosition(20, Gdx.graphics.getHeight()-100);
        stage.addActor(labelDescription1);
        labelDescription2.setPosition(20, Gdx.graphics.getHeight()-120);
        stage.addActor(labelDescription2);
        stage.addActor(table);
        stage.addActor(personajeAdministrar);
        Gdx.input.setInputProcessor(stage);
    }

	
	
	
	/*public void crearTablaYBotonesMenu(){
		 Table table=new Table();
	     skin=new Skin(Gdx.files.internal("skin2/uiskin.json"));

	     final TextButton textButtonHolla=new TextButton("Jugar", skin, "default");
	     final TextButton textButtonPuntuacion=new TextButton("Puntuacion", skin, "default");
	     final TextButton textButtonOtro1=new TextButton("Configuracion", skin, "default");
	     final TextButton textButtonOtro2=new TextButton("Records", skin, "default");
	        
	     table.add(textButtonHolla).width(200f).height(40).space(20).row();
	     table.add(textButtonOtro1).width(200f).height(40).space(20).row();
	     table.add(textButtonOtro2).width(200f).height(40).space(20).row();
	     table.setFillParent(true);



	     textButtonHolla.addListener(new ClickListener(){
	         @Override
	         public void clicked(InputEvent event, float x, float y) {
	              // super.clicked(event, x, y);
	              juego.verPantalla(2);
	         }
	     });
	}*/
	
	
	
	
	
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }
}
