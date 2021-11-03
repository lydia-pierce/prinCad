package csci240.prinCad.control;

import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.ExMarkerItem;
import csci240.prinCad.model.MarkerItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class ExMarkerTool extends MarkerTool {

	@Override
	protected void Draw(GraphicsContext gc, MouseEvent e) {
		
		double x = e.getX();
		double y = e.getY();
		
		gc.strokeLine(x - MarkerItem.MarkerSize, y - MarkerItem.MarkerSize, x + MarkerItem.MarkerSize, y + MarkerItem.MarkerSize);
		gc.strokeLine(x + MarkerItem.MarkerSize, y - MarkerItem.MarkerSize, x - MarkerItem.MarkerSize, y + MarkerItem.MarkerSize);
	}
	
	// Create the marker item
	@Override
	protected MarkerItem createMarkerItem(CadPoint point) {
		return new ExMarkerItem(point);
	}

}
