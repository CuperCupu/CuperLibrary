/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import Main.Main;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Cuper
 */
public class KeyAction extends Thread implements ListenerItem {

    protected Main main;
    protected Listener listener;
    protected String name;
    private int key;
    protected Boolean alive;

    public KeyAction(Main main, String name, int key) {
        this.main = main;
        listener = main.getListener();
        this.name = name;
        this.key = key;
        this.alive = true;
    }

    public KeyAction(Main main, Listener listener, String name, int key) {
        this(main, name, key);
        this.listener = listener;
    }

    @Override
    public void action() {
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public boolean isAction() {
        return Keyboard.isKeyDown(key);
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
