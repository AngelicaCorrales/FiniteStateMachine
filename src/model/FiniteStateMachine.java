package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

	public void minimizeMachine() {
		//Paso 1: eliminar todos los estados que no son accesibles desde el estado inicial
		Integer[][] statesMachine=floydWarshall();
		ArrayList<Integer> conectionStates=new ArrayList<Integer>();
		Collections.addAll(conectionStates, statesMachine[0]); //Sirve para determinar el automata conexo equivalente
		for(int j=0; j< states.size();j++) {
			if(statesMachine[0][j]>=Integer.MAX_VALUE/2) {
				removeOutputResult(j);
				states.remove(j);
				stateTransition.remove(j);
				conectionStates.remove(j);
				j--;
			}
		}
		
		//Paso 2: algoritmo de particionamiento
		particioningAlgorithm();
		
		//Paso 3: Definir nuevos estados, sucesores y salidas del automata minimo equivalente
		minimumEquivalentMachine();
	}
	
	public void particioningAlgorithm() {
		//Paso 2a: Formar una particion inicial P1 de Q

		initialPartition();

		//Paso 2b: Obtener Pk+1 de Pk

		partitionkPLUS1();
	}
	
	public void initialPartition() {
		
	}
	
	public void minimumEquivalentMachine() {
		finalPartition.addAll(partitions.get(partitions.size()-1));
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
	
	public Integer[][] floydWarshall() {
		int size=states.size();
		Integer dist[][] = new Integer[size][size];

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
	
	public void distWeights(Integer[][] dist) {
		for(int i=0; i<stateTransition.size();i++) {
			for(int j=0; j<stateTransition.get(i).size();j++) {
				int stateCol= states.indexOf(stateTransition.get(i).get(j));
				if(i!=stateCol) {
					dist[i][stateCol]=1;
				}
			}
				
			
		}

	}

	public void createCopyOfPartitions(ArrayList<ArrayList<ArrayList<String>>> copy){
		for(int j=0; j<getPartitions().size();j++) {
			copy.add(new ArrayList<ArrayList<String>>());
			for(int a=0; a<getPartitions().get(j).size();a++) {
				copy.get(j).add(new ArrayList<String>());
				copy.get(j).get(a).addAll(getPartitions().get(j).get(a));
			}
		}
	}
	
	public void partitionkPLUS1() {
		
		boolean find=true; //Indica si dos estados estan en un mismo bloque o no
		boolean exit=false;
		
		for(int j=0; j<getPartitions().size() && !exit;j++) { //Se mira cada particion k

			ArrayList<ArrayList<String>> partition_kPLUS1=new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<ArrayList<String>>> partitionsCopy=new ArrayList<ArrayList<ArrayList<String>>>();
			createCopyOfPartitions(partitionsCopy);
			
			for(int k=0; k<partitionsCopy.get(j).size();k++) { //Se mira cada bloque de la particion

				for(int l=0; l<partitionsCopy.get(j).get(k).size();l++) { //Se mira cada elemento de un bloque
					ArrayList<String> block_kPLUS1=new ArrayList<String>();
					String est1=partitionsCopy.get(j).get(k).get(l); 
					block_kPLUS1.add(est1);
					int indexStateBlock=-1;
					if(partitionsCopy.get(j).get(k).size()==1) {
						partition_kPLUS1.add(block_kPLUS1);
					}
					for(int p=0; p<partitionsCopy.get(j).get(k).size();p++) {
						indexStateBlock=p;
						String est2=partitionsCopy.get(j).get(k).get(p);
						find=true;
						if(l!=p) {
							for(int m=0; m<inputAlphabet.size() && find;m++) { //Se mira cada sucesor con cada simbolo de entrada
								//Sucesores de est1 y est2 a partir de cada una de las entradas.
								String st1=stateTransition.get(states.indexOf(est1)).get(m); //f(q,s)
								String st2=stateTransition.get(states.indexOf(est2)).get(m); //f(q',s)

								//Buscar sucesor de est1 dentro de los bloques. 
								for(int n=0; n<getPartitions().get(j).size() && find;n++) {
									if(getPartitions().get(j).get(n).indexOf(st1)!=-1) {

										//Cuando lo encuentre, buscar sucesor de est2 dentro de dicho bloque
										if(getPartitions().get(j).get(n).indexOf(st2)==-1) { //Si no se encuentra, est1 y est2 no estarian dentro del mismo bloque.
											find=false;	
										}
									}	
								}
							}
							
							if(find) {
								if(block_kPLUS1.indexOf(est1)==-1) {
									block_kPLUS1.add(est1);
								}

								if(block_kPLUS1.indexOf(est2)==-1) {
									block_kPLUS1.add(est2);
									partitionsCopy.get(j).get(k).remove(indexStateBlock);
									p--;
								}
							}
							
							if(p==partitionsCopy.get(j).get(k).size()-1) {
								partitionsCopy.get(j).get(k).remove(0);
								l--;
								partition_kPLUS1.add(block_kPLUS1);
							}
						}
					}
				}
			}
			getPartitions().add(partition_kPLUS1);
			if(getPartitions().get(j).equals(getPartitions().get(j+1))) { //Paso 2c: Si Pm+1=Pm, Pm es la particion final de Q
				exit=true;
			}
		}
	}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
