package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class EllipseItem extends CadItem {

	public final double XCenter;
	public final double YCenter;
	public final double XRadius;
	public final double YRadius; 

	public EllipseItem(double xCenter, double yCenter, double xRadius, double yRadius) {
		XCenter = xCenter;
		YCenter = yCenter;
		XRadius = xRadius;
		YRadius = yRadius;
	}
	
	// load ellipse item from string data
	public static EllipseItem load(String data) {
		
		EllipseItem item = null;
		try {
			String[] tokens = data.split(" ");
			double xc = Double.parseDouble(tokens[0]);
			double yc = Double.parseDouble(tokens[1]);
			double xr  = Double.parseDouble(tokens[2]);
			double yr  = Double.parseDouble(tokens[3]);
			item = new EllipseItem(xc, yc, xr, yr);
		}
		catch (Exception ex) {
			Log.error("Invalid EllipseItem data string: " + data, ex);
		}
		return item;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(XCenter - XRadius, YCenter - YRadius, XRadius + XRadius, YRadius + YRadius);
	}

	@Override
	public String save() {
		return String.format("%1$f %2$f %3$f %4$f", XCenter, YCenter, XRadius, YRadius);
	}

}
