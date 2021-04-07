public class StartCommand implements Command {

  // Document being cleared
  IDocument _document;
  ISequence _sequence;

  public StartCommand(IDocument _document) {
    this._document = _document;
  }

  @Override
  public void execute() {
    _sequence = _document.sequence();
    _document.clear();
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