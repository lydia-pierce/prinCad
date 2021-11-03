package csci240.prinCad.control;

import java.util.ArrayList;

import csci240.prinCad.model.CadPoint;
import csci240.prinCad.model.PolylineItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolylineTool extends CadTool {
	
	// Mouse movement properties
	boolean _activeMouse;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	
	ArrayList<CadPoint> _points;
	
	public PolylineTool() {
		_points = new ArrayList<CadPoint>();
	}

	@Override
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
	}

	@Override
	public void onMouseDrag(CanvasToolInterface canvas, MouseEvent e) {
		
		if (_activeMouse) {

			canvas.draw();
			
			CadPoint p1 = _points.get(0);
			for (int i=1; i<_points.size(); i++) {
				CadPoint p2 = _points.get(i);
				canvas.getGC().strokeLine(p1.x, p1.y, p2.x, p2.y);
				p1 = p2;
			}
			_xEnd = e.getX();
			_yEnd = e.getY();
			canvas.getGC().strokeLine(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}

	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			CadPoint p = new CadPoint(x,y);
			_points.add(p);
			
			if (_activeMouse) {
				_xPivot = _xEnd;
				_yPivot = _yEnd;
				_xEnd = x;
				_yEnd = y;
			}
			else {
				_xPivot = x;
				_yPivot = y;
				_xEnd = x;
				_yEnd = y;
				_activeMouse = true;
				canvas.getGC().setStroke(Color.ORANGERED);
				canvas.getGC().setLineWidth(0);
				canvas.setCursor(Cursor.CROSSHAIR);
			}
		}
		else if (e.getButton() == MouseButton.SECONDARY && _activeMouse) {
			
			canvas.draw();
			
			CadPoint p1 = _points.get(0);
			for (int i=1; i<_points.size(); i++) {
				CadPoint p2 = _points.get(i);
				canvas.getGC().strokeLine(p1.x, p1.y, p2.x, p2.y);
				p1 = p2;
			}
			
			canvas.setCursor(Cursor.DEFAULT);
			CadPoint[] points = new CadPoint[_points.size()];
			points = _points.toArray(points);
			canvas.reset(new PolylineItem(points));
		}
	}

}
