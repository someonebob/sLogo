//This entire file is part of my masterpiece.
//Matthew Barbano
//See GenericDisplayCommand for description.

package instruction.DisplayCommands;

import java.util.List;

import instruction.InstructionData;
import property.Property;
import util.MathUtil;

/**
 * This class is the root of all Display Command classes that modify the
 * currently displayed feature by accessing a value from an indexed list. They
 * take in a single argument, the index of the indexed list, and return the set
 * value, after setting it. Note that execute() is implemented in this class
 * instead of its concrete subclasses, since generics enable a generalized
 * implementation and prevent repeated code (DRY).
 * 
 * @author Matthew Barbano
 *
 * @param <CollectionDataType>
 * @param <PropertyDataType>
 * @param <ConcretePropertyType>
 */
public abstract class DisplayModifier<CollectionDataType, PropertyDataType, ConcretePropertyType extends Property<PropertyDataType> & IndexedProperty<CollectionDataType>>
		extends GenericDisplayCommand<CollectionDataType, PropertyDataType, ConcretePropertyType> {

	/**
	 * @see DisplayCommand constructor
	 */
	public DisplayModifier(InstructionData instructionData, List<String> args, String myText) {
		super(instructionData, args, myText);
	}

	/**
	 * The second step of execute(), this method takes in the new
	 * PropertyDataType representing the mutated value and updates the active
	 * actor with it.
	 * 
	 * @param newValue
	 */
	protected abstract void updateProperty(PropertyDataType newValue);

	/**
	 * Takes in a CollectionDataType (most likely extracted from the indexed
	 * list) and converts it to the associated PropertyDataType. They are
	 * sometimes different (i.e. with turtle images), but if they are the same
	 * (i.e. with colors), this method just returns the targetInput - similar in
	 * spirit to the Null Object Design Pattern.
	 * 
	 * @param targetInput
	 */
	protected abstract PropertyDataType convertCollectionTypeToPropertyType(CollectionDataType targetInput);

	@Override
	protected double execute() {
		PropertyDataType propertyToUpdate = getNewValue();
		updateProperty(propertyToUpdate);
		return getArgumentDouble(0);
	}

	/**
	 * The first step of execute(), this method determines and returns the
	 * PropertyDataType representing the new value of the property. It also
	 * checks to ensure the index from the SLogo argument is valid (i.e. in
	 * range and an integer).
	 */
	protected PropertyDataType getNewValue() {
		ConcretePropertyType associatedProperty = getActiveProperty(getInstructionData().getActiveActor());
		List<CollectionDataType> listWithValue = getIndexedCollectionAndCheckList(associatedProperty);
		MathUtil.checkValidIndex(getArgumentDouble(0), listWithValue.size());
		return convertCollectionTypeToPropertyType(listWithValue.get((int) getArgumentDouble(0)));
	}
}
