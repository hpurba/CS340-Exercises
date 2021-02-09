import javax.sound.sampled.Line;
import java.io.*;
import java.util.*;

/**
 * LineCount
 * Use this on my computer to run the FileSearch which implements the Template Method
 * /Users/hikarupurba/Desktop/WINTER_2021/CS_340/CS340-Exercises/Template_Method_Pattern-Exercise/src ".*\.java"
 */
public class LineCount extends Template {
    @Override
    public void performSpecificSearch(File file) throws IOException {
        ///////
        Reader reader = new BufferedReader(new FileReader(file));
        int curLineCount = 0;
        try {
            curLineCount = 0;
            Scanner input = new Scanner(reader);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                ++curLineCount;
                ++_totalLineCount;
            }
        }
        finally {
            System.out.println(curLineCount + "  " + file);
            reader.close();
        }
    }

    @Override
    public void printOutput() {
        System.out.println("TOTAL: " + _totalLineCount);
    }

    public static void main(String[] args) {
        String searchPattern = "";
        String directory = "";
        String pattern = "";
        boolean recurse = false;
        String usageString = "USAGE: java LineCount {-r} <dir> <file-pattern>";

        if (args.length == 2) {
            recurse = false;
            directory = args[0];
            pattern = args[1];
        }
        else if (args.length == 3 && args[0].equals("-r")) {
            recurse = true;
            directory = args[1];
            pattern = args[2];
        }
        else {
            usage(usageString);
            return;
        }
        LineCount lineCount = new LineCount();
        lineCount.initializeVariables(directory, pattern, recurse, searchPattern);
        lineCount.searchDirectory(new File(directory));
    }
}
