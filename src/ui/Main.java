package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.FSMController;

public class Main extends Application{

	private FSMController fsmC;
	private FiniteStateMachineGUI fsmGUI;
	
	public Main() {
		fsmGUI= new FiniteStateMachineGUI(fsmC);	
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
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/ui/iconexe.png")));
		primaryStage.setTitle("Finite State Machines!!");
		primaryStage.show();
		fsmGUI.showWelcomeWindow();
	}

}
