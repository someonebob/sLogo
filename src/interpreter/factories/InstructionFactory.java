package interpreter.factories;

import java.util.List;

import instruction.Instruction;
import instruction.InstructionData;

/**
 * Class that uses reflection to create
 * the desired Instruction
 * 
 * @author maddiebriere
 *
 */

public class InstructionFactory extends AbstractFactory<Instruction>{

	public InstructionFactory(String classPath) {
		super(classPath);
	}

	@Override
	public Instruction failResponse() {
		throwError();
		return null;
	}
	
	
	/**
	 * Note: This override was necesssary because of the List.class reference,
	 * which could not be accomplished via the original implementation
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected Class<?>[] getClasses(Object ...args){
		Class[] toRet = new Class[]{InstructionData.class, List.class, String.class};
		return toRet;
	}

	@Override
	public String generateObjectType(String name) {
		return "";
	}

}
