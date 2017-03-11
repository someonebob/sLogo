package tool;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;

public class ActorButtons extends ToolButton
{
	private List<AbstractButton> buttons;

	public ActorButtons()
	{
		super();
	}

	@Override
	public void makeItems()
	{
		buttons = new ArrayList<>();
		addButtons(buttons, new CreateActorButton(), new DeleteActorButton());
	}

	@Override
	protected List<AbstractButton> getButtons()
	{
		return buttons;
	}

	public class CreateActorButton extends AbstractButton
	{

		public CreateActorButton()
		{
			super(new Button("New Actor"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});

		}

	}

	public class DeleteActorButton extends AbstractButton
	{

		public DeleteActorButton()
		{
			super(new Button("Delete Actor"));
			this.getItem().setOnMouseClicked(e -> {
				this.setChanged();
				this.notifyObservers();
			});
		}

	}

}
