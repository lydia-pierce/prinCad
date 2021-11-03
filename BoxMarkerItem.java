package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

public class BoxMarkerItem extends MarkerItem {

	public BoxMarkerItem(CadPoint point) {
		super(point);

	}

	@Override
	public void draw(GraphicsContext gc) {
		double x = _point.x - MarkerSize;
		double y = _point.y - MarkerSize;
		
		double s = MarkerSize + MarkerSize;
		
		gc.strokeRect(x, y, s, s);
	}

	// load box marker item from string data
	public static BoxMarkerItem load(String data) {
		
		CadPoint point = MarkerItem.ToPoint(data);
		if (point == null)
			return null;
		
		return new BoxMarkerItem(point);
	}

}
