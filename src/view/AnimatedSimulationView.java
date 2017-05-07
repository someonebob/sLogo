package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

import javax.xml.transform.TransformerException;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import main.Defaults;
import property.BackgroundColorProperty;
import property.Property;
import tool.ActorButtons.CreateActorButton;
import tool.ActorButtons.DeleteActorButton;
import tool.SettingsMenuTool.BackgroundColorButton;
import tool.SettingsMenuTool.DefaultButton;
import tool.SettingsMenuTool.PenColorButton;
import tool.SettingsMenuTool.TurtleImageButton;
import xml.XMLEditor;
import xml.XMLException;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class AnimatedSimulationView implements SimulationView, Cloneable
{
	private SimulationView backupSimulation;
	private StackPane root;
	private BackgroundColorProperty backgroundColor;
	private ObservableList<TurtleView> actors;
	private List<ImageView> stamps;
	private int id = 0;
	private Defaults defaults;
	private Tooltip tip;

	public AnimatedSimulationView(Defaults defaults)
	{
		root = new StackPane();
		backgroundColor = new BackgroundColorProperty("Background Color", root);
		this.defaults = defaults;
		List<TurtleView> list = new ArrayList<>();
		actors = FXCollections.observableList(list);

		for (int i = 0; i < defaults.numTurtles(); i++) {
			newActor();
		}
		backgroundColor.setValue((Color) defaults.background());
		stamps = new ArrayList<ImageView>();
	}

	@Override
	public void undo()
	{
		this.set(backupSimulation);
	}

	@Override
	public void step()
	{
		backupSimulation = this.clone();
		for (ActorView actor : actors) {
			if (actor.isTold()) {
				actor.step();
			}
		}
	}

	@Override
	public void move(Point2D deltaLocation)
	{
		for (ActorView actor : actors) {
			if (actor.isTold()) {
				actor.move(deltaLocation);
			}
		}
	}

	@Override
	public void setBackgroundColor(String color)
	{
		backgroundColor.setValue(color);
	}

	@Override
	public void setTold(Collection<Integer> toldTurtles)
	{
		for (int i = 0; i < actors.size(); i++) {
			if (toldTurtles.contains(i)) {
				actors.get(i).setTold();
			} else {
				actors.get(i).setUntold();
			}
		}
	}

	@Override
	public List<TurtleView> getActors()

	{
		return actors;
	}

	@Override
	public TurtleView getTurtle()
	{
		return actors.get(0);
	}

	@Override
	public void newActor()
	{
		TurtleView actor = new TurtleView(defaults, id);
		
		id++;
		tip = new Tooltip();
		tip.textProperty().bind(Bindings.concat(actor.getActorPositionProperty().getStringValue(), "\n",
				actor.getHeadingProperty().getStringValue(), "\n", actor.getPen().getPenUpProperty().getStringValue()));

		Tooltip.install(actor.display(), tip);


		actor.getPen().getCanvas().toBack();
		actor.getPen().getCanvas().widthProperty().bind(root.widthProperty());
		actor.getPen().getCanvas().heightProperty().bind(root.heightProperty());

		root.getChildren().add(actor.getPen().getCanvas());
		root.getChildren().add(actor.display());

		actors.add(actor);
	}
	
	/**
	 * Draws the stamp as an imageView and adds it to the stamp list
	 */
	@Override
	public void drawStamp(ActorView activeActor){
		ImageView imageViewToAdd = activeActor.getDuplicateImageView();
		imageViewToAdd.setLayoutX(activeActor.getImageView().getLayoutX());
		imageViewToAdd.setTranslateX(activeActor.getImageView().getTranslateX());
		imageViewToAdd.setLayoutY(activeActor.getImageView().getLayoutY());
		imageViewToAdd.setTranslateY(activeActor.getImageView().getTranslateY());
		imageViewToAdd.setRotate(activeActor.getHeading());
		root.getChildren().add(imageViewToAdd);
		stamps.add(imageViewToAdd);
	}
	
	/**
	 * Removes all stamps from the stamp list and from the screen.
	 */
	@Override
	public void clearStamps(){
		for(ImageView stamp : stamps){
			root.getChildren().remove(stamp);
		}
		stamps.removeAll(stamps);
	}
	
	@Override
	public List<ImageView> getStamps(){
		return stamps;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof BackgroundColorButton) {
			if (arg instanceof Color) {
				backgroundColor.setValue((Color) arg);
			}
		} else if (o instanceof TurtleImageButton) {
			if (arg instanceof Image) {
				actors.get(0).setImage((Image) arg);
			}
		} else if (o instanceof PenColorButton) {
			if (arg instanceof Color) {
				// TODO make ID's work
				actors.get(0).getPen().setColor((Color) arg);
			}
		} else if (o instanceof CreateActorButton) {
			newActor();
		} else if (o instanceof DeleteActorButton) {
			if (root.getChildren().size() != 0) {
				// remove last actor and its pen
				root.getChildren().remove(root.getChildren().size() - 2, root.getChildren().size());
			}

		} else if (o instanceof DefaultButton) {
			XMLEditor editor = new XMLEditor();
			String imageName;
			try {
				int index = actors.get(0).getImageProperty().getValue().getImage().impl_getUrl().lastIndexOf("/") + 1;
				imageName = actors.get(0).getImageProperty().getValue().getImage().impl_getUrl().substring(index);
			} catch (NullPointerException e) {
				imageName = defaults.image();
			}

			try {
				editor.setDefault("background", backgroundColor.getValue().toString());
				editor.setDefault("pen", actors.get(0).getPen().getPenColorProperty().getValue().toString());
				editor.setDefault("image", imageName);
				editor.setDefault("numTurtles", Integer.toString(actors.size()));
			} catch (TransformerException e1) {
				throw new XMLException(e1);
			}
		}

	}

	@Override
	public StackPane display()
	{
		return root;
	}

	@Override
	public Bounds getBounds()
	{
		return root.getBoundsInLocal();
	}

	@Override
	public BackgroundColorProperty getBackgroundColorProperty()
	{
		return backgroundColor;
	}

	@Override
	public List<Property<?>> getProperties()
	{
		return Arrays.asList(backgroundColor);
	}

	@Override
	public void set(SimulationView simulation)
	{
		this.root.getChildren().clear();
		this.root.getChildren().add(simulation.getTurtle().clone().display());
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