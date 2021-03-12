package view.expense;

import model.Expense;
import presenter.expense.ExpensePresenter;
import presenter.expense.IExpensePresenter;
import view.Navigator;

import java.util.List;
import java.util.Scanner;

public class ExpenseView implements IExpenseView {
    private IExpensePresenter _expensePresenter;

    @Override
    public void start(Object args) {
        _expensePresenter = new ExpensePresenter(this, args);
        _expensePresenter.fetchExpenses();
    }

    @Override
    public void displayOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n########## EXPENSES ##########\n");
        System.out.println("Select an option:");
        System.out.println("1. View all expenses");
        System.out.println("2. Add an expense");
        System.out.println("3. Edit an expense");
        System.out.println("4. Delete an expense");
        System.out.println("5. Go back");
        System.out.print("Your selection: ");

        String selection = scanner.nextLine();
        _expensePresenter.optionSelection(selection);
    }

    @Override
    public void displayExpenses(List<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("You have no expenses for this month");
        }

        for (int i = 0; i < expenses.size(); i++) {
            System.out.println(i + 1 + ". " + expenses.get(i).toString());
        }
    }

    @Override
    public void displayEditExpensePrompt(List<Expense> expenses) {
        displayExpenses(expenses);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select which expense you would like to edit (1 - " + expenses.size() + "): ");
        String selection = scanner.nextLine();
        _expensePresenter.editSelectedExpense(selection);
    }

    @Override
    public void displayEditNamePrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What is the name of this expense? ");
        String name = scanner.nextLine();
        _expensePresenter.editName(name);

    }

    @Override
    public void displayEditDayPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What day was this expense made? Use number representation: ");
        int day = scanner.nextInt();
        _expensePresenter.editDay(day);

    }

    @Override
    public void displayEditAmountPrompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How much was this expense? ");
        double amount = scanner.nextDouble();
        _expensePresenter.editAmount(amount);
    }

    @Override
    public void displaySavePrompt(Expense expense) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + expense.toString());
        System.out.print("Save (y/n) ");
        String answer = scanner.nextLine();
        _expensePresenter.handleSave(answer);
    }

    @Override
    public void displayDeleteExpensePrompt(List<Expense> expenses) {
        displayExpenses(expenses);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select which expense you would like to delete (1 - " + expenses.size() + "): ");
        String selection = scanner.nextLine();
        _expensePresenter.deleteSelectedExpense(selection);
    }

    @Override
    public void displayMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    @Override
    public void pop() {
        Navigator.pop();
    }
}
