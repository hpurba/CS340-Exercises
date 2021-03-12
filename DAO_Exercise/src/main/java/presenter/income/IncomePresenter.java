package presenter.income;

import database.ConnectionFactory;
import model.Budget;
import model.Income;
import view.income.IIncomeView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomePresenter implements IIncomePresenter {
    private final IIncomeView _incomeView;
    private final Budget _budget;
    private Income _currIncome;
    private Income _editableIncome;

    public IncomePresenter(IIncomeView incomeView, Object args) {
        _incomeView = incomeView;
        _budget = (Budget) args;
    }

    @Override
    public void fetchIncome() {
        _currIncome = getIncome(_budget.id);
        if (_currIncome != null) {
            _editableIncome = new Income(_currIncome.id, _currIncome.projected, _currIncome.actual);
        }
        _incomeView.displayOptions();
    }

    @Override
    public void optionSelected(String selection) {
        switch (selection) {
            case "1":
                if (_currIncome == null) {
                    _incomeView.displayMessage("You don\'t have a saved income for this budget");
                } else {
                    _incomeView.displayIncome(_currIncome);
                }
                _incomeView.displayOptions();
                break;
            case "2":
                if (_editableIncome == null) {
                    _editableIncome = new Income();
                }
                _incomeView.displayEditProjectedIncomePrompt(_budget.month, _budget.year);
                break;
            case "3":
                if (_editableIncome == null) {
                    _editableIncome = new Income();
                }
                _incomeView.displayEditActualIncomePrompt(_budget.month, _budget.year);
                break;
            case "4":
                _incomeView.displaySavePrompt(_editableIncome != null ? _editableIncome : new Income());
                break;
            case "5":
                _incomeView.pop();
                break;
            default:
                _incomeView.displayOptions();
                break;
        }
    }

    @Override
    public void projectedIncomeSelected(double income) {
        _editableIncome.projected = income;
        _incomeView.displayOptions();
    }

    @Override
    public void actualIncomeSelected(double income) {
        _editableIncome.actual = income;
        _incomeView.displayOptions();
    }

    @Override
    public void handleSave(String answer) {
        if (_editableIncome == null) {
            _incomeView.displayMessage("You haven\'t made any edits yet.");
            _incomeView.displayOptions();
            return;
        }

        if (answer.toLowerCase().equals("y")) {
            String message;
            if (_currIncome == null) {
                _editableIncome.id = _budget.id;
                boolean success = createIncome(_editableIncome);
                message = success ? "Income created successfully" : "Income creation failed";
            } else {
                boolean success = updateIncome(_editableIncome);
                message = success ? "Income updated successfully" : "Income update failed";
            }
            _currIncome = getIncome(_budget.id);
            _incomeView.displayMessage(message);
        }
        _incomeView.displayOptions();
    }

    private Income getIncome(String id) {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        String sql = "select incomes.id, incomes.projected, incomes.actual from incomes where id = ?";
        ResultSet rs;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                success = true;
                return new Income(
                        rs.getString(1),
                        rs.getDouble(2),
                        rs.getDouble(3)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(success);
        }

        return null;
    }

    private boolean createIncome(Income income) {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        String sql = "insert or ignore into incomes values(?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, income.id);
            stmt.setDouble(2, income.projected);
            stmt.setDouble(3, income.actual);

            success = true;
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(success);
        }

        return false;
    }

    private boolean updateIncome(Income income) {
        Connection connection = ConnectionFactory.connection();

        boolean success = false;
        String sql = "update incomes set projected = ? , "
                + "actual = ? "
                + "where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, income.projected);
            stmt.setDouble(2, income.actual);
            stmt.setString(3, income.id);

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
