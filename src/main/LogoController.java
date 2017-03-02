package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.SLogoException;
import instruction.InstructionData;
import interpreter.Interpreter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tool.AbstractButton;
import tool.FileTool;
import tool.FileTool.OpenButton;
import tool.HelpTool;
import tool.SelectionBar;
import tool.SettingsTool;
import tool.SettingsTool.LanguageButton;
import user_structures.FunctionData;
import user_structures.VariableData;
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
public class LogoController implements Observer
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
	private Interpreter interpret;
	private ObservableList<VariableData> variables;
	private ObservableList<FunctionData> functions;
	private String language;

	private FileTool file;
	private SettingsTool settings;
	private HelpTool help;

	public LogoController(Stage stage)
	{
		initiateViews();
		addTools();
		initiateObservers();
		
		language = "English";

		List<VariableData> varList = new ArrayList<>();
		variables = FXCollections.observableList(varList);

		List<FunctionData> funcList = new ArrayList<>();
		functions = FXCollections.observableList(funcList);

		workspace.setItems(variables);
		// TODO: Add functions to the workspace

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

		inputBox.getField().setOnAction(e -> {
			executeCommand();
			inputBox.getField().clear();
		});

		inputBox.display().setOnMouseClicked(e -> {
			executeClickedCommand();
		});

		return new Scene(pane, DISPLAY_WIDTH, DISPLAY_HEIGHT);
	}

	private void executeCommand()
	{

		String input = inputBox.getField().getText();

		if (input != null) {
			runCommand(input);

			// Do if command is valid
			inputBox.updateData(input);

		}

	}

	private void executeClickedCommand()
	{
		if (inputBox.getClickedCommands().size() > 0) {
			runCommand(inputBox.getClickedCommands().pop());
		}
	}

	private void runCommand(String command)
	{
		InstructionData data = new InstructionData(simulation, variables, functions);
		// TODO: make function to get language
		try{
		interpret = new Interpreter(data, language);
		interpret.parseAndRun(command);
		}
		catch(SLogoException exception){
			exception.displayAlert();
		}
	}

	public void addPage(PageView page)
	{
		this.pages.add(page);
	}

	public void setSimulation(SimulationView simulation)
	{
		this.simulation = simulation;
	}

	private void initiateViews()
	{
		pages = new ArrayList<PageView>();
		simulation = new SimulationView();
		selectionBar = new SelectionBar();
		inputBox = new InputBox();
		workspace = new WorkspaceView();
		userCommands = new SavedCommandsView();
	}

	private void addTools()
	{
		file = new FileTool(stage);
		settings = new SettingsTool(stage);
		help = new HelpTool(stage);
		selectionBar.addAllTools(file, settings, help);
	}

	private void initiateObservers()
	{
		for (AbstractButton ab : file.getButtons()) {
			ab.addObserver(simulation);
			ab.addObserver(inputBox);
			ab.addObserver(this);
		}

		for (AbstractButton ab : settings.getButtons()) {
			ab.addObserver(simulation);
			ab.addObserver(this);
		}

	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub
		if (o instanceof OpenButton) {
			openFile((File) arg);
		}
		
		if(o instanceof LanguageButton){
			language = (String) arg;
		}
	}

	private void openFile(File file)
	{
		FileReader fr = null;
		StringBuilder command = new StringBuilder();
		String line = null;
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			while ((line = reader.readLine()) != null) {
				command.append(line + "\n");
			}

			runCommand(command.toString());
			fr.close();

		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file");
		} catch (IOException e) {
			Logger.getLogger(InputBox.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
