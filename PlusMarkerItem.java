package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

public class PlusMarkerItem extends MarkerItem {

	public PlusMarkerItem(CadPoint point) {
		super(point);

	}

	@Override
	public void draw(GraphicsContext gc) {
		double x = _point.x;
		double y = _point.y;
		gc.strokeLine(x - MarkerSize, y, x + MarkerSize, y);
		gc.strokeLine(x, y - MarkerSize, x, y + MarkerSize);
	}

	// load plus marker item from string data
	public static PlusMarkerItem load(String data) {
		
		CadPoint point = MarkerItem.ToPoint(data);
		if (point == null)
			return null;
		
		return new PlusMarkerItem(point);
	}

}
