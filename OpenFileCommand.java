package csci240.prinCad.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import csci240.prinCad.model.ModelManager;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

// Open file command
public class OpenFileCommand extends CommandHandler {

	// Constructor
	public OpenFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Open File Event");
		
		try {
			ModelManager model = _canvas.getModel();
			
			String filePath = model.GetFilePath();
			
			Window stage = _canvas.getScene().getWindow();
			
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load Model");
			FileChooser.ExtensionFilter extFilterTxt = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
			FileChooser.ExtensionFilter extFilterBak = new FileChooser.ExtensionFilter("Backup files (*.bak)", "*.bak");
			fileChooser.getExtensionFilters().add(extFilterTxt);
			fileChooser.getExtensionFilters().add(extFilterBak);
			if (filePath != null)
				fileChooser.setInitialFileName(filePath);
			File file = fileChooser.showOpenDialog(stage);
			if (!file.exists()) 
				return;
			
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			
			model.clear();
			
			String line = reader.readLine();
			while (line != null) {
				model.load(line);
				line = reader.readLine();
			}
			_canvas.draw();

			reader.close();
			fr.close();
			
			model.SetFilePath(file.getAbsolutePath());
		}
		catch (Exception ex) {
			Log.error("Failed to open file due to error ", ex);
		}
	}

}
