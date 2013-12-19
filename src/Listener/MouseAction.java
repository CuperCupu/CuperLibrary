/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener;

import Main.Main;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Cuper
 */
public class MouseAction extends Thread implements ListenerItem {

    protected Main main;
    protected Listener listener;
    protected String name;
    protected int event;
    protected Boolean alive;
    protected int moved;
    protected boolean leftClicked;
    protected boolean rightClicked;
    public static int eventKeyDownLeft = 0;
    public static int eventKeyHoldLeft = 1;
    public static int eventKeyUpLeft = 2;
    public static int eventKeyDownRight = 3;
    public static int eventKeyHoldRight = 4;
    public static int eventKeyUpRight = 5;
    public static int eventMouseScroll = 6;
    public static int eventMouseScrollUp = 7;
    public static int eventMouseScrollDown = 8;
    public static int eventMouseMovement = 9;

    public MouseAction(Main main, String name, int event) {
        this.main = main;
        listener = main.getListener();
        this.name = name;
        this.event = event;
        this.alive = true;
    }

    public MouseAction(Main main, Listener listener, String name, int event) {
        this(main, name, event);
        this.listener = listener;
    }

    @Override
    public void action() {
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    @Override
    public boolean isAction() {
        if (Mouse.isButtonDown(0)) {
            if (!leftClicked) {
                leftClicked = true;
                if (event == eventKeyDownLeft) {
                    return true;
                }
            }
            if (event == eventKeyHoldLeft) {
                return true;
            }
        } else {
            if (leftClicked) {
                leftClicked = false;
                if (event == eventKeyUpLeft) {
                    return true;
                }
            }
        }
        if (Mouse.isButtonDown(1)) {
            if (!rightClicked) {
                rightClicked = true;
                if (event == eventKeyDownRight) {
                    return true;
                }
            }
            if (event == eventKeyHoldRight) {
                return true;
            }
        } else {
            if (rightClicked) {
                rightClicked = false;
                if (event == eventKeyUpRight) {
                    return true;
                }
            }
        }
        if (event == eventMouseScroll) {
            if (listener.scroll != 0) {
                return true;
            } else if ((event == eventMouseScrollUp) && (listener.scroll > 0)) {
                return true;
            } else if ((event == eventMouseScrollDown) && (listener.scroll < 0)) {
                return true;
            }
        }
        if (event == eventMouseMovement) {
            if ((listener.movementX != 0) || (listener.movementY != 0)) {
                return true;
            }
        }
        return false;
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

    public String getBName() {
        return name;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public int getMoved() {
        return moved;
    }

    public void setMoved(int moved) {
        this.moved = moved;
    }

    public boolean isLeftClicked() {
        return leftClicked;
    }

    public void setLeftClicked(boolean leftClicked) {
        this.leftClicked = leftClicked;
    }

    public boolean isRightClicked() {
        return rightClicked;
    }

    public void setRightClicked(boolean rightClicked) {
        this.rightClicked = rightClicked;
    }
}
