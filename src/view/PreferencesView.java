package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
	private ImageView actorImage;
	private Map<String, Node> preferenceViews;
	private VBox header;

	public PreferencesView(TurtleView initialActor)
	{
		root = new BorderPane();
		actorImage = new ImageView();
		actorImage.setPreserveRatio(true);
		actorImage.setFitHeight(70);
		this.actor = initialActor;
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
		actorImage.setImage(actor.getImage().getImage());
		header.getChildren().add(actorImage);
		root.setTop(header);
	}

	private void makePreferencesChooser()
	{
		ComboBox<String> chooser = new ComboBox<String>(FXCollections.observableArrayList(preferenceViews.keySet()));
		chooser.valueProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				root.setCenter(preferenceViews.get(newValue));
			}

		});
		header.getChildren().add(chooser);
	}

	private void initializePreferenceViews()
	{
		preferenceViews = new HashMap<>();
		preferenceViews.put("Pen", new PenPreferencesView(actor).display());
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// actor = (ActorView) o;
		System.out.println("hi");
	}

	@Override
	public Node display()
	{
		return root;
	}

}
