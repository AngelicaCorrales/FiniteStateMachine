package model;

import java.util.ArrayList;

public class MooreMachine extends FiniteStateMachine{

	private ArrayList<String> outputResult;
	private ArrayList<String> newOutputResult;

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

	@Override
	public void particioningAlgorithm() {

		//Paso 2a: Formar una particion inicial P1 de Q

		initialPartition();

		//Paso 2b: Obtener Pk+1 de Pk

		partitionkPLUS1();
		
	}

	public void initialPartition() {
		ArrayList<ArrayList<String>> partition1=new ArrayList<ArrayList<String>>();
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
			partition1.add(equalSymbols);
		}
		getPartitions().add(partition1);
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
							for(int m=0; m<super.getInputAlphabet().size() && find;m++) { //Se mira cada sucesor con cada simbolo de entrada
								//Sucesores de est1 y est2 a partir de cada una de las entradas.
								String st1=super.getStateTransition().get(super.getStates().indexOf(est1)).get(m); //f(q,s)
								String st2=super.getStateTransition().get(super.getStates().indexOf(est2)).get(m); //f(q',s)

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
	
	
	@Override
	public void addNewOutputResult(int index, int i) {
		newOutputResult.add(outputResult.get(index));
	}
	
}



