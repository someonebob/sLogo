package interpreter.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import exceptions.ReflectionException;
import interpreter.builders.BuilderUtil;
import interpreter.misc.InstructionNode;
import interpreter.misc.InstructionTracker;
/**
 * Class based on Factory Design Pattern
 * 
 * Creates BuilderUtil objects for use
 * in the creation of instruction trees
 * 
 * @author maddiebriere
 *
 */

public class BuilderUtilFactory {
	
	private static final String PATH = "interpreter.builders.";
	private static final String RESOURCE_REFLECTION_NAME = "ReflectionMessage";

	/**
	 * Creates the correct type of BuilderUtil 
	 * for the instruction given or returns null (if this
	 * is not a "special" node requiring pre-processing before 
	 * going through the generalized parsing (in TreeBuilder)
	 * 
	 * @param head The head node (the one that was removed in this cycle, precedes the list
	 * of current nodes)
	 * @param track Holds information about current instruction and workspace
	 * @return The correct BuilderUtil type corresponding to the
	 * head InstructionNode or null (if no special treatment is required)
	 */
	public static BuilderUtil make(InstructionNode head, String instructionType, InstructionTracker track){
		BuilderUtil toRet = null;
		Class<?> clazz;
		Object builderHopeful = new Object();
		
		try {
			String path = PATH + instructionType + "Util";
			clazz = Class.forName(path);
		}catch(Exception e){
			return null;
		}

		
		Constructor<?> ctor;
		try {
			ctor = clazz.getDeclaredConstructor(InstructionNode.class, InstructionTracker.class);
			builderHopeful = ctor.newInstance(head, track);
		} catch (NoSuchMethodException | SecurityException | InstantiationException 
				| IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException e) {
			throw new ReflectionException(RESOURCE_REFLECTION_NAME);
		} 
		
		try{
			toRet = (BuilderUtil)builderHopeful;
		}
		catch(Exception e){
			//TODO: Error throw
		}
		return toRet;
	}
}
	
	
