import database.ConnectionFactory;
import view.Navigator;
import view.main.MainView;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String BUDGET_CREATE_STATEMENT =
            "create table if not exists budgets ("
                    + "id text not null primary key,"
                    + "month text not null,"
                    + "year integer not null,"
                    + "monthly_allowance real not null"
                    + " )";
    private static final String INCOME_CREATE_STATEMENT =
            "create table if not exists incomes ("
                    + "id text not null primary key,"
                    + "projected real,"
                    + "actual real"
                    + " )";
    private static final String EXPENSE_CREATE_STATEMENT =
            "create table if not exists expenses ("
                    + "id text not null primary key,"
                    + "month text not null,"
                    + "day integer not null,"
                    + "year integer not null,"
                    + "name text not null,"
                    + "amount real not null,"
                    + "budget_id text not null"
                    + " )";

    public static void main(String[] args) {

        boolean databaseInitialized = false;
        Connection connection = ConnectionFactory.connection();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(BUDGET_CREATE_STATEMENT);
            stmt.executeUpdate(INCOME_CREATE_STATEMENT);
            stmt.executeUpdate(EXPENSE_CREATE_STATEMENT);

            ConnectionFactory.closeConnection(true);

            databaseInitialized = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (databaseInitialized) {
            System.out.println("database initialized");
            Navigator.push(MainView.class, null);
        }
    }
}
