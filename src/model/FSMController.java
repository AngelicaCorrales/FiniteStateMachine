package model;

public class FSMController {
	
	private FiniteStateMachine machine;

	public FSMController(String type, String[] inSymbols, String[] outSymbols, Integer nStates) {
		if(type.equals("Mealy")) {
			machine= new MealyMachine(inSymbols, outSymbols, nStates);
		}else {
			machine= new MooreMachine(inSymbols, outSymbols, nStates);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
