import com.google.inject.Inject;

import javax.inject.Named;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class Dictionary implements DictionarySource {

	private Set<String> words;

	@Inject
    public Dictionary(@Named("DictionaryFilePath") String filepath) throws FileNotFoundException {
      try (Scanner scanner = new Scanner(new File(filepath))) {
        words = new TreeSet<>();
        while (scanner.hasNextLine()) {
          String word = scanner.nextLine().trim();
          words.add(word);
        }
      }
	}

	public boolean isValidWord(String word) {
		return words.contains(word);
	}
}

