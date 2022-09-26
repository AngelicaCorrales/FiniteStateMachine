package ui;

import javafx.scene.control.ComboBox;

public class MooreTableViewRow extends FSMTableViewRow {
	
private ComboBox<String> hFunct;
	
	public MooreTableViewRow(String state) {
		super(state);
		hFunct= new ComboBox<String>();
		
	}

	public ComboBox<String> gethFunct() {
		return hFunct;
	}

	public void sethFunct(ComboBox<String> hFunct) {
		this.hFunct = hFunct;
	}
	

}
