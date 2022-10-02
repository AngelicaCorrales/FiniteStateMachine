package model;

import java.util.ArrayList;


public class MealyMinimizedRow extends FSMMinimizedRow {
	private ArrayList<String> gFunct;

	/*
	 * MealyMinimizedRow es una fila de la tabla para una maquina de Mealy minimizada y conexa
	 */
	public MealyMinimizedRow(String newState, ArrayList<String> block, ArrayList<String> fFunct, ArrayList<String> gFunct) {
		super(newState, block, fFunct);
		this.setgFunct(new ArrayList<String>(gFunct));
	}

	public ArrayList<String> getgFunct() {
		return gFunct;
	}

	public void setgFunct(ArrayList<String> gFunct) {
		this.gFunct = gFunct;
	}

}
