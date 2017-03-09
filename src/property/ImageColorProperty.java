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
		// ColorAdjust monochrome = new ColorAdjust();
		// monochrome.setSaturation(-1.0);

		// actor.getImage().setClip(actor.getImage());
		// actor.getImage().setClip(value);

		Lighting lighting = new Lighting();
		lighting.setDiffuseConstant(1.0);
		lighting.setSpecularConstant(0.0);
		lighting.setSpecularExponent(0.0);
		lighting.setSurfaceScale(0.0);
		lighting.setLight(new Light.Distant(45, 45, color));

		// Blend changeColor = new Blend(BlendMode.MULTIPLY, monochrome,
		// new
		// ColorInput(0, 0,
		// actor.getImage().getFitWidth(),
		// actor.getImage().getFitHeight(),
		// Color.ORANGE));
		// actor.getImage().setEffect(lighting);
		actingImage.getValue().setEffect(lighting);
		actingImage.updateDisplay();
	}
}
