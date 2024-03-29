public class InsertCommand implements Command {

    // know where inserted and what was inserted
    IDocument _document;
    int insertionIndex;
    String sequenceInput;
    int sequenceInputLen;

    /**
     * Constructor for InsertCommand
     *
     * @param _document
     * @param insertionIndex
     * @param sequenceInput
     */
    public InsertCommand(IDocument _document,int insertionIndex, String sequenceInput){
        this._document = _document;
        this.insertionIndex = insertionIndex;
        this.sequenceInput = sequenceInput;
        this.sequenceInputLen = sequenceInput.length();
    }

    // Receives a command object
    @Override
    public void execute() {
        _document.insert(insertionIndex, sequenceInput);
    }

    @Override
    public void undo() {
        _document.delete(insertionIndex, sequenceInputLen);
    }

    @Override
    public void redo() {
        execute();
    }
}
