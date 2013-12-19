package Model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cuper
 */
import Main.Main;
import java.util.ArrayList;

public class ModelMesh extends ModelItem {

    protected ArrayList<ModelPolygon> polygon;

    public ModelMesh(Main main, ModelItem i, String name) {
        super(main, name, i, ModelItem.type_Mesh);
        polygon = new ArrayList();
    }

    public ModelMesh(Main main, ModelItem i, String name, String type, float size, ColorOther color) {
        this(main, i, name);
        if ("cube".equals(type)) {
            //Top; 1
            ArrayList<ModelVertex> vertexes = new ArrayList();
            vertexes.add(new ModelVertex(main, this, name + "1", size, size, size, color));
            vertexes.add(new ModelVertex(main, this, name + "2", -size, size, size, color));
            vertexes.add(new ModelVertex(main, this, name + "3", -size, size, -size, color));
            vertexes.add(new ModelVertex(main, this, name + "4", size, size, -size, color));
            vertexes.add(new ModelVertex(main, this, name + "5", size, -size, size, color));
            vertexes.add(new ModelVertex(main, this, name + "6", -size, -size, size, color));
            vertexes.add(new ModelVertex(main, this, name + "7", -size, -size, -size, color));
            vertexes.add(new ModelVertex(main, this, name + "8", size, -size, -size, color));
            ModelPolygon poly = new ModelPolygon(main, this, name + "Top", color);
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(3));
            polygon.add(poly);
            //Bottom; 2
            poly = new ModelPolygon(main, this, name + "Bottom", color);
            poly.addVertex(vertexes.get(4));
            poly.addVertex(vertexes.get(5));
            poly.addVertex(vertexes.get(6));
            poly.addVertex(vertexes.get(7));
            polygon.add(poly);
            //Front; 3
            poly = new ModelPolygon(main, this, name + "Front", color);
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(5));
            poly.addVertex(vertexes.get(4));
            polygon.add(poly);
            //Back; 4
            poly = new ModelPolygon(main, this, name + "Back", color);
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(3));
            poly.addVertex(vertexes.get(7));
            poly.addVertex(vertexes.get(6));
            polygon.add(poly);
            //Left; 5
            poly = new ModelPolygon(main, this, name + "Left", color);
            poly.addVertex(vertexes.get(3));
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(4));
            poly.addVertex(vertexes.get(7));
            polygon.add(poly);
            //Right; 6
            poly = new ModelPolygon(main, this, name + "Right", color);
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(6));
            poly.addVertex(vertexes.get(5));
            polygon.add(poly);
        }
    }

    public ModelMesh(Main main, ModelItem i, String name, String type, float width, float height, float length, ColorOther color) {
        this(main, i, name);
        if (type == "centerBlock") {
            //Top; 1
            ArrayList<ModelVertex> vertexes = new ArrayList();
            vertexes.add(new ModelVertex(main, this, name + "1", width, height, length, color));
            vertexes.add(new ModelVertex(main, this, name + "2", -width, height, length, color));
            vertexes.add(new ModelVertex(main, this, name + "3", -width, height, -length, color));
            vertexes.add(new ModelVertex(main, this, name + "4", width, height, -length, color));
            vertexes.add(new ModelVertex(main, this, name + "5", width, -height, length, color));
            vertexes.add(new ModelVertex(main, this, name + "6", -width, -height, length, color));
            vertexes.add(new ModelVertex(main, this, name + "7", -width, -height, -length, color));
            vertexes.add(new ModelVertex(main, this, name + "8", width, -height, -length, color));
            ModelPolygon poly = new ModelPolygon(main, this, name + "Top", color);
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(3));
            polygon.add(poly);
            //Bottom; 2
            poly = new ModelPolygon(main, this, name + "Bottom", color);
            poly.addVertex(vertexes.get(4));
            poly.addVertex(vertexes.get(5));
            poly.addVertex(vertexes.get(6));
            poly.addVertex(vertexes.get(7));
            polygon.add(poly);
            //Front; 3
            poly = new ModelPolygon(main, this, name + "Front", color);
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(5));
            poly.addVertex(vertexes.get(4));
            polygon.add(poly);
            //Back; 4
            poly = new ModelPolygon(main, this, name + "Back", color);
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(3));
            poly.addVertex(vertexes.get(7));
            poly.addVertex(vertexes.get(6));
            polygon.add(poly);
            //Left; 5
            poly = new ModelPolygon(main, this, name + "Left", color);
            poly.addVertex(vertexes.get(3));
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(4));
            poly.addVertex(vertexes.get(7));
            polygon.add(poly);
            //Right; 6
            poly = new ModelPolygon(main, this, name + "Right", color);
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(6));
            poly.addVertex(vertexes.get(5));
            polygon.add(poly);
        } else if (type == "topBlock") {
            //Top; 1
            ArrayList<ModelVertex> vertexes = new ArrayList();
            vertexes.add(new ModelVertex(main, this, name + "1", width, 0, length, color));
            vertexes.add(new ModelVertex(main, this, name + "2", -width, 0, length, color));
            vertexes.add(new ModelVertex(main, this, name + "3", -width, 0, -length, color));
            vertexes.add(new ModelVertex(main, this, name + "4", width, 0, -length, color));
            vertexes.add(new ModelVertex(main, this, name + "5", width, 2 * -height, length, color));
            vertexes.add(new ModelVertex(main, this, name + "6", -width, 2 * -height, length, color));
            vertexes.add(new ModelVertex(main, this, name + "7", -width, 2 * -height, -length, color));
            vertexes.add(new ModelVertex(main, this, name + "8", width, 2 * -height, -length, color));
            ModelPolygon poly = new ModelPolygon(main, this, name + "Top", color);
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(3));
            polygon.add(poly);
            //Bottom; 2
            poly = new ModelPolygon(main, this, name + "Bottom", color);
            poly.addVertex(vertexes.get(4));
            poly.addVertex(vertexes.get(5));
            poly.addVertex(vertexes.get(6));
            poly.addVertex(vertexes.get(7));
            polygon.add(poly);
            //Front; 3
            poly = new ModelPolygon(main, this, name + "Front", color);
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(5));
            poly.addVertex(vertexes.get(4));
            polygon.add(poly);
            //Back; 4
            poly = new ModelPolygon(main, this, name + "Back", color);
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(3));
            poly.addVertex(vertexes.get(7));
            poly.addVertex(vertexes.get(6));
            polygon.add(poly);
            //Left; 5
            poly = new ModelPolygon(main, this, name + "Left", color);
            poly.addVertex(vertexes.get(3));
            poly.addVertex(vertexes.get(0));
            poly.addVertex(vertexes.get(4));
            poly.addVertex(vertexes.get(7));
            polygon.add(poly);
            //Right; 6
            poly = new ModelPolygon(main, this, name + "Right", color);
            poly.addVertex(vertexes.get(1));
            poly.addVertex(vertexes.get(2));
            poly.addVertex(vertexes.get(6));
            poly.addVertex(vertexes.get(5));
            polygon.add(poly);
        }
    }

    public void translateCenter(float x, float y, float z) {
        for (int i = 0; i < polygon.size(); i++) {
            ModelPolygon p = polygon.get(i);
            p.getPoint().move(x, 'x');
            p.getPoint().move(y, 'y');
            p.getPoint().move(z, 'z');
        }
    }

    public void addPolygon(ModelPolygon poly) {
        poly.setParent(this);
        polygon.add(poly);
    }

    public void removePolygon(ModelPolygon poly) {
        polygon.remove(poly);
    }

    public ArrayList<ModelPolygon> getPolygon() {
        return polygon;
    }
}
