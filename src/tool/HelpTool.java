package tool;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class HelpTool extends Tool
{
	public static final String name = "Help";

	private List<AbstractButton> buttons;

	public HelpTool(Stage window)
	{
		super(name, window);
	}

	@Override
	public void makeMenuItems()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public List<AbstractButton> getButtons()
	{
		buttons = new ArrayList<AbstractButton>();
		buttons.add(new HelpButton());
		return buttons;
	}

	public class HelpButton extends AbstractButton
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