package testers;

import instruction.Instruction;
import interpreter.InstructionClassifier;

public class InstructionClassifierTester {

	public static void main(String [] args){
		testTerms("English");
	}
	
	private static void testTerms(String language){
		InstructionClassifier in = new InstructionClassifier(language);
		String[] examples = generateExampleInput();
		parseText(in, examples);
		
	}
	
   	/**
   	 * @author rcd
   	 * @param lang Language
   	 * @param text Input examples
   	 */
    private static void parseText (InstructionClassifier classifier, String[] text) {
        for (String s : text) {
            if (s.trim().length() > 0) {
                System.out.println(String.format("%s : %s", s, classifier.generateTerm(s)));
               	Instruction generated = classifier.generateInstruction(s);
               	if(generated == null){
               		System.out.println(String.format("%s : %s", s + " Instruction", "NO INSTRUCTION MATCH"));
               	}else{
               		String className = generated.getClass().getName();
                    System.out.println(String.format("%s : %s", s + " Instruction", className));
               	}
            }
        }
        System.out.println();
    }
	
	private static String[] generateExampleInput(){
		String[] examples = {
	            "",
	            "# foo",
	            "foo #",
	            "#",
	            "fd",
	            "FD",
	            "forwardd",
	            "equalp",
	            "equal?",
	            "equal??",
	            "+",
	            "SuM",
	            "-",
	            "*",
	            "/",
	            "%",
	            "~",
	            "+not",
	            "not+",
	            "++",
	            "+*+",
	            "or",
	            "FOR",
	            "allOrNothing",
	            "all_or_nothing",
	            "allOr_nothing?",
	            "allOr?nothing_",
	            ":allornothing",
	            "PI",
	            "90",
	            "9.09",
	            "9.0.0",
	            "[",
	            "]",
	            "(",
	            ")"
	        };
		return examples;
	}
}
