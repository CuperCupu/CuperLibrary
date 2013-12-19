/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Button;

import Main.Main;

/**
 *
 * @author Cuper
 */
public class ButtonAction {

    protected String name;

    public ButtonAction(String name) {
        this.name = name;
    }
    
    //Override this method to change the action
    public void action(Button button) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
