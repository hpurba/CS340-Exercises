public class OpenCommand implements Command {

  IDocument _document;
  String openFileName;
  String _sequence;

  public OpenCommand(IDocument _document, String openFileName) {
    this._document = _document;
    this.openFileName = openFileName;
  }

  @Override
  public void execute() {
    _sequence = _document.sequence().toString();
    this._document.open(openFileName);
  }

  @Override
  public void undo() {
    _document.clear();
    _document.insert(0, _sequence);
  }

  @Override
  public void redo() {
    execute();
  }
}