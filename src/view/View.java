package view;

import java.util.Observer;

import javafx.scene.Node;

/**
 * A view is anything something in the front-end that can be displayed. Part of
 * the Model-View-Controller design pattern. Any class that implements this
 * interface must provide a way to display the object.
 * 
 * @author jimmy
 *
 */
public interface View extends Observer
{

	/**
	 * Returns the View's representation as a JavaFX.Node so that it can be
	 * displayed.
	 * 
	 * @return JavaFX.Node view image
	 */
	public Node display();


}