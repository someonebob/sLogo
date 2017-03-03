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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import exceptions.SLogoException;
import instruction.InstructionData;
import interpreter.Interpreter;
import javafx.beans.property.ObjectProperty;
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
import tool.AbstractButton;
import tool.FileTool;
import tool.FileTool.NewButton;
import tool.FileTool.OpenButton;
import tool.HelpTool;
import tool.SelectionBar;
import tool.SettingsTool;
import tool.SettingsTool.LanguageButton;
import user_structures.FunctionData;
import user_structures.VariableData;
import view.InputBox;
import view.SavedCommandsView;
import view.SimulationView;
import view.WorkspaceView;

public class Controller implements Observer {
	
	//public static final ResourceBundle RESOURCES = ResourceBundle.getBundle("resources/userinterface/default");
	
	private TabPane root;
	private ObjectProperty<Tab> currentTab;
	private Map<Tab, SelectionBar> selectionBarMap;
	private Map<Tab, SimulationView> simulationMap;
	private Map<Tab, InputBox> inputBoxMap;
	private Map<Tab, WorkspaceView> workspaceMap;
	private Map<Tab, SavedCommandsView> savedCommandsMap;
	
	private Map<Tab, ObservableList<VariableData>> variableMap;
	private Map<Tab, ObservableList<FunctionData>> functionMap;
	
	private Interpreter interpreter;
	private Stage stage;
	private String language = "English"; //RESOURCES.getString("language");
	private double printValue;
	
	public Controller(Stage stage){
		this.stage = stage;
		setupItems();
		newTab();
		//currentTab is always the one selected
		currentTab.bind(root.getSelectionModel().selectedItemProperty()); //GODLY
		
		stage.setTitle("SLogo");	
		stage.setScene(new Scene(root));
		stage.setMaximized(true);
		stage.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof NewButton){
			newTab();
		}
		if (o instanceof OpenButton) {
			openFile((File) arg);
		}
		if (o instanceof LanguageButton) {
			language = (String) arg;
		}
	}
	
	
	private void setupItems(){
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
	
	private void newTab(){
		Tab tab = new Tab();
		root.getSelectionModel().select(tab);;
		tab.setText("untitled.logo");
		BorderPane pane = new BorderPane();
		SimulationView simulation = new SimulationView();
		InputBox inputBox = new InputBox();
		inputBox.setFocus();
		WorkspaceView workspace = new WorkspaceView();
		SavedCommandsView userCommands = new SavedCommandsView();
		
		SelectionBar selectionBar = new SelectionBar();
		FileTool file = new FileTool(stage);
		SettingsTool settings = new SettingsTool(stage);
		HelpTool help = new HelpTool(stage);
		selectionBar.addAllTools(file, settings, help);
		
		List<VariableData> varList = new ArrayList<>();
		ObservableList<VariableData> variables = FXCollections.observableList(varList);
		List<FunctionData> funcList = new ArrayList<>();
		ObservableList<FunctionData> functions = FXCollections.observableList(funcList);
		
		workspace.setItems(variables);
		//TODO setup functions	

		setupBorderPane(pane, selectionBar, simulation, inputBox, workspace, userCommands);
		putIntoMaps(tab, selectionBar, simulation, inputBox, workspace, userCommands, variables, functions);
		setupObservers(simulation, inputBox, file, settings);
		setupCommands(inputBox);
		
		tab.setContent(pane);
		root.getTabs().add(tab);
	}
	
	private void setupBorderPane(BorderPane pane, SelectionBar selectionBar, SimulationView simulation, InputBox inputBox, WorkspaceView workspace, SavedCommandsView userCommands){
		pane.setTop(selectionBar.display());
		pane.setCenter(simulation.display());
		pane.setBottom(inputBox.display());
		pane.setLeft(workspace.display());
		pane.setRight(userCommands.display());
	}
	
	private void putIntoMaps(Tab tab, SelectionBar selectionBar, SimulationView simulation, InputBox inputBox, WorkspaceView workspace, SavedCommandsView userCommands, ObservableList<VariableData> variables, ObservableList<FunctionData> functions){
		selectionBarMap.put(tab, selectionBar);
		simulationMap.put(tab, simulation);
		inputBoxMap.put(tab, inputBox);
		workspaceMap.put(tab, workspace);
		savedCommandsMap.put(tab, userCommands);
		
		variableMap.put(tab, variables);
		functionMap.put(tab, functions);
	}

	private void setupObservers(SimulationView simulation, InputBox inputBox, FileTool file, SettingsTool settings){
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
	
	private void setupCommands(InputBox inputBox){
		inputBox.getConsole().setOnKeyPressed(e -> executeCommand(e, inputBox));
		inputBox.getPrevious().setOnMouseClicked(e -> executeClickedCommand(inputBox));
	}
	
	private void executeCommand(KeyEvent e, InputBox inputBox){
		if (e.getCode() == KeyCode.ENTER) {
			inputBox.enterAction(e);

			if (inputBox.getCurrentCommand() != null) {
				runCommand(inputBox, inputBox.getCurrentCommand());

			}
			inputBox.getConsole().appendText("\n" + Double.toString(printValue));
			inputBox.getConsole().appendText("\n" + inputBox.getPreamble());
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
	
	private void executeClickedCommand(InputBox inputBox){
		inputBox.getConsole().appendText(inputBox.getPrevious().getSelectionModel().getSelectedItem());

	}
	
	private void runCommand(InputBox inputBox, String command){
		InstructionData data = new InstructionData(simulationMap.get(currentTab.get()), variableMap.get(currentTab.get()), functionMap.get(currentTab.get()));
		try {
			interpreter = new Interpreter(data, language);
			printValue = interpreter.parseAndRun(command);
			simulationMap.get(currentTab.get()).step();
			inputBox.updateData(command);
		} catch (SLogoException exception) {
			exception.displayAlert();
		}
	}
	
	private void openFile(File file) {
		try {
			String command = new String(Files.readAllBytes(Paths.get(file.getPath())));
			
			for(Tab t : root.getTabs()){
				if (t.isSelected()){
					runCommand(inputBoxMap.get(t), command);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file");
		} catch (IOException e) {
			Logger.getLogger(InputBox.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
}
