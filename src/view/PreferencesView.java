package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.PenPreferencesView.PenColorButton;
import view.PenPreferencesView.PenThicknessUpdater;
import view.PenPreferencesView.PenUpDownUpdater;
import view.TurtlePreferencesView.TurtleColorButton;
import view.TurtlePreferencesView.TurtleImageButton;

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
		header.getChildren().add(new Label("Change Property:"));
		initializePreferenceViews();
		makePreferencesChooser();
		header.getChildren().add(new Label("Current Actor:"));
		updateActorImage();
		header.getChildren().add(actorImage);
		root.setTop(header);
		header.getChildren().add(new Label(String.format("Position: %s", actor.getActor().getLocation())));
		header.getChildren().add(new Label(String.format("Heading: %s", actor.getHeading())));
		header.getChildren().add(new Label(String.format("Pen up/down: %s", actor.getPen().isUp() ? "up" : "down")));
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
				root.setCenter(preferenceViews.get(newValue));
			}
		});
		header.getChildren().add(chooser);
	}

	private void initializePreferenceViews()
	{
		preferenceViews = new HashMap<>();
		PenPreferencesView penPreferences = new PenPreferencesView(actor);
		penPreferences.addObserver(this);
		preferenceViews.put("Pen", penPreferences.display());
		TurtlePreferencesView turtlePreferences = new TurtlePreferencesView(actor);
		turtlePreferences.addObserver(this);
		preferenceViews.put("Turtle", turtlePreferences.display());
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// when click on new turtle, update it in here
		if (o instanceof PenColorButton) {
			if (arg instanceof Color) {
				actor.getPen().setColor((Color) arg);
			}
		}
		if (o instanceof PenThicknessUpdater) {
			if (arg instanceof String) {
				actor.getPen().setThickness(Double.valueOf((String) arg));
			}
		}
		if (o instanceof PenUpDownUpdater) {
			if (arg instanceof String) {
				if (((String) arg).equals("Up")) {
					actor.getPen().penUp();
				} else if (((String) arg).equals("Down")) {
					actor.getPen().penDown();
				}
			}
		}
		if (o instanceof TurtleColorButton) {
			if (arg instanceof Color) {
				// ColorAdjust monochrome = new ColorAdjust();
				// monochrome.setSaturation(-1.0);

				// actor.getImage().setClip(actor.getImage());
				// actor.getImage().setClip(value);

				Lighting lighting = new Lighting();
				lighting.setDiffuseConstant(1.0);
				lighting.setSpecularConstant(0.0);
				lighting.setSpecularExponent(0.0);
				lighting.setSurfaceScale(0.0);
				lighting.setLight(new Light.Distant(45, 45, (Color) arg));

				// Blend changeColor = new Blend(BlendMode.MULTIPLY, monochrome,
				// new
				// ColorInput(0, 0,
				// actor.getImage().getFitWidth(),
				// actor.getImage().getFitHeight(),
				// Color.ORANGE));
				// actor.getImage().setEffect(lighting);
				actor.getImage().setEffect(lighting);
			}
		}
		if (o instanceof TurtleImageButton) {
			if (arg instanceof Image) {
				actor.setImage((Image) arg);
			}
		}

		// actor = (ActorView) o;
	}

	@Override
	public Node display()
	{
		return root;
	}

}
