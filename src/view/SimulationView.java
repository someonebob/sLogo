package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import models.Simulation;
import tool.SettingsTool.backgroundColorButton;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class SimulationView implements View
{
	private Simulation simulation;
	private TabPane root;
	private Rectangle background;

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
		// TODO Auto-generated method stub
		if(o instanceof backgroundColorButton){
			if(arg instanceof Color){
				background.setFill((Paint) arg);
			}
			
		}
	}

	@Override
	public void updateData(String arg) {
		// TODO Auto-generated method stub
		
	}

	
	private void newTab(){
		Tab newTab = new Tab();
		ActorView actor = new ActorView();
		StackPane layout = new StackPane();
		background = new Rectangle(400, 400, Color.ALICEBLUE);
		
		newTab.setText("new tab");
		newTab.setContent(layout);
		layout.getChildren().addAll(background, actor.display());
		root.getTabs().add(newTab);
	}

	

}
