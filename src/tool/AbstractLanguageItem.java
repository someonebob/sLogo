package tool;

import java.util.Arrays;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This specific item requires more details to invoke a new window so is made into a new class so that the tool is cleaner
 * @author Jesse
 *
 */
public class AbstractLanguageItem extends AbstractMenuItem{
	public static final List<String> LANGUAGES = Arrays.asList(new String[]{"Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"});
	
	private String language;
	
	public AbstractLanguageItem(MenuItem menu) {
		super(menu);
		this.getItem().setOnAction(e -> {
			Stage stage = new Stage();
			VBox box = new VBox(10);
			ListView<String> list = new ListView<>();
			Button select = new Button("Select");
			list.getItems().addAll(LANGUAGES);
			select.setOnAction(ev -> {
				language = list.getSelectionModel().getSelectedItem();
				this.setChanged();
				this.notifyObservers(language);
				stage.close();
			});
			
			box.getChildren().addAll(list, select);
			Scene scene = new Scene(box);
			stage.setScene(scene);
			stage.setTitle("Language Chooser");
			stage.show();
		});
	}

}
