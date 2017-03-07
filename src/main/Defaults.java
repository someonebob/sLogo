package main;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public final class Defaults {
	private Paint background;
	private Paint pen;
	private String image;
	private int numTurtles;
	private String language;
	
	public Defaults(String background, String pen, String image, int numTurtles, String language){
		this.background = Color.web(background);
		this.pen = Color.web(pen);
		this.image = image;
		this.numTurtles = numTurtles;
		this.language= language;
		
	}
	public Paint background(){
		return background;
	}
	public Paint pen(){
		return pen;
	}
	public String image(){
		return image;
	}
	public int numTurtles(){
		return numTurtles;
	}
	public String language(){
		return language;
	}

}
