package csci240.prinCad.ui;

import java.io.File;
import java.net.URL;

import csci240.prinCad.util.Log;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

// MainForm - the main window into the PrinCad application
public class MainForm extends Application {

	// Manager of file commands
	private FileManager _fileManager;
	
	// Manager of edit commands
	private EditManager _editManager;
	
	// Manager of cad tool commands
	private CadToolManager _cadToolManager;
	
	// The one and only app settings
	public static AppSettings appSettings = new AppSettingsProp();

	// Margin*2 between canvas and scene 
	private int _dx, _dy;
	
	// Remeber modified scene width and height
	private int _sceneWidth, _sceneHeight;

	// static main entry into PrinCad application
	public static void main(String[] args) {
		
		try {
			// restore app settings
			appSettings.restore();
			
			// Launch the javaFX application
			Log.info("PrinCad begin execution"); // call after restoring app settings
			launch(args);
			Log.info("PrinCad end execution");
			
			// Save app settings
			appSettings.save();	
		}
		catch (Exception ex) {
			Log.error("PrinCad crash with exception ", ex);
		}
	}
	
	// Override stop event
	@Override
	public void stop() {
		appSettings.setCanvasWidth(_sceneWidth - _dx);
		appSettings.setCanvasHeight(_sceneHeight - _dy);
		appSettings.setSceneWidth(_sceneWidth);
		appSettings.setSceneHeight(_sceneHeight);
	}
	
	// Override the start event
	@Override 
	public void start(Stage primaryStage) throws Exception{
		
		Color sceneBgColor = appSettings.getSceneBackgroundColor();
		int canvasWidth = appSettings.getCanvasWidth();
		int canvasHeight = appSettings.getCanvasHeight();
		_sceneWidth = appSettings.getSceneWidth();
		_sceneHeight = appSettings.getSceneHeight();
		_dx = (_sceneWidth - canvasWidth);
		_dy = (_sceneHeight - canvasHeight);
		
		// Create drawing canvas 
		PrinCanvas canvas = new PrinCanvas(canvasWidth, canvasHeight);
		
		// Create file manager
		_fileManager = new FileManager(canvas);
		
		// Create edit manager
		_editManager = new EditManager(canvas);
		
		// Create cad tool manager
		_cadToolManager = new CadToolManager(canvas);
		
		// Create the typical monolithic border layout
		// Attach canvas to center of layout
		BorderPane pane = new BorderPane(canvas);
		pane.setStyle(FormatStyleColor(sceneBgColor));	
		  
		// Create menu bar
		MenuBar mb = new MenuBar();
		pane.setTop(mb);
		_dy += mb.getHeight();
		
		ObservableList<Menu> menus = mb.getMenus();
		
		// add file menu to menu bar
		Menu fileMenu = _fileManager.buildMenu();
		menus.add(fileMenu);
		
		// add edit menu to menu bar
		Menu editMenu = _editManager.buildMenu();
		menus.add(editMenu);
		
		// add cad tool menu to menu bar
		Menu cadToolMenu = _cadToolManager.buildMenu();
		menus.add(cadToolMenu);
		
		// Create VBox to hold the buttons (right side)
		VBox vboxRight = new VBox(5);
		vboxRight.setPadding(new Insets(10));
		vboxRight.setAlignment(Pos.TOP_CENTER);
		pane.setRight(vboxRight);

		// Add buttons to bar
		ObservableList<Node> rightNodes = vboxRight.getChildren();
		_fileManager.addButtonsToBar(rightNodes);
		_editManager.addButtonsToBar(rightNodes);
		
		// Create VBox to hold the buttons (left side)
		VBox vboxLeft = new VBox(5);
		vboxLeft.setPadding(new Insets(10));
		vboxLeft.setAlignment(Pos.TOP_CENTER);
		pane.setLeft(vboxLeft);

		// Add buttons to bar
		ObservableList<Node> leftNodes = vboxLeft.getChildren();
		_cadToolManager.addButtonsToBar(leftNodes);
        
		// Create a scene, attach layout pane to scene, 
		// set the initial size and background color
		Scene scene = new Scene(pane, _sceneWidth, _sceneHeight, sceneBgColor);
		
		// Apply application styles
		File file = new File("AppStyles.css");
		if (!file.exists()) {
			Log.info(file.toString() + " does not exist");
		}
		else {
			URL url = file.toURI().toURL();
			scene.getStylesheets().add(url.toExternalForm());
		}
		
		// Attach scene to stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("CSCI 240 PrinCad Project");
		
		// Handle changes to window size
		primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
			_sceneWidth = (int)((double)newVal);
			canvas.setWidth(_sceneWidth - _dx);
			canvas.draw();
		});
		primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
			_sceneHeight = (int)((double)newVal);
			canvas.setHeight(_sceneHeight - _dy);
			canvas.draw();
		}); 
		
		primaryStage.show();
		
	}
	
	// Format color for use with setStyle
	private String FormatStyleColor(Color color) {
		String rx = String.format("%02X", Math.round(color.getRed() * 255.0));
		String gx = String.format("%02X", Math.round(color.getGreen() * 255.0));
		String bx = String.format("%02X", Math.round(color.getBlue() * 255.0));
		String fx = "-fx-background-color: #" + rx + gx + bx + ";";
		return fx;
	}
}
