import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class Document implements IDocument {
    private ISequence _sequence;
    private int _length;
    private int _cursor;

    Document(ISequence sequence) {
        _sequence = sequence;
        _length = 0;
        _cursor = 0;
    }

    @Override
    public void insert(int pos, String s) {
        _sequence.insert(pos, s);
        _length += s.length();
        _cursor += s.length();
    }

    @Override
    public String delete(int pos, int count) {
        try {
            _length -= count;
            _cursor = pos;
            return _sequence.delete(pos, count);
        } catch (ISequence.SequenceException e) {
            return null;
        }
    }

    @Override
    public void display() {
        _sequence.print();
    }

    @Override
    public void save(String fileName) {
        try {
            File file = new File(fileName);
            PrintWriter pw = new PrintWriter(file);
            pw.write(_sequence.toString());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println("Could not perform save operation: " + e.getMessage());
        }
    }

    @Override
    public void open(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            clear();
            while (scanner.hasNext()) {
                String next = scanner.next();
                _sequence.insert(_cursor, next);
                _cursor += next.length();
                _length += next.length();
            }
        } catch (IOException e) {
            System.out.println("Failure opening file: " + e.getMessage());
        }
    }

    @Override
    public void clear() {
        _sequence.clear();
        _length = 0;
        _cursor = 0;
    }

    @Override
    public ISequence sequence() {
        return _sequence;
    }
}
