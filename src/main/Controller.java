package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import exceptions.SLogoException;
import instruction.InstructionData;
import interpreter.Interpreter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tool.AnimationControlToolButtons;
import tool.ComboBar;
import tool.FileMenuTool;
import tool.FileMenuTool.NewButton;
import tool.FileMenuTool.OpenButton;
import tool.HelpMenuTool;
import tool.MenuTool;
import tool.SelectionBar;
import tool.SettingsMenuTool;
import tool.SettingsMenuTool.LanguageButton;
import tool.ToolButton;
import user_structures.FunctionData;
import user_structures.VariableData;
import view.InputBox;
import view.PreferencesView;
import view.SavedCommandsView;
import view.SimulationView;
import view.SingleLineInputBox;
import view.WorkspaceView;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class Controller implements Observer
{
	private TabPane root;
	private ObjectProperty<Tab> currentTab;
	private Map<Tab, SelectionBar> selectionBarMap;
	private Map<Tab, SimulationView> simulationMap;
	private Map<Tab, InputBox> inputBoxMap;
	private Map<Tab, WorkspaceView> workspaceMap;
	private Map<Tab, SavedCommandsView> savedCommandsMap;

	private Map<Tab, ObservableList<VariableData>> variableMap;
	private Map<Tab, ObservableList<FunctionData>> functionMap;

	private Stage stage;
	private double printValue;
	private Defaults defaults;
	private List<String> language;
	private IntegerProperty currentIndex;

	public Controller(Stage stage, Defaults defaults)
	{
		this.stage = stage;
		this.defaults = defaults;
		language = new ArrayList<>();
		currentIndex = new SimpleIntegerProperty();
		setupItems();
		newTab();
		// currentTab is always the one selected
		currentTab.bind(root.getSelectionModel().selectedItemProperty());
		currentIndex.bind(root.getSelectionModel().selectedIndexProperty());

		root.getStylesheets().add("DarkTheme.css");
		stage.setTitle("SLogo");
		stage.setScene(new Scene(root));
		stage.setMaximized(true);
		stage.show();
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof NewButton) {
			newTab();
		}
		if (o instanceof OpenButton) {
			openFile((File) arg);
		}
		if (o instanceof LanguageButton) {
			language.set(currentIndex.get(), (String) arg);
		}
	}

	private void setupItems()
	{
		root = new TabPane();
		currentTab = new SimpleObjectProperty<>();
		selectionBarMap = new HashMap<>();
		simulationMap = new HashMap<>();
		inputBoxMap = new HashMap<>();
		workspaceMap = new HashMap<>();
		savedCommandsMap = new HashMap<>();

		variableMap = new HashMap<>();
		functionMap = new HashMap<>();

	}

	private void newTab()
	{
		Tab tab = new Tab();
		root.getSelectionModel().select(tab);
		;
		language.add(defaults.language());
		tab.setText("untitled.logo");
		BorderPane pane = new BorderPane();
		SimulationView simulation = new SimulationView(defaults);
		InputBox inputBox = new SingleLineInputBox();
		inputBox.setFocus();
		WorkspaceView workspace = new WorkspaceView();
		SavedCommandsView userCommands = new SavedCommandsView();
		PreferencesView preferences = new PreferencesView(simulation.getTurtle(), simulation);

		SelectionBar selectionBar = new ComboBar();
		MenuTool file = new FileMenuTool(stage);
		MenuTool settings = new SettingsMenuTool(stage);
		MenuTool help = new HelpMenuTool(stage);
		ToolButton animation = new AnimationControlToolButtons();
		selectionBar.addAllTools(file, settings, help, animation);

		List<VariableData> varList = new ArrayList<>();
		ObservableList<VariableData> variables = FXCollections.observableList(varList);
		List<FunctionData> funcList = new ArrayList<>();
		ObservableList<FunctionData> functions = FXCollections.observableList(funcList);

		workspace.setItems(variables);
		// TODO setup functions

		setupBorderPane(pane, selectionBar, simulation, inputBox, workspace, preferences);
		putIntoMaps(tab, selectionBar, simulation, inputBox, workspace, userCommands, variables, functions);
		setupObservers(simulation, inputBox, file, settings, animation, preferences);
		setupCommands(inputBox);

		tab.setContent(pane);
		root.getTabs().add(tab);
	}

	private void setupBorderPane(BorderPane pane, SelectionBar selectionBar, SimulationView simulation,
			InputBox inputBox, WorkspaceView workspace, PreferencesView preferences)

	{
		pane.setTop(selectionBar.display());
		pane.setCenter(simulation.display());
		pane.setBottom(inputBox.display());
		pane.setLeft(workspace.display());
		pane.setRight(preferences.display());
	}

	private void putIntoMaps(Tab tab, SelectionBar selectionBar, SimulationView simulation, InputBox inputBox,

			WorkspaceView workspace, SavedCommandsView userCommands, ObservableList<VariableData> variables,
			ObservableList<FunctionData> functions)
	{
		selectionBarMap.put(tab, selectionBar);
		simulationMap.put(tab, simulation);
		inputBoxMap.put(tab, inputBox);
		workspaceMap.put(tab, workspace);
		savedCommandsMap.put(tab, userCommands);

		variableMap.put(tab, variables);
		functionMap.put(tab, functions);
	}

	private void setupObservers(SimulationView simulation, InputBox inputBox, MenuTool file,
			MenuTool settings, ToolButton slider, PreferencesView preferences)
	{
		file.addObservers(simulation);
		file.addObservers(inputBox);
		file.addObservers(this);

		settings.addObservers(simulation);
		settings.addObservers(this);
		
		slider.addObservers(simulation.getTurtle());
	}

	private void setupCommands(InputBox inputBox)
	{
		inputBox.assignOnEnterCommand(e -> executeCommand(e, inputBox));
	}

	private void executeCommand(KeyEvent e, InputBox inputBox)
	{
		if (e.getCode() == KeyCode.ENTER) {
			inputBox.enterAction(e);

			if (inputBox.getCurrentCommand() != null) {
				runCommand(inputBox, inputBox.getCurrentCommand());

			}
			inputBox.appendText("\n" + Double.toString(printValue));
			inputBox.appendPreamble();
		}
		if (e.getCode() == KeyCode.UP) {
			inputBox.upAction(e);
		}
		if (e.getCode() == KeyCode.DOWN) {
			inputBox.downAction(e);
		}
		if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.BACK_SPACE) {
			inputBox.protectPreamble(e);
		}
	}

	private void runCommand(InputBox inputBox, String command)
	{
		InstructionData data = new InstructionData(simulationMap.get(currentTab.get()),
				variableMap.get(currentTab.get()), functionMap.get(currentTab.get()), language.get(currentIndex.get()));
		try {
			Interpreter interpreter = new Interpreter(data);

			printValue = interpreter.parseAndRun(command);
			simulationMap.get(currentTab.get()).step();
			inputBox.updateData(command);
		} catch (SLogoException exception) {
			exception.displayAlert();
		}
	}

	private void openFile(File file)
	{
		try {
			String command = new String(Files.readAllBytes(Paths.get(file.getPath())));

			for (Tab t : root.getTabs()) {
				if (t.isSelected()) {
					runCommand(inputBoxMap.get(t), command);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file");
		} catch (IOException e) {
			Logger.getLogger(SingleLineInputBox.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}