package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import model.FSMController;

public class FiniteStateMachineGUI {
	
	private FSMController fsmC;
	
    @FXML
    private BorderPane mainPane;

	@FXML
	private Button continueToScreen2;

	@FXML
	private TextField inputSimbols;

	@FXML
	private ToggleGroup machines;

	@FXML
	private TextField outputSimbols;

	@FXML
	private RadioButton rbMealy;

	@FXML
	private RadioButton rbMoore;

	@FXML
	private Spinner<Integer> statesSpinner;
	
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
		if(!type.equals("") && !inputSimbols.getText().equals("") && !outputSimbols.getText().equals("")) {
			String[] inSimbols = inputSimbols.getText().split(",");
			String[] outSimbols = outputSimbols.getText().split(",");
			fsmC = new FSMController(type, inSimbols, outSimbols, statesSpinner.getValue());
			
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screen2.fxml"));
			fxmlLoader.setController(this);
			Parent menuPane = fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.setCenter(menuPane);
			mainPane.setStyle("-fx-background-image: url(/ui/background.jpeg)");
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Debe completar cada uno de los campos indicados para continuar.");
			alert.showAndWait();
		}
	}

	
	
	
	@FXML
	private TableColumn<?, ?> colOriginalStates;

	@FXML
	private Label lbNameMachine;

	@FXML
	private TableView<?> tvOriginalMachine;

	@FXML
	private TableColumn<?, ?> colBlocks;

	@FXML
	private TableColumn<?, ?> colNewStates;

	@FXML
	private Label lbMinimizedMachine;

	@FXML
	private TableView<?> tvMinimizedMachine;
	
	
	
	
	
	
	@FXML
	public void minimizeMachine(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screen3.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		mainPane.setStyle("-fx-background-image: url(/ui/background.jpeg)");
		
		
		
	}

	@FXML
	public void returnHome(ActionEvent event) throws IOException {
		showWelcomeWindow();
	}

	    	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
