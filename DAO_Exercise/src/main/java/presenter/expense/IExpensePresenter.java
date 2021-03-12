package presenter.expense;

public interface IExpensePresenter {
    void fetchExpenses();
    void optionSelection(String selection);
    void editDay(int day);
    void editName(String name);
    void editAmount(double amount);
    void editSelectedExpense(String selection);
    void handleSave(String answer);
    void deleteSelectedExpense(String selection);
}
