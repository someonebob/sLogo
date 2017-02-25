package testers;

public class Bottom extends Middle{
	
	public static void main(String[] args){
		Bottom b = new Bottom();
		b.runTest();   //"Middle" prints! :)
	}
	public Bottom() {
		// TODO Auto-generated constructor stub
	}
	
	public void runTest(){
		foo();
	}
	
}
