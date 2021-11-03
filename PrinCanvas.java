package csci240.prinCad.ui;

import csci240.prinCad.control.BoxSelectionTool;
import csci240.prinCad.control.CadTool;
import csci240.prinCad.control.CanvasToolInterface;
import csci240.prinCad.control.LineSelectionTool;
import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.ModelManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

// Drawing canvas for the Prin CAD tools application
public class PrinCanvas extends Canvas implements CanvasToolInterface {
	
	// Reference to graphics context
	private GraphicsContext _gc;
	public GraphicsContext getGC() { return _gc; }
	
	// Current selection tool
	private CadTool _selectionTool;
	
	// Active tool
	private CadTool _activeTool;
	public void setActiveTool(CadTool activeTool) { _activeTool = activeTool; }
	
	// The model attached to this canvas
	private ModelManager _model;
	public ModelManager getModel() { return _model; }

	// Data constructor
	public PrinCanvas(double width, double height) {
		// invoke (call) parent class constructor
		super(width, height);

		// Get graphics context and fill with background color
		_gc = getGraphicsContext2D();
		_gc.setFill(MainForm.appSettings.getCanvasBackgroundColor());
		_gc.fillRect(0, 0, width, height);
		
		// Subscribe to mouse events
		setOnMousePressed(e -> onMousePressed(e));
		setOnMouseDragged(e -> onMouseDrag(e));
		setOnMouseReleased(e -> onMouseRelease(e));
		setOnMouseMoved(e -> onMouseMoved(e));
		
		// Default to box selection
		_selectionTool = new BoxSelectionTool();
		_activeTool = _selectionTool;
		
		// Attach a model to this canvas
		_model = new ModelManager();
	}
	
	// Toggle selection type
	public void toggleSelectionType() { 
		if (_selectionTool instanceof BoxSelectionTool) {
			_selectionTool = new LineSelectionTool();
		}
		else {
			_selectionTool = new BoxSelectionTool();
		}
		_activeTool = _selectionTool;
	}
	
	// Set back to selection mode
	public void reset() {
		_activeTool = _selectionTool;
	}
	
	// Save created graphic object and set back to selection mode
	public void reset(CadItem cadItem) {
		_model.add(cadItem);
		_activeTool = _selectionTool;
	}
		
	// Draw all graphic objects
	public void draw() {
		_gc.fillRect(0, 0, getWidth(), getHeight());
		
		_gc.setStroke(Color.ORANGERED);
		_gc.setLineWidth(0);
		
		_model.draw(_gc);
	}
	
	// Handle mouse pressed (button down)
	private void onMousePressed(MouseEvent e) {
		
		_activeTool.onMousePressed(this, e);
	}
	
	// Handle mouse drag (only called when mouse button IS depressed)
	private void onMouseDrag(MouseEvent e) {
		
		_activeTool.onMouseDrag(this, e);
	}
	
	// Handle mouse move (only called when mouse button IS NOT depressed)
	private void onMouseMoved(MouseEvent e) {
		
		_activeTool.onMouseDrag(this, e);
	}
	
	// Handle mouse release (button up)
	private void onMouseRelease(MouseEvent e) {
		
		_activeTool.onMouseRelease(this, e);
	}
}
