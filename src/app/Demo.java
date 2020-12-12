package app;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage pStage) throws Exception {
		File file = new File("src/view/POS.fxml");
		Parent root = FXMLLoader.load(file.toURI().toURL());
		Scene scene = new Scene(root,600,454);
		pStage.setTitle("Corner Coffee Shop");
		pStage.setScene(scene);
		pStage.show();
	}

}
