package view;

import javafx.scene.Node;

/**
 * Defines how a page is displayed. A page displays something related to the
 * simulation
 * 
 * @author jimmy
 *
 */
public interface PageView extends View
{
	/**
	 * Return how the PageView is displayed as a JavaFX.Node
	 */
	@Override
	public Node display();
}