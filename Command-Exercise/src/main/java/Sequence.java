public class Sequence implements ISequence {
    private StringBuilder _sequence;

    Sequence() {
        _sequence = new StringBuilder();
    }

    @Override
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public void insert(int pos, String s) {
        _sequence.insert(pos, s);
    }

    @Override
    public String delete(int pos, int count) throws SequenceException {
        try {
            String deletedText = _sequence.substring(pos, pos + count);
            _sequence.delete(pos, pos + count);
            return deletedText;
        } catch (Exception e) {
            throw new SequenceException();
        }
    }

    @Override
    public void clear() {
        _sequence = new StringBuilder();
    }

    @Override
    public String toString() {
        return _sequence.toString();
    }
}
