package main;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tool.AbstractButton;
import tool.FileTool;
import tool.SelectionBar;
import tool.SettingsTool;
import view.InputBox;
import view.PageView;
import view.SavedCommandsView;
import view.SimulationView;
import view.WorkspaceView;

/**
 * 
 * @author jimmy
 * @author Jesse
 */
public class LogoController
{
	public final int DISPLAY_WIDTH = 600;
	public final int DISPLAY_HEIGHT = 600;

	private List<PageView> pages;
	private SimulationView simulation;
	private SelectionBar selectionBar;
	private InputBox inputBox;
	private WorkspaceView workspace;
	private SavedCommandsView userCommands;
	private Stage stage;
	private BorderPane pane;
	
	private FileTool file;
	private SettingsTool settings;

	public LogoController(Stage stage)
	{
		pages = new ArrayList<PageView>();
		simulation = new SimulationView();
		selectionBar = new SelectionBar();
		inputBox = new InputBox();
		workspace = new WorkspaceView();
		userCommands = new SavedCommandsView();
		
		addTools();
		initiateObservers();
		this.stage = stage;
		stage.setTitle("SLogo");
		stage.show();
		stage.setScene(makeScene());
	}

	private Scene makeScene()
	{
		this.pane = new BorderPane();
		pane.setBottom(inputBox.display());
		pane.setTop(selectionBar.display());
		pane.setLeft(workspace.display());
		pane.setCenter(simulation.display());
		pane.setRight(userCommands.display());
		return new Scene(pane, DISPLAY_WIDTH, DISPLAY_HEIGHT);
	}

	public void addPage(PageView page)
	{
		this.pages.add(page);
	}

	public void setSimulation(SimulationView simulation)
	{
		this.simulation = simulation;
	}

	public void addTools()
	{
		file = new FileTool(stage);
		settings = new SettingsTool();
		selectionBar.addAllTools(file, settings);;
	}

	private void initiateObservers(){
		inputBox.addObserver(workspace);
		inputBox.addObserver(simulation);
		file.addObserver(simulation);
		settings.addObserver(simulation);
		for(AbstractButton ab : settings.getButtons()){
			ab.addObserver(simulation);
		}
	}

}
