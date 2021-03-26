import com.google.inject.Inject;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class SpellingChecker {

    /**
     * These are all interfaces which when set, will be concrete class by the @Inject constructor method.
     */
    private final SourceFetcher sourceFetcher;
    private final Extractor extractor;
    private final DictionarySource dictionarySource;

    /**
     * This part injects the concrete classes into the respective interfaces.
     * @param fetcher
     * @param extractor
     * @param dictionarySource
     */
    @Inject
    SpellingChecker(SourceFetcher fetcher, Extractor extractor, DictionarySource dictionarySource) {
        this.sourceFetcher = fetcher;
        this.extractor = extractor;
        this.dictionarySource = dictionarySource;
    }

	public SortedMap<String, Integer> check() throws IOException {

		// download the document content
    String content = sourceFetcher.getContent();

		// extract words from the content
		List<String> words = extractor.extract(content);

		// find spelling mistakes
    SortedMap<String, Integer> mistakes = new TreeMap<>();

    for (String word : words) {
        if (!dictionarySource.isValidWord(word)) {
            if (mistakes.containsKey(word)) {
                int oldCount = mistakes.get(word);
                mistakes.put(word, oldCount + 1);
            } else {
                mistakes.put(word, 1);
            }
        }
    }
		return mistakes;
	}
}

