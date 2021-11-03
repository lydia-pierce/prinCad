package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class RectangleItem extends CadItem {
	
	public final double XLeft, YLeft, Width, Height;
	
	public RectangleItem(double xCenter, double yLeft, double width, double height) {
		XLeft = xCenter;
		YLeft = yLeft;
		Width = width;
		Height = height;
	}
	
	// load circle item from string data
	public static RectangleItem load(String data) {
		
		RectangleItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xLeft = Double.parseDouble(tokens[0]);
			double yLeft = Double.parseDouble(tokens[1]);
			double width  = Double.parseDouble(tokens[2]);
			double height  = Double.parseDouble(tokens[2]);
			item = new RectangleItem(xLeft, yLeft, width, height);
		}
		catch (Exception ex) {
			Log.error("Invalid RectangleItem data string: " + data, ex);
		}
		return item;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeRect(XLeft, YLeft, Width, Height);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", XLeft, YLeft, Width, Height);
	}

}
