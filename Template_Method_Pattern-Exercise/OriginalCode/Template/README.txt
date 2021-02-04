
------------------------
LINE COUNT DOCUMENTATION
------------------------

The command line syntax for LineCount is:

	java LineCount [-r] directoryName fileSelectionPattern

The command line argument "-r" is optional and indicates whether the search for files is recursive.

The directoryName is an absolute or relative path name to a directory on the machine.

The fileSelectionPattern is a regular expression specifying which files to search. The syntax of the
regular expression must match that specified in the Pattern class in the java library.

Note: When typed on the command-line, the directoryName and fileSelectionPattern arguments may need 
to be enclosed in quotes. This is especially important if they contain whitespace or other special 
characters. The shell strips off the quotes before passing the command-line arguments to the program.

Possible Errors:
	The directoryName does not name a directory.
	The directoryName names a directory but is not readable.
	A selected file in a directory is not readable.
	Command line is not of the proper syntax.
	
EXAMPLE:

The command line entry:

	java LineCount -r c:\\Users\Scott\workspace\ExampleJavaCode ".*\.java"

returned the output:

355 c:\Users\Scott\workspace\ExampleJavaCode\src\evilHangMan\EvilHangman.java
39 c:\Users\Scott\workspace\ExampleJavaCode\src\evilHangMan\Result.java
30 c:\Users\Scott\workspace\ExampleJavaCode\src\evilHangMan\ValueResult.java
122 c:\Users\Scott\workspace\ExampleJavaCode\src\fileSearch\FileProcessor.java
91 c:\Users\Scott\workspace\ExampleJavaCode\src\fileSearch\FileSearch.java
71 c:\Users\Scott\workspace\ExampleJavaCode\src\fileSearch\LineCount.java
339 c:\Users\Scott\workspace\ExampleJavaCode\src\imageEditor\ImageEditor.java
132 c:\Users\Scott\workspace\ExampleJavaCode\src\spellingCorrector\SpellingCorrector.java
284 c:\Users\Scott\workspace\ExampleJavaCode\src\spellingCorrector\WordCounts.java
TOTAL: 1463


-------------------------
FILE SEARCH DOCUMENTATION
-------------------------
The command line syntax for FileSearch is:

	java FileSearch [-r] directoryName fileSelectionPattern substringSelectionPattern

The command line argument "-r" is optional and indicates whether the search for files is recursive.

The directoryName is an absolute or relative path name to a directory on the machine.

The fileSelectionPattern is a regular expression specifying which files to search. The syntax of the
regular expression must match that specified in the Pattern class in the java library.

The substringSelectionPattern is a regular expression specifying which lines in a selected file we are to
match. A line is matched if it contains 1 or more substrings that match the substringSelectionPattern.
The syntax of the regular expression must match that specified in the Pattern class in the java library.

Note: When typed on the command-line, the directoryName, fileSelectionPattern, and 
substringSelectionPattern arguments may need to be enclosed in quotes. This is especially important if 
they contain whitespace or other special characters. The shell strips off the quotes before  passing the
command-line arguments to the program.

Possible Errors:
	The directoryName does not name a directory.
	The directoryName names a directory but is not readable.
	A selected file in a directory is not readable.
	Command line is not of the proper syntax.

EXAMPLE:

The command line entry:

	java FileSearch -r c:\\Users\Scott\workspace\ExampleJavaCode ".*\.java" total

returned the output:

FILE: c:\Users\Scott\workspace\ExampleJavaCode\src\fileSearch\FileSearch.java
private int _totalMatches;
_totalMatches = 0;
System.out.println("TOTAL MATCHES: " + _totalMatches);
++_totalMatches;
MATCHES: 4
FILE: c:\Users\Scott\workspace\ExampleJavaCode\src\fileSearch\LineCount.java
private int _totalLineCount;
_totalLineCount = 0;
System.out.println("TOTAL: " + _totalLineCount);
++_totalLineCount;
MATCHES: 4
TOTAL MATCHES: 8

