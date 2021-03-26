import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;
import java.net.URL;
import java.util.SortedMap;


public class Main {


	public static void main(String[] args) {
	
		try {
			String url = "https://pastebin.com/raw/t6AZ5kx3";

			Injector injector = Guice.createInjector(new GuiceModule());
			SpellingChecker checker = injector.getInstance(SpellingChecker.class);
			SortedMap<String, Integer> mistakes = checker.check(url);
			System.out.println(mistakes);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

