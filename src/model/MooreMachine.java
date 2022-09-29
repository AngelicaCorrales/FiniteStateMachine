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

	@Override
	public void removeOutputResult(int index) {
		outputResult.remove(index);
	}

	@Override
	public void particioningAlgorithm() {
		//Paso 2a: Formar una particion inicial P1 de Q
		for(int k=0;k<super.getOutputAlphabet().size();k++) {
			ArrayList<String> equalSymbols=new ArrayList<String>();
			for(int i=0;i<outputResult.size();i++) {
				for(int j=1;j<outputResult.size();j++) {
					if(outputResult.get(i).equals(super.getOutputAlphabet().get(k)) && outputResult.get(i).equals(outputResult.get(j))) {
						
						if(equalSymbols.indexOf(super.getStates().get(i))==-1) {
							equalSymbols.add(super.getStates().get(i));
						}
						
						if(equalSymbols.indexOf(super.getStates().get(j))==-1) {
							equalSymbols.add(super.getStates().get(j));
						}
						i=j;
					}
				}
			}
			particioning.add(equalSymbols);
		}
		
		//Paso 2b: Obtener Pk+1 de Pk
		
		for(int j=0; j<particioning.size();j++) {
			for(int k=0; k<particioning.get(j).size();k++) {
				
			}
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
