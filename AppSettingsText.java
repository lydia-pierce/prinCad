package csci240.prinCad.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci240.prinCad.util.Log;
import javafx.scene.paint.Color;

//
// Use standard file IO to save and restore app settings
//
// This example uses the same format as the Properties version
// however, there is no requirement that is does
// therefore, duplicate code exists between 
// AppSettingsText.java and AppSettingsProp.java
//
// Example used to understand how the Properties tool saves time and effort.
//
public class AppSettingsText implements AppSettings {
	
	// Magic keys section -------------------------------------------------
	
	// Setting file
	final String FilePath = "AppSettings.txt";
	
	// Keys for Scene properties
	final String SceneWidth = "SceneWidth";
	final String SceneHeight = "SceneHeight";
	final String SceneBackgroundColor = "SceneBackgroundColor";
	
	// Keys for Canvas properties
	final String CanvasWidth = "CanvasWidth";
	final String CanvasHeight = "CanvasHeight";
	final String CanvasBackgroundColor = "CanvasBackgroundColor";
	
	// End of Magic keys section -------------------------------------------

	// Scene properties 
	private int _sceneWidth = 400;
	private int _sceneHeight = 300;
	private Color _sceneBackgroundColor = Color.DARKGOLDENROD;
	
	// Canvas properties
	private int _canvasWidth = 300;
	private int _canvasHeight = 250;
	private Color _canvasBackgroundColor = Color.BLACK;
	
	// Expose properties through getters and setters
	
	public int getSceneWidth() { return _sceneWidth; }
	public void setSceneWidth(int sceneWidth) { _sceneWidth = sceneWidth; }
	
	public int getSceneHeight() { return _sceneHeight; }
	public void setSceneHeight(int sceneHeight) { _sceneHeight = sceneHeight; }
	
	public Color getSceneBackgroundColor() { return _sceneBackgroundColor; }
	public void setSceneBackgroundColor(Color bgColor) { _sceneBackgroundColor = bgColor; }
	
	public int getCanvasWidth() { return _canvasWidth; }
	public void setCanvasWidth(int canvasWidth) { _canvasWidth = canvasWidth; }
	
	public int getCanvasHeight() { return _canvasHeight; }
	public void setCanvasHeight(int canvasHeight) { _canvasHeight = canvasHeight; }
	
	public Color getCanvasBackgroundColor() { return _canvasBackgroundColor; }
	public void setCanvasBackgroundColor(Color bgColor) { _canvasBackgroundColor = bgColor; }
	
	public Log.LoggingLevel getLoggingLevel() { return Log.LoggingLevel.None; }

	// Default constructor
	public AppSettingsText() {
	
	}

	// Restore settings (read from file)
	public void restore() throws Exception {
		File f = new File(FilePath);
		if (f.exists()) {
			FileReader fr = new FileReader(f);
			BufferedReader reader = new BufferedReader(fr);
			
			String line = reader.readLine();
			while (line != null) {
				String[] tokens = line.split("=");
				if (tokens.length > 0) {
					switch (tokens[0]) {
					case SceneWidth:
						_sceneWidth = ParseInt(tokens, 400);
						break;
					case SceneHeight:
						_sceneHeight = ParseInt(tokens, 400);
						break;
					case SceneBackgroundColor:
						_sceneBackgroundColor = ParseColor(tokens, Color.DARKGOLDENROD);
						break;
					case CanvasWidth:
						_canvasWidth = ParseInt(tokens, 400);
						break;
					case CanvasHeight:
						_canvasHeight = ParseInt(tokens, 400);
						break;
					case CanvasBackgroundColor:
						_canvasBackgroundColor = ParseColor(tokens, Color.BLACK);
						break;
					}
				}
				line = reader.readLine();
			}
			
			reader.close();
			fr.close();
		}
	}
	
	// Save settings (write to file)
	public void save() throws Exception {
		
		File file = new File(FilePath);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw, true);
		
		SaveValue(out, SceneWidth, _sceneWidth);
		SaveValue(out, SceneHeight, _sceneHeight);
		SaveValue(out, SceneBackgroundColor, _sceneBackgroundColor);
		SaveValue(out, CanvasWidth, _canvasWidth);
		SaveValue(out, CanvasHeight, _canvasHeight);
		SaveValue(out, CanvasBackgroundColor, _canvasBackgroundColor);
		
		out.flush();
		out.close();
	}
	
	// Utility to parse string as an integer
	private int ParseInt(String[] tokens, int defaultValue) {
		int v = defaultValue;
		if (tokens.length > 1) {
			try {
				v = Integer.parseInt(tokens[1]);
			}
			catch (Exception e) {
				v  = defaultValue;
			}
		}
		return v;
	}
	
	// Utility to parse string as a Color
	private Color ParseColor(String[] tokens, Color defaultValue) {
		Color v = defaultValue;
		if (tokens.length > 1) {
			try {
				v = Color.valueOf(tokens[1]);
			}
			catch (Exception e) {
				v  = defaultValue;
			}
		}
		return v;
	}
	
	private void SaveValue(PrintWriter out, String key, int value) {
		out.println(String.format("%s=%d", key, value));
	}
	
	private void SaveValue(PrintWriter out, String key, Color value) {
		out.println(String.format("%s=%s", key, value.toString()));
	}
	
}
