##Provides the high-level design goals of your project

In terms of functionality, we aimed to accomplish the following in our program:

Create an IDE designed to understand the SLogo language with features specific to that language, but accommodating of other language (English, Spanish, etc.) and added syntax.

Implement a way to allow users to write and execute new code, run old commands, and to load saved files of instructions.

Allow the simulation to run, and create a display showing turtles’ locations and pen markings.

Implement features allowing the user to graphically change preferences, such as color, actor image, and frames per second.

Display variables and functions (i.e. value and function body, respectively) graphically, and keep them updated in the back end workspace.

In terms of design, we aimed to accomplish the following in our program:

Avoid STUPID (Singleton, Tight Coupling, Untestability, Premature Optimization, Indescriptive Nature, Duplication) code

Create re-usable and flexible code, capable of being altered with each successive sprint, and general enough to allow for use of certain components in other projects.

Adhere to the open-closed principle -- create foundational code that will be closed to alteration and extend the project via extension of open code.

Utilize newly learned design tools including, but not limited to: reflection, regexs, generics, design patterns, streams, and lambda expressions.



##Explains, in detail, how to add new features to your project

**Add a command**

1. *Define command:* Decide what this command will do and what its name will be (consistency in this is important for reflection). Add a subclass of Instruction in the appropriate “Tier 2” category, implementing its execute() method appropriately. Implement its constructor the same way as in other Instruction classes. Update the following properties files: NumArgs.properties, English.properties, and all other languages besides English.

2. *Add to resource files:* Add the command to the relevant resource files: A) Syntax: So that the instruction can be recognized, B) NumArgs: So that a tree can be built using this instruction, C) JavaSpeak: So that the Instruction factory can find the class by looking up its full address, D) Groupings/Brackets. If the instruction is another type of instruction grouping. 

3. *Create a Instruction subclass:* Have this subclass have the exact same name as in the resource file. Put in the expected constructor (which just calls super) and complete the execute method. The execute method should modify the workspace via the given InstructionData object and return its "run value" (as decided by the creator). 

4. (Optional) *Specialize processing:* Some types of commands, such GroupStart and ListStart, require special processing via BuilderUtil classes. If  you want the information passed to your instruction to be any different than the default (based on children), then a class of the type “_InstructionName_Util” must be added to the builders package, extending the BuilderUtil class. In this class, you will implement the method *construct* which will alter the current instruction/ list of nodes (being processed) accordingly. 

5. *Add a new SLogoException* Add a new exception class extending either SyntaxException or ExecuteException. Add any methods needed for that exception, and create constructors similar to other SLogoException subclasses. Throw the new exception wherever appropriate in the project with constructor parameter as a new error message you add to Exception.properties.

**Add a front-end feature**
A new component to the front end: Create a Node (displayable object) and add it to the tabs within main/Controller.java. For buttons within the top toolbar (File, Settings, Help), create a new class which extends tool/Tool.java. Then, add it to the selectionBar within Controller.java. For buttons within the bottom toolbar (play, pause, etc.), make a class which extends tool/ToolButton.java and add it to the selectionBar within Controller.java. Each tab is comprised of a BorderPane. You can add any displayable element to the BorderPane. For example, the variables, commands, previous commands, simulation, inputbox, and preferences sidebar are added to the BorderPane to display them. You can add different front-end elements to the BorderPane if you want to display them. In order to add dynamic updater front-end elements, you add them to the corresponding property classes and then display these within PreferencesView.java.

##Justifies major design choices, including trade-offs (i.e., pros and cons), made in your project

**Using trees in interpretation:**

The use of trees in the interpreter is a slightly more implementation-based decision, but it impacted how the interpreter component of the program would be designed. For instance, with a tree implementation, we now have the following integral classes:

1. **TreeBuilder:** Parses a String (instruction) into a tree of InstructionNodes holding a list of InstructionNode arguments, a run value, a classification (e.g., ListStart) and a command (e.g., '['). 
2. **TreeExecuter** Takes in a tree of InstructionNodes, build Instructions from the nodes and executes them in the correct order.

These classes play a huge role in the back end parsing. 

Originally, we had thought that we might use a set of Stacks in order to dictate the order of execution of the instructions. This would've been conceptually intuitive in terms of order of observation. We quickly realized, however, that this would be complicated (in code) and might not facilitate special cases. It also likely would've been harder to "chunk" the instructions in this manner -- this would've made the code messy.

Instead, we chose to use InstructionNodes linked together in an instruction tree. These InstructionNodes hold a variety of information (run value, command, classification) that make the actually implementation code less complicated. We then broke down the process into Node creation, Node linking (arguments), and Instruction creation. This breakdown of the problem simplified the code and gave each class a very specific purpose (making debugging and refactoring easier). This was the preferable choice because of how easily it broke the problem down.

**Using reflection and factory design pattern:**

We quickly realized that creating all of our elements using switch statements would not be a good idea. Though conceptually easy, this would require modification with any new option for object types. We needed a way to go from a String command to a physical object in our program, with memory and behavior -- a way that wouldn’t require hard-coding responses to specific object types.

We solved this problem using the factory design pattern -- by creating a variety of factories (for Instructions, BuilderUtils, GroupTypes, etc.), extending an AbstractFactory class, we were able to create specialized object creation utility classes. These classes use reflection to go from a String to an object of the required type. This means that new object types can easily be created without adding code -- the reflection process will simply throw an error (or whatever its coded fail response is) if the object it is trying to create does not exist as a type. 

**Stacks in the VariableData objects**
In our workspace, we have both VariableData and FunctionData objects to keep track of variables and functions in the program.

For a variable, the general design is as follows: a variable will be assigned many different values during its time in the workspace. During a recursive call, a variable may have a different value at any level of the function call. To implement this functionality, I added to the VariableData class a Stack of Doubles as an instance variable. Hence, each variable has a "memory."

This is a neat case of implementation being encapsulated from other parts of the program. Although the variable itself keeps track of a stack of Doubles, when called upon to return a value (e.g., for display in the current variables list), it returns the top value on the stack. Hence, the variable can be used in the exact same way as before, but it has additional functionality for use in the back end.

This design choice has two consequences:

1) **Recursion** is now possible

2) Variables have **scope**

These changes were required in the second sprint -- this fix allowed us to add functionality and robustness to our system.

**Instructions**
InstructionData - (from Matthew’s analysis): InstructionData, on the one hand, prevents Instructions from calling frontend methods they should not access (i.e. no getSimulationView(); instead getters for only select fields in SimulationView). It also unifies the location of where to access frontend objects, paralleling the intent of the Mediator Design Pattern (although it does not follow its format exactly) - to make a strong coupling/dependency (the multiple fields of InstructionData) looser (InstructionData iteself) - also avoiding the "T" in "STUPID" ("Tight Coupling"). On the other hand, the drawback of InstructionData its large quantity of getters and setters, which is not only poor practice, but also made the Backend Internal API change considerably (since we did not anticipate all fields of frontend classes from the beginning).

Instruction hierarchy - All SLogo Commands are represented in the subclasses of this hierarchy. There are three levels - “Tier 1”, or the Instruction superclass/root, “Tier 2”, or the categories of commands (i.e. MathOperations, TurtleQueries, etc.), and “Tier 3,” or the actual SLogo commands. The pros of this 
approach are that it makes it easy for the Interpreter to generate Instructions via reflection, code common to all Instructions (i.e. handling InstructionData) can be placed once in Instruction, and code common to categories of Instructions but not all Instructions can be placed in the appropriate Tier 2 category class (DRY). Still, the drawbacks are that execute() is extremely general -- a new command that does anything can be defined, and execute() must reflect that, but this could cause unpredictable problems in other areas of the project. Also, because constructors are not inherited, the same constructor had to be copy-pasted in every Tier 2 and 3 class. 

Multiple Turtle Handling (from Matthew’s analysis): - Namely, the executeAllToldTurtles() method in Instruction (see Javadoc comment). The idea of "active" vs. "told" turtles was particularly useful because it allowed information to be carried across Instructions (i.e. the system remembers which turtles are "told" after the TELL command) without using Interpreter classes (already very confusing!) as the mediator (that would be my first instinct, since they are where Instructions are instantiated). Also, the creation of executeAllTurtleCommands() was a minimal change from the normal execute() method and can easily be changed back. Finally, this approach exemplifies encapsulation, as methods from other classes like setActiveActor(), getActiveActor(), etc. make all the changes outside of the Instruction hierarchy, and uses method calls without any parameters or return values to minimize knowledge of the Instruction hierarchy. On the other hand, this approach requires checking “instanceof ActorSpecificInstruction” and executes commands after “tell” such that the first executes for all actors before the second is begun (perhaps incorrect looking at the provided example code).

**Tools**
Tools were made using JavaFx’s built in MenuBar and ToolBar but we created individual items through our own AbstractMenuItems and AbstractButtons. This was to incorporate the Observable feature of Java so that the changes in the tools can directly be observed by the rest of the program.

**Properties**
Properties were added in order to increase encapsulation and improve extendability of different xml-defined values. The Properties class defines ways in which a property’s value can be created from xml, saved to xml, and updated dynamically through a GUI representation.

**Forgoing Models**
In order to implement Properties, we had to essentially combine Models and Views into a single class for the Turtle, Pen, And Simulation. The TurtleView, PenView, and SimulationView classes now contain all of the properties that they need to be displayed and updated. While this doesn’t follow the MVC design structure, we argue that it improves encapsulation because everything required for a property is defined in a single class.

#States any assumptions or decisions made to simplify or resolve ambiguities in your the project's functionality

**Assumption 1: ** When an Instruction is created, any improper SyntaxExceptions has already been thrown in the Interpreter. This means that Instructions can assume SLogo commands have the correct number of arguments, which simplifies work in their execute() method.

**Assumption 2: ** Any ExecuteExceptions will be thrown in the instruction hierarchy. Therefore, by the
end of the execute() method, all exceptions relates to SLogo commands will have been thrown.

**Assumption 3: ** Instructions are executed with the assumption that the interpreter passes to the instruction the expected arguments. For some instructions, these arguments are more complicated. The group instruction, for instance, expected a child that is a String of text representing the instructions within the grouping. It executes all of its actions based on this assumption. This assumption was made in order to avoid having to add specialized code in any part of the closed components (e.g., the interpreter), but adds a hidden dependency.

**Assumption 4: ** AbstractMenuItems are made with the assumption that there is a JavaFx MenuItem 



