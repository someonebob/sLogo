//This entire file is part of my masterpiece.
//Matthew Barbano
//See GenericDisplayCommand for description.
package instruction.DisplayCommands;
import java.util.Collection;
/**
 * This interface is extended by all frontend classes which contain an indexed
 * list of objects that represent properties. Currently, these frontend classes
 * are AnimatedSimulationView, ImageProperty, and SimulationView. Any
 * functionality common to these indexed lists should be declared here.
 * @author Matthew Barbano
 * @param <PropertyType> the type of objects the implementing property stores
 */
public interface IndexedProperty<PropertyType> {
	/** Returns the indexed collection (as a Collection, to avoid loss of
	 * generality) housed within the implementing class.*/
	public Collection<PropertyType> getIndexedCollection();
}
