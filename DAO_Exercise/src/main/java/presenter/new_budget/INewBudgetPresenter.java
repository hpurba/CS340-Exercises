package presenter.new_budget;

public interface INewBudgetPresenter {
    void monthSelected(String month);
    void yearSelected(String year);
    void monthlyAllowanceSelected(double allowance);
    void handleSave(String answer);
}
