package model;

import java.util.ArrayList;

public class MooreMachine extends FiniteStateMachine{
	
	private ArrayList<String> outputResult;
	private ArrayList<ArrayList<String>> particioning;

	

	public MooreMachine(String[] inputSymbols, String[] outputSymbols, Integer numberofStates) {
		super(inputSymbols, outputSymbols, numberofStates);
		outputResult=new ArrayList<String>();
		particioning=new ArrayList<ArrayList<String>>();
	}
	
	public ArrayList<String> getOutputResult() {
		return outputResult;
	}


	public void setOutputResult(ArrayList<String> outputResult) {
		this.outputResult = outputResult;
	}


	public ArrayList<ArrayList<String>> getParticioning() {
		return particioning;
	}


	public void setParticioning(ArrayList<ArrayList<String>> particioning) {
		this.particioning = particioning;
	}


	
	public void particioningAlgorithm(ArrayList<String> outputs) {
		outputResult=outputs;
		for(int k=0;k<super.getOutputAlphabet().size();k++) {
			ArrayList<String> equalSymbols=new ArrayList<>();
			for(int j=0;j<outputResult.size();j++) {
				for(int i=0;i<outputResult.size();i++) {
					if(outputResult.get(j).equals(outputResult.get(i))) {
						equalSymbols.add(super.getStates().get(j));
					}
				}
			}
			particioning.add(equalSymbols);
		}
	}
	
	
	public void matrizAdyacencia() {
		 String[][] StatesxStates= new String[super.getStates().size()][super.getStates().size()];;

		for(int i=0; i<super.getStates().size();i++) {
			for(int j=0; j<super.getStateTransition().size();j++) {
				for(int k=0; k<super.getStateTransition().size();k++) {
					if(super.getStates().get(i).equals(super.getStateTransition().get(j).get(k))) {
						StatesxStates[i][k]=super.getStateTransition().get(j).get(k);
					}
				}
			}
		}
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
