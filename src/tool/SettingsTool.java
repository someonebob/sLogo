package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 * @author jimmy
 * @author Jesse
 *
 */
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
		menuItems.add(makeActorColorItem());
		menuItems.add(makePenColorItem());
		menuItems.add(new SeparatorMenuItem());
		menuItems.add(makeLanguageItem());
		return menuItems;
	}

	private MenuItem makeBkgdColorItem()
	{
		Menu backgroundColor = new Menu("Background Color");
		
		MenuItem red = new MenuItem("Red");
		MenuItem green = new MenuItem("Green");
		MenuItem blue = new MenuItem("Blue");
		
		backgroundColor.getItems().addAll(red, green, blue);
		
		
		return backgroundColor;
	}
	
	private MenuItem makeActorColorItem()
	{
		MenuItem actorColor = new MenuItem("Actor Color");
		actorColor.setOnAction(e -> {
			System.out.println("Nice color m'dude");
		});
		return actorColor;
	}
	
	private MenuItem makePenColorItem()
	{
		MenuItem penColor = new MenuItem("Pen Color");
		penColor.setOnAction(e -> {
			System.out.println("Nice color m'dude");
		});
		return penColor;
	}
	
	private MenuItem makeLanguageItem()
	{
		MenuItem language = new MenuItem("Language");
		language.setOnAction(e -> {
			System.out.println("Nice color m'dude");
		});
		return language;
	}

}
