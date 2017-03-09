## Refactoring Code Smells
## mfb33, meb100

**1. Error handling in BuilderUtilFactory ** 

Before we made changes, the BuilderUtilFactory printed out the stack trace every time one of the many possible reflection errors occurred. This is undesirable for many reasons and we fixed the problem by removing the repeated stack trace prints and adding in calls to specific SLogo Exceptions. 

**2. Unifying Duplicated Code in VariableData and FunctionData ** 

Before we made changes, there were several areas with repeated code, particularly in the compareTo() and equals() methods. We fixed this by creating a superclass of both these classes called StructureData which implements Comparable and contains the implements the compareTo() and equals() methods with the common implementations.

**3. Fixing magic values ** 

