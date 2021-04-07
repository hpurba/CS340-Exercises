public class DeleteCommand implements Command {

  // know where and what was deleted so it can be inserted upon undo.
  IDocument _document;
  int deletionIndex;
  int deletionDistance;
  String sequenceString;

  /**
   * Constructor for DeleteCommand
   *
   * @param _document
   * @param deletionIndex
   * @param deletionDistance
   */
  public DeleteCommand(IDocument _document,int deletionIndex, int deletionDistance){
    this._document = _document;
    this.deletionIndex = deletionIndex;
    this.deletionDistance = deletionDistance;
  }

  @Override
  public void execute() {
    this.sequenceString = _document.delete(deletionIndex, deletionDistance);
    if (sequenceString == null) {
      System.out.println("Deletion unsuccessful");
    }
  }

  @Override
  public void undo() {
    _document.insert(deletionIndex, sequenceString);

  }

  @Override
  public void redo() {
    execute();
  }
}
