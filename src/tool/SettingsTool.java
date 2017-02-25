package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.paint.Color;

/**
 * @author jimmy
 * @author Jesse
 *
 */
public class SettingsTool extends Tool
{
	public static final String name = "Settings";
	
	private List<AbstractButton> buttons;

	public SettingsTool()
	{
		super(name);
	}

	@Override
	public void makeMenuItems()
	{
		buttons = new ArrayList<>();
		buttons.add(new backgroundColorButton());
		/*
		buttons.add(makeActorColorItem());
		buttons.add(makePenColorItem());
		buttons.add(new SeparatorMenuItem());
		buttons.add(makeLanguageItem());
		*/
	}
	
	public List<AbstractButton> getButtons(){
		return buttons;

	}
	
	public class backgroundColorButton extends AbstractButton
	{
		private Color color;
		
		public backgroundColorButton()
		{
			super(new MenuItem("Background Color"));
			color = Color.BLACK;
			this.setOnAction(color);
		}
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
