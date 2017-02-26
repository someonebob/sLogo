package tool;

import java.io.File;
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
public class FileTool extends Tool
{

	public static final String name = "File";
	public static final String EXTENSION = "*.logo";

	private Stage window;
	private List<AbstractButton> buttons;

	public FileTool() {
		super(name);
		window = new Stage();
	}

	@Override
	public void makeMenuItems() {
		buttons = new ArrayList <>();
		buttons.add(new NewButton());
		buttons.add(new OpenButton());
		buttons.add(new SaveButton());
		buttons.add(new SaveAsButton());
	}
	

	@Override
	public List<AbstractButton> getButtons() {
		return buttons;
	}
	
	public class NewButton extends AbstractButton{

		public NewButton() {
			super(new MenuItem("New"));
			//TODO action
			this.getItem().setOnAction(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
		
	}
	
	public class OpenButton extends AbstractButton{

		public OpenButton() {
			super(new MenuItem("Open"));
			// TODO Auto-generated constructor stub
			this.getItem().setOnAction(e -> {
				File selectedFile = setupFileChooser().showOpenDialog(window);
				if(selectedFile != null){
					this.setChanged();
					this.notifyObservers(selectedFile);
				}
				
			});
		}
		
	}
	
	public class SaveButton extends AbstractButton{

		public SaveButton() {
			super(new MenuItem("Save"));
			// TODO Make it save
			this.getItem().setOnAction(e -> {
				File save = setupFileChooser().showSaveDialog(window);
				if(save != null){
					this.setChanged();
					this.notifyObservers(save);
				}
				
			});
		}
		
	}

	public class SaveAsButton extends AbstractButton{

		public SaveAsButton() {
			super(new MenuItem("Save As"));
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