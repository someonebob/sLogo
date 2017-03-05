package interpreter;

import java.util.HashSet;
import java.util.Set;

import util.Pair;

public class NodeClassifier {
	
	public static final String NO_MATCH = "NO MATCH";
	
	private static Set<Pair> populateBrackets(){
		String gstart = GroupTreeBuilder.getStartBracket();
		String gend = GroupTreeBuilder.getStartBracket();
		String lstart = ListTreeBuilder.getStartBracket();
		String lend = ListTreeBuilder.getEndBracket();
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
		return name.equals(ListTreeBuilder.getStartBracket());
	}
	
	public static boolean isGroup(String name){
		return name.equals(GroupTreeBuilder.getStartBracket());
	}
	
	public static boolean isUnknown(String name){
		return name.equals(NO_MATCH);
	}
}
