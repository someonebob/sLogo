package tool;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class HelpMenuTool extends MenuTool
{
	public static final String name = "Help";

	private List<AbstractMenuItem> buttons;

	public HelpMenuTool(Stage window)
	{
		super(name, window);
	}

	@Override
	public void makeItems()
	{

	}

	@Override
	protected List<AbstractMenuItem> getButtons()
	{
		buttons = new ArrayList<AbstractMenuItem>();
		buttons.add(new HelpButton());
		return buttons;
	}

	public class HelpButton extends AbstractMenuItem
	{
		public HelpButton()
		{
			super(new MenuItem("Help Page"));
			this.getItem().setOnAction(e -> {
				File htmlFile = new File("data/HelpPage.html");
				if (Desktop.isDesktopSupported()) {
					loadPage(htmlFile.toURI());
				}
			});
		}

		private void loadPage(URI uri)
		{
			new Thread(() -> {
				try {
					Desktop.getDesktop().browse(uri);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}).start();
		}
	}

}