package testers;

import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import interpreter.InstructionClassifier;

public class InstructionClassifierTester {

	public static void main(String [] args){
		testTerms("English");
	}
	
	private static void testTerms(String language){
		InstructionClassifier in = new InstructionClassifier(language);
		String[] examples = generateExampleInput();
		//parseText(in, examples);
		//listTest(in);
		
	}
	
	
	private static void listTest(InstructionClassifier in){
		List<Entry<String, Pattern>> syntax = in.getMySyntaxList();
		List<Entry<String, Pattern>> lang = in.getMyLanguageList();
		List<Entry<String, Pattern>> addr = in.getMyPathsList();
		printList(syntax);
		printList(lang);
		printList(addr);
		
		
	}
	
	private static void printList(List<Entry<String,Pattern>> input){
		for(Entry<String,Pattern> e: input){
			System.out.println(String.format("%s : %s", e.getKey(), e.getValue()));
		}
		System.out.println();
	}
	
   	/**
   	 * @author rcd
   	 * @param lang Language
   	 * @param text Input examples
   	 */
    private static void parseText (InstructionClassifier classifier, String[] text) {
    	/*
        for (String s : text) {
            if (s.trim().length() > 0) {
            	String shorty = classifier.findShortcutKey(s);
                System.out.println(String.format("%s : %s", s, shorty));
                System.out.println(String.format("%s : %s", shorty, classifier.findAddressKey(shorty)));
               	Instruction generated = classifier.generateInstruction(s);
               	if(generated == null){
               		System.out.println(String.format("%s : %s", s + " Instruction", "NO INSTRUCTION MATCH"));
               	}else{
               		String className = generated.getClass().getName();
                    System.out.println(String.format("%s : %s", s + " Instruction", className));
               	}
            }
            System.out.println();
        }
        System.out.println();
        */
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
