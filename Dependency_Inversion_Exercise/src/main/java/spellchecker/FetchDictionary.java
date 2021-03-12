package spellchecker;

import java.util.Set;

public interface FetchDictionary {
  public Set<String> getDictionary();
  public boolean isValidWord(String word);
}
