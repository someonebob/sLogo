package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public abstract class TextUpdater extends AbstractUpdater
{

	private TextField input;

	public TextUpdater(String defaultText)
	{
		input = initializeTextBox(defaultText);
	}

	private TextField initializeTextBox(String defaultText)
	{
		TextField input = new TextField();
		input.setText(defaultText);
		input.setOnAction(e -> {
			this.setChanged();
			this.notifyObservers(input.getText());
		});
		// update when box is deselected
		input.focusedProperty().addListener(new ChangeListener<Boolean>()
		{
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
			{
				if (!newValue && oldValue) {
					setChanged();
					notifyObservers(input.getText());
				}
			}
		});
		return input;
	}

	@Override
	public Node display()
	{
		return input;
	}

}
