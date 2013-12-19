/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Point.Point;
import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class Sorter {

    private ArrayList<Model> container;
    private ArrayList<ModelPolygon> toRender;
    private ArrayList<ModelPolygon> sortedRender;
    private Point cameraPoint;

    public Sorter(ArrayList container, Point point) {
        this.container = container;
        cameraPoint = point;
        toRender = new ArrayList();
        sortedRender = new ArrayList();
    }

    public Point calculatePoint(ModelItem p) {
        Point po = new Point();
        Point.copy(p.getPoint(), po);
        if (p.getParent() != null) {
            Point.add(calculatePoint(p.getParent()), po);
        }
        return po;
    }

    public ModelVertex getFurthestVertex(ArrayList<ModelVertex> vertex) {
        ArrayList<ModelVertex> vertexes = new ArrayList();
        for (int i = 0; i < vertex.size(); i++) {
            vertexes.add(vertex.get(i));
        }
        ModelVertex furthest = vertexes.get(0);
        if (vertexes.size() > 1) {
            for (int i = 1; i < vertexes.size(); i++) {
                Point p1 = calculatePoint(furthest);
                Point p2 = calculatePoint(vertexes.get(i));;
                float dis1 = p1.distanceFrom(cameraPoint);
                float dis2 = p2.distanceFrom(cameraPoint);
                if (dis2 > dis1) {
                    furthest = vertexes.get(i);
                }
            }
        }
        return furthest;
    }

    public ModelVertex getClosestVertex(ArrayList<ModelVertex> vertex) {
        ArrayList<ModelVertex> vertexes = new ArrayList();
        for (int i = 0; i < vertex.size(); i++) {
            vertexes.add(vertex.get(i));
        }
        ModelVertex closest = vertexes.get(0);
        if (vertexes.size() > 1) {
            for (int i = 1; i < vertexes.size(); i++) {
                Point p1 = calculatePoint(closest);
                Point p2 = calculatePoint(vertexes.get(i));;
                float dis1 = p1.distanceFrom(cameraPoint);
                float dis2 = p2.distanceFrom(cameraPoint);
                if (dis2 < dis1) {
                    closest = vertexes.get(i);
                }
            }
        }
        return closest;
    }

    public void sort() {
        toRender.clear();
        sortedRender.clear();
        for (int i = 0; i < container.size(); i++) {
            Model m = container.get(i);
            for (int j = 0; j < m.getGroup().size(); j++) {
                sortItem(m.getGroup().get(j));
            }
        }

        ArrayList<ModelPolygon> unsortedRender = new ArrayList();
        for (int i = 0; i < toRender.size(); i++) {
            ModelPolygon p = toRender.get(i);

            if ((p.getTexture() != null) && ((p.getTexture().isAlpha()) || (p.getColor().getAlpha() < 1f))) {
                unsortedRender.add(p);
            } else {
                sortedRender.add(p);
            }
        }


        while (unsortedRender.size() > 0) {
            ModelPolygon furthest = unsortedRender.get(0);
            if (unsortedRender.size() > 1) {
                for (int i = 1; i < unsortedRender.size(); i++) {
                    Point p1 = calculatePoint(furthest);
                    Point p2 = calculatePoint(unsortedRender.get(i));
                    Point p1f = calculatePoint(getFurthestVertex(furthest.getVertex()));
                    Point p2f = calculatePoint(getFurthestVertex(unsortedRender.get(i).getVertex()));
                    Point p1c = calculatePoint(getClosestVertex(furthest.getVertex()));
                    Point p2c = calculatePoint(getClosestVertex(unsortedRender.get(i).getVertex()));
                    float dis1 = p1.distanceFrom(cameraPoint);
                    float dis2 = p2.distanceFrom(cameraPoint);
                    float disf1 = p1f.distanceFrom(cameraPoint);
                    float disc1 = p1c.distanceFrom(cameraPoint);
                    float disf2 = p2f.distanceFrom(cameraPoint);
                    float disc2 = p2c.distanceFrom(cameraPoint);
                    if (disf2 > disf1) {
                        furthest = unsortedRender.get(i);
                    }
                }
            }
            unsortedRender.remove(furthest);
            sortedRender.add(furthest);
        }


    }

    public void sortItem(ModelItem mi) {
        if (mi instanceof ModelGroup) {
            sortGroup((ModelGroup) mi);
        } else if (mi instanceof ModelMesh) {
            sortMesh((ModelMesh) mi);
        } else if (mi instanceof ModelPolygon) {
            sortPolygon((ModelPolygon) mi);
        }
    }

    public void sortGroup(ModelGroup g) {
        for (int i = 0; i < g.getContent().size(); i++) {
            sortItem(g.getContent().get(i));
        }
    }

    public void sortMesh(ModelMesh me) {
        for (int i = 0; i < me.getPolygon().size(); i++) {
            sortPolygon(me.getPolygon().get(i));
        }
    }

    public void sortPolygon(ModelPolygon p) {
        toRender.add(p);
    }

    public ArrayList<ModelPolygon> getToRender() {
        return toRender;
    }

    public void setToRender(ArrayList<ModelPolygon> toRender) {
        this.toRender = toRender;
    }

    public ArrayList<Model> getContainer() {
        return container;
    }

    public void setContainer(ArrayList<Model> container) {
        this.container = container;
    }

    public ArrayList<ModelPolygon> getSortedRender() {
        return sortedRender;
    }

    public Point getCameraPoint() {
        return cameraPoint;
    }

    public void setCameraPoint(Point cameraPoint) {
        this.cameraPoint = cameraPoint;
    }

    public void sortModel() {
        ArrayList<Model> ml = container;
        for (int i = 0; i < ml.size(); i++) {
            ArrayList<ModelVertex> vl = ml.get(i).getContentVertex();
        for (int j = 0; j < vl.size(); j++) {}
        }
    }
}
