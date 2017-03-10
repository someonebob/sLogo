package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

import javax.xml.transform.TransformerException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import main.Defaults;
import property.BackgroundColorProperty;
import property.Property;
import tool.ActorButtons.CreateActorButton;
import tool.SettingsMenuTool.*;
import xml.XMLEditor;
import xml.XMLException;

public class SimulationView implements View, Cloneable
{
	private SimulationView backupSimulation;
	private StackPane root;
	private BackgroundColorProperty backgroundColor;
	private ObservableList<ActorView> actors;
	private int id = 0;
	private Defaults defaults;

	public SimulationView(Defaults defaults)
	{
		root = new StackPane();
		backgroundColor = new BackgroundColorProperty("Background Color", root);
		this.defaults = defaults;
		List<ActorView> list = new ArrayList<>();
		actors = FXCollections.observableList(list);
		for (int i = 0; i < defaults.numTurtles(); i++) {
			newActor();
		}
		backgroundColor.setValue((Color) defaults.background());
	}

	public void undo()
	{
		this.set(backupSimulation);
	}

	public void step()
	{
		backupSimulation = this.clone();
		actors.get(0).step();
	}

	public void move(Point2D deltaLocation)
	{
		actors.get(0).move(deltaLocation);
	}

	public void setBackgroundColor(String color)
	{
		backgroundColor.setValue(color);
	}
	
	public void setTold(List<Integer> toldTurtles){
		for(int i = 0; i < actors.size(); i++){
			if(toldTurtles.contains(i)){
				actors.get(i).setTold();
			}else{
				actors.get(i).setUntold();
			}
		}
	}
	
	public List<ActorView> getActors(){
		return actors;
	}

	public TurtleView getTurtle()
	{
		return (TurtleView) actors.get(0);
	}

	public void newActor()
	{
		TurtleView actor = new TurtleView(defaults, id);
		id++;
		actor.getPen().getCanvas().toBack();
		actor.getPen().getCanvas().widthProperty().bind(root.widthProperty());
		actor.getPen().getCanvas().heightProperty().bind(root.heightProperty());

		root.getChildren().add(actor.getPen().getCanvas());
		root.getChildren().add(actor.display());

		actors.add(actor);

	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof BackgroundColorButton) {
			if (arg instanceof Color) {
				root.setBackground(new Background(new BackgroundFill((Paint) arg, null, null)));
			}
		}else if (o instanceof TurtleImageButton) {
			if (arg instanceof Image) {
				// TODO make ID's work
				actors.get(0).setImage((Image) arg);
			}
		}else if (o instanceof PenColorButton) {
			if (arg instanceof Color) {
				// TODO make ID's work
				((TurtleView) actors.get(0)).getPen().setColor((Color) arg);
			}
		}else if (o instanceof CreateActorButton){
			newActor();
		}else if(o instanceof DefaultButton){
			XMLEditor editor = new XMLEditor();
			try {
				editor.setDefault("background", backgroundColor.getValue().toString());
				editor.setDefault("pen", ((TurtleView)actors.get(0)).getPen().getPenColorProperty().getValue().toString());
				editor.setDefault("image", actors.get(0).getImageProperty().getIndexedImages().get(actors.get(0).getImageProperty().getIndexedImages().indexOf(actors.get(0).getImageView())).getFilename());
				editor.setDefault("numTurtles", Integer.toString(actors.size()));
			} catch (TransformerException e1) {
				throw new XMLException(e1);
			}
		}else{
			
		}

	}

	@Override
	public StackPane display()
	{
		return root;
	}

	public Bounds getBounds()
	{
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

	public void set(SimulationView simulation)
	{
		this.root.getChildren().clear();
		this.root.getChildren().add(simulation.getTurtle().clone().display());
		System.out.println(simulation.getTurtle().clone().getHeading());
		this.backgroundColor.setValue(simulation.getBackgroundColorProperty().getValue());
		this.actors.clear();
		this.actors.addAll(simulation.getTurtle().clone());
	}

	@Override
	public SimulationView clone()
	{
		// SimulationView clone = new SimulationView(this.defaults);
		// System.out.println(clone.getTurtle().getHeading());
		// clone.getTurtle().setHeading(300);
		// System.out.println(clone.getTurtle().getHeading());
		// return clone;
		return null;
	}
}