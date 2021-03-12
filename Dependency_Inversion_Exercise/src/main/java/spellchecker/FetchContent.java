package spellchecker;

import java.io.IOException;
import java.util.List;

public interface FetchContent {

  public List<String> getContent() throws IOException;
}
