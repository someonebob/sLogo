package property;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

/**
 * 
 * @author jimmy
 *
 */
public class PenColorProperty extends AbstractColorProperty
{
	private Canvas pen;

	public PenColorProperty(String name, Canvas pen)
	{
		super(name);
		this.pen = pen;
	}

	@Override
	public void setValue(Color color)
	{
		if (color != Color.TRANSPARENT) {
			super.setValue(color);
		}
		updateColor(color);
	}

	@Override
	protected void updateColor(Color color)
	{
		pen.getGraphicsContext2D().setFill(color);
		pen.getGraphicsContext2D().setStroke(color);
	}
}
