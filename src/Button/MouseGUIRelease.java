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
public class MouseGUIRelease extends MouseAction {

    public MouseGUIRelease(Main main, String name, int event) {
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
                    if (g.isClicked()) {
                        g.onRelease();
                    }
                    g.setClicked(false);

                } else {
                    g.setClicked(false);
                }
            }
        }
    }
}
