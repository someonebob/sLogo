package view;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.FileChooserUtil;

public class AllActorsView implements View{
	private ScrollPane display;
	private VBox actors;
	private ObservableList<TurtleView> turtles;

	public AllActorsView(ObservableList<TurtleView> turtles){
		display = new ScrollPane();
		display.setFitToWidth(true);
		actors = new VBox(10);
		display.setContent(actors);
		this.turtles = turtles;
		update();
	}
	
	public void update(){
		actors.getChildren().clear();
		for(TurtleView turtle : turtles){
			ImageView image = new ImageView(turtle.getImageView().getImage());
			image.setOnMouseClicked(e -> clickAction(image));
			preserveSize(image);
			turtle.getImageView().imageProperty().bind(image.imageProperty());
			actors.getChildren().add(image);
		}
	}
	
	@Override
	public Node display(){
		return display;
	}
	
	private void clickAction(ImageView original){
		Stage newWindow = new Stage();
		File selectedFile = FileChooserUtil
				.setupFileChooser("IMAGE", "IMAGE", new File(System.getProperty("user.dir") + "/images"), "*.png")
				.showOpenDialog(newWindow);
		if (selectedFile != null) {
			original.setImage(new Image(selectedFile.toURI().toString()));
			preserveSize(original);
		}
	}
	
	private void preserveSize(ImageView original){
		original.setPreserveRatio(true);
		original.setFitHeight(70);
	}

}
