import java.io.*;
import java.util.*;

/**
 * FileSearch
 * Use this on my computer to run the FileSearch which implements the Template Method
 * /Users/hikarupurba/Desktop/WINTER_2021/CS_340/CS340-Exercises/Template_Method_Pattern-Exercise/src ".*\.java" int
 */

public class FileSearch extends Template {
    @Override
    public void performSpecificSearch(File file) throws IOException {
        int curMatches = 0;
        InputStream data = new BufferedInputStream(new FileInputStream(file));
        try {
            Scanner input = new Scanner(data);
            while (input.hasNextLine()) {
                String line = input.nextLine();

                _searchMatcher.reset(line);
                if (_searchMatcher.find()) {
                    if (++curMatches == 1) {
                        System.out.println("");
                        System.out.println("FILE: " + file);
                    }

                    System.out.println(line);
                    ++_totalMatches;
                }
            }
        }
        finally {
            data.close();
            if (curMatches > 0) {
                System.out.println("MATCHES: " + curMatches);
            }
        }
    }

    @Override
    public void printOutput() {
        System.out.println("");
        System.out.println("TOTAL MATCHES: " + _totalMatches);
    }

    public static void main(String[] args) {
        String dirName = "";
        String filePattern = "";
        String searchPattern = "";
        boolean recurse = false;
        String usageString = "USAGE: java FileSearch {-r} <dir> <file-pattern> <search-pattern>";

        if (args.length == 3) {
            recurse = false;
            dirName = args[0];
            filePattern = args[1];
            searchPattern = args[2];
        }
        else if (args.length == 4 && args[0].equals("-r")) {
            recurse = true;
            dirName = args[1];
            filePattern = args[2];
            searchPattern = args[3];
        }
        else {
            usage(usageString);
            return;
        }
        FileSearch fileSearch = new FileSearch();
        fileSearch.initializeVariables(dirName, filePattern, recurse, searchPattern);
        fileSearch.searchDirectory(new File(dirName));
        fileSearch.printOutput();
    }
}
