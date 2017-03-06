package main;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public final class Defaults {
	private Paint background;
	private String image;
	private int numTurtles;
	private String language;
	
	public Defaults(String color, String image, int numTurtles, String language){
		background = Color.web(color);
		this.image = image;
		this.numTurtles = numTurtles;
		this.language= language;
		
	}
	public Paint background(){
		return background;
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
