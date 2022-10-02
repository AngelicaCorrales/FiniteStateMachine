package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FiniteStateMachine {
	/*
	 * Un automata de estado finito M, tanto de Mealy como de Moore, cuenta con:
	 *  un conjunto finito de estados Q,
	 *  un alfabeto finito de simbolos de entrada S,
	 *  un alfabeto finito de simbolos de salida R,
	 *  una funcion de transicion de estados f--- f:QxS->Q, 
	 *  una funcion de salida (la cual se define de manera diferente en Mealy y en Moore),
	 *  un estado inicial q0
	 */
	private ArrayList<String> states;  // Q (el estado en la posicion 0 del ArrayList corresponde a q0)
	private ArrayList<String> inputAlphabet; // S
	private ArrayList<String> outputAlphabet; // R
	private ArrayList<ArrayList<String>> stateTransition; // matrix de resultados de la funcion f con cada estado y cada simbolo de S.  
	private ArrayList<ArrayList<ArrayList<String>>> partitions; // conjunto de todas las particiones resultantes del algoritmo de particionamiento sobre el automata conexo equivalente 
	
	//Para el nuevo automata conexo y minimo equivalente
	private ArrayList<ArrayList<String>> finalPartition; //Pf particion final con sus bloques, resulta del algoritmo de particionamiento
	private ArrayList<String> newStates; // conjunto de nuevos nombres para los estados correspondientes a los bloques de Pf
	private ArrayList<ArrayList<String>> newStateTransition; // matrix de resultados de la funcion f con cada nuevo estado y cada simbolo de S.  

	/*
	 * Constructor of FiniteStateMachine
	 * Se crea una maquina inicialmente con S, R, y un numero dado de estados que da resultado a Q (lo que se obtiene en la screen 1).
	 * Luego de crear la maquina, se le agrega, con los datos que se obtienen de la screen 2:
	 * 		los valores de matrix de resultados de la funcion f con cada estado y cada simbolo de S.  
	 * 		los valores de los resultados de la funcion de salida. 
	 */
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

	/*
	 * minimizeMachine se basa en el pseudocodigo del algoritmo de minimizacion de automatas de estado finito
	 * Se aborda el paso 1, 2 y 3
	 */
	public void minimizeMachine() {
		//Paso 1: eliminar todos los estados que no son accesibles desde el estado inicial
		deleteInaccessibleStates();
		
		//Paso 2: algoritmo de particionamiento
		particioningAlgorithm();
		
		//Paso 3: Definir nuevos estados, sucesores y salidas del automata minimo equivalente
		minimumEquivalentMachine();
	}
	
	//Paso 1: eliminar todos los estados que no son accesibles desde el estado inicial
	public void deleteInaccessibleStates() {
		Integer[][] statesMachine=floydWarshall(); //Sirve para determinar el automata conexo equivalente
		ArrayList<Integer> conectionStates=new ArrayList<Integer>();
		Collections.addAll(conectionStates, statesMachine[0]); // solo interesa en este caso la fila del estado inicial
		for(int j=0; j< states.size();j++) {
			if(conectionStates.get(j)>=Integer.MAX_VALUE/2) {
				removeOutputResult(j); 
				states.remove(j);
				stateTransition.remove(j);
				conectionStates.remove(j);
				j--;
			}
		}
	}
	
	
	 //Paso 2: algoritmo de particionamiento
	public void particioningAlgorithm() {
		//Paso 2a: Formar una particion inicial P1 de Q

		initialPartition();

		//Paso 2b: Obtener Pk+1 de Pk

		partitionkPLUS1();
	}


	// Paso 2a: Formar una particion inicial P1 de Q
	public void initialPartition() {
		//se sobreescribe en Mealy y en Moore 
	}
	
	//Paso 2b: Obtener Pk+1 de Pk
	public void partitionkPLUS1() {

		boolean find=true; //Indica si dos estados estan en un mismo bloque o no
		boolean exit=false;

		for(int j=0; j<getPartitions().size() && !exit;j++) { //Se mira cada particion k

			ArrayList<ArrayList<String>> partition_kPLUS1=new ArrayList<ArrayList<String>>(); //particion k+1
			ArrayList<ArrayList<ArrayList<String>>> partitionsCopy=new ArrayList<ArrayList<ArrayList<String>>>();
			createCopyOfPartitions(partitionsCopy);

			for(int k=0; k<partitionsCopy.get(j).size();k++) { //Se mira cada bloque de la particion

				for(int l=0; l<partitionsCopy.get(j).get(k).size();l++) { //Se mira cada elemento de un bloque
					ArrayList<String> block_kPLUS1=new ArrayList<String>(); //bloque de particion k+1
					String est1=partitionsCopy.get(j).get(k).get(l); //est1 es un estado del bloque de Pk
					block_kPLUS1.add(est1);
					int indexStateBlock=-1;
					if(partitionsCopy.get(j).get(k).size()==1) {
						partition_kPLUS1.add(block_kPLUS1);
					}
					for(int p=0; p<partitionsCopy.get(j).get(k).size();p++) {
						indexStateBlock=p;
						String est2=partitionsCopy.get(j).get(k).get(p); //est2 es un estado del bloque de Pk
						find=true;
						if(l!=p) {
							for(int m=0; m<inputAlphabet.size() && find;m++) { 
								//Sucesores de est1 y est2 a partir de uno de los simbolos de entrada s:
								String st1=stateTransition.get(states.indexOf(est1)).get(m); //f(est1,s)= st1
								String st2=stateTransition.get(states.indexOf(est2)).get(m); //f(est2,s)=st2

								//Buscar sucesor de est1 (st1) dentro de los bloques. 
								for(int n=0; n<getPartitions().get(j).size() && find;n++) {
									if(getPartitions().get(j).get(n).indexOf(st1)!=-1) {

										//Cuando lo encuentre, buscar sucesor de est2 (st2) dentro del bloque donde esta st1
										if(getPartitions().get(j).get(n).indexOf(st2)==-1) {
											find=false;	 //Si no se encuentra, est1 y est2 no estarian dentro del mismo bloque.
										}
									}	
								}
							}

							if(find) { //Si st1 y st2 se encontraron en el mismo bloque...
								if(block_kPLUS1.indexOf(est1)==-1) {
									block_kPLUS1.add(est1); //se agrega est1 al bloque de Pk+1
								}

								if(block_kPLUS1.indexOf(est2)==-1) {
									block_kPLUS1.add(est2); //se agrega est2 al bloque de Pk+1
									partitionsCopy.get(j).get(k).remove(indexStateBlock);
									p--;
								}
							}

							if(p==partitionsCopy.get(j).get(k).size()-1) {
								partitionsCopy.get(j).get(k).remove(0);
								l--;
								partition_kPLUS1.add(block_kPLUS1); //se agrega el bloque de Pk+1 a la particion Pk+1
							}
						}
					}
				}
			}
			partitions.add(partition_kPLUS1); // Cuando la particion Pk+1 ya tiene todos los bloques, se agrega al conjunto de las particiones
			
			//Paso 2c: Si Pm+1=Pm, Pm es la particion final de Q
			if(partitions.get(j).equals(partitions.get(j+1))) { 
				exit=true;
				//Fin del algoritmo de particionamiento
			}
		}
	}

	//Paso 3: Definir nuevos estados, sucesores y salidas del automata minimo equivalente
	public void minimumEquivalentMachine() {
		finalPartition.addAll(partitions.get(partitions.size()-1)); //Pf particion final
		int numASCII=65;
		for(int k=0;k<finalPartition.size();k++) {
			newStates.add(Character.toString((char) numASCII)+"'");
			numASCII++;
			
			newStateTransition.add(new ArrayList<String>());
		}
		
		//Paso 3.1: Encontrar nuevos sucesores
		
		boolean exit=false;
		
		for(int i=0;i<finalPartition.size();i++) { //Se recorre cada bloque de la particion final
			int index= states.indexOf(finalPartition.get(i).get(0)); //Se obtiene el index que hace referencia al sucesor de cualquier estado q(en este caso aquel que se encuentra en la posicion 0) en el bloque 
			for(int j=0;j<inputAlphabet.size();j++) {
				exit=false;
				String successor=stateTransition.get(index).get(j); //Se obtiene el sucesor dado el estado q y un simbolo de entrada s ---f(q,s)=successor 
				for(int m=0;m<finalPartition.size() && !exit;m++) { //Se recorre cada bloque de la particion final para buscar al sucesor dentro de los bloques
					if(finalPartition.get(m).indexOf(successor)!=-1) { //Si el sucesor se encuentra en el bloque...
						exit=true;
						newStateTransition.get(i).add(newStates.get(m));//Se agrega el nuevo estado sucesor a la matrix de resultados de la funcion f de los nuevos estados
						
						//Paso 3.2: Agregar nuevas salidas
						if(j==0) {
							addNewOutputResult(index, i);	
						}
					}
				}
			}
		}
		
		
	}

	//Paso 3.2: Agregar nuevas salidas para el automata conexo y minimo equivalente.
	public void addNewOutputResult(int index, int i) {
		// Agregar nuevas salidas. Se sobreescribe en Mealy y en Moore
		
	}

	public void removeOutputResult(int index) {
		//Quitar los resultados de la funcion de salida asociados al estado al que no es posible llegar desde el estado inicial. Se sobreescribe en Mealy y en Moore
	}
	
	/*
	 * Algoritmo de Floyd Warshall
	 * Sirve para determinar el automata conexo equivalente
	 * En este caso, permite saber los estados que se pueden acceder a partir de q0 y los que no.
	 */
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

	
}
