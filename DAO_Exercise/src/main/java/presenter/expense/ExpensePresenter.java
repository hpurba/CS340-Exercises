package presenter.expense;

import database.ConnectionFactory;
import model.Budget;
import model.Expense;
import view.expense.IExpenseView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpensePresenter implements IExpensePresenter {
    private final IExpenseView _expenseView;
    private final Budget _budget;
    private List<Expense> _allExpenses;
    private Expense _editableExpense;

    public ExpensePresenter(IExpenseView expenseView, Object args) {
        _expenseView = expenseView;
        _budget = (Budget) args;
    }

    @Override
    public void fetchExpenses() {
        setExpenses(getAllExpenses(_budget.id));
        _expenseView.displayOptions();
    }

    @Override
    public void optionSelection(String selection) {
        switch (selection) {
            case "1":
                if (_allExpenses != null && !_allExpenses.isEmpty()) {
                    _expenseView.displayExpenses(_allExpenses);
                } else {
                    _expenseView.displayMessage("You have no expenses for this budget");
                }
                _expenseView.displayOptions();
                break;
            case "2":
                _editableExpense = new Expense();
                _editableExpense.createId();
                _editableExpense.month = _budget.month;
                _editableExpense.year = _budget.year;
                _editableExpense.budgetId = _budget.id;
                _expenseView.displayEditNamePrompt();
                break;
            case "3":
                if (_allExpenses == null || _allExpenses.isEmpty()) {
                    _expenseView.displayMessage("You don\'t have any expenses to edit");
                    _expenseView.displayOptions();
                } else {
                    _expenseView.displayEditExpensePrompt(_allExpenses);
                }
                break;
            case "4":
                if (_allExpenses == null || _allExpenses.isEmpty()) {
                    _expenseView.displayMessage("You don\'t have any expenses to delete");
                    _expenseView.displayOptions();
                } else
                    _expenseView.displayDeleteExpensePrompt(_allExpenses);
                break;
            case "5":
                _expenseView.pop();
                break;
            default:
                _expenseView.displayOptions();
                break;
        }
    }

    @Override
    public void editName(String name) {
        _editableExpense.name = name;
        _expenseView.displayEditDayPrompt();
    }

    @Override
    public void editDay(int day) {
        _editableExpense.day = day;
        _expenseView.displayEditAmountPrompt();
    }

    @Override
    public void editAmount(double amount) {
        _editableExpense.amount = amount;
        _expenseView.displaySavePrompt(_editableExpense);
    }

    @Override
    public void editSelectedExpense(String selection) {
        int index = isValidSelection(selection);

        _editableExpense = _allExpenses.get(index);

        _expenseView.displayEditDayPrompt();
    }

    @Override
    public void handleSave(String answer) {
        if (answer.toLowerCase().equals("y")) {
            String message;
            if (_allExpenses != null && _allExpenses.contains(_editableExpense)) {
                boolean success = updateExpense(_editableExpense);
                message = success ? "Expense updated successfully" : "Expense update failed";
            } else {
                boolean success = createExpense(_editableExpense);
                message = success ? "Expense created successfully" : "Expense creation failed";
            }
            setExpenses(getAllExpenses(_budget.id));
            _expenseView.displayMessage(message);
        }
        _expenseView.displayOptions();
    }

    @Override
    public void deleteSelectedExpense(String selection) {
        int index = isValidSelection(selection);

        Expense expenseToDelete = _allExpenses.get(index);

        boolean success = deleteExpense(expenseToDelete.id);
        String message = success ? "Expense deleted successfully" : "Expense deletion failed";
        _expenseView.displayMessage(message);
        if (success) {
            setExpenses(getAllExpenses(_budget.id));
        }

        _expenseView.displayOptions();
    }


    private int isValidSelection(String selection) {
        int s;
        try {
            s = Integer.parseInt(selection);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            _expenseView.displayOptions();
            return -1;
        }

        int index = s - 1;

        if (index < 0 || index > _allExpenses.size() - 1) {
            _expenseView.displayOptions();
            return -1;
        }

        return index;
    }

    private void setExpenses(List<Expense> expenses) {
        if (expenses != null) {
            Collections.sort(expenses);
        }
        _allExpenses = expenses;
    }

    private List<Expense> getAllExpenses(String budgetId) {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        ResultSet rs;
        String sql = "select expenses.id, expenses.month, expenses.day, expenses.year, expenses.name, expenses.amount, expenses.budget_id "
                + "from expenses "
                + "inner join budgets on budgets.id = expenses.budget_id "
                + "where expenses.budget_id = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, budgetId);

            rs = stmt.executeQuery();
            List<Expense> expenses = new ArrayList<>();
            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7)
                );

                expenses.add(expense);
            }

            success = true;
            return expenses;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(success);
        }

        return null;
    }

    private boolean createExpense(Expense expense) {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        String sql = "insert or ignore into expenses values(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, expense.id);
            stmt.setString(2, expense.month);
            stmt.setInt(3, expense.day);
            stmt.setInt(4, expense.year);
            stmt.setString(5, expense.name);
            stmt.setDouble(6, expense.amount);
            stmt.setString(7, expense.budgetId);

            success = true;
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(success);
        }

        return false;
    }

    private boolean updateExpense(Expense expense) {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        String sql = "update expenses set month = ?, "
                + "day = ?, "
                + "year = ?, "
                + "name = ?, "
                + "amount = ? "
                + "where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, expense.month);
            stmt.setInt(2, expense.day);
            stmt.setInt(3, expense.year);
            stmt.setString(4, expense.name);
            stmt.setDouble(5, expense.amount);
            stmt.setString(6, expense.id);

            success = true;
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(success);
        }

        return false;
    }

    private boolean deleteExpense(String id) {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        String sql = "delete from expenses where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);

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
