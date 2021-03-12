package view.income;

import model.Income;
import presenter.income.IIncomePresenter;
import presenter.income.IncomePresenter;
import view.Navigator;

import java.util.Scanner;

public class IncomeView implements IIncomeView {
    private IIncomePresenter _incomePresenter;

    @Override
    public void start(Object args) {
        _incomePresenter = new IncomePresenter(this, args);
        _incomePresenter.fetchIncome();
    }

    @Override
    public void displayOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n########## INCOME ##########\n");
        System.out.println("What would you like to do?");
        System.out.println("1. See current monthly income");
        System.out.println("2. Edit projected income");
        System.out.println("3. Edit actual income");
        System.out.println("4. Save");
        System.out.println("5. Go back");
        System.out.print("Your selection: ");

        String selection = scanner.nextLine();
        _incomePresenter.optionSelected(selection);
    }

    @Override
    public void displayIncome(Income income) {
        System.out.println(income.toString());
    }

    @Override
    public void displayEditProjectedIncomePrompt(String month, int year) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your projected income for " + month + " " + year + "? ");
        double projectedIncome = scanner.nextDouble();
        _incomePresenter.projectedIncomeSelected(projectedIncome);
    }

    @Override
    public void displayEditActualIncomePrompt(String month, int year) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is your current actual income for " + month + " " + year + "? ");
        double actualIncome = scanner.nextDouble();
        _incomePresenter.actualIncomeSelected(actualIncome);
    }

    @Override
    public void displaySavePrompt(Income income) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to save this income:\n" + income.toString() + "\n(y/n)? ");
        String answer = scanner.nextLine();
        _incomePresenter.handleSave(answer);
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
