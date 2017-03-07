package tool;

import javafx.scene.control.MenuItem;

public class AbstractDefaultButton extends AbstractButton {

	public AbstractDefaultButton(MenuItem menu) {
		super(menu);
		this.getItem().setOnAction(e -> {
			
		});
	}

}
