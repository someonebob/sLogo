package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

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
import tool.SettingsTool.BackgroundColorButton;
import tool.SettingsTool.PenColorButton;
import tool.SettingsTool.TurtleImageButton;
import user_structures.ID;

public class NewSimulationView implements View {
	//public static final ResourceBundle RESOURCES = ResourceBundle.getBundle("resources/userinterface/default");

	private StackPane root;
	private ObservableList<ActorView> actors;
	private int id = 1;

	public NewSimulationView() {
		root = new StackPane();
		List<ActorView> list = new ArrayList<>();
		actors = FXCollections.observableList(list);
		for (int i = 0; i < Integer.parseInt("1"); i++) {//RESOURCES.getString("numTurtles")
			newActor();
		}
		root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

	}
	
	public void step()
	{
		actors.get(0).step();
	}
	
	public void move( Point2D deltaLocation)
	{
		actors.get(0).move(deltaLocation);
	}
	
	public TurtleView getTurtle()
	{
		return (TurtleView) actors.get(0);
	}

	public void newActor() {
		TurtleView actor = new TurtleView(id);
		id++;
		actor.getPen().getCanvas().toBack();
		actor.getPen().getCanvas().widthProperty().bind(root.widthProperty());
		actor.getPen().getCanvas().heightProperty().bind(root.heightProperty());

		root.getChildren().add(actor.display());
		root.getChildren().add(actor.getPen().getCanvas());

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

	@Override
	public void updateData(String arg) {
		// TODO Auto-generated method stub

	}

	public Bounds getBounds() {
		return root.getBoundsInLocal();
	}

}