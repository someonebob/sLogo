package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tool.FileTool.SaveButton;

public class SingleLineInputBox extends InputBox {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof SaveButton) {
			saveFile((File) arg);
		}
	}

	@Override
	public Node display() {
		// TODO Auto-generated method stub
		return root;
	}


	public void updateData(String arg) {
		previous.getItems().add(arg);
	}

	
	public void enterAction(KeyEvent e) {
		e.consume();
		historyIndex = 0;
	}
	


	public SingleLineInputBox() {
		super();
	}

	

	private void saveFile(File file) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(convertPrevious());
			fw.close();
		} catch (IOException e) {
			Logger.getLogger(SingleLineInputBox.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private String convertPrevious() {
		StringBuilder content = new StringBuilder();
		for (String s : previous.getItems()) {
			content.append(s + "\n");
		}
		content.delete(content.length()-1, content.length());
		return content.toString();
	}

}
