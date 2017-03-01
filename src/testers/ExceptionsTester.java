package testers;

import exceptions.MathException;

public class ExceptionsTester {

	public static void main(String[] args){
		try{
			foo(1);
		}
		catch(MathException e){
			System.out.println("Here");   //if catch, no stack trace printed - if don't, there is a stack trace printed
		}
	}
	public static void foo(int x){
		if(x == 1){
			System.out.println("First clause");
			throw new MathException("QuotientMessage");
		}
		System.out.println("Second clause");
	}
}
