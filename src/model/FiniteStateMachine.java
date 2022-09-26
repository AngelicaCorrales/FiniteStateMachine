package model;

import java.util.ArrayList;
import java.util.Arrays;

public class FiniteStateMachine {
	
	private ArrayList<String> states;
	private ArrayList<String> inputAlphabet;
	private ArrayList<String> outputAlphabet;
	private ArrayList<ArrayList<String>> stateTransition;
	private ArrayList<String> blocks;

	public FiniteStateMachine(String[] inputSymbols, String[] outputSymbols, Integer numberOfStates, ArrayList<ArrayList<String>> transitions) {
		stateTransition=transitions;
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
			for(int j=0; j<stateTransition.size();j++) {
				if(i!=j) {
					dist[i][j]=super.getWeights().get(i).get(j);
				}
				
			}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
