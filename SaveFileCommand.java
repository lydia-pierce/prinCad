package csci240.prinCad.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci240.prinCad.model.ModelManager;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

// Save file command
public class SaveFileCommand extends CommandHandler {

	public SaveFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Save File Event");
		
		ModelManager model = _canvas.getModel();
		
		Window stage = _canvas.getScene().getWindow();
		
		// Only ask for file path if not already known
		File file = null;
		String filePath = model.GetFilePath();
		if (filePath != null && filePath.trim() != "") {
			file = new File(filePath);
		}
		else {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Model");
			
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);
			
			file = fileChooser.showSaveDialog(stage);
		}

		try {
			// Backup existing file
			filePath = file.getAbsolutePath();
			if (file.exists()) {
				File src = new File(filePath);
				File dst = new File(filePath += ".bak");
				if (dst.exists()) {
					dst.delete();
				}
				src.renameTo(dst);
			}
			
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);
			
			model.save(out);
			
			out.flush();
			out.close();
			
			model.SetFilePath(filePath);
		}
		catch (Exception ex) {	
			Log.error("Save File ", ex);
		}
	}
}
