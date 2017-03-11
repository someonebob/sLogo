package tool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * 
 * @author Jesse
 *
 */
public class FileMenuTool extends MenuTool
{

	public static final String name = "File";
	public static final String EXTENSION = "*.logo";

	private List<AbstractMenuItem> buttons;

	public FileMenuTool(Stage window) {
		super(name, window);
	}

	@Override
	public void makeItems() {
		buttons = new ArrayList <>();
		buttons.add(new NewButton());
		buttons.add(new OpenButton());
		buttons.add(new SaveButton());
		buttons.add(new SaveImageButton());
	}
	

	@Override
	protected List<AbstractMenuItem> getButtons() {
		return buttons;
	}
	
	public class NewButton extends AbstractMenuItem{

		public NewButton() {
			super(new MenuItem("New"));
			//TODO action
			this.getItem().setOnAction(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
		
	}
	
	public class OpenButton extends AbstractMenuItem{

		public OpenButton() {
			super(new MenuItem("Open"));
			// TODO Auto-generated constructor stub
			this.getItem().setOnAction(e -> {
				File selectedFile = setupFileChooser().showOpenDialog(getStage());
				if(selectedFile != null){
					this.setChanged();
					this.notifyObservers(selectedFile);
				}
				
			});
		}
		
	}
	
	public class SaveButton extends AbstractMenuItem{

		public SaveButton() {
			super(new MenuItem("Save"));
			// TODO Make it save
			this.getItem().setOnAction(e -> {
				File save = setupFileChooser().showSaveDialog(getStage());
				if(save != null){
					this.setChanged();
					this.notifyObservers(save);
				}
				
			});
		}
		
	}

	public class SaveImageButton extends AbstractMenuItem{

		public SaveImageButton() {
			super(new MenuItem("Save Image"));
			// TODO make it save
			this.getItem().setOnAction(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
		
	}
	
	
	private FileChooser setupFileChooser(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("LOGO Programs");
		File defaultDirectory = new File(System.getProperty("user.dir") + "/data");
		chooser.setInitialDirectory(defaultDirectory);
		chooser.getExtensionFilters().setAll(new ExtensionFilter("LOGO", EXTENSION));

		return chooser;
	}



}