//This entire file is part of my masterpiece.
//Matthew Barbano
//See GenericDisplayCommand for description.

package instruction.DisplayCommands;

import java.util.List;

import exceptions.InvalidIndexException;
import instruction.InstructionData;
import property.Property;

/**
 * This class is at the root of all Display Commands which get the currently
 * displayed feature from the simulation or actor, search for it in an
 * associated indexed list, and return the found index (or throw an exception if
 * not found). They take no arguments, and this functionality is implemented in
 * execute() (in this class, not its subclasses, as enabled by generics and
 * which allows me to avoid repetition).
 * 
 * @author Matthew Barbano
 *
 * @param <CollectionDataType>
 * @param <PropertyDataType>
 * @param <ConcretePropertyType>
 */
public abstract class DisplayAccessor<CollectionDataType, PropertyDataType, ConcretePropertyType extends Property<PropertyDataType> & IndexedProperty<CollectionDataType>>
		extends GenericDisplayCommand<CollectionDataType, PropertyDataType, ConcretePropertyType> {
	private static final String RESOURCE_NONINDEXED_NAME = "NonindexedImageMessage";

	/**
	 * @see DisplayCommand constructor
	 */
	public DisplayAccessor(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * Takes in a PropertyDataType and returns the associated
	 * CollectionDataType, which may not be the same (i.e. turtle images), but
	 * may be (i.e. colors), in which case it returns the input value - similar
	 * in spirit to the Null Object Design Pattern.
	 * @param targetInput
	 */
	protected abstract CollectionDataType convertPropertyTypeToCollectionType(PropertyDataType targetInput);

	@Override
	protected double execute() {
		ConcretePropertyType property = getActiveProperty(getInstructionData().getActiveActor());
		PropertyDataType propertyData = property.getValue();
		List<CollectionDataType> listToSearch = getIndexedCollectionAndCheckList(property);
		return searchList(listToSearch, propertyData);
	}

	/**
	 * Searches listToSearch for the activePropertyValue (converted to the
	 * appropriate CollectionDataType). Returns the found index if found, and if
	 * not, throws an InvalidIndexException.
	 * 
	 * @param listToSearch
	 * @param activePropertyValue
	 * @throws InvalidIndexException
	 */
	private int searchList(List<CollectionDataType> listToSearch, PropertyDataType activePropertyValue) {
		int index = listToSearch.indexOf(convertPropertyTypeToCollectionType(activePropertyValue));
		if (index == -1) {
			throw new InvalidIndexException(RESOURCE_NONINDEXED_NAME);
		}
		return index;
	}

}
