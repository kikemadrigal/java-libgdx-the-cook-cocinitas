package es.tipolisto.cocinitas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import java.util.HashMap;
import java.util.Map;

public class Juego extends Game {
	private InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos;

	public Juego(InterfacePlataformaBaseDeDatos interfacePlataformaBaseDeDatos){
		this.interfacePlataformaBaseDeDatos=interfacePlataformaBaseDeDatos;
	}
	//public Juego(){}
	//Para almacenar las pantallas:
	private Map<Integer, PantallaAbstracta> hasMapPantalla;
	@Override
	public void create () {
		hasMapPantalla=new HashMap<Integer, PantallaAbstracta>();
		hasMapPantalla.put(0, new Menu(this));
		hasMapPantalla.put(1, new Supermercado(this));
		hasMapPantalla.put(2, new Olla(this,interfacePlataformaBaseDeDatos));
		hasMapPantalla.put(3, new Administrar(this));
		hasMapPantalla.put(4, new AdministrarAlimentos(this,interfacePlataformaBaseDeDatos));
		hasMapPantalla.put(5, new AdministrarPlatos(this,interfacePlataformaBaseDeDatos));
		hasMapPantalla.put(6, new AdministrarRecetas(this,interfacePlataformaBaseDeDatos));
		//hasMapPantalla.put(7, new AdministrarIngredientes(this,interfacePlataformaBaseDeDatos));
		verPantalla(0);
	}
	public void verPantalla(int numeroPantalla) {
		setScreen(hasMapPantalla.get(numeroPantalla));
	}
	public void verLosIngredientesDeUnPlato(int idPlato) {
		setScreen(new AdministrarIngredientes(this,interfacePlataformaBaseDeDatos, idPlato));
	}

	
	
}
