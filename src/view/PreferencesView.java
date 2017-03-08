package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PreferencesView implements View
{

	private BorderPane root;
	private TurtleView actor;
	private SimulationView simulation;
	private ImageView actorImage;
	private Map<String, Node> preferenceViews;
	private VBox header;

	public PreferencesView(TurtleView initialActor, SimulationView simulation)
	{
		root = new BorderPane();
		actorImage = new ImageView();
		actorImage.setPreserveRatio(true);
		actorImage.setFitHeight(70);
		this.actor = initialActor;
		this.simulation = simulation;
		setupHeader();
		// root.setCenter(new PenPreferencesView(actor).display());
	}

	private void setupHeader()
	{
		header = new VBox();
		header.setAlignment(Pos.CENTER);
		header.setPrefWidth(200);
		header.getChildren().add(makeUndoButton());
		header.getChildren().add(new Label("Change Property:"));
		initializePreferenceViews();
		makePreferencesChooser();
		header.getChildren().add(new Label("Current Actor:"));
		updateActorImage();
		header.getChildren().add(actor.getImageProperty().display());
		// header.getChildren().add(actorImage);
		// root.getChildren().add(header);
		root.setTop(header);

		// header.getChildren().add(actor.getActorPositionProperty().display());
		// header.getChildren().add(new Label(String.format("Position: %s",
		// actor.getActor().getLocation())));
		// header.getChildren().add(new Label(String.format("Heading: %s",
		// actor.getHeading())));
		// header.getChildren().add(new Label(String.format("Pen up/down: %s",
		// actor.getPen().isUp() ? "up" : "down")));
	}

	private void updateActorImage()
	{
		actorImage.setImage(actor.getImage().getImage());
	}

	private void makePreferencesChooser()
	{
		ComboBox<String> chooser = new ComboBox<String>(FXCollections.observableArrayList(preferenceViews.keySet()));
		chooser.valueProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				BorderPane.setAlignment(preferenceViews.get(newValue), Pos.CENTER);
				// root.getChildren().add(preferenceViews.get(newValue));

				root.setCenter(preferenceViews.get(newValue));
			}
		});
		header.getChildren().add(chooser);
	}

	private Button makeUndoButton()
	{
		Button undo = new Button("Undo");
		undo.setOnAction(e -> {
			simulation.undo();
		});
		return undo;
	}

	private void initializePreferenceViews()
	{
		preferenceViews = new HashMap<>();
		PenPreferencesView penPreferences = new PenPreferencesView(actor);
		preferenceViews.put("Pen", penPreferences.display());
		TurtlePreferencesView turtlePreferences = new TurtlePreferencesView(actor);
		preferenceViews.put("Turtle", turtlePreferences.display());
		DisplayPreferencesView displayPreferences = new DisplayPreferencesView(simulation);
		preferenceViews.put("Display", displayPreferences.display());
	}

	@Override
	public void update(Observable o, Object arg)
	{

	}

	@Override
	public Node display()
	{
		return root;
	}

}
