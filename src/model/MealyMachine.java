package model;

import java.util.ArrayList;

public class MealyMachine extends FiniteStateMachine{
	
	private ArrayList<ArrayList<String>> outputResult;

	public MealyMachine(String[] inputSymbols, String[] outputSymbols, Integer numberofStates) {
		super(inputSymbols, outputSymbols, numberofStates);
		outputResult = new ArrayList<ArrayList<String>>();
		
		for(int i=0;i<numberofStates;i++) {
			outputResult.add(new ArrayList<String>());
		}
	}

	public ArrayList<ArrayList<String>> getOutputResult() {
		return outputResult;
	}

	public void setOutputResult(ArrayList<ArrayList<String>> outputResult) {
		this.outputResult = outputResult;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
