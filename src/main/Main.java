package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		LogoController controller = new LogoController(stage);
		stage.setMaximized(true);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
