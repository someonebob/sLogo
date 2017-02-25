package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tool.FileTool;
import tool.SelectionBar;
import tool.SettingsTool;

public class TestMain extends Application {
	private BorderPane root;
	private InputBox console;
	private WorkspaceView workspace;

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

		workspace = new WorkspaceView();
		root.setLeft(workspace.display());

		
		SelectionBar bar = new SelectionBar();
		bar.addAllTools(new FileTool(window), new SettingsTool());
		root.setTop(bar.display());

		window.setScene(scene);
		window.setTitle("SLogo");
		window.show();
	}

}
