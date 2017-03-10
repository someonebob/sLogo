package view;

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
	public Node display()
	{
		return display;
	}

}
