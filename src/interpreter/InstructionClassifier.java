package interpreter;

import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import instruction.*;

/**
 * This class performs the reflection necessary to produce instances of each
 * command type without direct statement of the desired class type. This class
 * can translate a String into an instance of the intended class type.
 * 
 * @author maddiebriere
 *
 */

public class InstructionClassifier {
	public final String SYNTAX = "resources/languages/Syntax";
	public final String LANGUAGE = "resources/languages/";

	private String mySyntax;
	private String myLanguage;
	private List<Entry<String, Pattern>> mySyntaxList;
	private List<Entry<String, Pattern>> myLanguageList;

	public InstructionClassifier(String s) {
		myLanguage = LANGUAGE + s;
		mySyntax = SYNTAX;
		generateTerms();
	}
	
    /**
     * Step before actually generating instruction
     * 
     * Searches for text to match String to key
     * @author rcd
     * @param text
     * @return matching Key
     */
    public String generateTerm (String text) {
        final String ERROR = "NO MATCH";
        /**
         * Check specific options first
         */
        for (Entry<String, Pattern> e : myLanguageList) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        /**
         * Default to higher classifiers if only possibility
         */
        for (Entry<String, Pattern> e : mySyntaxList) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return ERROR;
    }
    
    /**
     * Check if a String matches a pattern
     * @author rcd
     * @param text String to check
     * @param regex Pattern to compare against
     * @return true if text and regex match, false otherwise
     */
    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

	/**
	 * Main avenue of reflection
	 * 
	 * @param command
	 *            String representing instruction (e.g., fd)
	 * @return Instruction object corresponding to String
	 */
	public Instruction generateInstruction(String comm) {
		
		//TODO: Complete and figure out instruction address problem
		
		Class<?> clazz;
		Instruction instruction = null;
		Object instructionHopeful = new Object();
		
		String classification = generateTerm(comm);
		if(classification.equals("NO MATCH")){ //Break out if the command isn't an option
			//TODO: Error handling of mismatch
			return null;
		}
		
		try {
			String classPath = "instruction.TurtleCommands."+classification;
			clazz = Class.forName(classPath);
			instructionHopeful = clazz.newInstance();
			System.out.println("HERE");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Handle error --> non-valid class
		} // This is probably wrong

		try{
			instruction = (Instruction) instructionHopeful;
		}
		catch(Exception e){
			System.out.println(instructionHopeful.getClass().getName());
			//TODO Handle error of non-instruction input
		}
		return instruction;
	}

	public void generateTerms() {
		mySyntaxList = new ArrayList<Entry<String, Pattern>>();
		myLanguageList = new ArrayList<Entry<String, Pattern>>();
		addSymbols(mySyntax);
		addTerms(myLanguage);
	}

	public void addSymbols(String resource) {
		ResourceBundle resources = ResourceBundle.getBundle(resource);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			mySyntaxList.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}
	
	public void addTerms(String resource) {
		ResourceBundle resources = ResourceBundle.getBundle(resource);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			myLanguageList.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
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

	

}
