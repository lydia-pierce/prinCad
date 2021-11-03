package csci240.prinCad.control;

import csci240.prinCad.model.CircleItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CircleTool extends CadTool {
	
	// Mouse movement properties
	boolean _activeMouse;
	double _xc, _yc, _r;

	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {

		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xc = x;
			_yc = y;
			_r = 0;
			_activeMouse = true;
			canvas.getGC().setStroke(Color.ORANGERED);
			canvas.getGC().setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
		}
	}

	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			
			canvas.draw();
			
			double xr = e.getX();
			double yr = e.getY();
			double dx = xr - _xc;
			double dy = yr - _yc;
			_r = Math.sqrt(dx * dx + dy * dy);
			canvas.getGC().strokeOval(_xc - _r, _yc - _r, _r + _r, _r + _r);
		}
	}

	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			
			canvas.reset(new CircleItem(_xc, _yc, _r));
		}
	}

}
