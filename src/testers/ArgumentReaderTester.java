package testers;

import util.ArgumentReader;

/**
 * Test argument reader
 * 
 * @author maddiebriere
 *
 */

public class ArgumentReaderTester {

	public static void main (String [] args){
		printNumArgs("Forward");
		printNumArgs("PenUp");
		
	}
	
	private static void printNumArgs(String type){
		System.out.println(type + " " + ArgumentReader.getNumArgs(type));

	}
}
