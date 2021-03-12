package spellchecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class FetchDictionaryByFileName implements FetchDictionary {

  private String fileName;
  private Set<String> words;

  public FetchDictionaryByFileName(String filename){
    this.fileName = filename;
  }

  @Override
  public Set<String> getDictionary() {

      try (Scanner scanner = new Scanner(new File(fileName))) {
        words = new TreeSet<>();
        while (scanner.hasNextLine()) {
          String word = scanner.nextLine().trim();
          words.add(word);
        }
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

      return words;
  }

  @Override
  public boolean isValidWord(String word) {
    return words.contains(word);
  }
}
