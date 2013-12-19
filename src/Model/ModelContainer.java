/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Main.Main;
import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class ModelContainer {

    protected ArrayList<Model> content;

    public ModelContainer() {
        content = new ArrayList();
    }

    public ArrayList<Model> getContent() {
        return content;
    }

    public void addModel(Model model) {
        content.add(model);
    }

    public void removeModel(Model model) {
        content.remove(model);
    }
}
