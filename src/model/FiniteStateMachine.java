package model;

import java.util.ArrayList;
import java.util.Arrays;

public class FiniteStateMachine {
	
	private ArrayList<String> states;
	private ArrayList<String> inputAlphabet;
	private ArrayList<String> outputAlphabet;
	private String[][] stateTransition;
	private ArrayList<String> blocks;

	public FiniteStateMachine(String[] inputSymbols, String[] outputSymbols, Integer numberOfStates) {
		states=new ArrayList<String>();
		blocks=new ArrayList<String>();
		inputAlphabet= new ArrayList<String>(Arrays.asList(inputSymbols));
		outputAlphabet= new ArrayList<String>(Arrays.asList(outputSymbols));
		int numASCII=65;
		for(int k=0;k<numberOfStates;k++) {
			states.add(Character.toString((char) numASCII));
			numASCII++;
		}
	}

	public ArrayList<String> getStates() {
		return states;
	}

	public void setStates(ArrayList<String> states) {
		this.states = states;
	}

	public ArrayList<String> getInputAlphabet() {
		return inputAlphabet;
	}

	public void setInputAlphabet(ArrayList<String> inputAlphabet) {
		this.inputAlphabet = inputAlphabet;
	}

	public ArrayList<String> getOutputAlphabet() {
		return outputAlphabet;
	}

	public void setOutputAlphabet(ArrayList<String> outputAlphabet) {
		this.outputAlphabet = outputAlphabet;
	}

	public String[][] getStateTransition() {
		return stateTransition;
	}

	public void setStateTransition(String[][] stateTransition) {
		this.stateTransition = stateTransition;
	}

	public ArrayList<String> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<String> blocks) {
		this.blocks = blocks;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
