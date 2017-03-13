package interpreter.classification;

import java.util.List;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import instruction.*;
import interpreter.util.ResourceToListUtil;

/** This class
 * can translate a String into an instance of the intended class type.
 * 
 * @author maddiebriere
 */
public class InstructionClassifier {
	private final String ERROR = "NO MATCH";
	private final String INSTRUCTION = "Instruction";
	private final String USER_INSTRUCTION = "UserInstruction";

	public final String SYNTAX = "resources/languages/Syntax";
	public final String PATHS = "resources/interpreter/JavaSpeak"; 
	public final String LANGUAGE = "resources/languages/";
	public final String RESOURCE_REFLECTION_NAME = "InvalidCommandMessage";

	private String mySyntax;
	private String myLanguage;
	private String myPaths;

	private List<Entry<String, Pattern>> mySyntaxList;
	private List<Entry<String, Pattern>> myLanguageList;
	private List<Entry<String, Pattern>> myPathsList;

	public InstructionClassifier(String s) {
		myLanguage = LANGUAGE + s;
		mySyntax = SYNTAX;
		myPaths = PATHS;
		generateTerms();
	}

	public boolean isValid(String text, InstructionData data) {
		return !getInstructionType(text, data).equals(ERROR);
	}

	/**
	 * Step before actually generating instruction
	 * 
	 * Searches for text to match String to key
	 * 
	 * @author rcd
	 * @param text
	 *            Text to search for instruction type
	 * @return Matching key
	 */
	public String getInstructionType(String text, InstructionData data) {
		/**
		 * Default to higher classifiers if only possibility
		 */
		for (Entry<String, Pattern> e : mySyntaxList) {
			if (match(text, e.getValue())) {
				if (!e.getKey().equals(INSTRUCTION)) {
					return e.getKey();
				} else
					return findLetteredKey(text, data);
			}
		}
		return ERROR;
	}

	/**
	 * This method searches for different types of keys that are formated ONLY
	 * with characters (e.g., instructions, variables -- not comments, list
	 * starts, etc.).
	 * 
	 * @param key
	 *            The String for which to search
	 * @param data
	 *            The InstructionData object that holds current variables and
	 *            commands
	 * @return The String of the value stored with this key
	 */
	private String findLetteredKey(String key, InstructionData data) {
		/** Specific instruction key **/
		for (Entry<String, Pattern> e : myLanguageList) {
			if (match(key, e.getValue())) {
				return e.getKey();
			}
		}

		/** User instruction **/
		if (data.getFunction(key) != null) {
			return USER_INSTRUCTION;
		}
		return ERROR;
	}

	/**
	 * Searches for the specific address (Down to the correct package) using
	 * keys in another property file
	 * 
	 * @author rcd
	 * @param text
	 * @return matching Key
	 */
	public String findAddressKey(String text) {
		for (Entry<String, Pattern> e : myPathsList) {
			if (match(text, e.getKey())) {
				return e.getValue().toString();
			}
		}
		return ERROR;
	}

	/**
	 * Check if a String matches a pattern
	 * 
	 * @author rcd
	 * @param text
	 *            String to check
	 * @param regex
	 *            Pattern to compare against
	 * @return true if text and regex match, false otherwise
	 */
	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}

	/**
	 * Check if a String matches a pattern
	 * 
	 * @author rcd
	 * @param text
	 *            String to check
	 * @param regex
	 *            Pattern to compare against
	 * @return true if text and regex match, false otherwise
	 */
	private boolean match(String text, String regex) {
		return text.equals(regex);
	}

	public void generateTerms() {
		mySyntaxList = new ArrayList<Entry<String, Pattern>>();
		myLanguageList = new ArrayList<Entry<String, Pattern>>();
		myPathsList = new ArrayList<Entry<String, Pattern>>();
		ResourceToListUtil.addTerms(mySyntax, mySyntaxList);
		ResourceToListUtil.addTerms(myLanguage, myLanguageList);
		ResourceToListUtil.addTerms(myPaths, myPathsList);
	}

	public String getMyLanguage() {
		return myLanguage;
	}

	public void setMyLanguage(String myLanguage) {
		this.myLanguage = myLanguage;
	}

	public String getMySyntax() {
		return mySyntax;
	}

	public void setMySyntax(String mySyntax) {
		this.mySyntax = mySyntax;
	}

	public List<Entry<String, Pattern>> getMySyntaxList() {
		return mySyntaxList;
	}

	public void setMySyntaxList(List<Entry<String, Pattern>> mySyntaxList) {
		this.mySyntaxList = mySyntaxList;
	}

	public List<Entry<String, Pattern>> getMyLanguageList() {
		return myLanguageList;
	}

	public void setMyLanguageList(List<Entry<String, Pattern>> myLanguageList) {
		this.myLanguageList = myLanguageList;
	}

	public String getMyPaths() {
		return myPaths;
	}

	public void setMyPaths(String myPaths) {
		this.myPaths = myPaths;
	}

	public List<Entry<String, Pattern>> getMyPathsList() {
		return myPathsList;
	}

	public void setMyPathsList(List<Entry<String, Pattern>> myPathsList) {
		this.myPathsList = myPathsList;
	}
}