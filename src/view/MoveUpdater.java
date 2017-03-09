package view;

import javafx.scene.Node;
import javafx.scene.control.TextField;

public abstract class MoveUpdater extends AbstractUpdater
{
	private TextField input;

	public MoveUpdater(String defaultText)
	{
		input = initializeTextBox(defaultText);
	}

	private TextField initializeTextBox(String defaultText)
	{
		TextField input = new TextField();
		input.setPromptText(defaultText);
		input.setOnAction(e -> {
			this.setChanged();
			this.notifyObservers(input.getText());
		});
		return input;
	}

	@Override
	public Node display()
	{
		return input;
	}

}
