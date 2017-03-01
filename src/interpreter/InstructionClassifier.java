package interpreter;

import java.util.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import instruction.*;
import util.ResourceToList;

/**
 * This class performs the reflection necessary to produce instances of each
 * command type without direct statement of the desired class type. This class
 * can translate a String into an instance of the intended class type.
 * 
 * @author maddiebriere
 */

public class InstructionClassifier {
	public final String SYNTAX = "resources/languages/Syntax";
	public final String PATHS = "resources/interpreter/JavaSpeak"; //Full class names matched to shortcuts
	public final String LANGUAGE = "resources/languages/";
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
	
    /**
     * Step before actually generating instruction
     * 
     *TODO: Bug, can't find comments???
     * 
     * Searches for text to match String to key
     * @author rcd
     * @param text
     * @return matching Key
     */
    public String findShortcutKey(String text) {
        final String ERROR = "NO MATCH";
        /**
         * Default to higher classifiers if only possibility
         */
        for (Entry<String, Pattern> e : mySyntaxList) {
            if (match(text, e.getValue())) {
            	if(!e.getKey().equals("Instruction")){
            		return e.getKey();
            	}
            	else
            		return classifyInstructionShortcut(text);
            }
        }
        return ERROR;
    }
    
    /**
     * Classify an instruction as a specific instruction type (e.g., forward)
     * @param text String to be parsed
     * @return correct command
     */
    public String classifyInstructionShortcut(String text){
    	final String ERROR = "NO MATCH 2";
    	  /**
         * Check specific options first
         */
        for (Entry<String, Pattern> e : myLanguageList) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        
        return ERROR;
    }
    
    
    /**
     * Searches for the specific address (Down to the correct package)
     * using keys in another property file
     * @author rcd
     * @param text
     * @return matching Key
     */
    public String findAddressKey(String text) {
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : myPathsList) {
            if (match(text, e.getKey())) {
                return e.getValue().toString();
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
     * Check if a String matches a pattern
     * @author rcd
     * @param text String to check
     * @param regex Pattern to compare against
     * @return true if text and regex match, false otherwise
     */
    private boolean match (String text, String regex) {
        return text.equals(regex);
    }

	/**
	 * Main avenue of reflection
	 * 
	 * @param command
	 *            String representing instruction (e.g., fd)
	 * @param args The arguments used to make the instruction
	 * 
	 * @return Instruction object corresponding to String
	 */
	public Instruction generateInstruction(String comm, InstructionData  data, List<String> args) {
		
		//TODO: Complete and figure out instruction address problem
		//Piazza question
		
		Class<?> clazz;
		Instruction instruction = null;
		Object instructionHopeful = new Object();
		
		String classification = findShortcutKey(comm);
		if(classification.equals("NO MATCH") || classification.equals("NO MATCH 2")){ //Break out if the command isn't an option
			//TODO: Error handling of mismatch
			return null;
		}
		try {
			String classPath = findAddressKey(classification);
			clazz = Class.forName(classPath);
			Constructor<?> ctor = clazz.getDeclaredConstructor(InstructionData.class, List.class);
			instructionHopeful = ctor.newInstance(data, args);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Handle error --> non-valid class
		} // This is probably wrong
		catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
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
			instruction = (Instruction) instructionHopeful;
		}
		catch(Exception e){
			//TODO Handle error of non-instruction input
		}
		return instruction;
	}

	public void generateTerms() {
		mySyntaxList = new ArrayList<Entry<String, Pattern>>();
		myLanguageList = new ArrayList<Entry<String, Pattern>>();
		myPathsList = new ArrayList<Entry<String,Pattern>>();
		ResourceToList.addTerms(mySyntax, mySyntaxList);
		ResourceToList.addTerms(myLanguage, myLanguageList);
		ResourceToList.addTerms(myPaths, myPathsList);
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
