import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SpellingCheckerTest {

  @Test
  void testSpellChecker() throws IOException {
    String url = "https://pastebin.com/raw/t6AZ5kx3";

    SortedMap<String, Integer> expected = new TreeMap<String, Integer>();
    expected.put("be", 1);
    expected.put("doesn", 1);
    expected.put("funtion", 1);
    expected.put("t", 2);

    Injector injector = Guice.createInjector(new GuiceModule());
    SpellingChecker checker = injector.getInstance(SpellingChecker.class);
    SortedMap<String, Integer> mistakes = checker.check(url);
    assertNotNull(mistakes);
    assertEquals(expected, mistakes);
  }

  @Test
  void dependencyMock() throws IOException {
    String url = "https://pastebin.com/raw/t6AZ5kx3";

    SortedMap<String, Integer> expected = new TreeMap<String, Integer>();
    expected.put("be", 1);
    expected.put("doesn", 1);
    expected.put("funtion", 1);
    expected.put("t", 2);

    Dictionary dictionary = Mockito.mock(Dictionary.class);
    when(dictionary.isValidWord("funtion")).thenReturn(false);


    Injector injector = Guice.createInjector(new GuiceModule());
    SpellingChecker checker = injector.getInstance(SpellingChecker.class);
    SortedMap<String, Integer> mistakes = checker.check(url);
    assertNotNull(mistakes);
    assertEquals(expected, mistakes);
  }
}