package main;

import javafx.stage.Stage;
import tool.SettingsTool;

/**
 * 
 * @author jimmy
 *
 */
public class RunLogo
{
	public RunLogo(Stage stage)
	{
		LogoController controller = new LogoController(stage);
		controller.addTool(new SettingsTool());
	}
}
