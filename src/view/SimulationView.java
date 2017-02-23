package view;

import javafx.scene.Node;
import models.Simulation;

public class SimulationView implements View
{
	private Simulation simulation;

	public SimulationView()
	{

	}

	public Simulation getModel()
	{
		return simulation;
	}

	@Override
	public void update(String instruction)
	{
	}

	@Override
	public Node display()
	{
		return null;

	}
}
