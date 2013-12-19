/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Main.Main;
import Point.Point;

/**
 * 
 * @author Cuper
 */
public class ModelItem {

    protected String name;
    protected Point point;
    protected Point tPoint;
    protected Point animPoint;
    protected ModelItem parent;
    protected Main main;
    protected int type;
    
    final static public int type_Property = 0;
    final static public int type_Group = 1;
    final static public int type_Mesh = 2;
    final static public int type_Polygon = 3;
    final static public int type_Vertex = 4;
    final static public int type_Attachment = 5;

    public ModelItem(Main main, String name, int t) {
        this.name = name;
        point = new Point();
        animPoint = new Point();
        this.main = main;
        type = t;
    }

    public ModelItem(Main main, String name, ModelItem i, int t) {
        this(main, name, t);
        parent = i;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getAnimPoint() {
        return animPoint;
    }

    public void setAnimPoint(Point animPoint) {
        this.animPoint = animPoint;
    }

    public ModelItem getParent() {
        return parent;
    }

    public void setParent(ModelItem parent) {
        this.parent = parent;
    }

    public Point gettPoint() {
        return tPoint;
    }

    public void settPoint(Point tPoint) {
        this.tPoint = tPoint;
    }
    
    
}
