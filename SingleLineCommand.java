package csci240.prinCad.command;

import csci240.prinCad.control.SingleLineTool;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class SingleLineCommand extends CommandHandler {

	public SingleLineCommand(PrinCanvas canvas) {
		super(canvas);
		
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Single LIne Event");
		
		_canvas.setActiveTool(new SingleLineTool());
	}

}
