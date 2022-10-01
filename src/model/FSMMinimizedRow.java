package model;

import java.util.ArrayList;


public class FSMMinimizedRow {
	private String newState;
	private String block;
	private ArrayList<String> fFunct;
	
	public FSMMinimizedRow(String newState,ArrayList<String> block, ArrayList<String> fFunct) {
		this.newState=newState;
		this.block="{";
		for(int i=0; i<block.size();i++) {
			this.block+=block.get(i);
			if(i!=block.size()-1) {
				this.block+=", ";
			}else {
				this.block+="}";
			}
				
		}
	
		
		this.fFunct= new ArrayList<String>(fFunct);
		
	}

	public String getNewState() {
		return newState;
	}

	public void setNewState(String newState) {
		this.newState = newState;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public ArrayList<String> getfFunct() {
		return fFunct;
	}

	public void setfFunct(ArrayList<String> fFunct) {
		this.fFunct = fFunct;
	}
}
