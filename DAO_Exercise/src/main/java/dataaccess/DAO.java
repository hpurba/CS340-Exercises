package dataaccess;

import model.Expense;

import java.util.List;

public interface DAO {
  public boolean setDatabaseInitialized();  // returns true upon a successful database initialization.
  void fetchExpenses();
  //  void optionSelection(String selection);
  void editDay(int day);
  void editName(String name);
  void editAmount(double amount);
  void editSelectedExpense(String selection);
  String handleSave(String answer);
  String deleteSelectedExpense(String selection);
  int isValidSelection(String selection);
  void setExpenses(List<Expense> expenses);
  boolean deleteExpense(String id);
  boolean updateExpense(Expense expense);
  boolean createExpense(Expense expense);
  void optionSelection(String selection);
  List<Expense> getAllExpenses(String budgetId);
}
