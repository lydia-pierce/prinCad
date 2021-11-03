package csci240.prinCad.ui;

import csci240.prinCad.command.BoxMarkerCommand;
import csci240.prinCad.command.CircleCommand;
import csci240.prinCad.command.EllipseCommand;
import csci240.prinCad.command.ExMarkerCommand;
import csci240.prinCad.command.PlusMarkerCommand;
import csci240.prinCad.command.PolylineCommand;
import csci240.prinCad.command.RectangleCommand;
import csci240.prinCad.command.SingleLineCommand;
import csci240.prinCad.command.SplineCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

// Collection of CAD tool related commands
public class CadToolManager {
	
	private PlusMarkerCommand _plusMarker;
	private BoxMarkerCommand _boxMarker;
	private ExMarkerCommand _exMarker;
	private SingleLineCommand _singleLine;
	private RectangleCommand _rectangle;
	private CircleCommand _circle;
	private EllipseCommand _ellipse;
	private PolylineCommand _polyline;
	private SplineCommand _spline;
	
	// Constructor
	public CadToolManager(PrinCanvas canvas) {
		
		_plusMarker = new PlusMarkerCommand(canvas);
		_boxMarker = new BoxMarkerCommand(canvas);
		_exMarker = new ExMarkerCommand(canvas);
		_singleLine = new SingleLineCommand(canvas);
		_rectangle = new RectangleCommand(canvas);
		_circle = new CircleCommand(canvas);
		_ellipse = new EllipseCommand(canvas);
		_polyline = new PolylineCommand(canvas);
		_spline = new SplineCommand(canvas);
	}
	
	// Build menu
	public Menu buildMenu() {
		
        // create marker menu items 
        MenuItem miPlus = new MenuItem("Plus"); 
        miPlus.setOnAction(e -> _plusMarker.action(e));
		
        MenuItem miBox = new MenuItem("Box"); 
        miBox.setOnAction(e -> _boxMarker.action(e));
		
        MenuItem miEx = new MenuItem("Crisscross"); 
        miEx.setOnAction(e -> _exMarker.action(e));
        
        Menu markerMenu = new Menu("Markers");
        ObservableList<MenuItem> markerMenuItems = markerMenu.getItems();
        
        // add individual marker menu items to menu
        markerMenuItems.add(miPlus);
        markerMenuItems.add(miBox);
        markerMenuItems.add(miEx);
		
        // create cad tool menu items
        MenuItem miSl = new MenuItem("Single Line"); 
        miSl.setOnAction(e -> _singleLine.action(e));
		
        MenuItem miRect = new MenuItem("Rectangle"); 
        miRect.setOnAction(e -> _rectangle.action(e));
		
        MenuItem miCircle = new MenuItem("Circle"); 
        miCircle.setOnAction(e -> _circle.action(e));
		
        MenuItem miEllipse = new MenuItem("Ellipse"); 
        miEllipse.setOnAction(e -> _ellipse.action(e));
		
        MenuItem miPolyline = new MenuItem("Polyline"); 
        miPolyline.setOnAction(e -> _polyline.action(e));
		
        MenuItem miSpline = new MenuItem("Spline"); 
        miSpline.setOnAction(e -> _spline.action(e));
		
        // create a menu 
        Menu cadToolMenu = new Menu("CAD Tools"); 
        ObservableList<MenuItem> cadToolMenuItems = cadToolMenu.getItems();
  
        // add menu items to menu 
        cadToolMenuItems.add(markerMenu); 
        cadToolMenuItems.add(miSl); 
        cadToolMenuItems.add(miRect); 
        cadToolMenuItems.add(miCircle);
        cadToolMenuItems.add(miEllipse);
        cadToolMenuItems.add(miPolyline);
        cadToolMenuItems.add(miSpline);
	
        return cadToolMenu;
	}
	
	// Add buttons to bar
	public void addButtonsToBar(ObservableList<Node> nodes) {
		
		Button plusb = new Button();
		plusb.setMinWidth(80);
		plusb.setText("+ Marker");
		plusb.setOnAction(e -> _plusMarker.action(e));
		
		Button boxb = new Button();
		boxb.setMinWidth(80);
		boxb.setText("Box Marker");
		boxb.setOnAction(e -> _boxMarker.action(e));
		
		Button exb = new Button();
		exb.setMinWidth(80);
		exb.setText("x Marker");
		exb.setOnAction(e -> _exMarker.action(e));
		
		Button slb = new Button();
		slb.setMinWidth(80);
		slb.setText("Single Line");
		slb.setOnAction(e -> _singleLine.action(e));
		
		Button rectb = new Button();
		rectb.setMinWidth(80);
		rectb.setText("Rectangle");
		rectb.setOnAction(e -> _rectangle.action(e));
		
		Button cirb = new Button();
		cirb.setMinWidth(80);
		cirb.setText("Circle");
		cirb.setOnAction(e -> _circle.action(e));
		
		Button ellb = new Button();
		ellb.setMinWidth(80);
		ellb.setText("Ellipse");
		ellb.setOnAction(e -> _ellipse.action(e));
		
		Button pllb = new Button();
		pllb.setMinWidth(80);
		pllb.setText("Polyline");
		pllb.setOnAction(e -> _polyline.action(e));
		
		Button splineb = new Button();
		splineb.setMinWidth(80);
		splineb.setText("Spline");
		splineb.setOnAction(e -> _spline.action(e));
	
		nodes.add(plusb);
		nodes.add(boxb);
		nodes.add(exb);
		nodes.add(slb);
		nodes.add(rectb);
		nodes.add(cirb);
		nodes.add(ellb);
		nodes.add(pllb);
		nodes.add(splineb);
	}

}
