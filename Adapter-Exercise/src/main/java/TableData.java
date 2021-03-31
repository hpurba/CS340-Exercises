

public interface TableData {
	
	// TABLE STRUCTURE
	
	// Returns number of columns in the table
	int getColumnCount();
	// Returns number of rows in the table
	int getRowCount();
	// Returns the number of spaces between the table columns
	int getColumnSpacing();
	// Returns the number of empty lines between the table rows
	int getRowSpacing();
	// Returns the character to be used to underline the table headers
	char getHeaderUnderline();
	
	
	// COLUMN STRUCTURE
	
	// Returns the header string for the specified column
	String getColumnHeader(int col);
	// Returns the width (in spaces) of the specified column
	int getColumnWidth(int col);
	// Returns the justification for the values in the specified column
	Justification getColumnJustification(int col);
	
	
	// CELL VALUES
	
	// Returns the value in the specified table cell
	String getCellValue(int row, int col);
	
}
