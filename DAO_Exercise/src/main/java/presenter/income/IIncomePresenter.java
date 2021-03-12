package presenter.income;

public interface IIncomePresenter {
    void fetchIncome();
     void optionSelected(String selection);
     void projectedIncomeSelected(double income);
     void actualIncomeSelected(double income);
     void handleSave(String answer);
}
