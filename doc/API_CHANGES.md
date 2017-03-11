## Changes to the API

## Front-End 

## Changes to the API

1. **Change in model design:** The only class that still has a model *and* view is the Actor class. All of the other classes were minimized for simplicity and ease of design. While the Pages, for instance, don't require all that much updating/functionality, the Actors have to keep track of parallel information (both backend data and frontend information about display).

2. **Tool Interface:** The tool class was refactored into a tool interface to be all encompassing for menus and control items. The original tool class was renamed MenuTool and implements the tool interface. There is now a new abstract class ToolButton that is a sibling of MenuTool that also implements the Tool interface. The AbstractButton was renamed AbstractMenuItem and a new AbstractButton was made for the ToolButtons. Subclasses of ToolButton have inner classes that extend AbstractButton to group control items related to the same feature.

3. **SelectionBar Interface:** The selectionBar was refactored into an interface so that we can have multiple selectionBars. The original bar made use of a JavaFx MenuBar and was renamed to SelectionMenuBar. The new bar, SelectionToolBar uses JavaFx’s ToolBar class and makes use of the new ToolButtons. There is also a new ComboBar which combines both the SelectionMenuBar and SelectionToolBar into one, 2 row bar.

## New API Features

1. **Observable objects:** View now implements observer so that the views can be updated when changes occur. We created a new class AbstractButton which all of our MenuItems will extend. These MenuItems will be inner classes within the tool that they will be under. The AbstractButton just gives united functionality for calling observers since it extends Observable. This way actions in the MenuBar can affect things in View if View observes it.

2. **Observer Controller:** Controller also implements Observer so it now has the update method to go with it. This is to allow it to observe the AbstractButtons.

3. **Defaults:** A new Defaults class was created that stores the Default values found in the XML file. A XMLParser class was created to parse the default.xml file and passes the values into the Defaults class. There is also a XMLEditor class that allows changes to be made in the XML file based on user selection.

4. **Editors/Choosers:** These are classes in the tool package that are pop up windows to make some sort of edit or choice. For example the VariableEditor creates a pop up that allows the user to edit variables in the workspace. They have a method that returns the new changed item/selected item.
5. **Properties:** Property classes were added to define properties of a turtle that could be defined through xml or graphically. The property classes define a graphical dynamic updater which can be displayed to update the property. Moreover, they have commands to specify how they can be created through xml.

6. **PreferencesView**: Displays graphical updaters which display the properties of a turtle. This is important because not only can the user write code to move the turtle, but they can also do it through this sidebar.

7. **SimulationView**: Display the running turtle and its pen. Supports the addition of multiple turtles.

## Back-End

## Changes to the API

1. **Interpreter:** The interpreter didn’t change all that much in terms of the API. The only modification in this class was the removal of the InstructionData object passed to the parseAndRun method. This object is now passed to the constructor. This object is used to assign the InstructionData object for the Interpreter object, as well as the InstructionClassifier object (using the language accessed via the InstructionData object). These objects also have public getters and setters (four new public methods).

2. **Instruction:** The Instruction class underwent some minor changes as well. An important modification was changing the return type of the execute method from void to double. Previously, we had thought that the execute method would not need to do anything other than make modifications and then return control to the calling method. We later realized that the execution method would also have to return a double representing the return value of the instruction execution (for the purposes of layering instructions). We also removed some of the public methods (e.g., getText(), getInstructionData(), setInstructionData()) that were not necessary (making them protected instead). Finally, to implement commands with multiple turtles, the public execute() method was renamed executeAllToldTurtles(), and the original abstract execute() was changed to protected since it was now only a helper called by executeAllToldTurtles().

3. **Instruction Data** Several changes were made to the InstructionData class. Note that the getActorsList() was unchanged (and called quite a bit). The getBackgroundColor() and setBackgroundColor() methods were omitted, since the frontend changed the design to incorporate a ColorProperty object, which was directly accessed. A similar omission was decided on for getTurtleImage() and setTurtleImage() due to the creation of the ImageProperty class. The setActorList() method was removed since it was never used (only the contents of this List were modified via getActorsList()). 

4. **InstructionNode:** The API for InstructionNode was also changed quite a bit. When we initially wrote our design, we didn’t have any clue how we were going to build our instruction tree, what we would need, etc. Hence, the only public method we had was getText(). We added several new methods to accommodate interpretation in the back-end. The InstructionNode now has a classification (for instance, GroupStart, ListStart), a command (for instance, ‘(‘ ‘[‘) and a list of InstructionNodes, its children, to keep track of the information it needs to create and run a command. Finally, we added a run value variable to keep track of whether the node has been evaluated yet. These new variables translate to matching getters and setters.

5. **CommandDefinition and VariableDefinition** Although these two interfaces/classes were planned, they were ultimately removed and replaced by a Variable Instruction class (a much more streamlined approach for parsing).

## New API Features

1. **New classes for tree-building:** Several new classes with public methods were added to the back-end for the purposes of tree-building (InstructionNode trees) for interpretation. The process turned out to be much more work than expected and required several helper classes (such as TreeBuilder) with which to work. These had to have public methods for the purposes of communication between the interpreter and the other classes in the package. These classes were added to split up functionality and avoid having the Interpreter class become one monstrous class of code. 

2. **New util classes: ** Several new util classes were added for use in tree-construction, tree-execution, instruction-processing, etc. The new classes are as follows:

**InstructionSplitter:** Holds a variety of text-processing methods used for converting instructions to nodes for tree-construction. The most notable public methods are getInstructions (which parses from a String into a List of InstructionNodes) and getInstructionStrings (which parses a String in words representing individual commands). This class also holds a public removeFirstItem method which removes the first command from a String (used in tree construction). This class was necessary in order to make the String processing independent of the actual tree-construction and instruction execution.

**InstructionCleaner:** “Cleans” an input so that the interpreter is more likely to be able to parse it. For instance, it goes through and sweeps out any comments so that the rest of the code can run. This addition was necessary for user-friendliness.

**InstructionClassifier:** This is *the* reflection class. It reads in from the given language file (as well as workspace information from the InstructionData object) and translates a String representing a command into its corresponding Instruction using reflection. This class is also used throughout the tree-building process to classify the nodes under a larger category for comparison. This allowed us to check if, for instance, ‘fd’ and ‘forward’ represented the same classification of instruction (even though they have different commands). This class was necessary to add flexibility to the program (allowing for new Instruction types to be added easily) and avoid hard-coding in Interpreter.

**ArgumentReaderUtil:** This class reads in from the NumArgs file and assigns any given String (with a corresponding InstructionClassifier and InstructionData) to the number of arguments it should take. Without this class, TreeBuilder would have to take on this specialized functionality (which is undesirable). This class allows us to avoid hard-coding the number of arguments for each command and treat instructions more generically when building the tree. 

**GroupReaderUtil:** This class is used to read in from the corresponding Groupings properties file what type of grouping should be used for any given head command. This was necessary to avoid hard-coding different group types.

**ResourceToListUtil:** This class is used to read information from a resource file into a list for searching and matching. This was necessary to avoid repetitive code across the classes constructing information from resource files (e.g., ArgumentReader, InstructionClassifier).

**WorkspaceUpdaterUtil:** This fairly simple class allows for the addition of a generic item to a List of generics without duplication. This class was made to accommodate the fact that we chose to have an ObservableList of VariableDatas and of FunctionDatas, but still needed the non-repetitive characteristics of a Set. Changing data structures so late in the design would have too radically altered our API. 

3. **Variables and Functions:** New data structures, called VariableData and FunctionData, were created to incorporate the storage of variables and functions in a workspace. These data structures are Observable, so that changes to them can be displayed in the workspace. These structures share a common parent, StructureData, which is comparable (so that we can compare Variables and Functions when changing the workspace) and has knowledge of its name (again, an Observable property). This super-class gives the public methods setName(String value), getName(), nameProperty(), equals(Object o) and compareTo(StructureData o). These are generic and used across both of the mentioned data structures. This consolidation of common properties allows us to easily change the way features in our workspace are compared and defined.

4. **BuilderUtil:** A massive new addition to the API is the interpreter.builders package. A new abstract class called BuilderUtil was defined for use in specialized node processing. Earlier in the game, we were hard-coding the parsing procedures for certain types of commands (e.g., user-defined commands). With the addition of the BuilderUtil abstract class, we were able to make “pre-processing classes” that modify “special text” in order to make these pieces of text processable by the parser. This allows for the addition of the new instruction types with unique processing strategies without modification to the TreeBuilder class. Hence, it allows us to keep TreeBuilder closed for change. The two most important public methods in BuilderUtil are:

A construction method for modification of text according to this specific processor (abstract String construct())

A way to add a child to the current node (with a pre-defined run-value) in order to save specialized data (void addChild(String value))

5. **BuilderUtilFactory:** Yet another class using reflection. This class has a method for the construction of a BuilderUtil object via reflection called: BuilderUtil make(List<InstructionNode> nodes, InstructionNode head, String current, InstructionData data). This method will try to make a BuilderUtil class for the instruction type, but will return null if it cannot (signalling to the tree-builder to use conventional methods for construction). This means that we can continue to add new types of BuilderUtil classes for each instruction type without having to change any of the foundational code.

6. **Subclasses of BuilderUtil:** The following classes are subclasses of the BuilderUtil class and implement the construct method for their respective commands:

*GroupStartUtil* gives the GroupStart node the information it needs to parse when it runs in a child node.

*ListStartUtil* gives the ListStart node the information it needs for when it *is parsed* so that it doesn’t execute right away.

*MakeUserInstructionUtil* allows for the re-definition of a user instruction.


7. **Instruction Data** Several methods were added for any unanticipated functionalities. 

The getBackgroundColorProperty() was added to replace the simple color getters and setters when the ColorProperty design pattern was implemented.
Since the purpose of InstructionData is to avoid accessing frontend classes like SimulationView directly, several methods to access only the required fields/make the required changes of SimulationView were implemented. These were: newActor(), setToldAndUntellRest(Collection<Integer> toldTurtles), replicateSelfWithNewVariables(List<VariableData> newVariableList), and getSimulationBounds().
A new field was added to InstructionData called activeActor (not to be confused with the “told” field in ActorView) when multiple turtle commands were implemented. Associated getters and setters were added: getActiveActor() and setActiveActorIndex(int newIndex).
To manage functions and variables (which developed considerably since the beginning of this project), several methods were added to InstructionData: getVariables(), getStackVariables(), containsVariable(String variableName), containsFunction(String functionName), getFunctionValue(String functionName), addVariable(VariableData v), and addFunction(FunctionData f).
Two miscellaneous methods, getLanguage() and getActivePenView(), were added. The former is needed by Interpreter to make a new InstructionClassifier with the right language, and the latter is needed by several DisplayCommand Instructions to access the active actor’s PenView.

