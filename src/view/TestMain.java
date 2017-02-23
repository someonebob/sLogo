package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestMain extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Stage window = arg0;
		InputBox console = new InputBox();
		Scene scene = new Scene(console.display());
		
		window.setScene(scene);
		window.setTitle("SLogo");
		window.show();
	}

}
