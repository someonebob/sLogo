package user_structures;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Superclass to VariableData and FunctionData, created to remove duplicated code.
 * Commonality represented in 'name' as both Variables and Functions are
 * identified via name (hence the shared compareTo and equals methods).
 * 
 * @author maddiebriere
 *
 */

public abstract class StructureData implements Comparable<StructureData> {
	private StringProperty name;

	public StructureData(String name) {
		setName(name);
	}

	@Override
	public int compareTo(StructureData o) {
		if (this == o) {
			return 0;
		}
		return this.getName().compareTo(o.getName());
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof StructureData)) {
			return false;
		}
		if (this == o) {
			return true;
		}
		return this.getName().equals(((StructureData) o).getName());
	}

	public StringProperty nameProperty() {
		if (name == null) {
			name = new SimpleStringProperty(this, "");
		}
		return name;
	}

	public void setName(String value) {
		nameProperty().set(value);
	}

	public String getName() {
		return nameProperty().get();
	}

}
