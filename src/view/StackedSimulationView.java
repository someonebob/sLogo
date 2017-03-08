package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.Defaults;
import property.BackgroundColorProperty;
import property.Property;
import tool.SettingsTool.BackgroundColorButton;
import tool.SettingsTool.PenColorButton;
import tool.SettingsTool.TurtleImageButton;
import user_structures.ID;

public class StackedSimulationView implements SimulationView {

	private StackPane root;
	private BackgroundColorProperty backgroundColor;
	private ObservableList<ActorView> actors;
	private int id = 1;
	private Defaults defaults;

	public StackedSimulationView(Defaults defaults) {
		root = new StackPane();
		this.defaults = defaults;
		List<ActorView> list = new ArrayList<>();
		actors = FXCollections.observableList(list);
		for (int i = 0; i < defaults.numTurtles(); i++) {
			newActor();
		}
		root.setBackground(new Background(new BackgroundFill(defaults.background(), null, null)));

	}
	
	public void step()
	{
		actors.get(0).step();
	}
	
	public void move( Point2D deltaLocation)
	{
		actors.get(0).move(deltaLocation);
	}
	
	public void setBackgroundColor(String color){
		root.setBackground(new Background(new BackgroundFill(Paint.valueOf(color), null, null)));
	}
	
	public TurtleView getTurtle()
	{
		return (TurtleView) actors.get(0);
	}

	public void newActor() {
		TurtleView actor = new TurtleView(defaults,id);
		id++;
		actor.getPen().getCanvas().toBack();
		actor.getPen().getCanvas().widthProperty().bind(root.widthProperty());
		actor.getPen().getCanvas().heightProperty().bind(root.heightProperty());
		
		root.getChildren().add(actor.getPen().getCanvas());
		root.getChildren().add(actor.display());

		actors.add(actor);

	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof BackgroundColorButton) {
			if (arg instanceof Color) {

				root.setBackground(new Background(new BackgroundFill((Paint) arg, null, null)));
			}
		}

		if (o instanceof TurtleImageButton) {
			if (arg instanceof Image) {
				//TODO make ID's work
				actors.get(0).setImage((Image) arg);
					
			}
		}

		if (o instanceof PenColorButton) {
			if (arg instanceof Color) {
				
				//TODO make ID's work
				((TurtleView) actors.get(0)).getPen().setColor((Color) arg);
			}
		}
		
	}

	@Override
	public Node display() {
		// TODO Auto-generated method stub
		return root;
	}

	public Bounds getBounds() {
		return root.getBoundsInLocal();
	}
	
	public BackgroundColorProperty getBackgroundColorProperty()
	{
		return backgroundColor;
	}

	public List<Property<?>> getProperties()
	{
		return Arrays.asList(backgroundColor);
	}

}