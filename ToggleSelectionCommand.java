package csci240.prinCad.command;

import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

// Selection type command
public class ToggleSelectionCommand extends CommandHandler {
	
	// Constructor
	public ToggleSelectionCommand(PrinCanvas canvas) {
		super(canvas);
	}

	// action handler
	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Toggle Selection Event");
		
		_canvas.toggleSelectionType();
	}
}
