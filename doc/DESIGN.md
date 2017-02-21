# Plan - SLogo

## Authors:
>Maddie Briere 
Jimmy Shackford
Matthew Barbano
Jesse Yue

## Introduction
>This project addresses the creation of Integrated Development Environments, using the simplified “coding” language Logo. In this project, we will be creating a user-friendly coding environment supporting a simple coding language that is used by beginners. Sample commands and expressions are as follows:
>
FORWARD 50
SUBTRACT X Y
PENUP (Returns a boolean)
>
Within this IDE, the user is able to interact with a simulation -- in most instances of this particular program, this simulation is just a single display with a turtle at the center. Commands can control this turtle, moving it forward a certain number of steps, changing its speed, etc. 
>
With time, the user should be able to extend the environment, adding on user-defined commands and adding complexity to an initially simple environment. This means that we must add capabilities extending across a wide spectrum. On one hand, we must make the IDE extremely intuitive for beginners. On the other hand, it must be possible to build upon the basic framework of our program to develop and more complex program. This will require flexibility in the addition of new commands (meaning that commands cannot be hard-coded and must be added almost like entries in a dictionary). It will also require that the system be able to expand and grow WHILE running -- it must be able to incorporate user input to “learn.”
>
The closed elements of this design will be the items “higher on the totem pole.” Abstract superclasses like Actor, Instruction, Simulation, etc. will not be changeable -- adding a new instruction or actor type will not require modification of these superclasses. Rather, these superclasses will be extended to produce new functionalities. For instance, if a new type of actor were to be added to the simulation, we could extend the superclass Actor in this new class to inherit already existing functionality and add on new functionality. This new Actor would be a sibling to the already existing Turtle class. Similarly, adding a new instruction type will not require modification to the Instruction class. Rather, the new Instruction type will be create and extend the Instruction class, allowing for extension without modification of the initial framework. 

## Design Overview

>The general overview of the class structure/hierarchy for this project can be viewed in the attached images HierachyDraft.jpg and FinalDraft.jpg. 
>
In the first drawing, the front-end in this design is composed of visual-related elements such as the ToolBar. Meanwhile, the core of the back-end resides in the Interpreter, and its relationship to the Instruction class. Classes lying on the middle ground between front and back-end are Simulation, Page, Actor, etc. These have both back-end components (in updating) and front-end components (in display). 
>
In the second drawing, our more recent draft, we cement the front-end and back-end delineations, creating SimulationView, PageView, ActorView and LogoController in order to fit with the common Model-View-Controller design.
>
The subclasses/ detailed hierarchies (for Actor, Page, etc.) can be viewed in the attached image HierarchyDraft2.jpg. These show example extensions of abstract superclasses.
>
For our project, the Main class will have an instance of LogoController, which will be the heart of where the program is run. For the frontend, the LogoController will have a Map < Simulation, SimulationView>, where each Simulation has a Map < Actor, ActorView> (since our design will allow for multiple tabs, each with different simulations, and multiple turtles/actors in each simulation). We pair models to views so that we can properly update both accordingly.
>
The Simulation class will contain the details for displaying the graphic with the moving turtle(s), and specifically an InputBox class which is an area for the user to input commands. (Importantly, each Simulation contains its own InputBox since there could be multiple Simulations running at once). The Actor class will contain information about each turtle’s state (coordinates, heading, etc.) and likely be divided into a model and view class (tentative). The Controller will also have a List < Page>. Page will be an abstract class representing further subdivisions of the main window. It will have concrete subclasses CommandHistory (which displays previous commands that will likely be clickable to execute), VariablePage (which displays stored variables likely clickable to execute), and a HelpPage (which displays instructions for using the project). Finally the LogoController has a Toolbar, a class which displays menus (i.e. File, Edit, Settings, etc.) at the top of the main window. These menus will be stored in class Menu.
>
For the backend, LogoController will have an instance of the Interpreter class, which will know how to parse a String representing a command the user input. It will take in this String, and using a resource file with command names, return an Instruction to LogoController. Instruction will be a large inheritance hierarchy, at the bottom of which will be concrete classes representing all possible commands (i.e. Forward, Backward, SetX, etc. ). The abstract Instruction root class will have an abstract method execute() that is called by LogoController, whose implementation is unique to each concrete command class. Also, since Instruction will need to make changes to the simulation display, a new data structure, InstructionData, that contains List<Actor> and any other data that could potentially be modified will be will be passed to execute().
>
With respect to the four APIs, the following is planned:
Frontend External (used by the backend) - When a user inputs a command/instruction, the backend will need to perform several actions on the frontend. The backend will need to call a frontend method that gets the String the user input as a command into InputBox. To load the necessary information about simulation state into InstructionData, the backend will need to get values about Actor state, heading, etc. from the Simulation (frontend). Finally, when Instruction’s execute() is called (in the backend), it will need to call Simulation methods (in the frontend) to make changes to the Simulation.
>
Backend External (used by the frontend) - Whenever a simulation updates, it will need to ask the backend side of Actor (possibly ActorModel) about the current state of all Actors (i.e. coordinates, headings, etc.) and update the graphic appropriately. Also, the action of the frontend calling Instruction’s execute() (backend) is an example of this interface. Finally, when subclasses of Page are updated (i.e. VariablePage and CommandHistory), they will need to call backend methods to get the necessary information.
>
Frontend Internal (used by future programmers) - Several components will be open to extension by future programmers on the frontend. One can add additional menus to Toolbar by adding additional Menu subclasses and instantiating them in Toolbar. One can add a new Page type by extending the Page hierarchy with additional classes. Also, one can add more Actors to a Simulation, more Simulations, or additional types of Actors beyond Turtles by modifying the Simulation class, modifying the Controller class, or extending Actor with a new subclass, respectively.
>
Backend Internal (used by future programmers) - The Instruction hierarchy will likely need to be extended with additional commands, so subclasses can easily be added in current categories (TurtleCommand, TurtleQuery, etc.), which will hopefully not need any additional siblings in the hierarchy. Also, additional fields can be added to InstructionData if new Instructions need to operate on them (i.e. background color, turtle image filename, etc.). 

## User Interface

>The GUI will be very simple with 4 main components. There will be a menu bar at the top which will have the controls for all the settings and such, a workspace on the left which holds all the currently used variables, a display box at the top right which shows the turtle and the lines that it has made, and finally a console at the bottom right for user input.
>
For the menu bar, the user will interact with it by hovering over each of the options (File, Edit, Tab Properties, Actor Properties, Settings, and Help) to open a drop down menu where the user can make a more specific selection. The workspace will allow users to delete a variable by right clicking then clicking delete. The display will have tabs that the user can switch between to run multiple simulations in the same window. The console will take key inputs for instructions and users can click on a previous command to run it again.
>
All errors will be handled the same way, a pop up box will alert the user with a message describing the error. They will have to acknowledge it and close it before they return to the program again. If the user inputs an instruction that we do not recognize we will alert them that they have inputted an invalid command. If an instruction leads to the turtle going out of the bounds of the display, we will tell them that.

## API Details
###Frontend External (used by the backend)
>**What features from assignment specification?**
Entering commands and showing errors.
>
**What resources to use?**
The main resources used are JavaFx nodes to create UI components and Event Handlers to allow them to interact with the back end.
>
**How intended to be used?**
There will be a getInput method used by the Interpreter to get the text from the console. There will also be a showError method used by Interpreter so that whenever there is some invalid instruction it can be thrown up to the front end and displayed in an alert.
>
**How can be extended?**
Users can extend this through creating new subclasses of PageView that could take in inputs in new ways that they think up.
>
**Justify all classes within this API**
The classes within this API are:
>
A subclass of PageView called InputBox contains all the graphical elements of the console. So when the user types into the console, InputBox will have access to that text. This will be the class with the getInput method to return the inputted text.
>
ErrorHandler will be the class that creates the Alert that displays the error to the user. It will have the showError method to display the error. This could then be called within the Interpreter class whenever needed.
>
**Packages, exceptions thrown**
Packages: view, errors
Exceptions: InvalidInputException


###Backend External (used by the frontend) 
>**What features from assignment specification?**
The external backend API is composed primarily of functionality relating to interpretation. The front-end will take in a String from the user, using the InputBox, which must then be parsed. The front-end, however, will have no knowledge of syntax or how to “read” this instruction. Hence, it must pass this information to the Interpreter class. The Interpreter class will then parse the String, convert it to an Instruction of the correct type and return this Instruction to the front-end (LogoController). The front-end will then simply be able to call execute() on the instruction in order  to accomplish the intended result from the input. Hence, this portion will allow for an input like *forward 10* to be translated into actual functionality -- the backend will create a TurtleCommand Instruction of type Forward, with the instance variable 10, to be executed by the front. 
>
The other component of the external backend API is the set of models used to hold information in the program. A set of Actors will hold information about actor location, direction, etc. for use in front-end commands (including display). A set of Simulations will hold information about background color, current settings, etc. (for use in front-end commands). Finally, a set of Pages will hold information relevant to the page type, and return this information in a useful manner for the front-end. For instance, the command history page will store all executed commands and have a formatted toString(-like) method to return these commands for use in window display.
>
**What resources to use?**
The primary resource used will be the “dictionary” needed for interpretation. Currently, this is implemented using the resource files held in the project folder (e.g., English.properties) -- these resource files will be used to match up commands to actual implementation in the backend. By adding to this file and likely adding more resource files, we will be able to give the Interpreter new capabilities without having to modify the class at all.
>
The models will be independent of other components. They should be fundamentally modular components, holding information and being capable of updates, but not relying upon views in the front-end or other elements.
>
**How intended to be used?**
This API is meant to be used to translate user input from the front-end into actual implementation in the back-end, and then back into storable information held between the front and back end (for example: a list of commands and variables to be displayed on a sidebar). The API is meant to be used as an Interpreter (as the name implies) -- following the Interpreter design pattern. It takes in input, translates that according to its own local knowledge, and returns an executable object for use. 
>
The model classes are meant to be used solely for data-storage and back-end functionality -- they shouldn’t be taking on any front-end work. They shouldn’t know how to display components related to their structure, nor should they have any function other than returning information and changing state. The model classes can be used to update view class (corresponding to each model type) and change the actual display (just indirectly). 
>
**How can be extended?**
The main way to alter the interpretation element of the project is actually not through code, but through resource files. Most of the implementation will be occurring through resource files. For instance, we will have syntax in a resource file (e.g., [ = list, : = variable) -- adding a new type of syntax will involve adding that new type of delineation to the resource file. 
>
The models are meant to be extended from their initial abstract classes. Page, Simulation and Actor will all be abstract superclasses open to extension. Hence, adding a new Actor like Monkey would require the creation of a new class extending Actor and implementing an update method. This initial class Actor, however, would be closed to modification.
>
**Justify all classes within this API**
The classes within this API are as follows:
>
*Abstract superclass Actor, implements Model (including subclasses like Turtle)*
This is part of the backend external API because it allows for communication of actor-related information stored in the back-end (such as locate, speed, etc.) to be translated to front-end display.
>
*Concrete class Simulation, implements Model (holds actors)*
This is a part of the backend external API because it allows for communication of simulation-related information stored in the back-end (current settings, background speed, etc.) to be retrieved by the front-end for use and eventual display.
Abstract superclass Page, implements Model (including subclasses like HelpPage)
This is a part of the backend external API because it allows for communication of page-related information stored in the back-end (current variables, command history, etc.) to be retrieved by the front-end for use and eventual display.
>
*Concrete class Interpreter*
This is part of the backend external API because it allows the front-end to call upon the back-end for a service (interpretation) and receive and product back (an instruction, the result of that interpretation). This public service is therefore encompassed in the API.
>
**Packages, exceptions thrown**
Packages: model, interpreter
Exception: SyntaxException (Unbalanced, Unknown, etc.), CommandException (Unknown, Invalid, etc.)


###Frontend Internal (used by future programmers)
>The Frontend Internal API is implemented through the ToolBar, Tool, Page, and PageView abstract classes. It is also implemented through the Model and View interfaces.
>>
**What features from assignment specification?**
By allowing the ToolBar class to contain a list of Tools, a new Tool can be constructed to set a background color for the turtle's display area, set an image to use for the turtle, and set a color to use for the pen. A future programmer can also add new Tools to a Toolbar to allow the user to configure different settings, such as language. The Page class would allow a programmer to add a new Page to display commands previously run in the environment, and possibly make them clickable to execute. A programmer could also add a new Page to display variables and possibly make them clickable to edit. This same approach could be used to see user-defined commands currently available in the environment. Finally, a programmer could make a HelpPage class which extends Page, which has a constructor which takes in an HTML string, in order to make an HTML help page.
>
**What resources to use?**
The primary resources are JavaFX classes.
>
**How intended to be used?**
ToolBar/Tools are designed so that programmers can add different ways to update the appearance of the display. Page/PageView are designed so that programmers can make new displays for different parts of a simulation’s properties. The Model/View interfaces are designed so that a programmer can add different types of objects to be displayed.
>
**How can be extended?**
You can add different widgets to the ToolBar by calling ToolBarInstance.add(Widget), and have the widget dynamically update the project through a listener. Adding different Pages is done by implementing the Model interface, and adding different PageViews is done by implementing the View interface.
>
**Justify all classes within this API**
Main: Future programmer can edit it to change the display or simulations
RunLogo: Future programmer can edit it to change initial setup/configurations.
ToolBar: Future programmer can add tools to the ToolBar. 
Tool: Future programmer can make different types of Tools to dynamically update the display.
Page: Future programmer can make a different type of Page to display information about the current simulations.
PageView: Future programmer can make different views for a Page to display simulation information.
Model: Future programmer can make a new model to describe how an object is updated in the backend.
View: Future programmer can make a new view to describe how an object’s display is updated in the frontend.


##Backend Internal (used by future programmers)
>**What features from assignment specification?**
The Backend Internal API will be the public methods used by future programmers to extend backend functionality for this project. It will implement all of the commands specified by the SLogo language (Turtle Commands, Turtle Queries, Math, etc.) and allow for addition of new commands by future programmers.
>
**What resources to use?**
The Instruction subclasses/inheritance hierarchy will need to use a resource file containing the names of all commands corresponding to Instruction concrete subclasses.
>
**How intended to be used?**
In the Instruction superclass, the most functional method will be execute(). This is an abstract method in Instruction implemented lower in the hierarchy, most likely in the lowest concrete subclasses (but possibly higher depending on the Instruction). The method modifies the environment appropriately based on the Instruction (for example, moving the turtle forward 50 units for “Forward 50”). Also, the get/setInstructionData() method returns/modifies the InstructionData structure, and getText() returns the original user input String representing the command. Subclasses of Instruction also have public API methods (see below). InstructionData’s main purpose is to store data associated with the current environment, not to take actions.
>
**How can be extended?**
For InstructionData, future programmers could add fields/getters/setters associated with new data in the environment. This could be related to the pen width, turtle image size, sound effects, etc. Also, future programmers could extend Instruction if new features relating to how they are displayed on the frontend are needed (for example, hiding the arguments or showing only an abbreviation would require a getAbbreviation()) method).
>
**Justify all classes within this API**
The InstructionData class is necessary to store information related to the current state of the simulation - a List< Actor> (which in turn store coordinates, headings, etc.), the background color, the turtle image, and more. The Instruction class and all of its subclasses are needed to associate each possible input command with code to execute its function (for example, moving a turtle forward by 50 units). Since the implementation of this execute() method differs by command, a unique subclass is needed for each command.
>
**Packages, exceptions thrown**
All of these classes (InstructionData, Instruction hierarchy) will be in the “Instruction” package. In the InstructionData class, if a TurtleImage is not an existing file, a FileNotFoundException should be thrown. If an error related to the way the turtle moves in the simulation arises (such as the turtle going off the edge), another exception unique the this project should be thrown.
>
**Packages**
Main, Tool, View

## API Example Code
>The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.
In Simulation:
return inputBox.getUserInput();
>
In LogoController’s update(): 
String input = simulation.getUserInput();
Interpreter interpreter = new Interpreter();
interpreter.parseAndRun(input);
>
In Interpreter’s parseAndRun():
//Parsing code here
Instruction instruction = new Forward(steps);  //steps = 50
instruction.setInstructionData(instructionData);
instruction.execute();
>
2 Uses Cases per person in their respective areas of the project: 
>
***Maddie:***
>
**Case 1: The String ‘fd 50’, collected by the front-end, is passed to the back-end for interpretation and returned as a runnable command**
The LogoController calls interpreter.parseAndRun(String s, InstructionData d)
The method uses the syntax resource file to classify the String by the first word. Here, it is ‘fd’ so the String is classified as a TurtleCommand. The command has an instance variable of 50, so this is also stored in the construction of the Instruction. This creation is outsourced to a factory that returns the correct Instruction.
The Instruction now has knowledge of the Turtle it is changing (garnered from InstructionData) and what type of change it is making (defined by execute in the Instruction class)
This instruction is returned to the front-end, which calls execute on the command (if desired) to implement the change on the back-end and set the number of steps for the desired turtle to 50. 
Animation on the front-end will continue and the turtle will move 50 steps (according to this new command).
>
**Case 2: The user sets one of their pages to CommandHistory**
If a CommandHistory was not already existing in the Map of Pages to PageViews, then a new CommandHistory will be instantiated using current information (e.g., starting from the current point and keeping track from then on out). 
The CommandHistory object will be added to the current Map of Pages and updated on the visuals
Future commands will be added to this object upon update
>
***Jimmy:***
**Case 1: The user starts the project/SLogo IDE**
The Main class calls RunLogo’s start method
RunLogo’s start method initializes the LogoController with the starting configuration. This method initializes all of the Simulations and Turtle models/views that are going to be displayed. These are hard-coded within the RunLogo class itself.
Within LogoController, call the display method for all of the SimulationViews and PageViews that are stored.
>
**Case 2: The user selects a new language through a Tool on the ToolBar**
When the language is selected, a mouseIsPressedListener within the Tool class is called.
This returns the newly selected language to the LogoController, which updates its interpreter based on the language’s resource file.
>
***Jesse:***
**Case 1: The user inputs some text instruction**
Within LogoController an event handler detects that the enter key has been pressed from within an InputBox.
LogoController then calls the getInput method to get the input text.
LogoController sends the text to the Interpreter which parses the text.
Interpreter sends the instruction to Instruction which then executes the command. 
>
**Case 2: The user inputs some unknown instruction**
Same as Case one for steps 1-3.
Interpreter realizes that the parsed text doesn’t match any of the instructions
Interpreter creates an instance of ErrorHandler and uses the showError method to alert the user that the input was invalid
>
***Matthew: (just the InstructionData and Instruction hierarchy classes)***
**Case 1: The user enters “LEFT 30”, and the turtle turns counterclockwise by 30 degrees**
In a class outside of InstructionData and Instruction, the line Instruction instruction = new Left(degrees) (where degrees = 30) would instantiate the Left object. Also, instruction.setInstructionData(instructionData) would give Left all the appropriate data about the state of the simulation. Most importantly, instruction.execute() would be called, in which the line actor.setHeading(actor.getHeading() + 30) turns the turtle counterclockwise.
>
**Case 2: The user enters “REPEAT 5 [FORWARD 10]” and the turtle moves forward by 50 steps**
First, the line Instruction instruction = new Repeat(iterations) (in which iterations = 5) would instantiate the Repeat object and instruction.setInstructionData(instructionData) would give the object appropriate simulation-state data. Then, instruction.execute() would create a for loop iterating “iterations” number of times. Each iteration, the loop would instantiate a new local Forward Instruction (with parameter 10), set its InstructionData object to the same instructionData object in the Repeat object (innerInstruction.setInstructionData(instructionData)), and call innerInstruction.execute(), which would move the turtle forward 10 steps, 5 times (for a total of 50 steps).

## Design Considerations
>Issues which need to be addressed or resolved before attempting to devise a complete design solution:
	- For the backend, our group isn’t completely sure how the model-view architecture will be implemented for an actor. Currently, we are thinking about exploring the MVC design pattern and seeing how we could implement that. The pros of this are that it is an actual design pattern and thus must provide a good way of implementing this model-view problem. The cons are that we would possibly need several different classes in order to add a new actor to the project. Earlier we considered having actor be an abstract class that describes the model-aspect of the actor, and requiring actor to have a nested view class that describes how the actor is displayed. The pros of this are that everything needed for an actor class could be inside of one class. However, it would also require a new programmer to know that they need a nested class in order for it to work.
	- For the input box, we had a hard time deciding whether it should be a Page or its own structure. Keeping it as a Page might reduce repeated code, but it wouldn’t allow for multiple input boxes if we wanted to have multiple simulations run at once. We ended up deciding that a Simulation should be required to have an input box because then we would be able to have multiple simulations run at once and have a different input box for each of them. However, this could result in repeated code with the Page class.
	- It was a difficult decision to decide how the front-end would interact with the backend. We initially considered making an ActorFactory class that is a Singleton and keeps track of every actor that has been created and is being displayed. Then, whenever a command is called by a user, every actor that has been created through the factory class will be updated. The pros of this are that it would be easy to update the actors and keep track of which ones have been created. The cons are that we would be required to use a Singleton, which we were told not to do, and the actors would essentially be global variables, which also isn’t good design. We decided to create a middle-class called “Controller” which is between the Front-End and Back-End and says how they interact. The Controller is initialized in the main class and it has an interpreter, a list of simulations, and a toolbox. Then, whenever the input box in a simulation is updated, the Controller class can get the simulation’s list of actors and update them based on the input and its interpreter. The pros of this are that we don’t need to make a Singleton class in order for it to work, and the Controller class will be the only point of access between the front-end and back-end, reducing any unnecessary dependencies between them. The cons are that there are still some points of ambiguity between backend and frontend with this implementation, and we would need to find way to make the frontend/backend delineation more clear.
	- When considering multiple concurrent simulations, we had a hard time deciding how to call a command only on the current simulation being displayed without giving too much information. One method of implementing this is passing the entire Controller object to the backend whenever a command is called, and then the backend can get the current simulation from the controller and call the command on all of its actors. The pros of this are that there is a simple flow of data. The cons are that the back-end would have knowledge of more information than necessary (it would see the entire Controller class, which contains every simulation including the current one). The solution that we came up with was creating an InstructionData data type, which only keeps track of the Controller’s current simulation and all of its actors. Then, whenever a new command is called, a new instance of this data type is created and passed to the backend. The pros of this are that the backend only sees everything that it needs to see and nothing more. The cons are that the flow of instructions gets a little less understandable.
-  Include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design.


## Team Responsibilities

>The current division of labor places Maddie and Matt on the back-end and Jimmy and Jesse on the front-end. These subgroups will be working together and delegating tasks accordingly, and will meet periodically to incorporate front and back end changes. The class responsibilities will be as follows:
>
Main: Jimmy, Jesse
RunLogo: Jimmy
LogoController: Maddie/ Jimmy
Model interface: Jimmy
View interface: Jimmy
ToolBar: Jimmy
Menu abstract classes: Jesse
Menu classes: Jesse
Interpreter: Maddie
Page abstract class: Jimmy
Page/PageView classes: Jimmy
Simulation/SimulationView: Jesse
InputBox: Jimmy
Actor abstract class: Maddie
Actor classes (turtle, etc.): Maddie
ActorView classes: Jimmy
InstructionData: Matthew
Instruction abstract class: Matthew
Instruction classes: Matthew, Maddie
