package model;

import java.util.ArrayList;
import java.util.Arrays;

public class FiniteStateMachine {
	
	private ArrayList<String> states;
	private ArrayList<String> inputAlphabet;
	private ArrayList<String> outputAlphabet;
	private ArrayList<ArrayList<String>> stateTransition;
	private ArrayList<ArrayList<ArrayList<String>>> partitions;
	
	//Para el nuevo automata minimo equivalente
	private ArrayList<ArrayList<String>> finalPartition;
	private ArrayList<String> newStates;
	private ArrayList<ArrayList<String>> newStateTransition;

	public FiniteStateMachine(String[] inputSymbols, String[] outputSymbols, Integer numberOfStates) {
		stateTransition=new ArrayList<ArrayList<String>>();
		states=new ArrayList<String>();
		inputAlphabet= new ArrayList<String>(Arrays.asList(inputSymbols));
		outputAlphabet= new ArrayList<String>(Arrays.asList(outputSymbols));
		partitions=new ArrayList<ArrayList<ArrayList<String>>>();
		finalPartition=new ArrayList<ArrayList<String>>();
		newStates=new ArrayList<String>();
		newStateTransition=new ArrayList<ArrayList<String>>();
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
	
	public ArrayList<ArrayList<ArrayList<String>>> getPartitions() {
		return partitions;
	}
	
	public void setPartitions(ArrayList<ArrayList<ArrayList<String>>> partitions) {
		this.partitions = partitions;
	}
	
	public ArrayList<ArrayList<String>> getFinalPartition() {
		return finalPartition;
	}

	public void setFinalPartition(ArrayList<ArrayList<String>> finalPartition) {
		this.finalPartition = finalPartition;
	}
	
	public ArrayList<String> getNewStates() {
		return newStates;
	}

	public void setNewStates(ArrayList<String> newStates) {
		this.newStates = newStates;
	}

	public ArrayList<ArrayList<String>> getNewStateTransition() {
		return newStateTransition;
	}

	public void setNewStateTransition(ArrayList<ArrayList<String>> newStateTransition) {
		this.newStateTransition = newStateTransition;
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
		
		//Paso 3: Definir nuevos estados, sucesores y salidas del automata minimo equivalente
		minimumEquivalentMachine();
	}
	
	public void minimumEquivalentMachine() {
		finalPartition=partitions.get(partitions.size()-1);
		int numASCII=65;
		for(int k=0;k<finalPartition.size();k++) {
			newStates.add(Character.toString((char) numASCII)+"'");
			numASCII++;
			
			newStateTransition.add(new ArrayList<String>());
		}
		
		//Paso 3.1: Encontrar nuevos sucesores
		
		boolean exit=false;
		
		for(int i=0;i<finalPartition.size();i++) { //Recorrer cada bloque de la particion final
			int index= states.indexOf(finalPartition.get(i).get(0)); //Se obtiene el indice que hace referencia al sucesor de cualquier estado(en este caso aquel que se encuentra en la posicion cero) en el bloque 
			for(int j=0;j<inputAlphabet.size();j++) {
				exit=false;
				String successor=stateTransition.get(index).get(j); //Se obtiene el sucesor del estado relacionado con el index y con el símbolo de simbolo de entrada al que j hace referencia
				for(int m=0;m<finalPartition.size() && !exit;m++) { //Recorrer cada bloque de la particion final para buscar al sucesor dentro de los bloques
					if(finalPartition.get(m).indexOf(successor)!=-1) { //Si el sucesor se encuentra en el bloque...
						exit=true;
						newStateTransition.get(i).add(newStates.get(m));//Se agrega el sucesor al nuevo estado
						
						//Paso 3.2: Agregar nuevas salidas
						if(j==0) {
							addNewOutputResult(index, i);	
						}
					}
				}
			}
		}
		
		
	}


	public void addNewOutputResult(int index, int i) {
		// Agregar nuevas salidas 
		
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
