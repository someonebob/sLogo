package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestMain extends Application{
	private BorderPane root;
	private InputBox console;
	private Workspace workspace;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Stage window = arg0;
		root = new BorderPane();
		Scene scene = new Scene(root);

		console = new InputBox();
		root.setBottom(console.display());
		
		workspace = new Workspace();
		root.setLeft(workspace.display());
		

		
		window.setScene(scene);
		window.setTitle("SLogo");
		window.show();
	}
	
}
