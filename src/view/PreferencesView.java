package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PreferencesView implements View
{
	private BorderPane root;
	private TurtleView actor;
	private List<TurtleView> actors;
	private SimulationView simulation;
	private ImageView actorImage;
	private Map<String, Node> preferenceViews;
	private VBox header;
	private VBox footer;
	

	public PreferencesView(TurtleView initialActor, SimulationView simulation)
	{
		root = new BorderPane();
		actorImage = new ImageView();
		actorImage.setPreserveRatio(true);
		actorImage.setFitHeight(70);
		this.actor = initialActor;
		this.simulation = simulation;

		updateDisplay();
	}

	public void step()
	{
		actors = simulation.getActors();
		for (TurtleView turtle : actors) {
			turtle.getImageView().setOnMouseClicked(e -> {
				actor = turtle;
				updateDisplay();
			});
		}

	}

	private void setupHeader()
	{
		header = new VBox();
		header.setAlignment(Pos.CENTER);
		header.setPrefWidth(200);
		// header.getChildren().add(makeUndoButton());
		header.getChildren().add(new Label("Change Property:"));
		initializePreferenceViews();
		ComboBox<String> preferenceChooser = makePreferencesChooser();
		preferenceChooser.setValue(preferenceChooser.getItems().get(0));
		header.getChildren().add(preferenceChooser);
		header.getChildren().add(new Label("Current Actor:"));
		updateActorImage();
		header.getChildren().add(actor.getImageProperty().display());
		root.setTop(header);
	}

	private void updateDisplay()
	{
		root.getChildren().clear();
		setupHeader();
		setupFooter();
		updateActorImage();
	}

	private void setupFooter()
	{
		footer = new VBox();
		footer.setAlignment(Pos.CENTER);
		footer.getChildren().add(new Label("Color Palette:"));
		footer.getChildren().add(simulation.getBackgroundColorProperty().getColorPalette());
		root.setBottom(footer);
	}

	private void updateActorImage()
	{
		actorImage.setImage(actor.getImageView().getImage());
	}

	private ComboBox<String> makePreferencesChooser()
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
		return chooser;
	}

	// private Button makeUndoButton()
	// {
	// Button undo = new Button("Undo");
	// undo.setOnAction(e -> {
	// simulation.undo();
	// });
	// return undo;
	// }

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
	public Node display()
	{
		return root;
	}

}