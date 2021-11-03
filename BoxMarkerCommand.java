package csci240.prinCad.command;

import csci240.prinCad.control.BoxMarkerTool;
import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class BoxMarkerCommand extends CommandHandler {

	public BoxMarkerCommand(PrinCanvas canvas) {
		super(canvas);
		
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Box Marker Event");
		
		_canvas.setActiveTool(new BoxMarkerTool());
	}

}
