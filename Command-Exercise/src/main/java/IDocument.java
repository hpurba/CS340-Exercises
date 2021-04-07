public interface IDocument {

    /**
     * Inserts a character sequence into the document
     * @param pos Position at which the character sequence should be inserted
     * @param s Character sequence to be inserted
     */
    void insert(int pos, String s);

    /**
     * Deletes a character sequence from the document
     * @param pos Starting position of the sequence that is being deleted
     * @param count Number of characters to be deleted
     * @return The deleted string
     */
    String delete(int pos, int count);

    /**
     * Displays the current contents of the document
     */
    void display();

    /**
     * Saves the document to an existing file
     * @param fileName Name of the file to which the document will be saved
     */
    void save(String fileName);

    /**
     * Opens a document from an existing file
     * @param fileName Name of the file to be opened
     */
    void open(String fileName);

    /**
     * Deletes the entire contents of the document and resets it to an empty state
     */
    void clear();

    /**
     * Get the current sequence of the document
     * @return The current sequence
     */
    ISequence sequence();
}
