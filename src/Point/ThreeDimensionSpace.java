package Point;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * A class that represent 3d object
 *
 * @version 1.0.0
 * @author Surya Wono
 */
public class ThreeDimensionSpace {

    private Point centerPoint;
    private ArrayList<Point> pointList;

    public ThreeDimensionSpace(Point centerPoint) {
        this(centerPoint, new ArrayList());
    }

    public ThreeDimensionSpace() {
        this(new Point());
    }

    public ThreeDimensionSpace(Point centerPoint, ArrayList pointList) {
        this.centerPoint = centerPoint;
        this.pointList = pointList;
    }

    public Point getCentralPoint() {
        return centerPoint;
    }

    public void setCentralPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public void addPoint(Point p) {
        this.pointList.add(p);
    }

    public ArrayList<Point> getPointList() {
        return pointList;
    }

    /**
     * rotate the object by yaw axis
     *
     * @param degree amount of degree to rotate
     */
    public void rotateYaw(float degree) {
        for (Point p : pointList) {
            float alpha = (float) Math.atan2(p.getX(), p.getZ());
            degree = (float) Math.toRadians(degree);
            alpha += degree;
            float r = (float) Math.sqrt(Math.pow(p.getX() - centerPoint.getX(), 2) + Math.pow(p.getZ() - centerPoint.getZ(), 2));
            float newX = (float) Math.sin(alpha) * r;
            float newZ = (float) Math.cos(alpha) * r;
            p.setX(newX);
            p.setZ(newZ);
        }
    }

    /**
     * rotate the object by pitch axis
     *
     * @param degree amount of degree to rotate
     */
    public void rotatePitch(float degree) {
        for (Point p : pointList) {
            float alpha = (float) Math.atan2(p.getZ(), p.getY());
            degree = (float) Math.toRadians(degree);
            alpha += degree;
            float r = (float) Math.sqrt(Math.pow(p.getY() - centerPoint.getY(), 2) + Math.pow(p.getZ() - centerPoint.getZ(), 2));
            float newZ = (float) Math.sin(alpha) * r;
            float newY = (float) Math.cos(alpha) * r;
            p.setY(newY);
            p.setZ(newZ);
        }
    }

    /**
     * rotate the object by roll axis
     *
     * @param degree amount of degree to rotate
     */
    public void rotateRoll(float degree) {
        for (Point p : pointList) {
            float alpha = (float) Math.atan2(p.getY(), p.getX());
            degree = (float) Math.toRadians(degree);
            alpha += degree;
            float r = (float) Math.sqrt(Math.pow(p.getX() - centerPoint.getX(), 2) + Math.pow(p.getY() - centerPoint.getY(), 2));
            float newY = (float) Math.sin(alpha) * r;
            float newX = (float) Math.cos(alpha) * r;
            p.setX(newX);
            p.setY(newY);
        }
    }
}
