# Addition to SLogo Exercise as Part of VoogaSalad Analysis

By: Matthew Barbano (meb100)

Doing the backend extension (stamp and clearstamps)

## Sources
http://stackoverflow.com/questions/21118394/explicitly-positioning-nodes-in-javafx
Java FX 8 Documentation https://docs.oracle.com/javase/8/javafx/api/toc.htm

## Estimation: before looking at the old code:
### how long do you think it will take you to complete this new feature?
1 hour

### how many files will you need to add or update? Why?
2 files - One for each new command in the Instruction hierarchy.

## Review: after completing the feature:
### how long did it take you to complete this new feature?
4 hours

### how many files did you need to add or update? Why?
6 files

Added Stamp and ClearStamps to Instruction hierarchy
Edited InstructionData to pass stamp list from frontend to backend
Edited AnimatedSimulationView and SimulationView to add stamp list and associated methods
Edited TurtleView to add method to duplicate image

### did you get it completely right on the first try?
No - there was significant debugging especially when adding the stamp list associated methods

## Analysis: what do you feel this exercise reveals about your project's design and documentation?
### was it as good (or bad) as you remembered?
Overall, the Instruction hierarchy was as easy to work with as I remembered. I looked at similar Instructions
like SetShape to remember what method calls to InstructionData I needed to make. It was more difficult
adding code to the frontend classes, since I have been working more on the backend this semester and
spent several hours figuring out it was layoutX and translateX that BOTH needed to be set.

### what could be improved?
At first, I thought using newActor() would be a possibility to clone images, but the fact that it 
was not refactored into helper methods that I could piece together in a custom way for this task meant
I had to make a completely new method.

### what would it have been like if you were not familiar with the code at all?
It would have been difficult to find a place to start. I was lucky since I started from the command hierarchy,
which I wrote and was most familiar with, and used that to ease into the frontend through Instruction Data,
but without this it would be much more challenging.