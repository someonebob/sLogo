package view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class SavedCommandsView implements View
{
	private ScrollPane scroll;
	private VBox commands;

	public SavedCommandsView()
	{
		initiateItems();
	}

	@Override
	public Node display()
	{
		return scroll;
	}


	private void initiateItems()
	{
		scroll = new ScrollPane();
		commands = new VBox(10);
		scroll.setContent(commands);
	}

}
