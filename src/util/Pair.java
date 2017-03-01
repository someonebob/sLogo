package util;

/**
 * Helper class for pairing keywords
 * 
 * @author maddiebriere
 *
 */

public class Pair {
	
	private String myA;
	private String myB;
	
	public Pair(String a, String b){
		myA = a;
		myB = b;
	}

	public String getMyA() {
		return myA;
	}

	public void setMyA(String myA) {
		this.myA = myA;
	}

	public String getMyB() {
		return myB;
	}

	public void setMyB(String myB) {
		this.myB = myB;
	}
	
	
}
