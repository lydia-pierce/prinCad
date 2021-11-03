package csci240.prinCad.command;

import csci240.prinCad.ui.PrinCanvas;
import csci240.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PropertiesCommand extends CommandHandler {

	public PropertiesCommand(PrinCanvas canvas) {
		super(canvas);
	}

	@Override
	public void action(ActionEvent e) {
		Log.info("Handle Properties Event");
	}

}
