package csci240.prinCad.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import csci240.prinCad.util.Log;
import javafx.scene.paint.Color;

// Use the Properties class to save and restore setting values
public class AppSettingsProp implements AppSettings {
	
	// Magic keys section -------------------------------------------------
	
	// Setting file
	final String FilePath = "AppSettings.txt";
	
	// Keys for Scene properties
	final String SceneWidthKey = "SceneWidth";
	final String SceneHeightKey = "SceneHeight";
	final String SceneBackgroundColorKey = "SceneBackgroundColor";
	
	// Keys for Canvas properties
	final String CanvasWidthKey = "CanvasWidth";
	final String CanvasHeightKey = "CanvasHeight";
	final String CanvasBackgroundColorKey = "CanvasBackgroundColor";
	
	// Other keys
	final String LoggingLevelKey = "LoggingLevel";
	
	// End of Magic keys section -------------------------------------------

	// The contained Properties variable
	Properties _props;
	
	// Scene properties 
	private int _sceneWidth = 500;
	private int _sceneHeight = 400;
	private Color _sceneBackgroundColor = Color.DARKGOLDENROD;
	
	// Canvas properties
	private int _canvasWidth = 300;
	private int _canvasHeight = 250;
	private Color _canvasBackgroundColor = Color.BLACK;
	
	private Log.LoggingLevel _loggingLevel = Log.LoggingLevel.None;
	
	// Expose properties through getters and setters
	
	public int getSceneWidth() { return _sceneWidth; }
	public void setSceneWidth(int width) { _sceneWidth = width; }
	
	public int getSceneHeight() { return _sceneHeight; }
	public void setSceneHeight(int height) { _sceneHeight = height; }
	
	public Color getSceneBackgroundColor() { return _sceneBackgroundColor; }
	public void setSceneBackgroundColor(Color bgColor) { _sceneBackgroundColor = bgColor; }
	
	public int getCanvasWidth() { return _canvasWidth; }
	public void setCanvasWidth(int width) { _canvasWidth = width; }
	
	public int getCanvasHeight() { return _canvasHeight; }
	public void setCanvasHeight(int height) { _canvasHeight = height; }
	
	public Color getCanvasBackgroundColor() { return _canvasBackgroundColor; }
	public void setCanvasBackgroundColor(Color bgColor) { _canvasBackgroundColor = bgColor; }
	
	public Log.LoggingLevel getLoggingLevel() { return _loggingLevel; }

	// Default constructor
	public AppSettingsProp() {
		
		_props = new Properties();
	}

	// Restore settings (read from file)
	public void restore() throws Exception {
		
		File file = new File(FilePath);
		if (file.exists()) {
			FileInputStream in = new FileInputStream(file);
			_props.load(in);
			in.close();
			
			_sceneWidth = getInt(SceneWidthKey, 400);
			_sceneHeight = getInt(SceneHeightKey, 300);
			_sceneBackgroundColor = getColor(SceneBackgroundColorKey, Color.DARKGOLDENROD);
			
			_canvasWidth = getInt(CanvasWidthKey, 300);
			_canvasHeight = getInt(CanvasHeightKey, 250);
			_canvasBackgroundColor = getColor(CanvasBackgroundColorKey, Color.BLACK);
			
			_loggingLevel = getLoggingLevel(LoggingLevelKey, Log.LoggingLevel.None);
		}
	}
	
	// Save settings (write to file)
	public void save() throws Exception {
		
		setValue(SceneWidthKey, _sceneWidth);
		setValue(SceneHeightKey, _sceneHeight);
		setValue(SceneBackgroundColorKey, _sceneBackgroundColor);
		
		setValue(CanvasWidthKey, _canvasWidth);
		setValue(CanvasHeightKey, _canvasHeight);
		setValue(CanvasBackgroundColorKey, _canvasBackgroundColor);
		
		setValue(LoggingLevelKey, _loggingLevel);
		
		FileOutputStream out = new FileOutputStream(FilePath);
		_props.store(out, null);
		out.close();
	}

	
	// Utility to get property value as an integer
	private int getInt(String key, int defaultValue) {
		int v;
		try {
			v = Integer.parseInt(_props.getProperty(key));
		}
		catch (Exception ex) {
			v = defaultValue;
		}
		return v;
	}
	
	// Utility to get property value as a color
	private Color getColor(String key, Color defaultValue) {
		
		Color c;
		try {
			c = Color.valueOf(_props.getProperty(key));
		}
		catch (Exception ex) {
			c = defaultValue;
		}
		return c;
	}
	
	// Utility to get property value as logging level
	private Log.LoggingLevel getLoggingLevel(String key, Log.LoggingLevel defaultValue) {
		
		Log.LoggingLevel loggingLevel;
		String s = _props.getProperty(key);
		if (s != null) {
			try {
				loggingLevel = Log.LoggingLevel.valueOf(s);
			}
			catch (Exception ex) {
				loggingLevel = defaultValue;
			}
		}
		else {
			loggingLevel = defaultValue;
		}
		return loggingLevel;
	}
	
	// Utility to set integer value
	private void setValue(String key, int value) {
		_props.setProperty(key, Integer.toString(value));
	}
	
	// Utility to set color value
	private void setValue(String key, Color value) {
		_props.setProperty(key, value.toString());
	}
	
	// Utility to set logging level value
	private void setValue(String key, Log.LoggingLevel value) {
		_props.setProperty(key, value.toString());
	}
}
