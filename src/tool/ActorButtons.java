// This entire file is part of my masterpiece.
// Jesse Yue
// This is a concrete implementation of a ToolButton. It contains inner classes that define the individual buttons in the group.
// The use of inner classes made sense because all of these items are related to the same functionality and should be contained together for organization.
// Each of the buttons extends the AbstractButton which gets rid of a lot of duplicated code.
// Observers are notified in the lambda expression of the buttons so that something can happen when there is an action.
package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;

public class ActorButtons extends ToolButton {
	private List<AbstractButton> buttons;

	public ActorButtons() {
		super();
	}

	@Override
	public void makeItems() {
		buttons = new ArrayList<>();
		addButtons(buttons, new CreateActorButton(), new DeleteActorButton());
	}

	@Override
	protected List<AbstractButton> getButtons() {
		return buttons;
	}

	public class CreateActorButton extends AbstractButton {
		public CreateActorButton() {
			super(new Button("New Actor"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
	}

	public class DeleteActorButton extends AbstractButton {
		public DeleteActorButton() {
			super(new Button("Delete Actor"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}
	}
}
