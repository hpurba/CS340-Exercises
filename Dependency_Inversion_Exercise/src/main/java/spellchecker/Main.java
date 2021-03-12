package spellchecker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.SortedMap;

public class Main {
  public static void main(String[] args) throws IOException {

    URL url = null;
    try {
      url = new URL(args[0]);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    FetchContent content = new FetchContentByURL(url);
    FetchDictionary dictionary = new FetchDictionaryByFileName("dict.txt");

    spellchecker.SpellingChecker spellingChecker = new spellchecker.SpellingChecker();

    SortedMap<String, Integer> mistakes = spellingChecker.check(content, dictionary);
    System.out.println(mistakes);
  }
}

// Expected output
//{be=1, doesn=1, funtion=1, t=2}
