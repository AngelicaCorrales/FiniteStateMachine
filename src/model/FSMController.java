package model;

import java.util.ArrayList;

public class FSMController {
	
	private FiniteStateMachine machine;
	private ArrayList<FSMMinimizedRow> minimizedMachineRows;

	public FSMController(String type, String[] inSymbols, String[] outSymbols, Integer nStates) {
		if(type.equals("Mealy")) {
			machine= new MealyMachine(inSymbols, outSymbols, nStates);
		}else {
			machine= new MooreMachine(inSymbols, outSymbols, nStates);
		}
		minimizedMachineRows= new ArrayList<FSMMinimizedRow>();
	}

	public FiniteStateMachine getMachine() {
		return machine;
	}

	public void setMachine(FiniteStateMachine machine) {
		this.machine = machine;
	}
	
	
	public ArrayList<FSMMinimizedRow> getMinimizedMachineRows() {
		return minimizedMachineRows;
	}

	public void setMinimizedMachineRows(ArrayList<FSMMinimizedRow> minimizedMachineRows) {
		this.minimizedMachineRows = minimizedMachineRows;
	}

	public void createMinimizedMachineRows() {
		if(machine instanceof MealyMachine) {
			
			createMealyMinimizedRows();
		}else {
			createMooreMinimizedRows();
		}
	}

	private void createMooreMinimizedRows() {
		for(int i=0; i<machine.getNewStates().size();i++) {
			FSMMinimizedRow row= new MooreMinimizedRow(machine.getNewStates().get(i),machine.getFinalPartition().get(i), machine.getNewStateTransition().get(i), ((MooreMachine)machine).getNewOutputResult().get(i));
			//(String newState, String block, ArrayList<String> fFunct, String hFunct)
			minimizedMachineRows.add(row);
		}
		
	}

	private void createMealyMinimizedRows() {
		for(int i=0; i<machine.getNewStates().size();i++) {
			FSMMinimizedRow row= new MealyMinimizedRow(machine.getNewStates().get(i),machine.getFinalPartition().get(i), machine.getNewStateTransition().get(i), ((MealyMachine)machine).getNewOutputResult().get(i));
			//(String newState, String block, ArrayList<String> fFunct,  ArrayList<String> gFunct)
			minimizedMachineRows.add(row);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
