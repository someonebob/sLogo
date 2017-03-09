## Changes to the API

## Front-End 
1. ** Change in model design: ** The only class that still has a model *and* view is the Actor class. All of the other classes were minimized for simplicity and ease of design. While the Pages, for instance, don't require all that much updating/functionality, the Actors have to keep track of parallel information (both backend data and frontend information about display).

2. **Observable objects: ** View now implements observer so that the views can be updated when changes occur. We created a new class AbstractButton which all of our MenuItems will extend. These MenuItems will be inner classes within the tool that they will be under. The AbstractButton just gives united functionality for calling observers since it extends Observable. This way actions in the MenuBar can affect things in View if View observes it.

## Back-End
1. **New classes: ** Several new classes with public methods were added to the back-end for the purposes of interpretation. The process turned out to be much more work than expected and required several helper classes (such as InstructionClassifier, TreeBuilder, etc.) with which to work. These had to have public methods for the purposes of communication between the interpreter and the other classes in the package. These classes were added to split up functionality and avoid having the Interpreter class become one monstrous class of code. 

2. ** Responsibilities in the backend: ** The Instruction class was modified several times, with the constructors being changed to accomodate various requirements from the interpreter. In the end, the Instruction class needed to accept a List of arguments and a String of text to define the instruction, which were not included before. These were necessary for communication between the interpreter and the instructions.