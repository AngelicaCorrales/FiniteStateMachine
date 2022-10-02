package model;

import java.util.ArrayList;

public class MealyMachine extends FiniteStateMachine{

	private ArrayList<ArrayList<String>> outputResult; // matrix de resultados de la funcion de salida g ---g: QxS->R
	private ArrayList<ArrayList<String>> newOutputResult; // matrix de resultados de la funcion de salida g del nuevo automata conexo y minimo equivalente

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

	/*
	 * Quitar de la matrix el resultado de la funcion de salida asociado al estado al que no es posible llegar desde el estado inicial
	 */
	@Override
	public void removeOutputResult(int index) {
		outputResult.remove(index);
	}
	
	/*
	 * combinationsOfOutputs permite obtener las combinaciones de los simbolos de salida asociados a los estados
	 */
	public ArrayList<ArrayList<String>> combinationsOfOutputs(){
		ArrayList<ArrayList<String>> combinations=new ArrayList<ArrayList<String>>();
		for(int i=0;i<outputResult.size();i++) {
			combinations.add(new ArrayList<String>());
			combinations.get(combinations.size()-1).addAll(outputResult.get(i));
			int count =0;
			for(int a=0;a<combinations.size();a++) {
				if(outputResult.get(i).equals(combinations.get(a))) {
					count++;
				}
				if(count>1) {
					combinations.remove(combinations.size()-1);
				}
			}
		}
		return combinations;
	}

	/*
	 * Paso 2a: Formar una particion inicial P1 de Q
	 */
	@Override
	public void initialPartition() {
		ArrayList<ArrayList<String>> combinations=combinationsOfOutputs();
		ArrayList<ArrayList<String>> partition1=new ArrayList<ArrayList<String>>();
		for(int a=0;a<combinations.size();a++) { //se mira cada combinacion de los simbolos de salida asociados a los estados
			int index=-1;
			boolean exit=false;
			ArrayList<String> equalSymbols=new ArrayList<String>(); //bloque de estados con los mismos resultados de las funciones de salidas para todos los simbolos de entrada
			for(int i=0;i<outputResult.size()  && !exit;i++) {
				index=i;
				for(int j=i;j<outputResult.size();j++) {
					if(outputResult.get(i).equals(combinations.get(a)) && outputResult.get(i).equals(outputResult.get(j))) {
						//si un estado qi tiene los mismos resultados de las funciones de salidas que otro qj...
						if(equalSymbols.indexOf(super.getStates().get(i))==-1) {
							equalSymbols.add(super.getStates().get(i)); //se agrega qi al bloque 
						}

						if(equalSymbols.indexOf(super.getStates().get(j))==-1) {
							equalSymbols.add(super.getStates().get(j)); //se agrega qj al bloque 
						}
						i=j;
					}else if(!outputResult.get(i).equals(combinations.get(a))) {
						i++;
					}
				}
				i=index;
				exit=true;
			}	
			partition1.add(equalSymbols); // se agrega el bloque a la particion inicial
		}
		getPartitions().add(partition1); // se agrega la particion inicial al conjunto de particiones
	}

	/*
	 * Paso 3.2: Agregar nuevas salidas para el automata conexo y minimo equivalente.
	 */
	@Override
	public void addNewOutputResult(int index, int i) {
		newOutputResult.add(new ArrayList<String>());
		newOutputResult.get(i).addAll(outputResult.get(index));
	}


































}
