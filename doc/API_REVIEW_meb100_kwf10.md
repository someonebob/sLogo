# API Review
Partner: Kyle Finke (kwf10)

- NUM_ARGS set via constructors
- Add another 
- Error checking: public methods are the ones that need error checking (protected/private don't)
- Backend - just throw the exception, but in the frontend do the try catch to check for these Exceptions
- Alt: I catch the exception and pass text to display to frontend
- Compile vs Runtime errors

## Part 1
1. What about your API/design is intended to be flexible?
To add another command, all you need to do is write a new concrete subclass in the Instruction hierarchy. No modification of existing classes is required.
2. How is your API/design encapsulating your implementation decisions?
The superclasses in the Instruction hierarchy contain many protected helper methods used by concrete subclasses. These are well-organized: for example, TurtleCommand contains many helpers not used by its siblings, like move() and turn().
3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
The Interpreter will detect syntax errors, and the Instruction hierarchy will detect logic-based errors. For
all of them, a window will pop up on the GUI indicating the error.

- The InstructionNode root's number of children does not equal NUM_ARGS for the corresponding instruction. Handle this in Interpreter.
- A command is misspelled. Handle in Interpreter during reflection.
- A type error occurs (see "Basic Syntax"): Ex: fw sum. Handle in Interpreter.
- A disallowed numerical value is entered. Ex: negative number of pixels for forward. Handle in Instruction hierarchy.
- The turtle moves off the edge. 2 Options: Make it an error, or implement torodial edges. Handle in Instruction hierarchy.
- QUOTIENT - Divide by 0. Handle in Instruction hierarhcy.
- REMAINER - One of the args is not an integer. Handle in Instruction hierarchy.
- TAN and ATAN - A divide by 0 error occurs at an asymptote. Handle in Instruction hierarchy.
- LOG - Argument is <=0. Handle in Instruction hierarchy.
- POW - Result is imaginary. Handle in Instruction hierarchy.
- RANDOM max - Needs to return a nonnegative number strictly less than max. So if max < 0, throw error. Handle in Instruction hierarchy.
- Note to self: Still need to look at Control Structures category in more detail.
 
4. Why do you think your API/design is good (also define what your measure of good is)?
The InstructionData wrapper class allows Instructions to modify the Simulation without having access to Simulation methods it should not know about. Also, the Instruction hierarchy is closed to modification.

 Part 2
How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
The MVC Design pattern could improve the design. We discussed in class striking a balance between using this pattern for all GUI components and only for a few; I think it would be appropriate to use just for the highest-level GUI wrappers and then just have a model and view for its subdivisions.
 How do you think the "advanced" Java features will help you implement your design?
Reflection will definitely be useful in creating commands without a large if tree. Also, binding could be helpful for modifying the Simulation, but it will need to replace InstructionData.
 What feature/design problem are you most excited to work on?
The trig/vector math involved with turtle movement
 What feature/design problem are you most worried about working on?
Implementing the commands in the “Control Structures” category
5. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).