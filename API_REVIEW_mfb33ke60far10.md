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

A) InvalidArgumentException -- the arguments to the instruction are not valid for the instruction type

B) UnknownCommandException -- command is not stored in the resource file

C) UnbalancedExpressionException -- Imbalance in instruction structure (e.g., "[" but no "]")

D) MissingArgsException -- Not enough arguments for command

We will handle these errors by passing them to front-end to display for users.


**4. Why do you think your API/design is good (also define what your measure of good is)?**

Our API serves all of the relevant purposes of an API. It presents to the user a variety of user-friendly and purposeful methods for use, while hiding some of the hairier implementation from view. It also separates front and back end interactions so that the APIs can change and be flexible without huge impact on other parts of the program.

###Part 2

**1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?**

**2. How do you think the "advanced" Java features will help you implement your design?**

**3. What feature/design problem are you most excited to work on?**

**4. What feature/design problem are you most worried about working on? **

**5. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).**