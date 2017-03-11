package interpreter.factories;

import interpreter.builders.BuilderUtil;
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

public class BuilderUtilFactory extends AbstractFactory<BuilderUtil>{
	
	private static final String PATH = "interpreter.builders.";
	
	public BuilderUtilFactory(InstructionNode head){
		super(PATH + head.getMyClassification() + "Util");
	}

	@Override
	public BuilderUtil failResponse() {
		return null;
	}

}
	
	
