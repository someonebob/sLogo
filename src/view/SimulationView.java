package view;

import java.util.List;
import java.util.Observable;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import models.Actor;
import models.Simulation;
import tool.FileTool.NewButton;
import tool.SettingsTool.*;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class SimulationView implements View
{
	private Simulation simulation;
	private TabPane root;
	private TurtleView actor;

	public SimulationView()
	{
		root = new TabPane();
		actor = new TurtleView();
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
		if (o instanceof NewButton) {
			newTab();
		}

		if (o instanceof BackgroundColorButton) {
			if (arg instanceof Color) {

				// Find which tab is active and change color of that tab
				for (Tab t : root.getTabs()) {
					if (t.isSelected()) {
						((StackPane) t.getContent())
								.setBackground(new Background(new BackgroundFill((Paint) arg, null, null)));
					}
				}
			}
		}

		if (o instanceof TurtleImageButton) {
			if (arg instanceof Image) {

				for (Tab t : root.getTabs()) {
					if (t.isSelected()) {
						((StackPane) t.getContent()).getChildren().remove(0);
						ActorView actor = new TurtleView();
						actor.setImage((Image) arg);
						((StackPane) t.getContent()).getChildren().add(actor.display());
					}
				}
				actor.setImage((Image) arg);
			}
		}
	}

	@Override
	public void updateData(String arg)
	{
		// TODO Auto-generated method stub

	}
	
	public Bounds getBounds(){
		return root.getBoundsInLocal();
	}

	private void newTab()
	{
		Tab newTab = new Tab();
		StackPane layout = new StackPane();
		ActorView actor = new TurtleView();
		layout.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		newTab.setText("new tab");
		newTab.setContent(layout);
		layout.getChildren().add(actor.display());
		root.getTabs().add(newTab);
	}
	
	
	
	
	//Added from simulation
	public Color getColor() {
		return null;
	}
	public void setColor(Color c) {
	}
	
	public List<Actor> getActors() {
		return null;
	}
	public void setActors(List<Actor> newActors) {
	}
	public Actor getActiveActor() {
		return null;
	}
	public void setActiveActor(Actor newActor) {
	}

}