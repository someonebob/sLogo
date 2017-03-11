package interpreter.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import exceptions.InvalidCommandException;

/**
 * TODO: Use this class to consolidate
 * factory methods
 * 
 * @author maddiebriere
 *
 */

public abstract class AbstractFactory <A> {
	private static final String RESOURCE_REFLECTION_NAME = "InvalidCommandMessage";
	
	private String objectName;
	
	public AbstractFactory (String objectName){
		this.objectName = objectName;
	}
	
	public A make(Object...args){
		return buildObject(objectName, args);
	}
	
	public abstract A failResponse();
	
	public A buildObject(String classPath, Object ...args) {
		Class<?> clazz;

		try {
			clazz = Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			return failResponse();
		}
		return tryToBuild(clazz, args);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private A tryToBuild(Class clazz, Object ...args){
		A toRet = null;
		Class[] classes = getClasses(args);
		try {
			Constructor<?> ctor = clazz.getDeclaredConstructor(classes);
			toRet = (A) ctor.newInstance(args);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
				| SecurityException | IllegalArgumentException | InvocationTargetException e) {
			throwError();
		}
		return toRet;
	}
	
	protected void throwError(){
		throw new InvalidCommandException(RESOURCE_REFLECTION_NAME);
	}
	
	@SuppressWarnings("rawtypes")
	protected Class[] getClasses(Object ...args){
		Class [] classes = new Class [args.length];
		for(int i=0; i<classes.length; i++){
			classes[i] = args[i].getClass();
		}
		return classes;
	}
	
}
