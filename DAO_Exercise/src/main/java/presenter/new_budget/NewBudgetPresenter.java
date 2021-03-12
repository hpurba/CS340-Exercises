package presenter.new_budget;

import database.ConnectionFactory;
import model.Budget;
import view.new_budget.INewBudgetView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewBudgetPresenter implements INewBudgetPresenter {
    private final INewBudgetView _newBudgetView;
    private final Budget _budget;

    public NewBudgetPresenter(INewBudgetView budgetView) {
        _newBudgetView = budgetView;
        _budget = new Budget();
    }

    @Override
    public void monthSelected(String month) {
        _budget.month = month.toLowerCase();
        _newBudgetView.displayYearPrompt();
    }

    @Override
    public void yearSelected(String year) {
        int y;
        try {
            y = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            _newBudgetView.displayYearPrompt();
            return;
        }

        _budget.year = y;

        _newBudgetView.displayMonthlyAllowancePrompt();
    }

    @Override
    public void monthlyAllowanceSelected(double allowance) {
        _budget.monthlyAllowance = allowance;
        _newBudgetView.displaySavePrompt();
    }

    @Override
    public void handleSave(String answer) {
        if (answer.toLowerCase().equals("y")) {
            _budget.id = _budget.month + "_" + _budget.year;
            boolean success = createBudget(_budget);
            _newBudgetView.displayMessage(success ? "New budget made successfully!" : "New budget could not be created.");
            _newBudgetView.pop();
        } else if (answer.toLowerCase().equals("n")) {
            _newBudgetView.pop();
        } else {
            _newBudgetView.displaySavePrompt();
        }
    }

    private boolean createBudget(Budget budget) {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        String sql = "insert or ignore into budgets values(?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, budget.id);
            stmt.setString(2, budget.month);
            stmt.setInt(3, budget.year);
            stmt.setDouble(4, budget.monthlyAllowance);

            success = true;
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(success);
        }

        return false;
    }
}
