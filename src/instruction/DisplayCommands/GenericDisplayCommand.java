//This entire file is part of my masterpiece.
//Matthew Barbano
/* Files Included in Masterpiece: GenericDisplayCommand, DisplayModifier, DisplayAccessor, GetPenColor, IndexedProperty
 * Note: This does NOT include the full DisplayCommands subhierarchy (some DisplayCommands are excluded).
 * The purpose of this code is to implement SLogo commands related to changing the state of the display.
 * Namely, it implements four SLogo commands: GetShape, GetPenColor, SetShape, and SetPenColor.
 * Note 2: GetShape, SetShape, and SetPenColor are not part of the masterpiece, but they were refactored so they were still functional.
 * 
 * It is well-designed because:
 * 1) It avoids repeated code (DRY) through an inheritance subhierarchy. I noticed that GetShape and GetPenColor had
 * many commonalities, so I grouped them under DisplayAccessor, and that SetShape and SetPenColor did too, so
 * I grouped them under DisplayModifier. Both DisplayAccessor and DisplayModifier extend GenericDisplayCommand, the
 * root of this masterpiece subhierarchy. The execute() method also reflects (but is not exactly) the Strategy and Template
 * Design Patterns (the former because its algorithm is different between DisplayAccessor and DisplayModifier, and the latter
 * because it calls more specific methods implemented in subclasses.)
 * 2) It exemplifies the use of Generics, an Advanced Java feature. I noticed that each of the four commands had three
 * general types associated with it: the data type of the currently displayed property, the data type of the objects in
 * the indexed list/collection, and the data type of the property class itself, so I made these Generics in class declarations.
 * 3) Bounded Type Parameters - Since ConcretePropertyType had to call both Property and IndexedProperty methods, writing 
 * "ConcretePropertyType extends ..." (the Bounded Type Parameter) solved the problem elegantly.
 * 4) Wildcards - In getIndexedCollectionAndCheckList(), List<?> was used since I was only comparing the Collection type, not
 * the type of its contents.
 * 5) It encapsulates code into short, well-named methods with a single purpose.
 * 6) It makes use of abstract methods to ensure future developers implement the necessary methods.
 * 7) I declare indexed lists/collections using Collection<CollectionDataType> to maintain the greatest possible generality,
 * until I must cast it to a List to call a List method.
 * 8) I list public methods, then protected, then private, in that order.
 */

package instruction.DisplayCommands;

import java.util.Collection;
import java.util.List;

import exceptions.CastingException;
import instruction.InstructionData;
import property.Property;
import view.ActorView;

/**
 * This class is the superclass of all Display Commands that employ a set of
 * three generic parameters for implemention. These three parameters (listed
 * below) represented, in order, the data type of the currently displayed
 * property, the data type of the objects in the indexed list/collection, and
 * the data type of the property class itself. This class also contains
 * commonalities to all of its subclasses.
 * 
 * @author Matthew Barbano
 *
 * @param <CollectionDataType>
 * @param <PropertyDataType>
 * @param <ConcretePropertyType>
 */
public abstract class GenericDisplayCommand<CollectionDataType, PropertyDataType, ConcretePropertyType extends Property<PropertyDataType> & IndexedProperty<CollectionDataType>>
		extends DisplayCommand {
	private static final String RESOURCE_CAST_EXCEPTION = "CastingMessage";

	/**
	 * @see DisplayCommand constructor
	 */
	public GenericDisplayCommand(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Given the currently "active" actor (as described in Instruction's
	 * Javadoc), returns its associated active property wrapper instance that
	 * will be accessed or modified.
	 * 
	 * @param activeActorView
	 * @return the associated property
	 */
	protected abstract ConcretePropertyType getActiveProperty(ActorView activeActorView);

	/**
	 * Returns, as a List, the indexed Collection housed within
	 * propertyWithCollection. Also checks if the Collection is able to be cast
	 * to a List, and throws a CastingException (a SLogoException) if not. This
	 * is in preparation for calling List-specific methods, such as indexOf()
	 * and get().
	 * 
	 * @param propertyWithCollection
	 * @return the housed collection as a List
	 * @throws CastingException
	 */
	protected List<CollectionDataType> getIndexedCollectionAndCheckList(ConcretePropertyType propertyWithCollection) {
		Collection<CollectionDataType> indexedCollection = propertyWithCollection.getIndexedCollection();
		if (!(indexedCollection instanceof List<?>)) {
			throw new CastingException(RESOURCE_CAST_EXCEPTION);
		}
		return (List<CollectionDataType>) indexedCollection;
	}

}
