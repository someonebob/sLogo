package interpreter.clean;


import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

import instruction.InstructionData;
import interpreter.classification.InstructionClassifier;
import interpreter.util.ResourceToListUtil;

/**
 * Clean up the text before it is
 * parsed to avoid unnecessary 
 * errors
 * 
 * @author maddiebriere
 *
 */
public class InstructionCleaner{
	public static final String BRACKETS = "resources.interpreter.Brackets";
	
	private InstructionData data;
	private InstructionClassifier clazz; 
	
	public InstructionCleaner (InstructionData data, InstructionClassifier clazz){
		this.data = data;
		this.clazz = clazz;
	}
	
	public String fullTextClean(String text) {
		return removeComments(text);
	}
	
	public String singleWordClean(String text){
		text = spaceBrackets(text);
		text = fixTypos(text);
		return text;
	}
	
	/**
	 * Separate brackets:
	 * Ex: (fd 50) --> ( fd 50 )
	 * @param text Text to modify
	 * @return Text with brackets separated
	 */
	private String spaceBrackets(String text){
		//TODO: Complete
		List<Entry<String, Pattern>> list = new ArrayList<Entry<String,Pattern>>();
		ResourceToListUtil.addTerms(BRACKETS, list);
		return text;
	}
	/**
	 * Fix obvious typos
	 * ex: forwardd --> forward
	 * @param text Text to modify
	 * @return Text with typos fixed
	 */
	private String fixTypos(String text){
		String extraFirst = text.substring(1, text.length());
		String extraLast = text.substring(0, text.length()-1);
		if(clazz.isValid(extraFirst, data)){
			return extraFirst;
		}
		if(clazz.isValid(extraLast,data)){
			return extraLast;
		}
		return text;
	}

	/**
	 * Remove comments from the instruction text.
	 * 
	 * BUG: Currently, this has to pull out every 
	 * single first word of a line (whereas the initial regex
	 * took care of looking for the first word).
	 * This fix is hard-coded
	 * 
	 * @param text Text from which to remove comments
	 * @return Text without comments
	 */
	private String removeComments(String text){
		Scanner toScan = new Scanner(text);
		String toRet = "";
		while(toScan.hasNext()){
			String line = toScan.nextLine();
			Scanner word = new Scanner(line);
			String firstWord;
			if(word.hasNext()){
				firstWord = word.next();
			} else{
				firstWord = "";
			}
			word.close();
			if(!getClazz().getInstructionType(firstWord, getData()).equals("Comment")){
				toRet += line + " ";
			}
		}
		toScan.close();
		return toRet;
	}

	public InstructionData getData() {
		return data;
	}

	public void setData(InstructionData data) {
		this.data = data;
	}

	public InstructionClassifier getClazz() {
		return clazz;
	}

	public void setClazz(InstructionClassifier clazz) {
		this.clazz = clazz;
	}
	
	
}