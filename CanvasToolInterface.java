package csci240.prinCad.control;

import csci240.prinCad.model.CadItem;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;

// External needs of control library
public interface CanvasToolInterface {

	public GraphicsContext getGC();
	
	public void setCursor(Cursor cursor);
	
	public void draw();
	
	public void reset();
	
	public void reset(CadItem cadItem);
}
