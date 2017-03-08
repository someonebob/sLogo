package view;

import java.util.Observable;

import javafx.scene.Node;

public abstract class AbstractUpdater extends Observable
{
	public abstract Node display();
}
