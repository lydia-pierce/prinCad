package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

public class ExMarkerItem extends MarkerItem {

	public ExMarkerItem(CadPoint point) {
		super(point);

	}

	@Override
	public void draw(GraphicsContext gc) {
		double x = _point.x;
		double y = _point.y;
		gc.strokeLine(x - MarkerSize, y - MarkerSize, x + MarkerSize, y + MarkerSize);
		gc.strokeLine(x + MarkerSize, y - MarkerSize, x - MarkerSize, y + MarkerSize);
	}

	// load criss cross  marker item from string data
	public static ExMarkerItem load(String data) {
		
		CadPoint point = MarkerItem.ToPoint(data);
		if (point == null)
			return null;
		
		return new ExMarkerItem(point);
	}

}
