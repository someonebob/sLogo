package interpreter.classification;

import java.util.HashSet;
import java.util.Set;

import interpreter.builders.GroupStartUtil;
import interpreter.builders.ListStartUtil;
import util.Pair;

public class NodeClassifier {
	
	//TODO: Move to decommision
	
	public static final String NO_MATCH = "NO MATCH";
	
	private static Set<Pair> populateBrackets(){
		String gstart = GroupStartUtil.getStartBracket();
		String gend = GroupStartUtil.getStartBracket();
		String lstart = ListStartUtil.getStartBracket();
		String lend = ListStartUtil.getEndBracket();
		Set<Pair> myBrackets = new HashSet<Pair>();
		myBrackets.add(new Pair(lstart, lend));
		myBrackets.add(new Pair(gstart, gend));
		return myBrackets;
	}
	
	
	public static boolean isBracket(String classification){
		return getBrackets(classification)!=null;
	}
	
	public static Pair getBrackets(String value){
		Set<Pair> myBrackets = populateBrackets();
		for(Pair pair: myBrackets){
			if(pair.getMyA().equals(value)){
				return pair;
			}
		}
		return null;
	}
	
	public static boolean isExecutable(String name){
		return !(isList(name) || isGroup(name) || isUnknown(name));
	}
	
	public static boolean isList(String name){
		return name.equals(ListStartUtil.getStartBracket());
	}
	
	public static boolean isGroup(String name){
		return name.equals(GroupStartUtil.getStartBracket());
	}
	
	public static boolean isUnknown(String name){
		return name.equals(NO_MATCH);
	}
}
