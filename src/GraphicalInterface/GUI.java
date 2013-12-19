/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import Point.Point;

/**
 *
 * @author Cuper
 */
public abstract class GUI {

    public abstract void render();
    protected boolean hover = false;
    protected boolean clicked = false;
    protected float sizeX, sizeY;
    protected Point point;
    protected boolean visible = true;
    protected GUIContainer container;

    public GUI(Point point, float sX, float sY) {
        this.point = point;
        sizeX = sX;
        sizeY = sY;
    }

    public GUI(GUIContainer container, Point point, float sX, float sY) {
        this(point, sX, sY);
        this.container = container;
        container.addGUI(this);
    }

    /**
     * override these as you please
     */
    public void onClick() {
    }

    public void onHover() {
    }

    public void onRelease() {
    }

    public float getSizeX() {
        return sizeX;
    }

    public void setSizeX(float sizeX) {
        this.sizeX = sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public void setSizeY(float sizeY) {
        this.sizeY = sizeY;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public GUIContainer getContainer() {
        return container;
    }

    public void setContainer(GUIContainer container) {
        this.container = container;
        container.addGUI(this);
    }
}
