package view.new_budget;

import presenter.new_budget.INewBudgetPresenter;
import presenter.new_budget.NewBudgetPresenter;
import view.IView;
import view.Navigator;

import java.util.Scanner;

public class  NewBudgetView implements INewBudgetView, IView {
    private INewBudgetPresenter _budgetPresenter;

    @Override
    public void start(Object args) {
        _budgetPresenter = new NewBudgetPresenter(this);
        displayMonthPrompt();
    }

    @Override
    public void displayMonthPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What month is this new budget for? ");
        String month = scanner.nextLine();
        _budgetPresenter.monthSelected(month);
    }

    @Override
    public void displayYearPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What year is this new budget for? ");
        String year = scanner.nextLine();
        _budgetPresenter.yearSelected(year);
    }

    @Override
    public void displayMonthlyAllowancePrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your monthly allowance? ");
        double allowance = scanner.nextDouble();
        _budgetPresenter.monthlyAllowanceSelected(allowance);
    }

    @Override
    public void displaySavePrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Save? (y/n) ");
        String answer = scanner.nextLine();
        _budgetPresenter.handleSave(answer);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void pop() {
        Navigator.pop();
    }
}
