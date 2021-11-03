package csci240.prinCad.model;

import java.io.PrintWriter;
import java.util.ArrayList;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class ModelManager {
	
	// Current file path (can be null if new project)
	private String _currentFilePath;
	public String GetFilePath() {
		return _currentFilePath;
	}
	public void SetFilePath(String filePath) {
		_currentFilePath = filePath;
	}

	ArrayList<CadItem> _items;
	
	public ModelManager() {
		_items = new ArrayList<CadItem>();
	}
	
	public void add(CadItem item) {
		_items.add(item);
	}
	
	public void clear() {
		_items.clear();
	}
	
	public void draw(GraphicsContext gc) {
		
		for (CadItem item : _items) {
			item.draw(gc);
		}
	}
	
	public void save(PrintWriter out) {
		
		for (CadItem item : _items) {
			Class<? extends CadItem> cl = item.getClass();
			String className = cl.getName();
				
			String data = item.save();
				
			out.println(String.format("<%1$s>%2$s</%1$s>", className, data));
		}
	}
	
	public void load(String line) {
		
		try {
				
			int i1 = line.indexOf('<') + 1;
			int i2 = line.indexOf('>');
			String classType = line.substring(i1, i2);
			int i3 = line.indexOf("</" + classType + ">");
			String data = line.substring(i2 + 1, i3);
			
			CadItem cadItem;
			switch (classType) {
			case "csci240.prinCad.model.CircleItem":
				cadItem = CircleItem.load(data);
				break;
			case "csci240.prinCad.model.SingleLineItem":
				cadItem = SingleLineItem.load(data);
				break;
			case "csci240.prinCad.model.RectangleItem":
				cadItem = RectangleItem.load(data);
				break;
			case "csci240.prinCad.model.EllipseItem":
				cadItem = EllipseItem.load(data);
				break;
			case "csci240.prinCad.model.PolylineItem":
				cadItem = PolylineItem.load(data);
				break;
			case "csci240.prinCad.model.BoxMarkerItem":
				cadItem = BoxMarkerItem.load(data);
				break;
			case "csci240.prinCad.model.ExMarkerItem":
				cadItem = ExMarkerItem.load(data);
				break;
			case "csci240.prinCad.model.PlusMarkerItem":
				cadItem = PlusMarkerItem.load(data);
				break;
			default:
				throw new Exception("Failed to parse line: " + line);
			}
			if (cadItem != null) {
				_items.add(cadItem);
			}
		}
		catch (Exception ex) {
			Log.error("Failed to load file. Exception: ", ex);
		}
	}

}
