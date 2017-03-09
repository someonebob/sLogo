package interpreter.builders;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import instruction.InstructionData;
import interpreter.misc.InstructionNode;
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

	/**
	 * Creates the correct type of BuilderUtil 
	 * for the instruction given or returns null (if this
	 * is not a "special" node requiring pre-processing before 
	 * going through the generalized parsing (in TreeBuilder)
	 * 
	 * @param nodes The current nodes left to be processed
	 * @param head The head node (the one that was removed in this cycle, precedes the list
	 * of current nodes)
	 * @param current The text from the instruction that has yet to be parsed
	 * @param data The InstructionData object holding information about the workspace
	 * @return The correct BuilderUtil type corresponding to the
	 * head InstructionNode or null (if no special treatment is required)
	 */
	public static BuilderUtil make(List<InstructionNode> nodes,
			InstructionNode head, String current, InstructionData data){
		String instructionType = head.getMyClassification();
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
			ctor = clazz.getDeclaredConstructor(List.class, InstructionNode.class, 
					String.class, InstructionData.class);
			builderHopeful = ctor.newInstance(nodes, head, current, data);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
