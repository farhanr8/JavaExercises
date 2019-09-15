package assignment6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ClientGUI.fxml"));
		primaryStage.setTitle("CLIENT GUI");
		primaryStage.setScene(new Scene(root,517,348));
		primaryStage.show();
	}
}