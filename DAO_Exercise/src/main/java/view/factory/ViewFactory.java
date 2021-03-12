package view.factory;

import view.IView;
import view.expense.ExpenseView;
import view.income.IncomeView;
import view.main.MainView;
import view.new_budget.NewBudgetView;
import view.update_budget.UpdateBudgetView;

public class ViewFactory implements IViewFactory {
    @Override
    public IView make(Class<?> clazz) {
        if (clazz == MainView.class) {
            return new MainView();
        } else if (clazz == NewBudgetView.class) {
            return new NewBudgetView();
        } else if (clazz == UpdateBudgetView.class) {
            return new UpdateBudgetView();
        } else if (clazz == IncomeView.class) {
            return new IncomeView();
        } else if (clazz == ExpenseView.class) {
            return new ExpenseView();
        }
        return null;
    }
}
