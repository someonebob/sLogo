package property;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.FileChooserUtil;

//This entire file is part of my masterpiece.
//Jimmy Shackford

/**
 * This factory class is used to create a dynamic updater for any arbitrary
 * property type. The factory design pattern allows you to check if a property
 * type is of a given class, and based on that class you can create a dynamic
 * updater. The importance of this code is that it reduces reused code (ex:
 * PenThicknessProperty and SpeedProperty used to have essentially repeated code
 * for creating a slider, but now they don't). Moreover, this code ensures that
 * the program will work for properties that don't have a defined
 * makeDynamicUpdaters() method. If the property type is supported in this
 * class, then this class will make the dynamic updater within the Property
 * superclass. With this code, coders no longer have to type their own
 * makeDynamicUpdaters() method when they make a new property. Finally, if a
 * property type isn't supported within this class, it defaults to a String
 * value and the program doesn't crash.
 * 
 * @author jimmy
 */
public class DynamicUpdaterFactory<T>
{
	private final String SET_STRING = "Set %s";
	private final String IMAGE_NAME = "IMAGE";

	/**
	 * Make a Dynamic updater for the given property. In some cases, a min
	 * and/or max value (extrema) might be needed to make the updater.
	 * 
	 * Supported property class types: Double: makes a slider which defaults the
	 * values from 1 to 10. Color: makes a colorpicker Point2D: x and y input
	 * boxes, updates value when press "move" button. ImageView: make a new
	 * screen with a filechooser. Update the image to the chosen files' image.
	 * Boolean: make a togglebutton. If it is pressed, the value is true, else
	 * false.
	 * 
	 * If the property class type is none of these, then display a String
	 * textField that allows the user to update the property by String value
	 * (must match the String syntax of xml file)
	 * 
	 * @param property
	 *            Property that Factory is making a dynamic updater for.
	 * @param extrema
	 *            Min and/or max value (primarily for Double slider)
	 * @return Dynamic updater, which can be displayed through the GUI to allow
	 *         the user to update the property.
	 */
	public List<Node> makeDynamicUpdater(Property<T> property, double... extrema)
	{
		Label label = new Label(String.format(SET_STRING, property.getName()));
		Class<T> clazz = property.getClassType();
		T defaultValue = property.getValue();

		if (clazz.equals(Double.class)) {
			double minValue = 1;
			double maxValue = 10;
			if (extrema.length == 2) {
				minValue = extrema[0];
				maxValue = extrema[1];
			}
			Slider slider = makeSlider(minValue, maxValue, (Double) defaultValue);
			slider.valueProperty().addListener(new ChangeListener<Number>()
			{
				@Override
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
				{
					property.setValue((T) new_val);
				}
			});
			return Arrays.asList(label, slider);
		}

		if (clazz.equals(Color.class)) {
			ColorPicker colorPicker = makeColorPicker((Color) defaultValue);
			colorPicker.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent t)
				{
					property.setValue((T) colorPicker.getValue());
				}
			});
			return Arrays.asList(label, colorPicker);
		}

		if (clazz.equals(Point2D.class)) {
			VBox xBox = new VBox();
			VBox yBox = new VBox();
			HBox xyBox = new HBox();
			xBox.getChildren().add(new Label("x"));
			yBox.getChildren().add(new Label("y"));
			TextField x = new TextField();
			TextField y = new TextField();
			xBox.getChildren().add(x);
			yBox.getChildren().add(y);
			xyBox.setPrefWidth(100);
			xyBox.getChildren().addAll(xBox, yBox);
			Button moveButton = new Button("Move");

			moveButton.setOnAction(e -> {
				property.setValue((T) new Point2D(Integer.valueOf(x.getText()), Integer.valueOf(y.getText())));
			});

			return Arrays.asList(label, xyBox, moveButton);
		}

		if (clazz.equals(ImageView.class)) {
			Button input = new Button();
			input.setText(property.getName());
			Stage newWindow = new Stage();
			input.setOnAction(e -> {
				File selectedFile = FileChooserUtil.setupFileChooser(IMAGE_NAME, "New Image",
						new File(System.getProperty("user.dir") + "/images"), "*.png", "*.gif")
						.showOpenDialog(newWindow);
				if (selectedFile != null) {
					property.setValue((T) new ImageView(new Image(selectedFile.toURI().toString())));
				}
			});
			return Arrays.asList(label, input);
		}

		if (clazz.equals(Boolean.class)) {
			ToggleButton upDownButton = new ToggleButton();

			upDownButton.setOnAction(e -> {
				if (!upDownButton.isPressed()) {
					property.setValue((T) Boolean.TRUE);
				} else if (upDownButton.isPressed()) {
					property.setValue((T) Boolean.FALSE);
				}
			});
			return Arrays.asList(label, upDownButton);
		}

		else {
			TextField input = new TextField();
			input.setPromptText(defaultValue.toString());
			input.setOnAction(e -> {
				property.setValue(input.getText());
			});

			return Arrays.asList(label, input);
		}
	}

	private Slider makeSlider(double minValue, double maxValue, double defaultValue)
	{
		Slider slider = new Slider();
		slider.setMin(minValue);
		slider.setMax(maxValue);
		slider.setValue(defaultValue);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit((int) ((maxValue - minValue) / 10) + 1);
		slider.setMinorTickCount((int) ((maxValue - minValue) / 2));
		return slider;
	}

	private ColorPicker makeColorPicker(Color defaultValue)
	{
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setValue(defaultValue);
		return colorPicker;
	}
}
