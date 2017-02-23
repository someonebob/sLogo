package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.MenuItem;

public class SettingsTool extends Tool
{
	public static final String name = "Settings";

	public SettingsTool()
	{
		super(name);
	}

	@Override
	public List<MenuItem> makeMenuItems()
	{
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(makeBkgdColorItem());
		return menuItems;
	}

	private MenuItem makeBkgdColorItem()
	{
		MenuItem backgroundColor = new MenuItem("Set Background Color");
		backgroundColor.setOnAction(e -> {
			System.out.println("Nice color m'dude");
		});
		return backgroundColor;
	}

}
