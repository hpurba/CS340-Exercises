import com.google.inject.Inject;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


public class SpellingChecker {

    private final SourceFetcher sourceFetcher;
    private final Extractor extractor;
    private final DictionarySource dictionarySource;

    @Inject
    SpellingChecker(SourceFetcher fetcher, Extractor extractor, DictionarySource dictionarySource) {
        this.sourceFetcher = fetcher;
        this.extractor = extractor;
        this.dictionarySource = dictionarySource;
    }

	public SortedMap<String, Integer> check(String uri) throws IOException {

		// download the document content
    String content = sourceFetcher.getContent(uri);

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

