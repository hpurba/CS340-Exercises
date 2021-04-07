/**
 * Don't change anything here
 */
public class Main {
    public static void main(String[] args) {
        ISequence sequence = new Sequence();
        Document document = new Document(sequence);
        TextEditor editor = new TextEditor(document);
        editor.run();
    }
}
