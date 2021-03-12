package dataaccess;

import model.Budget;

import java.util.List;

public interface BudgetDAO {
  public List<Budget> getAllBudgets();
  public Budget getBudget(String id);
  public void updateBudget(Budget budget);
  public void deleteBudget(Budget budget);
}
