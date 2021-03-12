package view.new_budget;

import view.IView;

public interface INewBudgetView extends IView {
    void displayMonthPrompt();
    void displayYearPrompt();
    void displayMonthlyAllowancePrompt();
    void displaySavePrompt();
    void displayMessage(String message);
    void pop();
}
