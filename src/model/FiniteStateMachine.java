package model;

import java.util.ArrayList;
import java.util.Arrays;

public class FiniteStateMachine {
	
	private ArrayList<String> states;
	private ArrayList<String> inputAlphabet;
	private ArrayList<String> outputAlphabet;
	private ArrayList<ArrayList<String>> stateTransition;
	private ArrayList<String> blocks;

	public FiniteStateMachine(String[] inputSymbols, String[] outputSymbols, Integer numberOfStates) {
		stateTransition=new ArrayList<ArrayList<String>>();
		states=new ArrayList<String>();
		blocks=new ArrayList<String>();
		inputAlphabet= new ArrayList<String>(Arrays.asList(inputSymbols));
		outputAlphabet= new ArrayList<String>(Arrays.asList(outputSymbols));
		int numASCII=65;
		for(int k=0;k<numberOfStates;k++) {
			states.add(Character.toString((char) numASCII));
			numASCII++;
			
			stateTransition.add(new ArrayList<String>());
			
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

	public ArrayList<ArrayList<String>> getStateTransition() {
		return stateTransition;
	}

	public void setStateTransition(ArrayList<ArrayList<String>> stateTransition) {
		this.stateTransition = stateTransition;
	}

	public ArrayList<String> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<String> blocks) {
		this.blocks = blocks;
	}
	
	public void mimimizeMachine() {
		//Paso 1: eliminar todos los estados que no son accesibles desde el estado inicial
		int[][] statesMachine=floydWarshall();
		for(int j=0; j< states.size();j++) {
			if(statesMachine[0][j]>=Integer.MAX_VALUE/2) {
				removeOutputResult(j);
				states.remove(j);
				stateTransition.remove(j);
			}
		}
		
		//Paso 2: algoritmo de particionamiento
		particioningAlgorithm();
	}
	
	public void removeOutputResult(int index) {
		//Quitar las salidas asociadas al estado al que no es posible llegar desde el estado inicial
	}
	
	public void particioningAlgorithm() {
		
	}
	
	public int[][] floydWarshall() {
		int size=states.size();
		int dist[][] = new int[size][size];

		for (int i=0;i<size;i++) {
			for (int j=0;j<size;j++) {
				dist[i][j] = Integer.MAX_VALUE/2;
			
			}
		}

		for (int i = 0; i < size; i++) {
			dist[i][i] = 0;
		}

		distWeights(dist);
		
		for (int k = 0; k < size; k++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if(dist[i][k] + dist[k][j] < dist[i][j]){
						dist[i][j] = dist[i][k] + dist[k][j];
					
					}
				}
			}
		}
		
		return dist;
	}
	
	public void distWeights(int[][] dist) {
		for(int i=0; i<stateTransition.size();i++) {
			for(int j=0; j<stateTransition.get(i).size();j++) {
				int stateCol= states.indexOf(stateTransition.get(i).get(j));
				if(i!=stateCol) {
					dist[i][stateCol]=1;
				}
			}
				
			
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
