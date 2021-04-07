public class ReplaceCommand implements Command {

  // know where and what was replaced so it can be re-replaced upon undo.
  IDocument _document;
  int replaceIndex;
  int replaceDistance;
  String replacementString;
  String originalString;
  int replacementStringLen;

  /**
   * Constructor for ReplaceCommand
   *
   * @param _document
   * @param replaceIndex
   * @param replaceDistance
   * @param replacementString
   */
  public ReplaceCommand(IDocument _document,int replaceIndex, int replaceDistance, String replacementString){
    this._document = _document;
    this.replaceIndex = replaceIndex;
    this.replaceDistance = replaceDistance;
    this.replacementString = replacementString;
    this.replacementStringLen = replacementString.length();
  }

  @Override
  public void execute() {
    originalString = _document.delete(replaceIndex, replaceDistance);
    _document.insert(replaceIndex, replacementString);
  }

  @Override
  public void undo() {
    replacementString = _document.delete(replaceIndex, replacementStringLen);
    _document.insert(replaceIndex, originalString);
  }

  @Override
  public void redo() {
    execute();
  }
}
