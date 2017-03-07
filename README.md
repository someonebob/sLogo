## SLogo Project
## Maddie Briere, Jesse Yue, Matthew Barbano and Jimmy Shackford
## Team 07

**Date started:** February 20th, 2017

**Date finished: ** March 2nd, 2017 (2nd sprint)

**Number of hour: ** 150 hours

## Roles:
**1. Maddie ** worked on the backend, writing the main engine for interpretation (Interpreter). She wrote all of the code controlling the backend, starting from when the string command was passed to the backend (non-processed and non-error checked) and ending with the execution of the line. This required the deconstruction of the input into a tree of instruction nodes (holding data and child nodes to display relevant argument relationships), the reading of this tree (using post-order traversal) and the construction of corresponding instructions (which then had to be executed). Several helper classes had to be created to facilitate this process, most notably: InstructionNode, InstructionClassifier, TreeBuilder and TreeExecuter. This included many utility classes as well (such as Argument Reader). 

**2. Matthew ** worked on the backend as well, focusing on the creation of Instructions and the various subclasses of Instruction. Implemented all commands in Turtle Queries, Math Operations, and Boolean Operations, some in Turtle Commands (in collaboration with Jimmy), and all but TO in Variables/Control Structures/User-Defined Commands (in collaboration with Maddie). Made the MathUtil and PointPolar classes as helpers/utility classes and InstructionData for passing data from the frontend to the backend. Also created the whole SLogoException hierarchy and added throws and try-catch statements to handle exceptions, creating a popup Alert when thrown. 

**3. Jimmy ** worked on the frontend. Wrote the design for the LogoController class, the abstract view, abstract model, abstract tool, page view, and the selection bar. Also wrote the AbstractButton, AbstractColorButton, HelpTool, and SettingsTool (in collaboration with Jesse) classes. With Jesse, wrote the ActorView class for displaying an actor. Wrote the TurtleView class for displaying turtle, and the PenView class for displaying the pen. Wrote tool button classes for editing background color, pen color, turtle image, and opening the help page. Made a basic html help page. Wrote most of the Turtle Commands (in collaboration with Matt), and wrote a couple of the Turtle Query Commands (pendown/showing).

**4. Jesse ** worked on the frontend. Edited the design of View, Abstractbutton, and LogoController to make View and LogoController Observers and AbstractButton observable. This allows the simulation to be updateable based on button actions. Worked on LogoController, SimulationView, and SettingsTool  in collaboration with Jimmy. Also created the InputBox to take in text input, WorkspaceView to display and edit declared variables, VariableData to store variable data, VariableEditor a pop up that allows the user to edit the selected variable, FileTools, and AbstractLanguageButton to change the language. Wrote tool button classes for making new files by implementing tabs, opening files, saving files, and changing language.

## Resources:
JavaFX 8 Documentation
Java 8 Documentation
Stack Overflow
http://stackoverflow.com/questions/11592635/how-to-get-around-the-lack-of-abstract-fields-in-java
http://alvinalexander.com/java/edu/pj/pj010018
https://docs.oracle.com/javase/tutorial/reflect/ 
http://docs.oracle.com/javase/7/docs/api/java/util/ResourceBundle.html?is-external=true



## Files used to start the project (the class(es) containing main)
**Main.java** (in main package) 

## Files used to test the project
In the testers package:

**ArgumentReaderTester.java:** Used to test the reading of the NumArgs resource file which controlled how many arguments a command would “look for” when the instruction node tree was being created.

**InstructionClassifierTester.java: ** Used to test the reading of the Syntax and language files. This class allows us to make sure that all of the inputs (e.g., fd, 50, make) were being correctly recognized as elements in the resource files and turned into the correct classes (e.g., Forward, Constant, MakeVariable) using reflection.

** TreeBuilderTester.java: ** Used to test that the TreeBuilder class was correctly implemented. This class allows us to print out each level of the tree of InstructionNodes to confirm that each command is taking the correct number of arguments and in the correct order.

## Any data or resource files required by the project (including format of non-standard files)
In the data folder:
HelpPage.html - The help page for the user interface:
Resource files:
In resources.interpreter package: (for classes in the Interpreter package)

* Brackets.properties

* JavaSpeak.properties

* NumArgs.properties

In resources.languages package:

* Exception.properties - messages and text displayed on Alert popup for SLogoExceptions

* Syntax.properties - types of expressions (regex)

* English.properties - and several other languages; the commands in these languages



## Any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)

The input allowed for this program is highly documented, as Logo is a common beginner’s programming language. The basic commands implemented in the first sprint can be found on the CompSci 308 assignment page ( http://www.cs.duke.edu/courses/cps108/current/assign/03_slogo/commands.php). These types of input can be split into the following types:

1. Basic Syntax (Variables, Command, Comment, List, etc.)

2. Turtle Queries (XCor -- what is the x coordinate?, YCor -- what is the y coordinate?)

3. Turtle Commands (Forward, Backward, etc.)

4. Math Operations (Sum, Minus, etc.)

5. Boolean Operations (Less, Greater, etc.)

6. User-defined commands (both making and re-using them)

7. Control structures (if statements, for loops, etc.)

8. Variables (the creation of workspace variables like x and y)

Other useful information:

* Old commands can be clicked on to re-run the commands

* Variables created by the user are displayed and changeable in a side-bar in the workspace

## Any known bugs, crashes, or problems with the project's functionality

**Back-end bugs**

The **variables** are still a bit buggy. The creation of variables works perfectly fine -- the correct values and names will be initialized in the sidebar. It is only when more complicated, nested commands are made that variables begin to break down (e.g., set :x :x 5).

For some reason, **comments** still are not recognized by the InstructionClassifier. This was tested thoroughly and eventually abandoned after too much time was put into the problem.

Commands across **multiple lines** still do not consistently run -- some sort of line cleaning method is required in the interpreter to work with spread out commands.

Some of the functionality is **hard-coded.** Because the components that were hard-coded are so essential to the program (e.g., making a variable), these were deemed reasonable assumptions (for the meantime). Still, these should be re-factored for the next sprint.

**Front-end bugs**

Turtle does not cleanly stop at simulation borders - is only a rough border detection algorithm
Running a command only affects the most recently opened tab (this functionality used to work--where a command would only execute on the currently opened tab--but it got messed up somehow.
When you load a new image for an actor, it doesn’t replace the last one and just gets placed over the old one. Moreover, new commands don’t work on the new image.
If you enter a forward/backward command too quickly after the last one and a Timeline animation is still running, the most recently run forward/backward command will not execute.


## Any extra features included in the project
Multiple tabs, each with a different simulation, can be opened, and code can be saved. Also, the background color, turtle image, and pen color can be changed. This is accomplished through the dropdown menus in the top toolbar. An html help page can be opened through the toolbar.

## Your impressions of the assignment to help improve it in the future

Matthew - A challenging and rewarding project overall. Having all commands return double was a helpful simplification. It was tedious implementing the Instruction hierarchy, but rewarding. I learned to improve communication skills, as there were many times when extensive communication was required, and I learned quite a bit about Exception handling as I designed the SLogoException hierarchy.

Maddie - Working with a slightly larger group encouraged more cognizance of communication. It also made it necessary to split up tasks wisely, which was a great learning experience. Overall, a rewarding assignment to complete -- the result is a fully functional IDE!

Jimmy - It was interesting to make a design where the front-end didn’t need to know much about the back-end in order to make the project work. Team meetings helped me understand the overall design features of both the front-end and back-end. I really enjoyed seeing how you could make your own language compiler in Java.

Jesse - The project was challenging and fun. It was cool to create our own IDE where we can see our commands come to life.

