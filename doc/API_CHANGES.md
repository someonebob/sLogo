## Changes to the API

## Front-End 

## Changes to the API
1. ** Change in model design: ** The only class that still has a model *and* view is the Actor class. All of the other classes were minimized for simplicity and ease of design. While the Pages, for instance, don't require all that much updating/functionality, the Actors have to keep track of parallel information (both backend data and frontend information about display).

2. ** Tool Interface: ** The tool class was refactored into a tool interface to be all encompassing for menus and control items. The original tool class was renamed MenuTool and implements the tool interface. There is now a new abstract class ToolButton that is a sibling of MenuTool that also implements the Tool interface. The AbstractButton was renamed AbstractMenuItem and a new AbstractButton was made for the ToolButtons. Subclasses of ToolButton have inner classes that extend AbstractButton to group control items related to the same feature.

3. ** SelectionBar Interface: ** The selectionBar was refactored into an interface so that we can have multiple selectionBars. The original bar made use of a JavaFx MenuBar and was renamed to SelectionMenuBar. The new bar, SelectionToolBar uses JavaFx’s ToolBar class and makes use of the new ToolButtons. There is also a new ComboBar which combines both the SelectionMenuBar and SelectionToolBar into one, 2 row bar.

## New API Features

1. ** Observable objects: ** View now implements observer so that the views can be updated when changes occur. We created a new class AbstractButton which all of our MenuItems will extend. These MenuItems will be inner classes within the tool that they will be under. The AbstractButton just gives united functionality for calling observers since it extends Observable. This way actions in the MenuBar can affect things in View if View observes it.

2. ** Observer Controller: ** Controller also implements Observer so it now has the update method to go with it. This is to allow it to observe the AbstractButtons.

3. ** Defaults: ** A new Defaults class was created that stores the Default values found in the XML file. A XMLParser class was created to parse the default.xml file and passes the values into the Defaults class. There is also a XMLEditor class that allows changes to be made in the XML file based on user selection.

4. ** Choosers: ** A new Chooser interface was created that will be implemented by classes made for displaying options. For example the DefaultChooser class lets the user choose what defaults to use on startup.

## Back-End

##Changes to the API
1. ** Interpreter: ** The interpreter didn’t change all that much in terms of the API. The only modification in this class was the removal of the InstructionData object passed to the parseAndRun method. This object is now passed to the constructor. This object is used to assign the InstructionData object for the Interpreter object, as well as the InstructionClassifier object (using the language accessed via the InstructionData object). These objects also have public getters and setters (four new public methods).

2. ** Instruction: ** The Instruction class underwent some minor changes as well. An important modification was changing the return type of the execute method from void to double. Previously, we had thought that the execute method would not need to do anything other than make modifications and then return control to the calling method. We later realized that the execution method would also have to return a double representing the return value of the instruction execution (for the purposes of layering instructions). We also removed some of the public methods (e.g., getText()) that were not necessary (making them protected instead).

3. **Instruction Data** Several changes were made to the InstructionData class. First, the getActorList() method was renamed as getActors() for brevity. The getBackgroundColor() and setBackgroundColor() methods were omitted, since the frontend changed the design to incorporate a ColorProperty object, which was directly accessed. A similar omission was decided on for getTurtleImage() and setTurtleImage() due to the creation of the ImageProperty class. The setActorList() method was removed since it was never used (only the contents of this List were modified). 


## New API Features
1. **New classes: ** Several new classes with public methods were added to the back-end for the purposes of interpretation. The process turned out to be much more work than expected and required several helper classes (such as InstructionClassifier, TreeBuilder, etc.) with which to work. These had to have public methods for the purposes of communication between the interpreter and the other classes in the package. These classes were added to split up functionality and avoid having the Interpreter class become one monstrous class of code. 

2. **Instruction Data** The getSimualationBounds() method was added for error checking for commands that move the turtle. 
