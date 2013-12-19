/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Point.Point;

/**
 *
 * @author Cuper
 */
public class Camera {

    private Point targetPoint;
    private Point eyePoint;
    private float speed;
    private float sensitivity;
    private float distance;

    public Camera() {
        targetPoint = new Point();
        eyePoint = new Point();
        speed = 0.5f;
        sensitivity = 0.5f;
        distance = 30f;
        targetPoint.setRotation(eyePoint.getRotation());
    }

    public Point getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(Point point) {
        this.targetPoint = point;
    }

    public Point getEyePoint() {
        eyePoint.setX(targetPoint.getX() - ((distance * (float) Math.cos(Math.toRadians(eyePoint.getRotation().getYaw() - 90))) * (float) Math.cos(Math.toRadians(eyePoint.getRotation().getPitch()))));
        eyePoint.setZ(targetPoint.getZ() - (distance * (float) Math.sin(Math.toRadians(eyePoint.getRotation().getYaw() - 90))) * (float) Math.cos(Math.toRadians(eyePoint.getRotation().getPitch())));
        eyePoint.setY(targetPoint.getY() + (distance * (float) Math.sin(Math.toRadians(eyePoint.getRotation().getPitch()))));
        return eyePoint;
    }

    public void setEyePoint(Point eyePoint) {
        this.eyePoint = eyePoint;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(float sensitivity) {
        this.sensitivity = sensitivity;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
