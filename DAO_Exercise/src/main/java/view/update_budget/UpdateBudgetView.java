package view.update_budget;

import model.Budget;
import presenter.update_budget.IUpdateBudgetPresenter;
import presenter.update_budget.UpdateBudgetPresenter;
import view.Navigator;

import java.util.List;
import java.util.Scanner;

public class UpdateBudgetView implements IUpdateBudgetView {
    private IUpdateBudgetPresenter _updateBudgetPresenter;

    @Override
    public void start(Object args) {
        _updateBudgetPresenter = new UpdateBudgetPresenter(this);
        _updateBudgetPresenter.fetchAllBudgets();
    }

    @Override
    public void displayAllBudgets(List<Budget> budgets) {
        System.out.println("\n########## UPDATE BUDGET ##########\n");

        for (int i = 0; i < budgets.size(); i++) {
            System.out.println((i + 1) + ". " + budgets.get(i).toString());
        }

        System.out.println("0. Go back");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select the budget you would like to update (" + 1 + " - " + budgets.size() + ") or go back (0): ");
        String selection = scanner.nextLine();
        _updateBudgetPresenter.budgetSelected(selection);
    }

    @Override
    public void displayEditingOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to edit (1 - 3)?");
        System.out.println("1. Income");
        System.out.println("2. Expenses");
        System.out.println("3. Go back");
        System.out.print("Your selection: ");

        String selection = scanner.nextLine();
        _updateBudgetPresenter.editingOptionSelected(selection);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void navigate(Class<?> clazz, Object args) {
        Navigator.push(clazz, args);
    }

    @Override
    public void pop() {
        Navigator.pop();
    }
}
