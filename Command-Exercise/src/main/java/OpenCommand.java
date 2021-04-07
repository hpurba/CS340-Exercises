public class OpenCommand implements Command {

  IDocument _document;
  String openFileName;
  ISequence _sequence;

  public OpenCommand(IDocument _document, String openFileName) {
    _sequence = _document.sequence();
    this._document = _document;
  }

  @Override
  public void execute() {
    _sequence = _document.sequence();
    this._document.open(openFileName);
  }

  @Override
  public void undo() {
    _document.insert(0, _sequence.toString());
  }

  @Override
  public void redo() {
    execute();
  }
}