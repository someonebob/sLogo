package instruction;

import java.util.ArrayList;
import java.util.List;

import interpreter.Interpreter;
import view.ActorView;

/**
 * This class is part of the Backend Internal API.
 * 
 * <p>
 * This is a the root of the inheritance hierarchy whose concrete subclasses
 * represent all possible commands in this project's implementation of the SLogo
 * language. Of greatest importance is its abstract execute() method, which
 * performs the unique action associated with any given Instruction.
 * </p>
 * 
 * <p>
 * There are two "states" that a turtle can have:
 * </p>
 * <ol>
 * <li><b>Active vs. Inactive</b> - Signifies the single Turtle (out of all
 * existing Turtles in AnimatedSimulationView's ObservableList called "actors")
 * that will be affected when execute() is called. Since execute() uses
 * InstructionData's getActiveActor() to update Turtle state, only the active
 * Turtle is affected. The Active Actor index of the "actors" ObservableList is
 * stored in InstructionData, as opposed to ActorView or TurtleView since there
 * is only one Active Actor at a time, and as opposed to SimulationView since
 * this pertains to the execution of Instructions, not the frontend simulation.
 * </li>
 * <li><b>Told vs. Untold</b> - Signifies the subset of Turtles selected via
 * "tell", "ask", or "askwith" on which future commands will execute. Since
 * there can be multiple "told" turtles at any given time, this state is stored
 * in the ActorView class.</li>
 * <p>
 * The method executeAllToldTurtles() is called by Interpreter at the
 * appropriate time and calls execute() the appropriate number of times,
 * handling the "active vs. inactive" and "told vs. untold" states.
 * </p>
 * 
 * <p>
 * The Instruction class also contains:
 * </p>
 * <ol>
 * <li>an instance of InstructionData for accessing information about simulation
 * state from the frontend</li>
 * <li>a List<String> for storing used-entered SLogo arguments (any number since
 * it is a List)</li>
 * <li>a String representation of this command</li>
 * </ol>
 * 
 * <p>
 * <b>Assumptions:</b> The user entered a valid command (SyntaxExceptions
 * handled by Interpreter), none of the three arguments in the constructor are
 * null.
 * </p>
 * 
 * <p>
 * <b>Dependencies:</b> InstructionData, List, String, ActorSpecificInstruction,
 * ActorView, Double
 * </p>
 * 
 * <p>
 * <b>Example of Use:</b>
 * 
 * <pre>
 * {
 * 	&#64;code
 * 	InstructionData instructionData = new InstructionData(simulation, new VariableData(), new FunctionData(),
 * 			LANGUAGE);
 * 	// Get List of arguments and text from user
 * 	Instruction instruction = new Forward(instructionData, arguments, textUserEntered);
 * 	instruction.executeAllToldTurtles();
 * }
 * </pre>
 * </p>
 * 
 * @author Matthew Barbano
 *
 */

public abstract class Instruction {
	private InstructionData instructionData;
	private List<String> arguments;
	private String myText;

	/**
	 * The only constructor for Instruction. Called using super() from
	 * Instruction subclasses, and sets corresponding instance variables to the
	 * three given parameters. No assumptions cause direct impact in this
	 * constructor. Setting any arguments or entries in args to null will cause
	 * errors elsewhere. Design decision: Making args a List accommodates SLogo
	 * commands with different numbers of arguments.
	 * 
	 * @param instructionData
	 *            for accessing frontend data
	 * @param args
	 *            for storing arguments to SLogo commands
	 * @param myText
	 *            a String representation of this Instruction
	 */
	public Instruction(InstructionData instructionData, List<String> args, String myText) {
		this.instructionData = instructionData;
		this.arguments = args;
		this.myText = myText;
	}

	/**
	 * Returns the instructionData object associated with this object. No
	 * assumptions, dependency is InstructionData.
	 * 
	 * @return instructionData
	 */
	protected InstructionData getInstructionData() {
		return instructionData;
	}

	/**
	 * Sets the instructionData object associated with this object to
	 * instructionData. No assumptions, dependency is InstructionData
	 * 
	 * @param instructionData
	 *            the new object
	 */
	protected void setInstructionData(InstructionData instructionData) {
		this.instructionData = instructionData;
	}

	/**
	 * Called by a helper class from the Interpreter, this method handles
	 * accessing/changing Turtle states between "active vs. inactive" and "told
	 * vs. untold" and calling execute() appropriately. The algorithm it follows
	 * is:
	 * <ul>
	 * <li>Checks if current Instruction is instanceof ActorSpecificInstruction.
	 * <ul>
	 * <li>If not, calls execute() once and returns its return value.</li>
	 * <li>If so, iterates over all existing actors. On each iteration:
	 * <ul>
	 * <li>Sets the current actor to the "active" one</li>
	 * <li>If that active actor is "told", calls execute(), keeping track of its
	 * most recently returned value and returning the last value</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * Assumptions are that any SyntaxExceptions have already been caught by the
	 * Interpreter, and that execute() will catch any ExecuteExceptions.
	 * Dependencies are ActorSpecificInstruction, ActorView, and
	 * InstructionData.
	 * 
	 * @return the return value of the last execute() called, which is displayed
	 *         in the InputBox
	 */
	public double executeAllToldTurtles() {
		int index = 0;
		double returnValue = -1.0;

		if (this instanceof ActorSpecificInstruction) {
			for (ActorView actor : instructionData.getActorList()) {
				instructionData.setActiveActorIndex(index);
				if (actor.isTold()) {
					returnValue = execute();
				}
				index++;
			}
		} else {
			returnValue = execute();
		}
		return returnValue;
	}

	/**
	 * Performs the action associated with this Instruction when called by
	 * executeAllToldTurtles(). Unique for each concrete subclass
	 * implementation. Returns the same return value for the SLogo command
	 * represented by this instruction. Assumptions and dependencies vary by
	 * subclass implementation.
	 * 
	 * @return the return value specified by the SLogo command
	 */
	protected abstract double execute();

	/**
	 * Converts List<String> arguments to an ArrayList of Doubles, since many
	 * (but not all - namely ones calling runListCommands()) commands use the
	 * contents of this List as such. Assumptions are that the contents of
	 * "arguments" can be parsed to doubles and that they are not null.
	 * Dependencies are List, ArrayList, String, and Double.
	 * 
	 * @return the List of doubles
	 */
	protected List<Double> getArgumentsDouble() {
		List<Double> doubleList = new ArrayList<>();
		for (String stringArgument : arguments) {
			doubleList.add(Double.parseDouble(stringArgument));
		}
		return doubleList;
	}

	/**
	 * Instantiates a new Interpreter with the same InstructionData as the
	 * current Instruction and calls parseAndRun() for the SLogo argument
	 * represented by "argumentNumber". Used for executing the commands in a
	 * SLogo list argument, such as "repeat 5 [ fd 50 left 30 fd 30 ]".
	 * Assumptions are that argumentNumber is a valid index, and dependencies
	 * are Interpreter and InstructionData.
	 * 
	 * @param argumentNumber
	 *            the SLogo argument number (0-indexed) for parsing
	 * @return the result of parseAndRun()
	 */
	protected double runListCommands(int argumentNumber) {
		Interpreter listInterpreter = new Interpreter(getInstructionData());
		return listInterpreter.parseAndRun(getArgumentString(argumentNumber));
	}

	/**
	 * Returns SLogo argument corresponding to "index" as a double. Assumes that
	 * argument can be parsed to a double. Dependencies are Double and List.
	 * 
	 * @param index
	 *            the argument to return
	 * @return the double form of that argument
	 */
	protected double getArgumentDouble(int index) {
		return Double.parseDouble(arguments.get(index));
	}

	/**
	 * Returns the "arguments" instance variable. No assumptions, dependencies
	 * are List and String.
	 * 
	 * @return arguments
	 */
	protected List<String> getArgumentsString() {
		return arguments;
	}

	/**
	 * Returns the SLogo argument represented by "index" in its String form.
	 * 
	 * @param index
	 *            corresponding to argument
	 * @return the argument as a String
	 */
	protected String getArgumentString(int index) {
		return arguments.get(index);
	}

	/**
	 * Returns the active actor from InstructionData, hiding the InstructionData
	 * dependency from callees and reducing the "getInstructionData()"
	 * repetitive code. Assumes that InstructionData is not null. Dependencies
	 * are ActorView and InstructionData.
	 * 
	 * @return the active actor
	 */
	protected ActorView getActiveActor() {
		return getInstructionData().getActiveActor();
	}

	/**
	 * Returns the text representation of this command. Notice there is no
	 * corresponding setter; this field is immutable. No assumptions; String is
	 * dependency.
	 * 
	 * @return myText
	 */
	protected String getMyText() {
		return myText;
	}
}