package view.main;

import presenter.main.IMainPresenter;
import presenter.main.MainPresenter;
import view.IView;
import view.Navigator;

import java.util.Scanner;

public class MainView implements IMainView, IView {
    private IMainPresenter _mainPresenter;

    @Override
    public void start(Object args) {
        _mainPresenter = new MainPresenter(this);

        Scanner scanner = new Scanner(System.in);
        displayOptions();
        String selection = scanner.nextLine();
        _mainPresenter.optionSelected(selection);
    }

    @Override
    public void displayOptions() {
        System.out.println("\n########## HOME ##########\n");
        System.out.println("1. Make a new budget");
        System.out.println("2. Update an existing budget");
        System.out.println("3. Quit");

        System.out.print("Your selection: ");
    }

    @Override
    public void navigate(Class<?> clazz) {
        Navigator.push(clazz, null);
    }
}
