package view;

import java.util.Observable;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class DisplayPreferencesView implements View
{
	private SimulationView simulation;
	private VBox display;

	public DisplayPreferencesView(SimulationView simulation)
	{
		this.simulation = simulation;
		display = new VBox();
		display.setAlignment(Pos.CENTER);
		simulation.getProperties().forEach(property -> display.getChildren().add(property.displayDynamicUpdater()));
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
