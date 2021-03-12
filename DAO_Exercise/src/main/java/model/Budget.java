package model;

public class Budget {
    public String id;
    public String month;
    public int year;
    public double monthlyAllowance;

    public Budget() {

    }

    public Budget(String id, String month, int year, double monthlyAllowance) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.monthlyAllowance = monthlyAllowance;
    }

    @Override
    public String toString() {
        String m = month.substring(0, 1).toUpperCase() + month.substring(1);
        return m + " " + year + "\'s Budget\n" +
                "   Monthly Allowance: " + String.format("$%.2f", monthlyAllowance);
    }
}
