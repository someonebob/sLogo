package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class SavedCommandsView implements PageView
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

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void updateData(String arg)
	{
		// TODO Auto-generated method stub

	}

	private void initiateItems()
	{
		scroll = new ScrollPane();
		commands = new VBox(10);
		scroll.setContent(commands);
	}

}
