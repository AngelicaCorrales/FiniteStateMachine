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
		states=new ArrayList<>();
		blocks=new ArrayList<>();
		inputAlphabet= new ArrayList<>(Arrays.asList(inputSymbols));
		outputAlphabet= new ArrayList<>(Arrays.asList(outputSymbols));
		for(int k=0;k<numberOfStates;k++) {
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
