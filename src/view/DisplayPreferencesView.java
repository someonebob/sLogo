package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class DisplayPreferencesView implements View
{
	private VBox display;

	public DisplayPreferencesView(SimulationView simulation)
	{
		display = new VBox();
		simulation.getProperties().forEach(property -> display.getChildren().add(property.makeDynamicUpdater()));
	}

	@Override
	public void update(Observable o, Object arg)
	{

	}

	@Override
	public Node display()
	{
		return display;
	}

}
