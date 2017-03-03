//package view;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Observable;
//
//import javafx.geometry.Bounds;
//import javafx.geometry.Point2D;
//import javafx.scene.Node;
//import javafx.scene.control.Tab;
//import javafx.scene.control.TabPane;
//import javafx.scene.image.Image;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import tool.SettingsTool.BackgroundColorButton;
//import tool.SettingsTool.PenColorButton;
//import tool.SettingsTool.TurtleImageButton;
//
///**
// * @author jimmy
// * @author Jesse
// *
// */
//public class SimulationView implements View
//{
//	private TabPane root;
//	private Map<Tab, ActorView> actors;
//
//	public SimulationView()
//	{
//		root = new TabPane();
//		actors = new HashMap<>();
//		newTab();
//	}
//
//
//	public void step()
//	{
//		actors.get(this.getCurrentTab()).step();
//	}
//
//	@Override
//	public Node display()
//	{
//		return root;
//	}
//
//	public TurtleView getTurtle()
//	{
//		return (TurtleView) actors.get(this.getCurrentTab());
//	}
//
//	@Override
//	public void update(Observable o, Object arg)
//	{
//
//		if (o instanceof BackgroundColorButton) {
//			if (arg instanceof Color) {
//
//				// Find which tab is active and change color of that tab
//				for (Tab t : root.getTabs()) {
//					if (t.isSelected()) {
//						((StackPane) t.getContent())
//								.setBackground(new Background(new BackgroundFill((Paint) arg, null, null)));
//					}
//				}
//			}
//		}
//
//		if (o instanceof TurtleImageButton) {
//			if (arg instanceof Image) {
//				for (Tab t : root.getTabs()) {
//					if (t.isSelected()) {
//						actors.get(this.getCurrentTab()).setImage((Image) arg);
//					}
//				}
//			}
//		}
//
//		if (o instanceof PenColorButton) {
//			if (arg instanceof Color) {
//				((TurtleView) actors.get(this.getCurrentTab())).getPen().setColor((Color) arg);
//			}
//		}
//	}
//
//	public Bounds getBounds()
//	{
//		return root.getBoundsInLocal();
//	}
//
//	@Override
//	public void updateData(String arg)
//	{
//		// TODO Auto-generated method stub
//
//	}
//
//	private void newTab()
//	{
//		Tab newTab = new Tab();
//		StackPane layout = new StackPane();
//
//		TurtleView actor = new TurtleView(1);
//		layout.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
//		newTab.setText("new tab");
//		newTab.setContent(layout);
//		root.getTabs().add(newTab);
//		layout.getChildren().add(actor.display());
//		layout.getChildren().add(actor.getPen().getCanvas());
//		actor.getPen().getCanvas().toBack();
//		actor.getPen().getCanvas().widthProperty().bind(layout.widthProperty());
//		actor.getPen().getCanvas().heightProperty().bind(layout.heightProperty());
//
//		actors.put(newTab, actor);
//	}
//
//	public void move(Point2D deltaLocation)
//	{
//		actors.get(getCurrentTab()).move(deltaLocation);
//	}
//
//	public Tab getCurrentTab()
//	{
//		for (Tab t : root.getTabs()) {
//			if (t.isSelected()) {
//				return t;
//			}
//		}
//		return null;
//	}
//
//
//}
//
//
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

public class SimulationView implements View {
	//public static final ResourceBundle RESOURCES = ResourceBundle.getBundle("resources/userinterface/default");

	private StackPane root;
	private ObservableList<ActorView> actors;
	private int id = 1;

	public SimulationView() {
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