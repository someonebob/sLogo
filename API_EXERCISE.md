## Cell Society Critique - Team 4
API Analyzed: Backend, Internal

### Methods That Should Not Be In API
Cell:
	
CellSociety:
	public abstract void parseRules(RawData data);

### External API Methods - Communicate Frontend-Backend
Cell:
	public Location getMyLocation()
 	public double getDistance(Patch patch)
	public int getMyRow() 
	public int getMyCol() 
public Color getMyState() 
	public void setMyRow(int row) 
	public void setMyCol(int col) 
	public void setMyState(Color myState)

### Internal API Methods - For Future Programmers
Cell class:
public boolean positionEquals(Object o)
public int compareTo(Object o) 
	public abstract Cell createCopy();
	public abstract List<Cell> update(CellData data);
	public void copyLocation(Cell copyFrom)
	public void copyLocation(Location copyFrom)
	public void setMyLocation(Location myLocation) 
	public <T extends Cell>boolean locationIn(List<T>cells)
	public void basicCopy(Cell copyFrom)

## SLogo Architecture
When does parsing need to take place and what does it need to start properly?
	It takes place whenever the user types in a command in the input window and presses enter
What is the result of parsing and who receives it?
	An interpreter module executes the command from the parsing and causes some change in the frontend
When are errors detected and how are they reported?
	Syntax errors - after parsing, logical errors - after the interpreter executes - they will be throughout the whole project
	Popup window appears
What do commands know, when do they know it, and how do they get it?
	They know their corresponding String, any arguments, and what in the interpreter module they need to execute. They get it from the interpreter module.
How is the GUI updated after a command has completed execution?
	Each tab is updated: first the turtle graphic is updated, then the list of variables, then the list of previous commands. It moves to the next line of the IDE text parser.

## CRC Cards
![alt text](https://coursework.cs.duke.edu/CompSci308_2017Spring/slogo_team07/blob/master/doc/FrontEnd.JPG "Front End")
![alt text](https://coursework.cs.duke.edu/CompSci308_2017Spring/slogo_team07/blob/master/doc/ClassStructures.JPG "Class Structures")
