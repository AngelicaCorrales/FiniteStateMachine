package ui;

import java.util.ArrayList;

import javafx.scene.control.ComboBox;

public class FSMTableViewRow {

	private String state;
	private ArrayList<ComboBox<String>> fFunct;
	
	/*
	 * FSMTableViewRow es una fila de la tabla para una maquina, ya sea Mealy o Moore, para agregar:
	 * 		los valor de la funcion f para el estado q con todos los simbolos de entrada s
	 */
	public FSMTableViewRow(String state) {
		this.state=state;
		fFunct= new ArrayList<ComboBox<String>>();
		
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ArrayList<ComboBox<String>> getfFunct() {
		return fFunct;
	}

	public void setfFunct(ArrayList<ComboBox<String>> fFunct) {
		this.fFunct = fFunct;
	}
	
	
}
