package csci240.prinCad.control;

import javafx.scene.input.MouseEvent;

public abstract class CadTool {
	
	// Handle mouse pressed (button down)
	public abstract void onMousePressed(CanvasToolInterface canvas, MouseEvent e);
	
	// Handle mouse drag (only called when mouse button IS depressed)
	public abstract void onMouseDrag(CanvasToolInterface canvas, MouseEvent e);
	
	// Handle mouse release (button up)
	public abstract void onMouseRelease(CanvasToolInterface canvas, MouseEvent e);
	
}
