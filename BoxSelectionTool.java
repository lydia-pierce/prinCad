package csci240.prinCad.control;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class BoxSelectionTool extends CadTool {
	
	// Mouse movement properties
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	double _x, _y, _w, _h;

	public BoxSelectionTool() {
		
	}
	
	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {

		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_x = x;
			_y = y;
			_w = 0.0;
			_h = 0.0;
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
			
			_xEnd = e.getX();
			_yEnd = e.getY();
			_x = Math.min(_xPivot, _xEnd);
			_y = Math.min(_yPivot, _yEnd);
			_w = Math.abs(_xEnd - _xPivot);
			_h = Math.abs(_yEnd - _yPivot);
			canvas.getGC().strokeRect(_x, _y, _w, _h);
		}
	}

	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {

		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
		}
	}
}
