
import java.io.*;


public class Table {
	
	private Writer destination;
	private TableData data;
	
	public Table(Writer destination, TableData data) {
		this.destination = destination;
		this.data = data;
	}
	
	public void display() throws IOException {
		displayHeaders();
		displayRows();
	}
	
	private void displayHeaders() throws IOException {
		int colCount = data.getColumnCount();
		
		for (int i = 0; i < colCount; ++i) {
			
			// Display header spacing
			if (i > 0) {
				displayChar(' ', data.getColumnSpacing());
			}
			
			// Display header
			displayValue(data.getColumnHeader(i), data.getColumnWidth(i), Justification.Center);
		}
		
		destination.append('\n');
		
		char underline = data.getHeaderUnderline();
		for (int i = 0; i < colCount; ++i) {
			
			// Display underline spacing
			if (i > 0) {
				displayChar(' ', data.getColumnSpacing());
			}
			
			// Display underline
			displayChar(underline, data.getColumnWidth(i));
		}
		
		destination.append('\n');
	}
	
	private void displayRows() throws IOException {
		int colCount = data.getColumnCount();
		int rowCount = data.getRowCount();	
		for (int row = 0; row < rowCount; ++row) {
			
			// Display row spacing
			displayChar('\n', data.getRowSpacing());
			
			// Display row's cell values
			for (int col = 0; col < colCount; ++col) {
				
				// Display column spacing
				if (col > 0) {
					displayChar(' ', data.getColumnSpacing());
				}
				
				// Display cell value
				displayValue(data.getCellValue(row, col), data.getColumnWidth(col),
								data.getColumnJustification(col));
			}
					
			destination.append('\n');
		}
	}
	
	private void displayValue(String value, int width, Justification justification) throws IOException {		
		if (value.length() > width) {
			value = value.substring(0, width);
		}
		
		int padding = width - value.length();
		
		switch (justification) {
			case Left:
				{
					destination.append(value);
					displayPadding(padding);
				}
				break;
			case Center:
				{
					int leftPadding = padding / 2;
					int rightPadding = padding - leftPadding;
					
					displayPadding(leftPadding);
					destination.append(value);
					displayPadding(rightPadding);
				}
				break;
			case Right:
				{
					displayPadding(padding);
					destination.append(value);
				}
				break;
			default:
				assert false;
				break;
		}		
	}
	
	private void displayPadding(int count) throws IOException {
		displayChar(' ', count);
	}
	
	private void displayChar(char c, int count) throws IOException {
		for (int i = 0; i < count; ++i) {
			destination.append(c);
		}
	}
	
}
