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
	public void initialPartition() {
		ArrayList<ArrayList<String>> partition1=new ArrayList<ArrayList<String>>();
		for(int k=0;k<super.getOutputAlphabet().size();k++) {
			int index=-1;
			boolean exit=false;
			ArrayList<String> equalSymbols=new ArrayList<String>();
			for(int i=0;i<outputResult.size() && !exit;i++) {
				index=i;
				for(int j=i;j<outputResult.size();j++) {
					if(outputResult.get(i).equals(super.getOutputAlphabet().get(k)) && outputResult.get(i).equals(outputResult.get(j))) {

						if(equalSymbols.indexOf(super.getStates().get(i))==-1) {
							equalSymbols.add(super.getStates().get(i));
						}

						if(equalSymbols.indexOf(super.getStates().get(j))==-1) {
							equalSymbols.add(super.getStates().get(j));
						}
						i=j;
					}else if(!outputResult.get(i).equals(super.getOutputAlphabet().get(k))) {
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
		newOutputResult.add(outputResult.get(index));
	}
	
}



