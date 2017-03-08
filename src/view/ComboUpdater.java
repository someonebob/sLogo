package view;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public abstract class ComboUpdater<T> extends AbstractUpdater
{

	private ComboBox<T> input;

	public ComboUpdater(T defaultValue, T... values)
	{
		input = initializeComboBox(defaultValue, values);
	}

	private ComboBox<T> initializeComboBox(T defaultValue, T... values)
	{
		ComboBox<T> input = new ComboBox<>(FXCollections.observableArrayList(values));
		input.setValue(defaultValue);
		input.setOnAction(e -> {
			this.setChanged();
			this.notifyObservers(input.getValue());
		});
		return input;
	}

	@Override
	public Node display()
	{
		return input;
	}

}
