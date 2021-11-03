package csci240.prinCad.control;

import csci240.prinCad.model.EllipseItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EllipseTool extends CadTool {
	
	// Mouse movement properties
	boolean _activeMouse;
	double _xc, _yc, _xr, _yr;

	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {

	}

	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			
			canvas.draw();
			
			double xr = e.getX();
			double yr = e.getY();
			double dx = xr - _xc;
			double dy = yr - _yc;
			_xr = Math.abs(dx);
			_yr = Math.abs(dy);
			canvas.getGC().strokeOval(_xc - _xr, _yc - _yr, _xr + _xr, _yr + _yr);
		}
	}

	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			
			canvas.reset(new EllipseItem(_xc, _yc, _xr, _yr));
		}
		else if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xc = x;
			_yc = y;
			_xr = 0;
			_yr = 0;
			_activeMouse = true;
			canvas.getGC().setStroke(Color.ORANGERED);
			canvas.getGC().setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
		}
	}

}
