package view;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * TODO: 
 * --> Get rid of offset
 * --> Comments
 * --> Image settings
 * 
 * @author maddiebriere
 *
 */

public class StampView implements View{
	private static final double OFFSET = 35;
	
	private Canvas canvas;
	private int numStamps;
	
	public StampView(){
		numStamps = 0;
		canvas = new Canvas();
	}
	
	public void addStamp(Image image, Point2D location){
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(image, location.getX() + canvas.getWidth() / 2 - OFFSET, 
				location.getY() + canvas.getHeight() / 2 - OFFSET);
		numStamps++;
	}
	
	public boolean clear(){
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		int temp = numStamps;
		numStamps = 0;
		return temp!=0;
	}
	
	@Override
	public Node display() {
		return canvas;
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
}
