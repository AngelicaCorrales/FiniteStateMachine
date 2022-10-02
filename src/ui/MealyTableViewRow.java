package ui;

import java.util.ArrayList;

import javafx.scene.control.ComboBox;

public class MealyTableViewRow extends FSMTableViewRow{
	
	private ArrayList<ComboBox<String>> gFunct;
	
	/*
	 * MealyTableViewRow es una fila de la tabla para una maquina de Mealy, para agregar:
	 * 		los valores de la funcion g para el estado q con todos los simbolos de entrada s
	 */
	public MealyTableViewRow(String state) {
		super(state);
		gFunct= new ArrayList<ComboBox<String>>();
		
	}

	public ArrayList<ComboBox<String>> getgFunct() {
		return gFunct;
	}

	public void setgFunct(ArrayList<ComboBox<String>> gFunct) {
		this.gFunct = gFunct;
	}
	
	

}
