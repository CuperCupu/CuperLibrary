package Point;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cuper
 */
public class Point {

    Location loc;
    Rotation rot;

    public Point() {
        loc = new Location();
        rot = new Rotation();
    }

    public Point(float x, float y, float z) {
        this();
        loc.setLocation(x, y, z);
    }

    public Point(float x, float y, float z, float yaw, float pitch, float roll) {
        this(x, y, z);
        rot.setRotation(yaw, pitch, roll);
    }

    /*  
     * value : self sexplained
     * axis : 'y' = yaw, 'p' = pitch, 'r' = roll
     */
    public void rotate(float value, char axis) {
        if (axis == 'y') {
            rot.setYaw(rot.getYaw() + value);
            while (rot.getYaw() > 180) {
                rot.setYaw(rot.getYaw() - 360);
                while (rot.getYaw() < -180) {
                    rot.setYaw(rot.getYaw() + 360);
                }
            }
            while (rot.getYaw() < -180) {
                rot.setYaw(rot.getYaw() + 360);
                while (rot.getYaw() > 180) {
                    rot.setYaw(rot.getYaw() - 360);
                }
            }
        } else if (axis == 'p') {
            rot.setPitch(rot.getPitch() + value);
            while (rot.getPitch() > 180) {
                rot.setPitch(rot.getPitch() - 360);
                while (rot.getPitch() < -180) {
                    rot.setPitch(rot.getPitch() + 360);
                }
            }
            while (rot.getPitch() < -180) {
                rot.setPitch(rot.getPitch() + 360);
                while (rot.getPitch() > 180) {
                    rot.setPitch(rot.getPitch() - 360);
                }
            }
        } else if (axis == 'r') {
            rot.setRoll(rot.getRoll() + value);
            while (rot.getRoll() > 180) {
                rot.setRoll(rot.getRoll() - 360);
                while (rot.getRoll() < -180) {
                    rot.setRoll(rot.getRoll() + 360);
                }
            }
            while (rot.getRoll() < -180) {
                rot.setRoll(rot.getRoll() + 360);
                while (rot.getRoll() > 180) {
                    rot.setRoll(rot.getRoll() - 360);
                }
            }
        }
    }

    /**
     * Move specific axis
     *
     * @param distance
     * @param axis: 'x', 'y', 'z'
     */
    public void move(float distance, char axis) {
        if (axis == 'x') {
            loc.setX(loc.getX() + distance);
        } else if (axis == 'y') {
            loc.setY(loc.getY() + distance);
        } else if (axis == 'z') {
            loc.setZ(loc.getZ() + distance);
        }
    }
    
    public void move(float x, float y, float z) {
        move(x, 'x');
        move(y, 'y');
        move(z, 'z');
    }

    public void move(float distance, float yaw, float pitch, float roll) {
        float xDis, yDis, zDis;
        float dis1 = distance * (float) Math.cos(Math.toRadians(pitch));
        float dis2 = distance * (float) Math.sin(Math.toRadians(pitch));
        float dis3 = dis1 * (float) Math.cos(Math.toRadians(yaw));
        float dis4 = dis1 * (float) Math.sin(Math.toRadians(yaw));
        float dis5 = dis2 * (float) Math.cos(Math.toRadians(roll));
        float dis6 = dis2 * (float) Math.sin(Math.toRadians(roll));
        float dis7 = dis5 * (float) Math.cos(Math.toRadians(yaw - 90));
        float dis8 = dis5 * (float) Math.sin(Math.toRadians(yaw - 90));
        xDis = dis3 + dis7;
        yDis = dis6;
        zDis = dis4 + dis8;

        loc.moveX(xDis);
        loc.moveY(yDis);
        loc.moveZ(zDis);
    }

    public float distanceFrom(Point p) {
        float distance = 0;

        float x, y, z;
        x = this.getX() - p.getX();
        y = this.getY() - p.getY();
        z = this.getZ() - p.getZ();

        distance = (float) Math.sqrt((double) Math.pow((double) x, 2) + (double) Math.pow((double) y, 2) + (double) Math.pow((double) z, 2));

        return distance;
    }

    //Copy data from p1 to p2
    static public void copy(Point p1, Point p2) {
        p2.setX(p1.getX());
        p2.setY(p1.getY());
        p2.setZ(p1.getZ());
        p2.getRotation().setYaw(p1.getRotation().getYaw());
        p2.getRotation().setPitch(p1.getRotation().getPitch());
        p2.getRotation().setRoll(p1.getRotation().getRoll());
    }

    /*Add data from p1 to p2
     * so if p1 has x = 10 and p2 has x = 5
     * so p1.x += p2.x
     */
    static public void add(Point p1, Point p2) {
        p2.setX(p2.getX() + p1.getX());
        p2.setY(p2.getY() + p1.getY());
        p2.setZ(p2.getZ() + p1.getZ());
        p2.getRotation().setYaw(p2.getRotation().getYaw() + p1.getRotation().getYaw());
        p2.getRotation().setPitch(p2.getRotation().getPitch() + p1.getRotation().getPitch());
        p2.getRotation().setRoll(p2.getRotation().getRoll() + p1.getRotation().getRoll());
    }

    public Location getLocation() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }

    public Rotation getRotation() {
        return rot;
    }

    public void setRotation(Rotation rot) {
        this.rot = rot;
    }

    public float getX() {
        return loc.getX();
    }

    public float getY() {
        return loc.getY();
    }

    public float getZ() {
        return loc.getZ();
    }

    public void setX(float x) {
        loc.setX(x);
    }

    public void setY(float y) {
        loc.setY(y);
    }

    public void setZ(float z) {
        loc.setZ(z);
    }
}
