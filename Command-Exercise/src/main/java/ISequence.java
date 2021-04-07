public interface ISequence {
    /**
     * Custom exception class to handle exceptions specific to sequence interface implementation class
     */
    @SuppressWarnings("serial")
    class SequenceException extends Exception {}

    /**
     * Print the sequence
     */
    void print();

    /**
     * Inserts a string into the sequence
     * @param pos Position in the sequence to insert the string
     * @param s String to be inserted
     */
    void insert(int pos, String s);

    /**
     * Deletes a character sequence from the sequence
     * @param pos Starting position of the sequence that is being deleted
     * @param count Number of characters to be deleted
     */
    String delete(int pos, int count) throws SequenceException;

    /**
     * Clears the sequence to a clean, empty state
     */
    void clear();
}
