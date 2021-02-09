import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Template {
    protected String dirName;
    protected String filePattern;
    protected boolean _recurse;
    protected int _totalLineCount;
    protected Matcher _fileMatcher;
    protected Matcher _searchMatcher;
    protected int _totalMatches;

    public void initializeVariables(String dirName, String filePattern, boolean recurse, String searchPattern){
        dirName = dirName;
        filePattern = filePattern;
        _recurse = recurse;
        _totalLineCount = 0;
        _totalMatches = 0;
        _fileMatcher = Pattern.compile(filePattern).matcher("");
        _searchMatcher = Pattern.compile(searchPattern).matcher("");
        // searchPattern is unused in FileSearch.
    }

    public void searchDirectory(File dir) {
        if (!dir.isDirectory()) {
            Template.nonDir(dir);
            return;
        }
        if (!dir.canRead()) {
            Template.unreadableDir(dir);
            return;
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                if (file.canRead()) {
                    performDeeperSearch(file); // Here one of these will execute:  countLinesInFile(file) or searchFile(file)
                }
                else {
                    Template.unreadableFile(file);
                }
            }
        }
        if (_recurse) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    searchDirectory(file);
                }
            }
        }
    }

    public void performDeeperSearch(File file) {
        try {
            basicFileSearch(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void basicFileSearch(File file) throws IOException {
        String fileName = getFileName(file);
        _fileMatcher.reset(fileName);
        if (_fileMatcher.find()) {
            performSpecificSearch(file);
        }
    }

    // This will get overridden in child class
    public void performSpecificSearch(File file) throws IOException {}

    public String getFileName(File file) {
        try {
            return file.getCanonicalPath();
        }
        catch (IOException e) {
            return "";
        }
    }

    public void printOutput() { }

    public static void usage(String usageString) {
        System.out.println(usageString);
    }
    public static void nonDir(File dir) {
        System.out.println(dir + " is not a directory");
    }
    public static void unreadableDir(File dir) {
        System.out.println("Directory " + dir + " is unreadable");
    }
    public static void unreadableFile(File file) {
        System.out.println("File " + file + " is unreadable");
    }
}