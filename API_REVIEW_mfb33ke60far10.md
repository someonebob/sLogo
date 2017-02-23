## SLogo API Design Review
##Maddie Briere, Faith Rodriguez & Kris Elbert
####mfb33, ke60, far10

###Part 1

**1. What about your API/design is intended to be flexible?**

A) We allow for multiple copies of several front-end elements so that the user is not restricted to only a single instance of every type (e.g., Page, Actor) -- rather, we keep lists of every type to allow, for instance, for a Simulation to keep multiple Actors instead of being bound to a single Actor.

B) We use tree traversal in the back-end to allow for an infinite number of combinations of instructions, which allows for unique combinations that have not been hard-coded.

C) We use reflection in order to avoid having to hardcode conversions from Strings to instances of a class. This removes the necessity to alter code everytime you add a new instance of something.

D) We use resource files so that changes to syntax or language will not require change to the actual code. Instead, our code will be written to facilitate any instance of an item in a resource file, making it so that accommodating a new item will require only adding the item to the list.


**2. How is your API/design encapsulating your implementation decisions?**

The back-end receives a String from the front-end, representing the input command, and expects a result from the instruction to be carried out by the back-end. It expects, for instance, that when it passes a String "fd 50" to the back-end, that the Turtle in question is moved 50 forward (the Turtle Model), initiating a chain reaction of updates. It does not need to know how the String is parsed and executed -- only that it does its job. The front end has no knowledge, for instance, of the fact that we use trees to parse the String. 


**3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**

Possible errors:

A) **InvalidArgumentException** -- the arguments to the instruction are not valid for the instruction type

B) **UnknownCommandException** -- command is not stored in the resource file

C) **UnbalancedExpressionException** -- Imbalance in instruction structure (e.g., "[" but no "]")

D) **MissingArgsException** -- Not enough arguments for command

We will handle these errors by passing them to front-end to display for users.


**4. Why do you think your API/design is good (also define what your measure of good is)?**

Our API serves all of the relevant purposes of an API. It presents to the user a variety of user-friendly and purposeful methods for use, while hiding some of the hairier implementation from view. It also separates front and back end interactions so that the APIs can change and be flexible without huge impact on other parts of the program.

###Part 2

**1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?**

**Used:**

A) **Interpreter** is used to parse Strings sent from the frontend to the backend

B) **Builder** is used to create Instructions from Strings in an ordered fashion

C) **Command** is used to represent the instructions and give them functionality

**Future use:**

A) **Observer** -- we could use this to mediate changes in the back and front end in order to facilitate updates without having to pass around large portions of code.


**2. How do you think the "advanced" Java features will help you implement your design?**

A) **Reflection** will be used to translate a user input such as "forward" from a String to an Instruction object that can have functionality and be passed around for execution.

B) **Generic types** will be used to specify the types of arguments across the program and allow for subclasses of important types (e.g., T extends TurtleCommand). 

C) **Regular Expressions** will be used to store instructions and syntax separately from the implementation used to interpret instances of these items.

D) **Bindings** will be used to keep track of changes across the program without having to pass large parts of the program around.


**3. What feature/design problem are you most excited to work on?**

Definitely the InstructionTree -- it will be really cool to use recursion to create and read these trees, and each Node will hold such a wealth of unique information.


**4. What feature/design problem are you most worried about working on? **

I am most worried about some of the more complex syntax, such as the creation of new functions and the instantiation of variables. I worry that our design will not facilitate these features.


**5. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**

1) User inputs "fd 50"
* The String "fd 50" is parsed into two nodes holding the Strings "fd" and "50"
* The nodes are stored in a tree, with 50 as the child of fd
* Each node is translated into an Instruction using reflection
* The fd node is executed, and "follows" its child node --> it goes to 50, which executes and returns 50, and the command forward 50 is executed (recursion stops)

2) User inputs "fd fd 50"
* Almost identical response to above, but note that the instructions are execute by executing the root node and then "following" the child node branches and executing these as well (until the bottom is reached)
* Hence, only addition is that this set up must execute one more node to get a return of 50 for 50, fd 50 and fd fd 50.

3) User inputs "[fd 50 fd 50]"
* Same recursive solution for two inner functions
* Now, instructions are held in a List which allows for multiple instructions to be returned

4) User input "fd rt 40"
* Nothing changes here, except that a single node, rt, is executed in a different fashion with different instructions (dictated by Right)
* Otherwise, all returns are the same

5) User input "fd"
* This will throw an error because there is a missing argument
* This will not execute and will instead call upon the front-end to display the error