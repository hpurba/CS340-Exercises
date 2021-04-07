public class StartCommand implements Command {

  // Document being cleared
  IDocument _document;
  String _sequence;

  public StartCommand(IDocument _document) {
    this._document = _document;
  }

  @Override
  public void execute() {
    _sequence = _document.sequence().toString();
    _document.clear();
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