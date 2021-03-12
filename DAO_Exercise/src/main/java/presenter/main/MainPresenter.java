package presenter.main;

import view.main.IMainView;
import view.new_budget.NewBudgetView;
import view.update_budget.UpdateBudgetView;

public class MainPresenter implements IMainPresenter {
    private final IMainView _mainView;

    public MainPresenter(IMainView mainView) {
        _mainView = mainView;
    }

    @Override
    public void optionSelected(String option) {
        switch (option) {
            case "1":
                _mainView.navigate(NewBudgetView.class);
                break;
            case "2":
                _mainView.navigate(UpdateBudgetView.class);
                break;
            case "3":
                System.exit(0);
            default:
                _mainView.displayOptions();
                break;
        }
    }
}
