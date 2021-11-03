package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class SingleLineItem extends CadItem {

	public final double X0, Y0, X1, Y1;
	
	public SingleLineItem(double x0, double y0, double x1, double y1) {
		X0 = x0;
		Y0 = y0;
		X1 = x1;
		Y1 = y1;
	}
	
	// load circle item from string data
	public static SingleLineItem load(String data) {
		
		SingleLineItem item = null;
		try {
			String[] tokens = data.split(" ");
			double x0 = Double.parseDouble(tokens[0]);
			double y0 = Double.parseDouble(tokens[1]);
			double x1  = Double.parseDouble(tokens[2]);
			double y1  = Double.parseDouble(tokens[3]);
			item = new SingleLineItem(x0, y0, x1, y1);
		}
		catch (Exception ex) {
			Log.error("Invalid SingleLineItem data string: " + data, ex);
		}
		return item;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(X0, Y0, X1, Y1);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", X0, Y0, X1, Y1);
	}

}
