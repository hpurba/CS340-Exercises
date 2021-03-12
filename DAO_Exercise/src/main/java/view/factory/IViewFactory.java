package view.factory;

import view.IView;

public interface IViewFactory {
    IView make(Class<?> clazz);
}
