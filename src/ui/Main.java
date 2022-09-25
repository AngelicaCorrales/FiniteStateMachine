package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FiniteStateMachine;

public class Main extends Application{

	private FiniteStateMachine fsm;
	private FiniteStateMachineGUI fsmGUI;
	
	public Main() {
		fsm= new FiniteStateMachine();
		fsmGUI= new FiniteStateMachineGUI(fsm);	
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPane.fxml"));
		
		fxmlLoader.setController(fsmGUI);
		Parent root= fxmlLoader.load();
		
		Scene scene= new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Finite State Machines!!");
		primaryStage.show();
		fsmGUI.showWelcomeWindow();
	}

}
