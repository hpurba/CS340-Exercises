//1. What design principles does this program violate?
//      Breaking the single responsibility principle. This can only write to System out.
//2. Refactor the program to improve its design.


public class CsvWriter {
    StringBuilder str;

    public CsvWriter() {
    }

    public String write(String[][] lines) {
        str = new StringBuilder();

        for (int i = 0; i < lines.length; i++){
            writeLine(lines[i]);
        }
        return str.toString();
    }

    private void writeLine(String[] fields) {
        if (fields.length == 0){
            str.append("\n");
        }
        else {
            writeField(fields[0]);

            for (int i = 1; i < fields.length; i++) {
                str.append(",");
                writeField(fields[i]);
            }
            str.append("\n");
        }
    }

    private void writeField(String field) {
        if (field.indexOf(',') != -1 || field.indexOf('\"') != -1)
            writeQuoted(field);
        else{
            str.append(field);
        }
    }

    private void writeQuoted(String field) {
        str.append('\"');
        for (int i = 0; i < field.length(); i++) {
            char c = field.charAt(i);
            if (c == '\"'){
                str.append("\"\"");
            }
            else{
                str.append(c);
            }
        }
        str.append('\"');
    }
}
