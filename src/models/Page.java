package models;
/**
 * Interface representing the structure that
 * holds information to be displayed on a window
 * Examples:
 * --> Help page
 * --> Informational page
 * --> Commands page
 * --> Variables page
 * 
 * @author maddiebriere
 *
 */

public abstract class Page implements Model{
/**
Get formatted string representing the text
Held in a page -- for instance, a page holding
Current variables would output a formatted
Variable list
**/
	public abstract String getText(); //get formatted text
	//no set text
}
