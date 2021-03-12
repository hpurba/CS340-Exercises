package spellchecker;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FetchContentByURL implements FetchContent {

  public URL url;

  public FetchContentByURL(URL url) {
    this.url = url;
  }

  @Override
  public List<String> getContent() throws IOException {
    String content = fetch();
    List<String> words = extract(content);
    return words;
  }

  public String fetch() throws IOException {
    URLConnection connection = url.openConnection();
    StringBuilder contentBuffer = new StringBuilder();
    try (InputStream input = connection.getInputStream()) {
      int c;
      while ((c = input.read()) >= 0) {
        contentBuffer.append((char) c);
      }
    }
    return contentBuffer.toString();
  }

  public List<String> extract(String content) {
    content = content.toLowerCase();
    List<String> words = new ArrayList<>();

    Pattern wordPattern = Pattern.compile("\\p{Alpha}+");
    Pattern delimiterPattern = Pattern.compile("[^\\p{Alpha}]+");

    Scanner scanner = new Scanner(content);
    scanner.useDelimiter(delimiterPattern);

    while (scanner.hasNext(wordPattern)) {
      String word = scanner.next(wordPattern);
      words.add(word);
    }

    return words;
  }
}
