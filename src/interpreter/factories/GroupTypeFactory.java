package interpreter.factories;

import interpreter.grouptypes.GroupType;
import interpreter.misc.InstructionTracker;
import interpreter.util.GroupReaderUtil;

public class GroupTypeFactory extends AbstractFactory <GroupType> {
	private static final String PATH = "interpreter.grouptypes.";
	
	public GroupTypeFactory() {
		super(PATH);
	}

	@Override
	public GroupType failResponse() {
		throwError();
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Class<?>[] getClasses(Object ...args){
		Class[] toRet = new Class[]{InstructionTracker.class, String.class, int.class};
		return toRet;
	}

	@Override
	public String generateObjectType(String classification) {
		return GroupReaderUtil.getGroup(classification) + "GroupType";
	}

}
