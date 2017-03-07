package util;

/**
 * Helper class for pairing keywords
 * 
 * @author maddiebriere
 *
 */

public class Pair <A, B> {
	
	private A myA;
	private B myB;
	
	public Pair(A a, B b){
		myA = a;
		myB = b;
	}

	public A getMyA() {
		return myA;
	}

	public void setMyA(A myA) {
		this.myA = myA;
	}

	public B getMyB() {
		return myB;
	}

	public void setMyB(B myB) {
		this.myB = myB;
	}
	
	
}
