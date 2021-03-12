package presenter.update_budget;

import database.ConnectionFactory;
import model.Budget;
import view.expense.ExpenseView;
import view.income.IncomeView;
import view.update_budget.IUpdateBudgetView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateBudgetPresenter implements IUpdateBudgetPresenter {
    private final IUpdateBudgetView _updateBudgetView;
    private Budget _selectedBudget;
    private final List<Budget> _allBudgets;

    public UpdateBudgetPresenter(IUpdateBudgetView updateBudgetView) {
        _updateBudgetView = updateBudgetView;
        _allBudgets = new ArrayList<>();
    }

    @Override
    public void fetchAllBudgets() {
        List<Budget> budgets = getAllBudgets();
        if (budgets == null || budgets.isEmpty()) {
            _updateBudgetView.displayMessage("You have no budgets");
            _updateBudgetView.pop();
            return;
        }
        _allBudgets.addAll(budgets);
        _updateBudgetView.displayAllBudgets(_allBudgets);
    }

    @Override
    public void budgetSelected(String selection) {
        int s;
        try {
            s = Integer.parseInt(selection);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            _updateBudgetView.displayAllBudgets(_allBudgets);
            return;
        }

        if (s == 0) {
            _updateBudgetView.pop();
        }

        int index = s - 1;

        if (index < 0 || index > _allBudgets.size() - 1) {
            _updateBudgetView.displayAllBudgets(_allBudgets);
            return;
        }

        _selectedBudget = _allBudgets.get(index);

        _updateBudgetView.displayEditingOptions();
    }

    @Override
    public void editingOptionSelected(String option) {
        switch (option) {
            case "1":
                _updateBudgetView.navigate(IncomeView.class, _selectedBudget);
                break;
            case "2":
                _updateBudgetView.navigate(ExpenseView.class, _selectedBudget);
                break;
            case "3":
                _updateBudgetView.pop();
                break;
            default:
                _updateBudgetView.displayEditingOptions();
                break;
        }
    }

    private List<Budget> getAllBudgets() {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        ResultSet rs;
        String sql = "select budgets.id, budgets.month, budgets.year, budgets.monthly_allowance " +
                "from budgets";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            rs = stmt.executeQuery();
            List<Budget> budgets = new ArrayList<>();
            while (rs.next()) {
                Budget budget = new Budget(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4)
                );

                budgets.add(budget);
            }

            success = true;
            return budgets;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(success);
        }

        return null;
    }
}
