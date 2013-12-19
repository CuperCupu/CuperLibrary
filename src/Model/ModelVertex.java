/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Main.Main;
import Point.Point;
import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class ModelVertex extends ModelItem {

    protected ColorOther color;

    public ModelVertex(Main main, ModelItem i, String name, float x, float y, float z) {
        super(main, name, i, ModelItem.type_Vertex);
        point.getLocation().setLocation(x, y, z);
        color = new ColorOther(1f, 1f, 1f);
    }

    public ModelVertex(Main main, ModelItem i, String name, Point point) {
        super(main, name, i, ModelItem.type_Vertex);
        this.point = point;
        color = new ColorOther(1f, 1f, 1f);
    }

    public ModelVertex(Main main, ModelItem i, String name, float x, float y, float z, ColorOther color) {
        super(main, name, i, ModelItem.type_Vertex);
        point.getLocation().setLocation(x, y, z);
        this.color = color;
    }

    public ModelVertex(Main main, ModelItem i, String name, Point point, ColorOther color) {
        super(main, name, i, ModelItem.type_Vertex);
        this.point = point;
        this.color = color;
    }

    public ColorOther getColor() {
        return color;
    }

    public void setColor(ColorOther color) {
        this.color = color;
    }

    public void setColor(float red, float green, float blue, float alpha) {
        this.color = new ColorOther(red, green, blue, alpha);
    }

    public float getX() {
        return point.getX();
    }

    public float getY() {
        return point.getY();
    }

    public float getZ() {
        return point.getZ();
    }

    public void setX(float x) {
        point.setX(x);
    }

    public void setY(float y) {
        point.setY(y);
    }

    public void setZ(float z) {
        point.setZ(z);
    }
}
