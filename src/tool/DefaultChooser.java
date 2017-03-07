package tool;

import java.util.Arrays;
import javax.xml.transform.TransformerException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
	private XMLEditor editor;
	
	public DefaultChooser(){
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
		VBox box = new VBox(8);
		Label background = new Label("Background Color");
		backgroundColorOptions = new ListView<>(COLORS);
		backgroundColorOptions.setPrefHeight(100);
		backgroundColorOptions.setPrefWidth(500);
		
		Label pen = new Label("Pen Color");
		penColorOptions = new ListView<>(COLORS);
		penColorOptions.setPrefHeight(100);
		penColorOptions.setPrefWidth(500);

		Label image = new Label("Image");
		imageOptions = new ListView<>(IMAGES);
		imageOptions.setPrefHeight(100);
		imageOptions.setPrefWidth(500);

		Label numTurtles = new Label("Number of Initial Turtles");
		numTurtleOptions = new ListView<>(TURTLES);
		numTurtleOptions.setPrefHeight(100);
		numTurtleOptions.setPrefWidth(500);

		Label language = new Label("Language");
		languageOptions = new ListView<>(LANGUAGES);
		languageOptions.setPrefHeight(100);
		languageOptions.setPrefWidth(500);

		box.getChildren().addAll(background, backgroundColorOptions, pen, penColorOptions, image, imageOptions, numTurtles, numTurtleOptions, language, languageOptions);
		
		return box;
	}
	private Node bottom(){
		HBox box = new HBox(10);
		Label instruction = new Label("Restart the program to apply changes");
		Button ok = new Button("OK");
		editor = new XMLEditor();
		ok.setOnAction(e -> {
			try {
				editor.setDefault("background", backgroundColorOptions.getSelectionModel().getSelectedItem());
				editor.setDefault("pen", penColorOptions.getSelectionModel().getSelectedItem());
				editor.setDefault("image", imageOptions.getSelectionModel().getSelectedItem());
				editor.setDefault("numTurtles", numTurtleOptions.getSelectionModel().getSelectedItem());
				editor.setDefault("language", languageOptions.getSelectionModel().getSelectedItem());

			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			stage.close();
		});
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			stage.close();
		});
		box.getChildren().addAll(instruction, ok, cancel);
		box.setAlignment(Pos.CENTER_RIGHT);
		return box;
	}

}
