package interpreter;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import instruction.Instruction;

/**
 * This class performs the reflection necessary to produce instances of each
 * command type without direct statement of the desired class type. This class
 * can translate a String into an instance of the intended class type.
 * 
 * @author maddiebriere
 *
 */

public class InstructionClassifier {
	public final String SYNTAX = "src/resources.languages/Syntax.properties";
	public final String LANGUAGE = "src/resources.langauges/";

	private String mySyntax;
	private String myLanguage;
	private List<Entry<String, Pattern>> myTerms;

	public InstructionClassifier(String s) {
		myLanguage = LANGUAGE + s;
		mySyntax = SYNTAX;
		generateTerms();
	}

	/**
	 * Main avenue of reflection
	 * 
	 * @param command
	 *            String representing instruction (e.g., fd)
	 * @return Instruction object corresponding to String
	 */
	public Instruction generateInstruction(String command) {
		Class<?> clazz;
		Instruction instruction = null;
		Object instructionHopeful = new Object();
		try {
			clazz = Class.forName(command);
			instructionHopeful = clazz.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Handle error --> non-valid class
		} // This is probably wrong

		if (!(instructionHopeful instanceof Instruction)) {
			// TODO: Error handling --> non-Instruction input
		} else {
			instruction = (Instruction) instructionHopeful;
		}
		return instruction;
	}

	public void generateTerms() {
		myTerms = new ArrayList<Entry<String, Pattern>>();
		addPatterns(mySyntax);
		addPatterns(myLanguage);
	}

	public void addPatterns(String resource) {
		ResourceBundle resources = ResourceBundle.getBundle(resource);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			myTerms.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
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

}
