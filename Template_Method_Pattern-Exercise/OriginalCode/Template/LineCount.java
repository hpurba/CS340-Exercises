import java.io.*;
import java.util.*;
import java.util.regex.*;

public class LineCount {
	private String _directory;
	private String _pattern;
	private boolean _recurse;
	private int _totalLineCount;
	private Matcher _matcher;
	
	public LineCount(String directory, String pattern, boolean recurse) {
		_directory = directory;
		_pattern = pattern;
		_recurse = recurse;
		_totalLineCount = 0;
		_matcher = Pattern.compile(_pattern).matcher("");		
	}
	
	private void run() {
		countLinesInDir(new File(_directory));
		System.out.println("TOTAL: " + _totalLineCount);
	}
	
	private void countLinesInDir(File dir) {
		if (dir.isDirectory())
		{
			if (dir.canRead())
			{
				for (File file : dir.listFiles()) {
					if (file.isFile()) {
						if (file.canRead()) {
							countLinesInFile(file);
						}
						else {
							System.out.println("File " + file + " is unreadable");
						}
					}
				}
					
				if (_recurse) {
					for (File file : dir.listFiles()) {
						if (file.isDirectory()) {
							countLinesInDir(file);
						}
					}
				}
			}
			else {
				System.out.println("Directory " + dir + " is unreadable");
			}
		}
		else {
			System.out.println(dir + " is not a directory");
		}
	}
	
	private void countLinesInFile(File file) {
		String fileName = getFileName(file);
		_matcher.reset(fileName);
		if (_matcher.find()) {
			try {
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
			catch (IOException e) {
				System.out.println("File " + file + " is unreadable");
			}
		}
	}
	
	private String getFileName(File file) {
		try {
			return file.getCanonicalPath();
		}
		catch (IOException e) {
			return "";
		}
	}
	
	public static void main(String[] args) {
		String directory = "";
		String pattern = "";
		boolean recurse = false;
		
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
			usage();
			return;
		}
		
		LineCount lineCounter = new LineCount(directory, pattern, recurse);
		lineCounter.run();
	}
	
	private static void usage() {
		System.out.println("USAGE: java LineCount {-r} <dir> <file-pattern>");
	}

}
