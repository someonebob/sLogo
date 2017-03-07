package interpreter.builders;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import instruction.InstructionData;
import interpreter.misc.InstructionNode;

public class BuilderUtilFactory {
	
	private static final String PATH = "interpreter.builders.";

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
	
	
