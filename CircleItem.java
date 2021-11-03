package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class CircleItem extends CadItem {
	
	public final double XCenter;
	public final double YCenter;
	public final double Radius;
	public final double Diameter; 
	
	public CircleItem(double xCenter, double yCenter, double radius) {
		XCenter = xCenter;
		YCenter = yCenter;
		Radius = radius;
		Diameter = Radius + Radius;
	}
	
	// load circle item from string data
	public static CircleItem load(String data) {
		
		CircleItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xc = Double.parseDouble(tokens[0]);
			double yc = Double.parseDouble(tokens[1]);
			double r  = Double.parseDouble(tokens[2]);
			item = new CircleItem(xc, yc, r);
		}
		catch (Exception ex) {
			Log.error("Invalid CircleItem data string: " + data, ex);
		}
		return item;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(XCenter - Radius, YCenter - Radius, Diameter, Diameter);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f", XCenter, YCenter, Radius);
	}

}
