package csci240.prinCad.ui;

import csci240.prinCad.command.DeleteCommand;
import csci240.prinCad.command.PropertiesCommand;
import csci240.prinCad.command.RedoCommand;
import csci240.prinCad.command.ToggleSelectionCommand;
import csci240.prinCad.command.UndoCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

// Collection of edit related commands
public class EditManager {

	// Edit commands
	private UndoCommand _undoCommand;
	private RedoCommand _redoCommand;
	private DeleteCommand _deleteCommand;
	private ToggleSelectionCommand _selectionTypeCommand;
	private PropertiesCommand _propertiesCommand;
	
	// Constructor
	public EditManager(PrinCanvas canvas) {
		_undoCommand = new UndoCommand(canvas);
		_redoCommand = new RedoCommand(canvas);
		_deleteCommand = new DeleteCommand(canvas);
		_selectionTypeCommand = new ToggleSelectionCommand(canvas);
		_propertiesCommand = new PropertiesCommand(canvas);
	}
	
	// Build menu
	public Menu buildMenu() {
		
        // create menu items 
        MenuItem miUndo = new MenuItem("Undo"); 
        miUndo.setOnAction(e -> _undoCommand.action(e));
		
        MenuItem miRedo = new MenuItem("Redo"); 
        miRedo.setOnAction(e -> _redoCommand.action(e));
		
        MenuItem miDelete = new MenuItem("Delete"); 
        miDelete.setOnAction(e -> _deleteCommand.action(e));
		
        MenuItem miSelect = new MenuItem("Toggle Selection"); 
        miSelect.setOnAction(e -> _selectionTypeCommand.action(e));
		
        MenuItem miProps = new MenuItem("Properties"); 
        miProps.setOnAction(e -> _propertiesCommand.action(e));
		
        // create a menu 
        Menu editMenu = new Menu("Edit"); 
        ObservableList<MenuItem> editMenuItems = editMenu.getItems();
  
        // add menu items to menu 
        editMenuItems.add(miUndo); 
        editMenuItems.add(miRedo); 
        editMenuItems.add(miDelete); 
        editMenuItems.add(miSelect); 
        editMenuItems.add(miProps); 
	
        return editMenu;
	}
	
	// Add buttons to bar
	public void addButtonsToBar(ObservableList<Node> nodes) {
		
		Button undob = new Button();
		undob.setMinWidth(80);
		undob.setText("Undo");
		undob.setOnAction(e -> _undoCommand.action(e));
		
		Button redob = new Button();
		redob.setMinWidth(80);
		redob.setText("Redo");
		redob.setOnAction(e -> _redoCommand.action(e));
		
		Button delb = new Button();
		delb.setMinWidth(80);
		delb.setText("Delete");
		delb.setOnAction(e -> _deleteCommand.action(e));
		
		Button selectb = new Button();
		selectb.setMinWidth(80);
		selectb.setText("^Selection");
		selectb.setOnAction(e -> _selectionTypeCommand.action(e));
		
		Button propsb = new Button();
		propsb.setMinWidth(80);
		propsb.setText("Properties");
		propsb.setOnAction(e -> _propertiesCommand.action(e));
		
		nodes.add(undob);
		nodes.add(redob);
		nodes.add(delb);
		nodes.add(selectb);
		nodes.add(propsb);
	}


}
