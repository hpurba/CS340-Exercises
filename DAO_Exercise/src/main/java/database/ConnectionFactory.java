package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = File.separator + System.getProperty("user.dir") + "/budget.sqlite";
    private static final String CONNECTION_URL = "jdbc:sqlite:" + URL;

    private static Connection _connection;

    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load driver");
            e.printStackTrace();
        }
    }

    public static Connection connection() {
        try {
            if (_connection == null || _connection.isClosed()) {
                _connection = DriverManager.getConnection(CONNECTION_URL);
                _connection.setAutoCommit(false);
            }
            return _connection;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static void closeConnection(boolean commit) {
        try {
            if (commit) _connection.commit();
            else _connection.rollback();

            _connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _connection = null;
        }
    }
}
