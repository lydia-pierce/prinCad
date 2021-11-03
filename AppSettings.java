package csci240.prinCad.ui;

import csci240.prinCad.util.Log;
import javafx.scene.paint.Color;

// Expose app settings
public interface AppSettings {

	public void restore() throws Exception;
	public void save() throws Exception;
	
	public int getCanvasHeight();
	public void setCanvasHeight(int canvasHeight);
	
	public int getCanvasWidth();
	public void setCanvasWidth(int sceneWidth);
	
	public Color getCanvasBackgroundColor();
	public void setCanvasBackgroundColor(Color bgColor);
	
	public int getSceneHeight();
	public void setSceneHeight(int sceneHeight);
	
	public int getSceneWidth();
	public void setSceneWidth(int sceneWidth);
	
	public Color getSceneBackgroundColor();
	public void setSceneBackgroundColor(Color bgColor);

	public Log.LoggingLevel getLoggingLevel();
}
