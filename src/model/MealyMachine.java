package model;

import java.util.ArrayList;

public class MealyMachine extends FiniteStateMachine{

	private ArrayList<ArrayList<String>> outputResult;
	private ArrayList<ArrayList<String>> newOutputResult;

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


	@Override
	public void removeOutputResult(int index) {
		outputResult.remove(index);
	}
	
	public ArrayList<ArrayList<String>> combinationsOfOutputs(){
		ArrayList<ArrayList<String>> combinations=new ArrayList<ArrayList<String>>();
		for(int i=0;i<outputResult.size();i++) {
			combinations.add(new ArrayList<String>());
			combinations.get(i).addAll(outputResult.get(i));
			int count =0;
			for(int a=0;a<combinations.size();a++) {
				if(outputResult.get(i).equals(combinations.get(a))) {
					count++;
				}
				if(count>1) {
					combinations.remove(i);
				}
			}
		}
		return combinations;
	}

	@Override
	public void initialPartition() {
		ArrayList<ArrayList<String>> combinations=combinationsOfOutputs();
		ArrayList<ArrayList<String>> partition1=new ArrayList<ArrayList<String>>();
		for(int a=0;a<combinations.size();a++) {
			int index=-1;
			boolean exit=false;
			ArrayList<String> equalSymbols=new ArrayList<String>();
			for(int i=0;i<outputResult.size()  && !exit;i++) {
				index=i;
				for(int j=i;j<outputResult.size();j++) {
					if(outputResult.get(i).equals(combinations.get(a)) && outputResult.get(i).equals(outputResult.get(j))) {
						
						if(equalSymbols.indexOf(super.getStates().get(i))==-1) {
							equalSymbols.add(super.getStates().get(i));
						}

						if(equalSymbols.indexOf(super.getStates().get(j))==-1) {
							equalSymbols.add(super.getStates().get(j));
						}
						i=j;
					}else if(!outputResult.get(i).equals(combinations.get(a))) {
						i++;
					}
				}
				i=index;
				exit=true;
			}	
			partition1.add(equalSymbols);
		}
		getPartitions().add(partition1);
	}


	@Override
	public void addNewOutputResult(int index, int i) {
		newOutputResult.add(new ArrayList<String>());
		newOutputResult.get(i).addAll(outputResult.get(index));
	}


































}
