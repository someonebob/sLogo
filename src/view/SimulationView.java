package view;

import java.io.File;
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
	private TurtleView actor;
	private Background background;

	public SimulationView()
	{
		root = new TabPane();
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

	public TurtleView getTurtle()
	{
		return actor;
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
		actor = new TurtleView();
		layout.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		newTab.setText("new tab");
		newTab.setContent(layout);
		root.getTabs().add(newTab);
		layout.getChildren().add(actor.display());
		layout.getChildren().add(actor.getPen().display());

		actor.getPen().display().layoutXProperty().set(100);
		actor.getPen().display().layoutYProperty().set(100);

		// StackPane.setAlignment(actor.display(), Pos.BOTTOM_CENTER);
		// StackPane.setAlignment(actor.getPen().display(), Pos.BOTTOM_CENTER);
		// AnchorPane.setTopAnchor(actor.display(),
		// getBounds().getHeight() / 2 -
		// actor.display().getBoundsInLocal().getWidth() / 2);
		// AnchorPane.setLeftAnchor(actor.display(),
		// getBounds().getWidth() / 2 -
		// actor.display().getBoundsInLocal().getWidth() / 2);
		// AnchorPane.setTopAnchor(actor.getPen().display(),
		// getBounds().getHeight() / 2 -
		// actor.getPen().display().getBoundsInLocal().getWidth() / 2);
		// AnchorPane.setLeftAnchor(actor.getPen().display(),
		// getBounds().getWidth() / 2 -
		// actor.getPen().display().getBoundsInLocal().getWidth() / 2);
	}

	public void move(Point2D deltaLocation)
	{
		actor.move(deltaLocation);
	}

	private void openFile(File file)
	{

	}

}