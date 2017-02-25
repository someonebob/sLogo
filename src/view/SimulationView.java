package view;

import java.util.Observable;

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
	public Node display()
	{
		return null;

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
