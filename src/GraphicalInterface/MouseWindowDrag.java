/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import Listener.MouseAction;
import Main.Main;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Cuper
 */
public class MouseWindowDrag extends MouseAction {

    public MouseWindowDrag(Main main, String name, int event) {
        super(main, name, event);
    }

    @Override
    public void action() {
        float x = Mouse.getX(), y = Mouse.getY();
        float mx = main.getListener().movementX, my = main.getListener().movementY;

        if (main.getFocusedWindow() != null) {
            if (main.getFocusedWindow().isMovable()) {
                main.getFocusedWindow().move(mx, my);
            }
        }
    }
}
