/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializable;

import Model.ModelItem;
import Model.ModelPolygon;
import Model.ModelVertex;
import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class SerializableModelItem implements java.io.Serializable {

    protected ArrayList<SerializableModelItem> content;
    protected String name;
    protected int type;
    protected SerializableModelItem parent;
    protected float[] location;
    protected float[] rotation;
    protected float[] color;
    protected String texture;
    
    final static public int type_Property = 0;
    final static public int type_Group = 1;
    final static public int type_Mesh = 2;
    final static public int type_Polygon = 3;
    final static public int type_Vertex = 4;
    final static public int type_Attachment = 5;

    public SerializableModelItem(ModelItem m, SerializableModelItem p) {
        content = new ArrayList();
        name = m.getName();
        parent = p;
        type = m.getType();
        location = new float[3];
        rotation = new float[3];
        color = new float[4];

        location[0] = m.getPoint().getX();
        location[1] = m.getPoint().getY();
        location[2] = m.getPoint().getZ();

        rotation[0] = m.getPoint().getRotation().getYaw();
        rotation[1] = m.getPoint().getRotation().getPitch();
        rotation[2] = m.getPoint().getRotation().getRoll();
        
        if (type == type_Polygon) {
            texture = ((ModelPolygon) m).getTexture().getName();
        }
        if (type == type_Vertex) {
            color[0] = ((ModelVertex) m).getColor().getRed();
            color[1] = ((ModelVertex) m).getColor().getGreen();
            color[2] = ((ModelVertex) m).getColor().getBlue();
            color[3] = ((ModelVertex) m).getColor().getAlpha();
        }
    }

    public float[] getColor() {
        return color;
    }

    public void setColor(float[] color) {
        this.color = color;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void addContent(SerializableModelItem m) {
        content.add(m);
    }

    public void removeContent(SerializableModelItem m) {
        content.remove(m);
    }

    public ArrayList<SerializableModelItem> getContent() {
        return content;
    }

    public void setContent(ArrayList<SerializableModelItem> content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SerializableModelItem getParent() {
        return parent;
    }

    public void setParent(SerializableModelItem parent) {
        this.parent = parent;
    }

    public float[] getLocation() {
        return location;
    }

    public void setLocation(float[] location) {
        this.location = location;
    }

    public float[] getRotation() {
        return rotation;
    }

    public void setRotation(float[] rotation) {
        this.rotation = rotation;
    }
}
