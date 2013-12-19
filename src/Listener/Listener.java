/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import Button.MouseGUIClick;
import Button.MouseGUIHover;
import Button.MouseGUIRelease;
import GraphicalInterface.MouseWindowDrag;
import GraphicalInterface.MouseWindowFocus;
import Main.Main;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Cuper
 */
public class Listener extends Thread {
    
    protected Main main;
    protected boolean lockMouse;
    protected ArrayList<ListenerItem> items;
    public float x = Mouse.getX(), y = Mouse.getY(), lx = x, ly = y, movementX, movementY, scroll;
    
    public Listener(Main main) {
        this.main = main;
        items = new ArrayList();
        
        addItem(new MouseGUIHover(main, "mouseButtonHover", MouseAction.eventMouseMovement));
        addItem(new MouseGUIClick(main, "mouseButtonClick", MouseAction.eventKeyHoldLeft));
        addItem(new MouseWindowFocus(main, "mouseWindowFocus", MouseAction.eventKeyDownLeft));
        addItem(new MouseWindowDrag(main, "mouseWindowDrag", MouseAction.eventKeyHoldLeft));
        addItem(new MouseGUIRelease(main, "mouseButtonRelease", MouseAction.eventKeyUpLeft));
    }

    /**
     * To be overriden
     */
    public void Initialization() {
    }
    
    public final void addItem(Object o) {
        if (o instanceof MouseAction) {
            ((MouseAction) o).setListener(this);
            items.add(((MouseAction) o));
        } else if (o instanceof KeyAction) {
            ((KeyAction) o).setListener(this);
            items.add(((KeyAction) o));
        }
    }
    
    public boolean isMouseLocked() {
        return lockMouse;
    }
    
    public void lockMouse(boolean lockMouse) {
        Mouse.setGrabbed(lockMouse);
        this.lockMouse = lockMouse;
    }
    
    public Main getMain() {
        return main;
    }
    
    public void setMain(Main main) {
        this.main = main;
    }
    
    public ArrayList<ListenerItem> getItems() {
        return items;
    }
    
    public void setItems(ArrayList<ListenerItem> items) {
        this.items = items;
    }
    
    @Override
    public void run() {
        while (main.isRunning()) {
            try {
                scroll = Mouse.getDWheel();
                x = Mouse.getX();
                y = Mouse.getY();
                movementX = x - lx;
                movementY = y - ly;
                lx = x;
                ly = y;
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isAction()) {
                        items.get(i).action();
                    }
                }
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
