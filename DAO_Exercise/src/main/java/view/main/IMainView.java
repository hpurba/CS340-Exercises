package view.main;

import view.IView;

public interface IMainView extends IView {
    void displayOptions();
    void navigate(Class<?> clazz);
}
