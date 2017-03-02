package view;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	private TabPane root;
	private Background background;
	private Map<Tab, ActorView> actors;

	public SimulationView()
	{
		root = new TabPane();
		actors = new HashMap<>();
		newTab();
	}

	public void addTab()
	{

	}

	@Override
	public Node display()
	{
		return root;
	}

	public TurtleView getTurtle()
	{
		return (TurtleView) actors.get(this.getCurrentTab());
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof NewButton) {
			newTab();
		}
		if (o instanceof OpenButton) {
			openFile((File) arg);
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
						actors.get(this.getCurrentTab()).setImage((Image) arg);
						((StackPane) this.getCurrentTab().getContent()).getChildren().clear();
						((StackPane) this.getCurrentTab().getContent()).getChildren().addAll(
								actors.get(this.getCurrentTab()).getImage(),
								((TurtleView) actors.get(this.getCurrentTab())).getPen().getCanvas());
					}
				}
			}
		}
	}

	public Bounds getBounds()
	{
		return root.getBoundsInLocal();
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

		TurtleView actor = new TurtleView();
		layout.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		newTab.setText("new tab");
		newTab.setContent(layout);
		root.getTabs().add(newTab);
		layout.getChildren().add(actor.display());
		layout.getChildren().add(actor.getPen().getCanvas());
		actor.getPen().getCanvas().toBack();
		actor.getPen().getCanvas().widthProperty().bind(layout.widthProperty());
		actor.getPen().getCanvas().heightProperty().bind(layout.heightProperty());

		actors.put(newTab, actor);
	}

	public void move(Point2D deltaLocation)
	{
		actors.get(getCurrentTab()).move(deltaLocation);
	}

	public Tab getCurrentTab()
	{
		for (Tab t : root.getTabs()) {
			if (t.isSelected()) {
				return t;
			}
		}
		return null;
	}

	private void openFile(File file)
	{

	}

}