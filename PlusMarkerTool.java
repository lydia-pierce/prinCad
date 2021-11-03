package csci240.prinCad.control;

import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.MarkerItem;
import csci240.prinCad.model.PlusMarkerItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class PlusMarkerTool extends MarkerTool {

	@Override
	protected void Draw(GraphicsContext gc, MouseEvent e) {
		
		double x = e.getX();
		double y = e.getY();
		
		gc.strokeLine(x - MarkerItem.MarkerSize, y, x + MarkerItem.MarkerSize, y);
		gc.strokeLine(x, y - MarkerItem.MarkerSize, x, y + MarkerItem.MarkerSize);
	}
	
	// Create the marker item
	@Override
	protected MarkerItem createMarkerItem(CadPoint point) {
		return new PlusMarkerItem(point);
	}

}
