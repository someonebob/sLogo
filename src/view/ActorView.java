package view;

import java.util.Observable;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ActorView implements View{
	
	public static final String SQUIRT = "Squirt.png";
	
	private ImageView actor;
	
	public ActorView(){
		loadImage();
	}

	@Override
	public Node display() {
		// TODO Auto-generated method stub
		return actor;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	private void loadImage(){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(SQUIRT));
		actor = new ImageView(image);
	}

}
