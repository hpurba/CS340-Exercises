package view;

import view.factory.IViewFactory;
import view.factory.ViewFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Navigator {
    private final static Stack<Map<IView, Object>> _views = new Stack<>();

    public static void push(Class<?> clazz, Object args) {
        IViewFactory viewFactory = new ViewFactory();
        IView view = viewFactory.make(clazz);
        if (view != null) {
            Map<IView, Object> map = new HashMap<>();
            map.put(view, args);
            _views.push(map);
            view.start(args);
        }
    }

    public static void pop() {
        if (_views.size() > 1) {
            _views.pop();
            Map<IView, Object> map = _views.peek();
            IView view = map.entrySet().iterator().next().getKey();
            Object args = map.entrySet().iterator().next().getValue();
            view.start(args);
        }
    }
}
