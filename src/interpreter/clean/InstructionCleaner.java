package interpreter.clean;

/**
 * Clean up the text before it is
 * parsed to avoid unnecessary 
 * errors
 * 
 * @author maddiebriere
 *
 */

public class InstructionCleaner{

	public static String clean(String text) {
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
	private static String spaceBrackets(String text){
		//TODO: Complete
		return text;
	}

	/**
	 * Fix obvious typos
	 * ex: forwardd 50 --> forward 50
	 * @param text Text to modify
	 * @return Text with typos fixed
	 */
	private static String fixTypos(String text){
		//TODO:Complete
		return text;
	}
}
