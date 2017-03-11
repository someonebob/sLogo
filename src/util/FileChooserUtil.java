package util;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * 
 * @author jimmy
 *
 */
public final class FileChooserUtil
{

	private FileChooserUtil()
	{
		// This constructor body is intentionally left blank.
	}

	public static FileChooser setupFileChooser(String extensionFilter, String title, File defaultDirectory,
			String... extensions)
	{
		// final String EXTENSION = "*.png";

		FileChooser chooser = new FileChooser();
		chooser.setTitle(title);
		// File defaultDirectory = new File(System.getProperty("user.dir") +
		// "/images");
		chooser.setInitialDirectory(defaultDirectory);
		chooser.getExtensionFilters().setAll(new ExtensionFilter(extensionFilter, extensions));
		// extensionfilter IMAGE

		return chooser;
	}
}