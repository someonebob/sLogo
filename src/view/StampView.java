package view;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Graphical representation of a stamp. A stamp is essentially the clone of an
 * ActorView.
 * 
 * @author jimmy
 *
 */
public class StampView implements View
{
	private ImageView stamp;

	public StampView(ActorView actor)
	{
		stamp = new ImageView(actor.getImageView().getImage());
		stamp.setPreserveRatio(true);
		stamp.setFitWidth(actor.getImageView().getFitWidth());
		stamp.setFitHeight(actor.getImageView().getFitHeight());
		stamp.setTranslateX(actor.getLocation().getX());
		stamp.setTranslateY(actor.getLocation().getY());
		stamp.setRotate(actor.getHeading());
		stamp.setEffect(actor.getImageView().getEffect());
	}

	@Override
	public Node display()
	{
		return stamp;
	}

}
