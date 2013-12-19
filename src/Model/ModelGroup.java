package Model;


import Main.Main;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cuper
 */
public class ModelGroup extends ModelItem {
    
    protected ArrayList<ModelItem> content;
    
    public ModelGroup (Main main, ModelItem i, String name) {
        super(main, name, i, ModelItem.type_Group);
        content = new ArrayList();
    }

    public ArrayList<ModelItem> getContent() {
        return content;
    }
    
    public void addContent(ModelItem obj) {
        content.add(obj);
    }
}
