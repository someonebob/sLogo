package tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xml.XMLEditor;

public class DefaultChooser implements Chooser {	
	public static final ObservableList<String> COLORS = FXCollections.observableList(Arrays.asList(new String[]{"white", "black", "red", "orange", "yellow", "green", "blue", "indigo", "violet"}));
	public static final ObservableList<String> IMAGES = FXCollections.observableList(Arrays.asList(new String[]{"default.png", "Koopa.png", "Leonardo.png", "Squirt.png"}));
	public static final ObservableList<String> TURTLES = FXCollections.observableList(Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
	public static final ObservableList<String> LANGUAGES = FXCollections.observableList(Arrays.asList(new String[]{"Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"}));

	
	private Stage stage;
	private ListView<String> backgroundColorOptions;
	private ListView<String> penColorOptions;
	private ListView<String> imageOptions;
	private ListView<String> numTurtleOptions;
	private ListView<String> languageOptions;
	private List<String> selections;
	private XMLEditor editor;
	
	public DefaultChooser(){
		selections = new ArrayList<>();
		stage = new Stage();
		BorderPane pane = new BorderPane();
		
		pane.setCenter(center());
		pane.setBottom(bottom());
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Defaults");
		stage.show();
	}

	
	private Node center(){
		ScrollPane scroll = new ScrollPane();
		scroll.setPrefWidth(500);
		VBox box = new VBox(8);
		Label background = new Label("Background Color");
		backgroundColorOptions = new ListView<>(COLORS);
		backgroundColorOptions.setPrefHeight(100);
		
		Label pen = new Label("Pen Color");
		penColorOptions = new ListView<>(COLORS);
		penColorOptions.setPrefHeight(100);

		Label image = new Label("Image");
		imageOptions = new ListView<>(IMAGES);
		imageOptions.setPrefHeight(100);

		Label numTurtles = new Label("Number of Initial Turtles");
		numTurtleOptions = new ListView<>(TURTLES);
		numTurtleOptions.setPrefHeight(100);

		Label language = new Label("Language");
		languageOptions = new ListView<>(LANGUAGES);
		languageOptions.setPrefHeight(100);

		box.getChildren().addAll(background, backgroundColorOptions, pen, penColorOptions, image, imageOptions, numTurtles, numTurtleOptions, language, languageOptions);
		scroll.setContent(box);
		
		return scroll;
	}
	private Node bottom(){
		HBox box = new HBox(10);
		Button ok = new Button("OK");
		ok.setOnAction(e -> {
			
		});
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			stage.close();
		});
		box.getChildren().addAll(ok, cancel);
		box.setAlignment(Pos.CENTER_RIGHT);
		return box;
	}

}
