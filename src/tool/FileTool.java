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
public class FileTool extends Tool{
	
	public static final String name = "File";
	public static final String EXTENSION = "*.logo";
	
	private Stage window;
	private List<AbstractButton> buttons;

	public FileTool(Stage window) {
		super(name);
		this.window = window;
	}

	
	@Override
	public void makeMenuItems() {
		buttons = new ArrayList <>();
		
		/*
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(makeNewItem());
		menuItems.add(makeOpenItem());
		menuItems.add(makeSaveItem());

		return menuItems;
		*/
	}
	

	@Override
	public List<AbstractButton> getButtons() {
		// TODO Auto-generated method stub
		return buttons;
	}
	
	public class NewButton extends AbstractButton{

		public NewButton() {
			super(new MenuItem("New"));
			//TODO action
			this.setOnAction(null);
		}
		
	}
	
	public class OpenButton extends AbstractButton{

		public OpenButton() {
			super(new MenuItem("Open"));
			// TODO Auto-generated constructor stub
			this.setOnAction(setupFileChooser().showOpenDialog(window));
		}
		
	}
	
	public class SaveButton extends AbstractButton{

		public SaveButton() {
			super(new MenuItem("Save"));
			// TODO Auto-generated constructor stub
			this.setOnAction(null);
		}
		
	}

	public class SaveAsButton extends AbstractButton{

		public SaveAsButton() {
			super(new MenuItem("Save As"));
			// TODO Auto-generated constructor stub
			this.setOnAction(null);
		}
		
	}
	
	
	
	
	
	private FileChooser setupFileChooser(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("LOGO Programs");	
		File defaultDirectory = new File(System.getProperty("user.dir")+"/data");
		chooser.setInitialDirectory(defaultDirectory);
		chooser.getExtensionFilters().setAll(new ExtensionFilter("LOGO", EXTENSION));
		
		return chooser;
	}



}
