import java.util.Stack;

public class UndoRedoManager {

    // Undo and Redo Stacks.
    Stack<Command> undoStack = new Stack<Command>();
    Stack<Command> redoStack = new Stack<Command>();

    /**
     * Executes the command. Clears the redoStack. Pushes the command to the undoStack.
     * @param command
     */
    public void execute(Command command) {
        command.execute();
        redoStack.clear();
        undoStack.push(command);
    }

    /**
     *  Check if undo is possible.
     *  Pop a command off of the undo stack, call its undo method, and then put that command on the redo stack.
     */
    public void undo() {
        if (canUndo()) {
            Command tempCommand = undoStack.pop();
            tempCommand.undo();
            redoStack.push(tempCommand);
        }
    }

    /**
     *  Check if redo is possible.
     *  Pop a command off of the redo stack, call its redo method, and then put that command on the undo stack.
     */
    public void redo() {
        if (canRedo()) {
            Command tempCommand = redoStack.pop();
            tempCommand.redo();
            undoStack.push(tempCommand);
        }
    }

    /**
     * canUndo will check if the undoStack is empty or not.
     * If empty, undo is not possible. Else, undo is possible.
     * @return boolean
     */
    public boolean canUndo() {
        if (undoStack.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * canRedo will check if the redoStack is empty or not.
     * If empty, redo is not possible. Else, redo is possible.
     * @return boolean
     */
    public boolean canRedo() {
        if (redoStack.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}

