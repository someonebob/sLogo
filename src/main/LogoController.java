package main;

import java.util.ArrayList;
import java.util.List;

import instruction.InstructionData;
import interpreter.Interpreter;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tool.AbstractButton;
import tool.FileTool;
import tool.HelpTool;
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
public class LogoController {
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
	private Interpreter interpret;

	private FileTool file;
	private SettingsTool settings;
	private HelpTool help;

	public LogoController(Stage stage) {
		initiateViews();
		addTools();
		initiateObservers();

		this.stage = stage;
		stage.setTitle("SLogo");
		stage.show();
		stage.setScene(makeScene());
	}

	private Scene makeScene() {
		this.pane = new BorderPane();
		pane.setBottom(inputBox.display());
		pane.setTop(selectionBar.display());
		pane.setLeft(workspace.display());
		pane.setCenter(simulation.display());
		pane.setRight(userCommands.display());

		inputBox.getField().setOnAction(e -> {
			executeCommand();
			inputBox.getField().clear();	
		});

		inputBox.display().setOnMouseClicked(e -> {
			executeClickedCommand();
		});

		return new Scene(pane, DISPLAY_WIDTH, DISPLAY_HEIGHT);
	}

	private void executeCommand() {
		InstructionData data = new InstructionData(simulation);
		// TODO: make function to get language
		interpret = new Interpreter(data, "English");
		String input = inputBox.getField().getText();
		
		if (input != null) {
			interpret.parseAndRun(inputBox.getField().getText());
			inputBox.addPrevious(input);
		}

	}

	private void executeClickedCommand() {
		if (inputBox.getClickedCommands().size() > 0) {
			System.out.println(inputBox.getClickedCommands().pop());
			System.out.println(simulation.display().getBoundsInLocal().getHeight());
			System.out.println(simulation.display().getBoundsInLocal().getWidth());

		}
	}

	public void addPage(PageView page) {
		this.pages.add(page);
	}

	public void setSimulation(SimulationView simulation) {
		this.simulation = simulation;
	}

	private void initiateViews() {
		pages = new ArrayList<PageView>();
		simulation = new SimulationView();
		selectionBar = new SelectionBar();
		inputBox = new InputBox();
		workspace = new WorkspaceView();
		userCommands = new SavedCommandsView();
	}

	private void addTools() {
		file = new FileTool(stage);
		settings = new SettingsTool(stage);
		help = new HelpTool(stage);
		selectionBar.addAllTools(file, settings, help);
	}

	private void initiateObservers() {
		for (AbstractButton ab : file.getButtons()) {
			ab.addObserver(simulation);
			ab.addObserver(inputBox);
		}

		for (AbstractButton ab : settings.getButtons()) {
			ab.addObserver(simulation);
		}
	}

}