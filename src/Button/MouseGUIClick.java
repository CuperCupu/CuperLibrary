/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Button;

import GraphicalInterface.CWindow;
import GraphicalInterface.GUI;
import Listener.MouseAction;
import Main.Main;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Cuper
 */
public class MouseGUIClick extends MouseAction {

    public MouseGUIClick(Main main, String name, int event) {
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
                    if (g instanceof CWindow) {
                        CWindow win = (CWindow) g;
                        ArrayList<GUI> guis2 = win.getGuis();
                        boolean b = false;
                        for (int j = 0; j < guis2.size(); j++) {
                            GUI g2 = guis2.get(j);
                            if ((x <= g2.getPoint().getX() + (g2.getSizeX())) && (x >= g2.getPoint().getX()) && (y <= g2.getPoint().getY() + (g2.getSizeY())) && (y >= g2.getPoint().getY())) {
                                b = true;
                                break;
                            }
                        }
                        if (!b) {
                            if (!g.isClicked()) {
                                g.onClick();
                            }
                            g.setClicked(true);
                        }
                    } else {
                        if (!g.isClicked()) {
                            g.onClick();
                        }
                        g.setClicked(true);
                    }
                } else {
                    g.setClicked(false);
                }
            }
        }
    }
}
