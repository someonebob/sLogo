package property;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

/**
 * 
 * @author jimmy
 *
 */
public class ImageColorProperty extends AbstractColorProperty
{
	ImageProperty actingImage;

	public ImageColorProperty(String name, ImageProperty image)
	{
		super(name);
		actingImage = image;
	}

	@Override
	protected void updateColor(Color color)
	{

		Lighting lighting = new Lighting();
		lighting.setDiffuseConstant(1.0);
		lighting.setSpecularConstant(0.0);
		lighting.setSpecularExponent(0.0);
		lighting.setSurfaceScale(0.0);
		lighting.setLight(new Light.Distant(45, 45, color));

		actingImage.getValue().setEffect(lighting);
		actingImage.updateDisplay();
	}
}
