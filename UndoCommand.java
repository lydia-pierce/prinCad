package csci240.prinCad.command;

import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

// Undo command
public class UndoCommand extends CommandHandler {

	public UndoCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Undo Event");
	}

}
