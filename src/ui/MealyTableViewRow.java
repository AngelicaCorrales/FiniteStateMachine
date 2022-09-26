package ui;

import java.util.ArrayList;

import javafx.scene.control.ComboBox;

public class MealyTableViewRow extends FSMTableViewRow{
	
	
	
	private ArrayList<ComboBox<String>> gFunct;
	
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
