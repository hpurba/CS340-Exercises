package dataaccess.sqlite;

import dataaccess.DAO;
import database.ConnectionFactory;
import model.Expense;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class databaseInitializationDAO implements DAO {

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

  boolean databaseInitialized = false;

  public boolean setDatabaseInitialized() {
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
      return true;
    }
    return false;
  }

  @Override
  public void fetchExpenses() {

  }

  @Override
  public void editDay(int day) {

  }

  @Override
  public void editName(String name) {

  }

  @Override
  public void editAmount(double amount) {

  }

  @Override
  public void editSelectedExpense(String selection) {

  }

  @Override
  public String handleSave(String answer) {

    return "";
  }

  @Override
  public String deleteSelectedExpense(String selection) {
    return "";
  }

  @Override
  public int isValidSelection(String selection) {
    return 0;
  }

  @Override
  public void setExpenses(List<Expense> expenses) {

  }

  @Override
  public boolean deleteExpense(String id) {
    return false;
  }

  @Override
  public boolean updateExpense(Expense expense) {
    return false;
  }

  @Override
  public boolean createExpense(Expense expense) {
    return false;
  }

  @Override
  public void optionSelection(String selection) {

  }

  @Override
  public List<Expense> getAllExpenses(String budgetId) {
    return null;
  }
}
