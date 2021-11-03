package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

public abstract class CadItem {

	public abstract void draw(GraphicsContext gc);
	
	public abstract String save();
}
