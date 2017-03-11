package interpreter.clean;

import java.util.Scanner;

import instruction.InstructionData;
import interpreter.classification.InstructionClassifier;

/**
 * Clean up the text before it is
 * parsed to avoid unnecessary 
 * errors
 * 
 * @author maddiebriere
 *
 */
public class InstructionCleaner{
	
	private InstructionData data;
	private InstructionClassifier clazz; 
	
	public InstructionCleaner (InstructionData data, InstructionClassifier clazz){
		this.data = data;
		this.clazz = clazz;
	}
	
	public String clean(String text) {
		text = spaceBrackets(text);
		text = fixTypos(text);
		text = removeComments(text);
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
		return text;
	}
	/**
	 * Fix obvious typos
	 * ex: forwardd 50 --> forward 50
	 * @param text Text to modify
	 * @return Text with typos fixed
	 */
	private String fixTypos(String text){
		//TODO:Complete
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
		/*Scanner toScan = new Scanner(text);
		String toRet = "";
		while(toScan.hasNext()){
			String line = toScan.nextLine();
			int space = line.indexOf(" ");
			String firstWord;
			if(space!=-1){
				firstWord = line.substring(0, space);
			}
			else{
				firstWord = text;
			}
			if(!getClazz().getInstructionType(firstWord, getData()).equals("Comment")){
				toRet += line + " ";
			}
		}
		toScan.close();*/
		return text;
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