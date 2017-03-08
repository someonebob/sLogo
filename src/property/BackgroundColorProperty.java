package property;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * 
 * @author jimmy
 *
 */
public class BackgroundColorProperty extends AbstractColorProperty
{
	private StackPane root;

	public BackgroundColorProperty(String name, StackPane root)
	{
		super(name);
		this.root = root;
	}

	@Override
	protected void updateColor(Color color)
	{
		root.setBackground(new Background(new BackgroundFill(color, null, null)));
	}
}
