/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Button;

import GraphicalInterface.GUI;
import Listener.MouseAction;
import Main.Main;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Cuper
 */
public class MouseGUIHover extends MouseAction {

    int k = 0;

    public MouseGUIHover(Main main, String name, int event) {
        super(main, name, event);
    }

    @Override
    public void action() {
        float x = Mouse.getX(), y = Mouse.getY();
        ArrayList<GUI> guis = main.getCurrentScene().getGuiContainer().getContent();
        for (int i = 0; i < guis.size(); i++) {
            GUI g = guis.get(i);
            if (g.isVisible()) {
                if ((x <= g.getPoint().getX() + (g.getSizeX())) && (x >= g.getPoint().getX()) && (y <= g.getPoint().getY() + (g.getSizeY())) && (y >= g.getPoint().getY())) {
                    if (!g.isHover()) {
                        g.onHover();
                    }
                    g.setHover(true);
                    break;
                } else {
                    g.setHover(false);
                }
            }
        }
    }
}
