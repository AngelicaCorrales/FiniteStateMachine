package model;

import java.util.ArrayList;


public class MooreMinimizedRow extends FSMMinimizedRow{
	private String hFunct;

	public MooreMinimizedRow(String newState, ArrayList<String> block, ArrayList<String> fFunct, String hFunct) {
		super(newState, block, fFunct);
		this.sethFunct(hFunct);
	}

	public String gethFunct() {
		return hFunct;
	}

	public void sethFunct(String hFunct) {
		this.hFunct = hFunct;
	}

}
