/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class GUIContainer {

    protected ArrayList<GUI> content;

    public GUIContainer() {
        content = new ArrayList();
    }

    public void addGUI(GUI gui) {
        content.add(gui);
    }

    public void removeGUI(GUI gui) {
        content.remove(gui);
    }

    public ArrayList<GUI> getContent() {
        return content;
    }

    public void setContent(ArrayList<GUI> content) {
        this.content = content;
    }
}
