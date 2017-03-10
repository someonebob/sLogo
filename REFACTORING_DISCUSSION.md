REFACTORING_DISCUSSION.md
Refactoring:
Jimmy Shackfor, Jesse Yue
Duplicated code:
1. Change makeDynamicUpdater methods in Property class so that the code for creating a VBox and settings its alignment to center isn't repeated. Creating the VBox is done in displayDynamicUpdater, which calls the abstract method called makeDynamicUpdaters. 
2. Add addButtons method to MenuTool to remove duplicated code for adding multiple buttons to the buttons list.

Locate non-private features in project:
1. Replaced protected instance variables in SingleLineInputBox and XML




## Refactoring Code Smells
## mfb33, meb100

**1. Error handling in BuilderUtilFactory ** 

Before we made changes, the BuilderUtilFactory printed out the stack trace every time one of the many possible reflection errors occurred. This is undesirable for many reasons and we fixed the problem by removing the repeated stack trace prints and adding in calls to specific SLogo Exceptions. 

**2. Unifying Duplicated Code in VariableData and FunctionData ** 

Before we made changes, there were several areas with repeated code, particularly in the compareTo() and equals() methods. We fixed this by creating a superclass of both these classes called StructureData which implements Comparable and contains the implements the compareTo() and equals() methods with the common implementations.

**3. Fixing magic values ** 

In InstructionClassifier, there were a couple of "magic values" used to discern certain important data types (that do not conform to the set given commands) -- these were replaced with constant Strings to avoid hard-coding in the file. 





