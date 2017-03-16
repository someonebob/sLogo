package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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

import javax.imageio.ImageIO;

import exceptions.SLogoException;
import instruction.InstructionData;
import interpreter.Interpreter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tool.ActorButtons;
import tool.AnimationControlToolButtons;
import tool.ComboBar;
import tool.FileMenuTool;
import tool.FileMenuTool.NewButton;
import tool.FileMenuTool.OpenButton;
import tool.FileMenuTool.SaveButton;
import tool.FileMenuTool.SaveImageButton;
import tool.HelpMenuTool;
import tool.MenuTool;
import tool.SelectionBar;
import tool.SettingsMenuTool;
import tool.SettingsMenuTool.LanguageButton;
import tool.ToolButton;
import user_structures.FunctionData;
import user_structures.VariableData;
import view.AnimatedSimulationView;
import view.InputBox;
import view.MultiLineInputBox;
import view.PreferencesView;
import view.SavedStructuresView;
import view.SimulationView;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class Controller implements Observer
{
	public static final KeyCombination SHIFT_ENTER = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.SHIFT_DOWN);
	private TabPane root;
	private ObjectProperty<Tab> currentTab;
	private Map<Tab, SelectionBar> selectionBarMap;
	private Map<Tab, SimulationView> simulationMap;
	private Map<Tab, PreferencesView> preferencesMap;
	private Map<Tab, InputBox> inputBoxMap;
	private Map<Tab, SavedStructuresView> workspaceMap;

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
		} else if (o instanceof OpenButton) {
			openFile((File) arg);
		} else if (o instanceof SaveButton) {
			saveFile((File) arg);
		} else if (o instanceof SaveImageButton) {
			saveImage((File) arg);
		} else if (o instanceof LanguageButton) {
			language.set(currentIndex.get(), (String) arg);
		}

	}

	private void setupItems()
	{
		root = new TabPane();
		currentTab = new SimpleObjectProperty<>();
		selectionBarMap = new HashMap<>();
		simulationMap = new HashMap<>();
		preferencesMap = new HashMap<>();
		inputBoxMap = new HashMap<>();
		workspaceMap = new HashMap<>();

		variableMap = new HashMap<>();
		functionMap = new HashMap<>();

	}

	private void newTab()
	{
		Tab tab = new Tab();
		root.getSelectionModel().select(tab);
		language.add(defaults.language());
		tab.setText("untitled.logo");
		BorderPane pane = new BorderPane();
		SimulationView simulation = new AnimatedSimulationView(defaults);
		InputBox inputBox = new MultiLineInputBox();
		inputBox.setFocus();
		SavedStructuresView workspace = new SavedStructuresView();
		PreferencesView preferences = new PreferencesView(simulation.getTurtle(), simulation);

		SelectionBar selectionBar = new ComboBar();
		MenuTool file = new FileMenuTool(stage);
		MenuTool settings = new SettingsMenuTool(stage);
		MenuTool help = new HelpMenuTool(stage);
		ToolButton animation = new AnimationControlToolButtons();
		ToolButton actorControl = new ActorButtons();
		selectionBar.addAllTools(file, settings, help, animation, actorControl);

		List<VariableData> varList = new ArrayList<>();
		ObservableList<VariableData> variables = FXCollections.observableList(varList);
		List<FunctionData> funcList = new ArrayList<>();
		ObservableList<FunctionData> functions = FXCollections.observableList(funcList);

		workspace.setVariables(variables);
		workspace.setFunctions(functions);

		setupBorderPane(pane, selectionBar, simulation, inputBox, workspace, preferences);
		putIntoMaps(tab, selectionBar, simulation, inputBox, workspace, variables, preferences, functions);
		setupObservers(simulation, inputBox, file, settings, animation, actorControl, preferences);
		setupCommands(inputBox);

		tab.setContent(pane);
		root.getTabs().add(tab);
	}

	private void setupBorderPane(BorderPane pane, SelectionBar selectionBar, SimulationView simulation,
			InputBox inputBox, SavedStructuresView workspace, PreferencesView preferences)

	{
		pane.setTop(selectionBar.display());
		pane.setCenter(simulation.display());
		pane.setBottom(inputBox.display());
		pane.setLeft(workspace.display());
		pane.setRight(preferences.display());
	}

	private void putIntoMaps(Tab tab, SelectionBar selectionBar, SimulationView simulation, InputBox inputBox,

			SavedStructuresView workspace, ObservableList<VariableData> variables, PreferencesView preferences,
			ObservableList<FunctionData> functions)
	{
		selectionBarMap.put(tab, selectionBar);
		simulationMap.put(tab, simulation);
		preferencesMap.put(tab, preferences);
		inputBoxMap.put(tab, inputBox);
		workspaceMap.put(tab, workspace);

		variableMap.put(tab, variables);
		functionMap.put(tab, functions);
	}

	private void setupObservers(SimulationView simulation, InputBox inputBox, MenuTool file, MenuTool settings,
			ToolButton animation, ToolButton actorControl, PreferencesView preferences)
	{
		file.addObservers(simulation);
		file.addObservers(this);

		settings.addObservers(simulation);
		settings.addObservers(this);

		// TODO: add observers for toolbuttons
		animation.addObservers(simulation.getTurtle());
		animation.addObservers(simulation.getTurtle().getPen());
		actorControl.addObservers(simulation);

	}

	private void setupCommands(InputBox inputBox)
	{
		inputBox.assignOnEnterCommand(e -> executeCommand(e, inputBox));
	}

	private void executeCommand(KeyEvent e, InputBox inputBox)
	{
		if (SHIFT_ENTER.match(e)) {
			inputBox.shiftEnterAction(e);
		} else if (e.getCode() == KeyCode.ENTER) {
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
			preferencesMap.get(currentTab.get()).step();
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
					System.out.println(inputBoxMap.get(t));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file");
		} catch (IOException e) {
			Logger.getLogger(MultiLineInputBox.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void saveFile(File file)
	{
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(inputBoxMap.get(currentTab.get()).returnAllText());
			fw.close();
			currentTab.get().setText(file.getName());
		} catch (IOException e) {
			Logger.getLogger(MultiLineInputBox.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void saveImage(File file)
	{
		WritableImage image = simulationMap.get(currentTab.get()).display().snapshot(new SnapshotParameters(), null);

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
			Logger.getLogger(SimulationView.class.getName()).log(Level.SEVERE, null, e);

		}
	}

}