package model;

import java.util.ArrayList;

public class MealyMachine extends FiniteStateMachine{
	
	private ArrayList<ArrayList<String>> outputResult;
	private ArrayList<ArrayList<String>> newOutputResult;
	
	public MealyMachine(String[] inputSymbols, String[] outputSymbols, Integer numberofStates) {
		super(inputSymbols, outputSymbols, numberofStates);
		outputResult = new ArrayList<ArrayList<String>>();
		newOutputResult = new ArrayList<ArrayList<String>>();
		
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
	
	public ArrayList<ArrayList<String>> getNewOutputResult() {
		return newOutputResult;
	}

	public void setNewOutputResult(ArrayList<ArrayList<String>> newOutputResult) {
		this.newOutputResult = newOutputResult;
	}

	
	@Override
	public void removeOutputResult(int index) {
		outputResult.remove(index);
	}
	
	@Override
	public void particioningAlgorithm() {
		
	}

	@Override
	public void addNewOutputResult(int index, int i) {
		newOutputResult.get(i).addAll(outputResult.get(index));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
