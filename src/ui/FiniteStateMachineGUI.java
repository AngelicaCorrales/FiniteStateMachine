package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.FSMController;
import model.FSMMinimizedRow;
import model.MealyMachine;
import model.MealyMinimizedRow;
import model.MooreMachine;
import model.MooreMinimizedRow;


public class FiniteStateMachineGUI {

	private FSMController fsmC;

	@FXML
	private BorderPane mainPane;

	@FXML
	private Button continueToScreen2;

	@FXML
	private TextField inputSymbols;

	@FXML
	private ToggleGroup machines;

	@FXML
	private TextField outputSymbols;

	@FXML
	private RadioButton rbMealy;

	@FXML
	private RadioButton rbMoore;

	@FXML
	private Spinner<Integer> statesSpinner;

	//----Screen 2

	@FXML
	private TableColumn<FSMTableViewRow, String> colOriginalStates;

	@FXML
	private Label lbNameMachine;

	@FXML
	private TableView<FSMTableViewRow> tvOriginalMachine;

	private ArrayList<FSMTableViewRow> fsmRows;

	//----Screen 3

	@FXML
	private TableColumn<FSMMinimizedRow, String> colBlocks;

	@FXML
	private TableColumn<FSMMinimizedRow, String> colNewStates;

	@FXML
	private Label lbMinimizedMachine;

	@FXML
	private TableView<FSMMinimizedRow> tvMinimizedMachine;




	public FiniteStateMachineGUI(FSMController m) {
		fsmC=m;
	}

	public void showWelcomeWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screen1.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		mainPane.setStyle("-fx-background-image: url(/ui/background.jpeg)");
		SpinnerValueFactory<Integer> valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 26, 1);
		statesSpinner.setValueFactory(valueFactory);
	}

	@FXML
	public void continueToScreen2(ActionEvent event) throws IOException {
		String type="";
		if(rbMealy.isSelected()) {
			type="Mealy";
		}
		if(rbMoore.isSelected()) {
			type="Moore";
		}

		String[] inSymbols = inputSymbols.getText().replace(" ","").split(",");
		String[] outSymbols = outputSymbols.getText().replace(" ","").split(",");
		
		if(!type.equals("") && inSymbols.length!=0 && outSymbols.length!=0) {
			
			fsmC = new FSMController(type, inSymbols, outSymbols, statesSpinner.getValue());


			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screen2.fxml"));
			fxmlLoader.setController(this);
			Parent menuPane = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.setCenter(menuPane);
			mainPane.setStyle("-fx-background-image: url(/ui/background.jpeg)");
			lbNameMachine.setText("Maquina de "+type);
			initializeTableViewOriginalMachine();


		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Debe completar cada uno de los campos indicados para continuar.");
			alert.showAndWait();
		}
	}



	@FXML
	public void minimizeMachine(ActionEvent event) throws IOException {
		if(valuesMissingMachine()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error de validacion");
			alert.setHeaderText(null);
			alert.setContentText("Por favor, llene todos los campos para crear la maquina");
			alert.showAndWait();
		}else {
			finishCreationMachine();
			fsmC.getMachine().minimizeMachine();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screen3.fxml"));
			fxmlLoader.setController(this);
			Parent menuPane = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.setCenter(menuPane);
			mainPane.setStyle("-fx-background-image: url(/ui/background.jpeg)");
			String machine="Maquina de Mealy";
			if(fsmC.getMachine() instanceof MooreMachine) {
				machine="Maquina de Moore";
			}
			lbMinimizedMachine.setText(machine+" Minimizada");
			initializeTableViewMinimizedMachine();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initializeTableViewMinimizedMachine() {
		fsmC.createMinimizedMachineRows();
		addColumnsMinimizedMachine();

		ObservableList<FSMMinimizedRow> observableList= FXCollections.observableArrayList(fsmC.getMinimizedMachineRows());
		tvMinimizedMachine.setItems(observableList);

		colNewStates.setCellValueFactory(new PropertyValueFactory<FSMMinimizedRow, String>("NewState"));
		colBlocks.setCellValueFactory(new PropertyValueFactory<FSMMinimizedRow, String>("Block"));

		int j=0;
		if(fsmC.getMachine() instanceof MealyMachine) {

			for(int i=2; i<tvMinimizedMachine.getColumns().size();i+=2) {

				final int  num=j;
				tvMinimizedMachine.getColumns().get(i).setCellValueFactory(cellData -> new SimpleObjectProperty((cellData.getValue().getfFunct().get(num))));
				tvMinimizedMachine.getColumns().get(i+1).setCellValueFactory(cellData -> new SimpleObjectProperty((((MealyMinimizedRow)cellData.getValue()).getgFunct().get(num))));
				j++;
			}
		}else {
			for(int i=2; i<tvMinimizedMachine.getColumns().size()-1;i++) {
				final int  num=j;
				tvMinimizedMachine.getColumns().get(i).setCellValueFactory(cellData -> new SimpleObjectProperty((cellData.getValue().getfFunct().get(num))));			
				j++;
			}

			tvMinimizedMachine.getColumns().get(tvMinimizedMachine.getColumns().size()-1).setCellValueFactory(cellData -> new SimpleObjectProperty((((MooreMinimizedRow)cellData.getValue()).gethFunct())));			

		}

	}

	public void finishCreationMachine() {
		for(int i=0; i<fsmRows.size();i++) {
			for(int j=0; j<fsmC.getMachine().getInputAlphabet().size();j++) {
				fsmC.getMachine().getStateTransition().get(i).add(fsmRows.get(i).getfFunct().get(j).getValue());
				if(fsmC.getMachine() instanceof MealyMachine) {
					((MealyMachine)fsmC.getMachine()).getOutputResult().get(i).add(((MealyTableViewRow)fsmRows.get(i)).getgFunct().get(j).getValue());
				}
			}

			if(fsmC.getMachine() instanceof MooreMachine) {
				((MooreMachine)fsmC.getMachine()).getOutputResult().add(((MooreTableViewRow)fsmRows.get(i)).gethFunct().getValue());
			}

		}
	}

	public boolean valuesMissingMachine() {
		boolean exit=false;
		for(int i=0;i<fsmRows.size() && !exit;i++) {
			for(int j=0;j<fsmRows.get(i).getfFunct().size() && !exit;j++) {
				if(fsmRows.get(i).getfFunct().get(j).getValue()==null) {
					exit=true; //salir si el valor de g es nulo
				}

				if(!exit) {
					if(fsmC.getMachine() instanceof MealyMachine) {
						if(((MealyTableViewRow)fsmRows.get(i)).getgFunct().get(j).getValue()==null) {
							exit=true; //salir si el valor de h es nulo
						}
					}
				}

			}
			if(!exit && fsmC.getMachine() instanceof MooreMachine) {
				if(((MooreTableViewRow)fsmRows.get(i)).gethFunct().getValue()==null) {
					exit=true;
				}
			}
		}

		return exit;
	}


	@FXML
	public void returnHome(ActionEvent event) throws IOException {
		showWelcomeWindow();
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initializeTableViewOriginalMachine() {
		fsmRows= new ArrayList<FSMTableViewRow>();

		ObservableList<String> statesOptions = FXCollections.observableArrayList(fsmC.getMachine().getStates());
		ObservableList<String> outputOptions = FXCollections.observableArrayList(fsmC.getMachine().getOutputAlphabet());



		if(fsmC.getMachine() instanceof MealyMachine) {
			initializeTableViewOriginalMealy(statesOptions,outputOptions);
		}else {
			initializeTableViewOriginalMoore(statesOptions,outputOptions);
		}

		addColumnsMachine();

		ObservableList<FSMTableViewRow> observableList= FXCollections.observableArrayList(fsmRows);
		tvOriginalMachine.setItems(observableList);

		colOriginalStates.setCellValueFactory(new PropertyValueFactory<FSMTableViewRow, String>("State"));
		int j=0;
		if(fsmC.getMachine() instanceof MealyMachine) {

			for(int i=1; i<tvOriginalMachine.getColumns().size();i+=2) {

				final int  num=j;
				tvOriginalMachine.getColumns().get(i).setCellValueFactory(cellData -> new SimpleObjectProperty((cellData.getValue().getfFunct().get(num))));
				tvOriginalMachine.getColumns().get(i+1).setCellValueFactory(cellData -> new SimpleObjectProperty((((MealyTableViewRow)cellData.getValue()).getgFunct().get(num))));
				j++;
			}
		}else {
			for(int i=1; i<tvOriginalMachine.getColumns().size()-1;i++) {
				final int  num=j;
				tvOriginalMachine.getColumns().get(i).setCellValueFactory(cellData -> new SimpleObjectProperty((cellData.getValue().getfFunct().get(num))));			
				j++;
			}

			tvOriginalMachine.getColumns().get(tvOriginalMachine.getColumns().size()-1).setCellValueFactory(cellData -> new SimpleObjectProperty((((MooreTableViewRow)cellData.getValue()).gethFunct())));			

		}


	}

	public void initializeTableViewOriginalMealy(ObservableList<String> statesOptions, ObservableList<String> outputOptions) {

		for(int i=0; i<fsmC.getMachine().getStates().size();i++) {
			FSMTableViewRow row=new MealyTableViewRow(fsmC.getMachine().getStates().get(i));

			for(int j=0; j<fsmC.getMachine().getInputAlphabet().size();j++) {
				row.getfFunct().add(new ComboBox<String>(statesOptions));
				((MealyTableViewRow)row).getgFunct().add(new ComboBox<String>(outputOptions));
			}

			fsmRows.add(row);

		}
	}

	public void initializeTableViewOriginalMoore(ObservableList<String> statesOptions, ObservableList<String> outputOptions) {

		for(int i=0; i<fsmC.getMachine().getStates().size();i++) {
			FSMTableViewRow row=new MooreTableViewRow(fsmC.getMachine().getStates().get(i));

			for(int j=0; j<fsmC.getMachine().getInputAlphabet().size();j++) {
				row.getfFunct().add(new ComboBox<String>(statesOptions));
			}

			((MooreTableViewRow)row).sethFunct(new ComboBox<String>(outputOptions));			
			fsmRows.add(row);						
		}
	}

	public void addColumnsMachine() {

		if(fsmC.getMachine() instanceof MealyMachine) {
			addColumnsMealy();
		}else {
			addColumnsMoore();
		}
	}


	public void addColumnsMealy() {	
		//Agregar columnas de las funciones f y g 
		for(int i=0; i<fsmC.getMachine().getInputAlphabet().size();i++) {
			tvOriginalMachine.getColumns().add(new TableColumn<FSMTableViewRow, ComboBox<String>>("f(q,"+fsmC.getMachine().getInputAlphabet().get(i)+")"));
			tvOriginalMachine.getColumns().add(new TableColumn<FSMTableViewRow, ComboBox<String>>("g(q,"+fsmC.getMachine().getInputAlphabet().get(i)+")"));
		}		
	}

	public void addColumnsMoore() {		
		//Agregar columnas de las funciones f
		for(int i=0; i<fsmC.getMachine().getInputAlphabet().size();i++) {
			tvOriginalMachine.getColumns().add(new TableColumn<FSMTableViewRow, ComboBox<String>>("f(q,"+fsmC.getMachine().getInputAlphabet().get(i)+")"));
		}		
		//Agregar columna de la funcion h
		tvOriginalMachine.getColumns().add(new TableColumn<FSMTableViewRow, ComboBox<String>>("h(q)"));
	}


	public void addColumnsMinimizedMachine() {

		if(fsmC.getMachine() instanceof MealyMachine) {
			addColumnsMealyMinimized();
		}else {
			addColumnsMooreMinimized();
		}
	}


	public void addColumnsMealyMinimized() {	
		//Agregar columnas de las funciones f y g 
		for(int i=0; i<fsmC.getMachine().getInputAlphabet().size();i++) {
			tvMinimizedMachine.getColumns().add(new TableColumn<FSMMinimizedRow, String>("f(q,"+fsmC.getMachine().getInputAlphabet().get(i)+")"));
			tvMinimizedMachine.getColumns().add(new TableColumn<FSMMinimizedRow, String>("g(q,"+fsmC.getMachine().getInputAlphabet().get(i)+")"));
		}		
	}

	public void addColumnsMooreMinimized() {		
		//Agregar columnas de las funciones f
		for(int i=0; i<fsmC.getMachine().getInputAlphabet().size();i++) {
			tvMinimizedMachine.getColumns().add(new TableColumn<FSMMinimizedRow, String>("f(q,"+fsmC.getMachine().getInputAlphabet().get(i)+")"));
		}		
		//Agregar columna de la funcion h
		tvMinimizedMachine.getColumns().add(new TableColumn<FSMMinimizedRow, String>("h(q)"));
	}


























































}
