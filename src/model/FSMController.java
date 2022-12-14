package model;

import java.util.ArrayList;

public class FSMController {
	
	private FiniteStateMachine machine; //Automata de estado finito M
	private ArrayList<FSMMinimizedRow> minimizedMachineRows; //filas para la tabla de la maquina minimizada

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

	/*
	 * createMinimizedMachineRows para crear las filas para la tabla de la maquina minimizada  
	 */
	public void createMinimizedMachineRows() {
		if(machine instanceof MealyMachine) {
			
			createMealyMinimizedRows();
		}else {
			createMooreMinimizedRows();
		}
	}

	/*
	 * createMooreMinimizedRows para crear las filas para la tabla de la maquina de Moore minimizada  
	 */
	private void createMooreMinimizedRows() {
		for(int i=0; i<machine.getNewStates().size();i++) {
			FSMMinimizedRow row= new MooreMinimizedRow(machine.getNewStates().get(i),machine.getFinalPartition().get(i), machine.getNewStateTransition().get(i), ((MooreMachine)machine).getNewOutputResult().get(i));
			//(String newState, String block, ArrayList<String> fFunct, String hFunct)
			minimizedMachineRows.add(row);
		}
		
	}

	/*
	 * createMealyMinimizedRows para crear las filas para la tabla de la maquina de Mealy minimizada  
	 */
	private void createMealyMinimizedRows() {
		for(int i=0; i<machine.getNewStates().size();i++) {
			FSMMinimizedRow row= new MealyMinimizedRow(machine.getNewStates().get(i),machine.getFinalPartition().get(i), machine.getNewStateTransition().get(i), ((MealyMachine)machine).getNewOutputResult().get(i));
			//(String newState, String block, ArrayList<String> fFunct,  ArrayList<String> gFunct)
			minimizedMachineRows.add(row);
		}
	}

}
