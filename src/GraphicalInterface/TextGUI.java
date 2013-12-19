/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import Main.Main;
import Main.Scene;
import Model.ColorOther;
import Point.Point;

/**
 *
 * @author Cuper
 */
public class TextGUI extends GUI {

    protected Main main;
    protected String text;
    protected float size;
    protected ColorOther color;

    public TextGUI(Main main, String text, Point point, float size) {
        super(main.getCurrentScene().getGuiContainer(), point, 0f, 0f);
        this.main = main;
        this.text = text;
        this.point = point;
        this.size = size;
        color = new ColorOther(0f, 0f, 0f);
    }
    
    public TextGUI(Scene scene, String text, Point point, float size) {
        super(scene.getGuiContainer(), point, 0f, 0f);
        this.main = scene.getMain();
        this.text = text;
        this.point = point;
        this.size = size;
        color = new ColorOther(0f, 0f, 0f);
    }

    public TextGUI(Main main, String text, Point point, float size, ColorOther color) {
        this(main, text, point, size);
        this.color = color;
    }

    @Override
    public void render() {
        main.getTextRenderer().drawString(text, point.getX(), point.getY(), size, color);
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public ColorOther getColor() {
        return color;
    }

    public void setColor(ColorOther color) {
        this.color = color;
    }
}
