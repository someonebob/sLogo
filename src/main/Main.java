package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		RunLogo runLogo = new RunLogo(stage);
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
