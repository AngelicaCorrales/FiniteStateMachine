package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import model.FiniteStateMachine;

public class FiniteStateMachineGUI {
	
	private FiniteStateMachine fsm;
	
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
	private Spinner<?> statesSpinner;
	
	public FiniteStateMachineGUI(FiniteStateMachine m) {
		fsm = m;
	}
	
	public void showWelcomeWindow() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/screen1.fxml"));
		fxmlLoader.setController(this);
		Parent menuPane = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(menuPane);
		mainPane.setStyle("-fx-background-image: url(/ui/background.jpeg)");
	}
}
