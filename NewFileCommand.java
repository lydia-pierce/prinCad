package csci240.prinCad.command;

import csci240.prinCad.model.ModelManager;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class NewFileCommand extends CommandHandler {

	public NewFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	// Handle action event
	@Override 
	public void action(ActionEvent e) {
		Log.info("Handle New File Event");
		
		ModelManager model = _canvas.getModel();
		model.SetFilePath(null);
		model.clear();
		_canvas.draw();
	
	}
}
