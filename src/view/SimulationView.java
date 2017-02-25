package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import models.Simulation;
import tool.FileTool;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class SimulationView implements View
{
	private Simulation simulation;
	private TabPane root;

	public SimulationView()
	{
		root = new TabPane();
		newTab();
	}
	
	public void addTab(){
		
	}

	public Simulation getModel()
	{
		return simulation;
	}


	@Override
	public Node display()
	{
		return root;

	}

	@Override
	public void update(Observable o, Object arg) {

		if(o instanceof InputBox){
			
		}
		
		if(o instanceof FileTool){
			newTab();
		}
	}
	
	private void newTab(){
		Tab newTab = new Tab();
		ActorView actor = new ActorView();
		StackPane layout = new StackPane();
		newTab.setText("new tab");
		newTab.setContent(layout);
		layout.getChildren().add(actor.display());
		root.getTabs().add(newTab);
	}
}
