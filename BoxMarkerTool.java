package csci240.prinCad.control;

import csci240.prinCad.model.BoxMarkerItem;
import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.MarkerItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class BoxMarkerTool extends MarkerTool {

	@Override
	protected void Draw(GraphicsContext gc, MouseEvent e) {
		
		double x = e.getX() - MarkerItem.MarkerSize;
		double y = e.getY() - MarkerItem.MarkerSize;
		
		double s = MarkerItem.MarkerSize + MarkerItem.MarkerSize;
		
		gc.strokeRect(x, y, s, s);
	}
	
	// Create the marker item
	@Override
	protected MarkerItem createMarkerItem(CadPoint point) {
		return new BoxMarkerItem(point);
	}

}
