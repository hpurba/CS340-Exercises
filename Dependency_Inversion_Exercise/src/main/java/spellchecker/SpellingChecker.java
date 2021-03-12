package spellchecker;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class SpellingChecker {

  List<String> words;
  Set<String> dictionary;
  SortedMap<String, Integer> mistakes = new TreeMap<>();

  public SpellingChecker() {
  }

  public SortedMap<String, Integer> check(FetchContent content, FetchDictionary dictionary) throws IOException {

    words = content.getContent();
    this.dictionary = dictionary.getDictionary();

    for (String word : words) {
      if (!dictionary.isValidWord(word)) {
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
