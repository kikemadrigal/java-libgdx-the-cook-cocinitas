package es.tipolisto.cocinitas.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import es.tipolisto.cocinitas.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Juego(new DesktopInterfacePlataformaBaseDeDatos()), config);
		//new LwjglApplication(new Juego(),config);
	}
}
