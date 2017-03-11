package tool;

import javafx.scene.control.MenuItem;

public class AbstractDefaultButton extends AbstractMenuItem {

	public AbstractDefaultButton(MenuItem menu) {
		super(menu);
		this.getItem().setOnAction(e -> {
			
		});
	}

}
