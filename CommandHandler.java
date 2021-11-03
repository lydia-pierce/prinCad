package csci240.prinCad.command;

import csci240.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

// Command handler
public abstract class CommandHandler {

	// Owning canvas
	protected final PrinCanvas _canvas;
	
	// Constructor
	public CommandHandler(PrinCanvas canvas) {
		_canvas = canvas;
	}
	
	// Handle action event
	public abstract void action(ActionEvent e);

}
