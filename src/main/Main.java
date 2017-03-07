package main;

import javafx.application.Application;
import javafx.stage.Stage;
import xml.XMLParser;

public class Main extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		// LogoController controller = new LogoController(stage);
		XMLParser parser = new XMLParser();
		Controller controller = new Controller(stage, parser.setDefaults());
	}

	public static void main(String[] args)
	{
		launch(args);
	}

}
