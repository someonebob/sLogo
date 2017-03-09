Refactoring:

Duplicated code:
1. Change makeDynamicUpdater methods in Property class so that the code for creating a VBox and settings its alignment to center isn't repeated. Creating the VBox is done in displayDynamicUpdater, which calls the abstract method called makeDynamicUpdaters. 
2. Add addButtons method to MenuTool to remove duplicated code for adding multiple buttons to the buttons list.

Locate non-private features in project:
1. Replaced protected instance variables in SingleLineInputBox and XML



> Written with [StackEdit](https://stackedit.io/).