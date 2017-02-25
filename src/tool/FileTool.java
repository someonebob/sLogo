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

	public FileTool(Stage window) {
		super(name);
		this.window = window;
	}

	
	@Override
	public void makeMenuItems() {
		/*
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(makeNewItem());
		menuItems.add(makeOpenItem());
		menuItems.add(makeSaveItem());

		return menuItems;
		*/
	}
	
	private MenuItem makeNewItem(){
		MenuItem noo = new MenuItem("New");
		noo.setOnAction(e ->{
			this.setChanged();
			this.notifyObservers();
		});
		
		return noo;		
	}
	
	private MenuItem makeOpenItem(){
		MenuItem open = new MenuItem("Open");
		open.setOnAction(e ->{
			File selectedFile = setupFileChooser().showOpenDialog(window);
			if(selectedFile != null){
				//TODO: do something with the file
			}
		});
		
		return open;
	}
	
	private MenuItem makeSaveItem(){
		MenuItem save = new MenuItem("Save");
		save.setOnAction(e ->{
			
		});
		
		return save;
	}
	
	
	
	
	
	private FileChooser setupFileChooser(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("LOGO Programs");	
		File defaultDirectory = new File(System.getProperty("user.dir")+"/data");
		chooser.setInitialDirectory(defaultDirectory);
		chooser.getExtensionFilters().setAll(new ExtensionFilter("LOGO", EXTENSION));
		
		return chooser;
	}


	@Override
	public List<AbstractButton> getButtons() {
		// TODO Auto-generated method stub
		return null;
	}

}
