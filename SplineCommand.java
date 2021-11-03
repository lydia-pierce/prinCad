package csci240.prinCad.command;

import csci240.prinCad.control.SplineTool;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class SplineCommand extends CommandHandler {

	public SplineCommand(PrinCanvas canvas) {
		super(canvas);
		
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Bezier Curve Event");
		
		_canvas.setActiveTool(new SplineTool());
	}

}
