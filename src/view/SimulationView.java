package view;

import java.io.File;
import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import models.Simulation;
import tool.FileTool.NewButton;
import tool.FileTool.OpenButton;
import tool.SettingsTool.BackgroundColorButton;
import tool.SettingsTool.TurtleImageButton;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class SimulationView implements View
{
	private Simulation simulation;
	private TabPane root;
	private ActorView actor;
	private Rectangle background;

	public SimulationView()
	{
		root = new TabPane();
		actor = new ActorView();
		newTab();
	}

	public void addTab()
	{

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
	public void update(Observable o, Object arg)
	{
		if (o instanceof NewButton){
			newTab();
		}
		if (o instanceof OpenButton){
			openFile((File) arg);
		}
		

		if (o instanceof BackgroundColorButton) {
			if (arg instanceof Color) {
				background.setFill((Paint) arg);
			}
		}

		System.out.println("HI");

		if (o instanceof TurtleImageButton) {
			if (arg instanceof Image) {
				actor.setImage((Image) arg);
			}
		}
	}

	@Override
	public void updateData(String arg)
	{
		// TODO Auto-generated method stub

	}

	private void newTab()
	{
		Tab newTab = new Tab();
		StackPane layout = new StackPane();
		background = new Rectangle(400, 400, Color.ALICEBLUE);

		newTab.setText("new tab");
		newTab.setContent(layout);
		layout.getChildren().addAll(background, actor.display());
		root.getTabs().add(newTab);
	}
	
	private void openFile(File file){
		
	}


}