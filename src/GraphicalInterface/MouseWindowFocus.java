/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import Listener.MouseAction;
import Main.Main;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Cuper
 */
public class MouseWindowFocus extends MouseAction {

    public MouseWindowFocus(Main main, String name, int event) {
        super(main, name, event);
    }

    @Override
    public void action() {
        float x = Mouse.getX(), y = Mouse.getY();
        ArrayList<CWindow> windows = main.getCurrentScene().getWindows();
        main.setFocusedWindow(null);
        for (int i = 0; i < windows.size(); i++) {
            CWindow w = windows.get(i);
            if (w != main.getCurrentScene().getScreen()) {
                if ((x >= w.getPoint().getX()) && (x <= w.getPoint().getX() + w.getSizeX()) && (y >= w.getPoint().getY()) && (y <= w.getPoint().getY() + w.getSizeY())) {
                    main.setFocusedWindow(w);
                    break;
                }
            }
        }
    }
}
