package csci240.prinCad.command;

import csci240.prinCad.control.ExMarkerTool;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class ExMarkerCommand extends CommandHandler {

	public ExMarkerCommand(PrinCanvas canvas) {
		super(canvas);
		
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle X Marker Event");
		
		_canvas.setActiveTool(new ExMarkerTool());
	}

}
