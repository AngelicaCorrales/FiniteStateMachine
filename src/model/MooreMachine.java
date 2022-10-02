package model;

import java.util.ArrayList;

public class MooreMachine extends FiniteStateMachine{

	private ArrayList<String> outputResult; // resultados de la funcion de salida h ---h: Q->R
	private ArrayList<String> newOutputResult; //resultados de la funcion de salida h del nuevo automata conexo y minimo equivalente

	public MooreMachine(String[] inputSymbols, String[] outputSymbols, Integer numberofStates) {
		super(inputSymbols, outputSymbols, numberofStates);
		outputResult=new ArrayList<String>();
		newOutputResult=new ArrayList<String>();
	}

	public ArrayList<String> getOutputResult() {
		return outputResult;
	}


	public void setOutputResult(ArrayList<String> outputResult) {
		this.outputResult = outputResult;
	}

	public ArrayList<String> getNewOutputResult() {
		return newOutputResult;
	}

	public void setNewOutputResult(ArrayList<String> newOutputResult) {
		this.newOutputResult = newOutputResult;
	}

	@Override
	public void removeOutputResult(int index) {
		outputResult.remove(index);
	}

	/*
	 * Paso 2a: Formar una particion inicial P1 de Q
	 */
	@Override
	public void initialPartition() {
		ArrayList<ArrayList<String>> partition1=new ArrayList<ArrayList<String>>();
		for(int k=0;k<super.getOutputAlphabet().size();k++) { //se mira cada simbolos de salida del alfabeto
			int index=-1;
			boolean exit=false;
			ArrayList<String> equalSymbols=new ArrayList<String>(); //bloque de estados con el mismo resultado de la funcion de salida 
			for(int i=0;i<outputResult.size() && !exit;i++) {
				index=i;
				for(int j=i;j<outputResult.size();j++) {
					if(outputResult.get(i).equals(super.getOutputAlphabet().get(k)) && outputResult.get(i).equals(outputResult.get(j))) {
						//si un estado qi tiene el mismo resultado de la funcion de salida que otro qj...
						if(equalSymbols.indexOf(super.getStates().get(i))==-1) {
							equalSymbols.add(super.getStates().get(i)); //se agrega qi al bloque 
						}

						if(equalSymbols.indexOf(super.getStates().get(j))==-1) {
							equalSymbols.add(super.getStates().get(j)); //se agrega qj al bloque 
						}
						i=j;
					}else if(!outputResult.get(i).equals(super.getOutputAlphabet().get(k))) {
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
		newOutputResult.add(outputResult.get(index));
	}
	
}



