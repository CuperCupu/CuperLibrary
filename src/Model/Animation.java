/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class Animation implements java.io.Serializable {

    protected String name;
    protected ArrayList<ArrayList> animation;
    protected int time;

    public Animation(String name) {
        this.name = name;
        animation = new ArrayList();
    }

    public void drawNewLine() {
        animation.add(new ArrayList());
    }

    /**
     * Draw the animation
     *
     * @param name name of the ModelItem to be manipulated
     * @param mode : "translate": Move each x, y, and z by x, y, and z; "set":
     * Set each x, y, and z to x, y, and z ; "rotate": Rotate yaw, pitch, and
     * roll by x, y, and z; "rotation": Set rotation of yaw, pitch, and roll to
     * x, y, z;
     * @param x
     * @param y
     * @param z
     */
    public void drawItem(String name, String mode, float x, float y, float z) {
        ArrayList ar = new ArrayList();
        ar.add(name);
        ar.add(mode);
        ar.add(x);
        ar.add(y);
        ar.add(z);
        animation.get(animation.size() - 1).add(ar);
    }

    public ArrayList<ArrayList> getData(int time) {
        return animation.get(time);
    }

    /**
     * Return the data of specific modelItem named name
     *
     * @param name
     * @return
     */
    public ArrayList getData(String name) {
        ArrayList<ArrayList> ar = getData(time);
        ArrayList a = null;
        for (int i = 0; i < ar.size(); i++) {
            if (name == null ? (String) ar.get(i).get(0) == null : name.equals((String) ar.get(i).get(0))) {
                a = ar.get(i);
                break;
            }
        }
        return a;
    }

    public ArrayList<ArrayList> getAnimation() {
        return animation;
    }

    public void setAnimation(ArrayList<ArrayList> animation) {
        this.animation = animation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDuration() {
        return animation.size();
    }
}
