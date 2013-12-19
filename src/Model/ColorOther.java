/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;

/**
 *
 * @author Cuper
 */
public final class ColorOther {

    float[] color;

    public ColorOther(float red, float green, float blue) {
        color = new float[4];
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setAlpha(1f);
    }

    public ColorOther(float red, float green, float blue, float alpha) {
        this(red, green, blue);
        setAlpha(alpha);
    }

    public ColorOther(Color c) {
        this(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f);
        setAlpha(c.getAlpha() / 255f);
    }

    public ColorOther(float red, float green, float blue, float alpha, boolean b) {
        this(red / 255, green / 255, blue / 255);
        setAlpha(alpha / 255);
    }

    public final void setRed(float val) {
        color[0] = val;
    }

    public final void setGreen(float val) {
        color[1] = val;
    }

    public void setBlue(float val) {
        color[2] = val;
    }

    public void setAlpha(float val) {
        color[3] = val;
    }

    public float getRed() {
        return color[0];
    }

    public float getGreen() {
        return color[1];
    }

    public float getBlue() {
        return color[2];
    }

    public float getAlpha() {
        return color[3];
    }

    public void setColor(float red, float green, float blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    public void setColor(float red, float green, float blue, float alpha) {
        setColor(red, green, blue);
        setAlpha(alpha);
    }

    public void setColor(float red, float green, float blue, char c) {
        if (c == 'c') {
            red /= 255;
            green /= 255;
            blue /= 255;
        }
        setColor(red, green, blue);
    }

    public void setColor(float red, float green, float blue, float alpha, boolean b) {
        red /= 255;
        green /= 255;
        blue /= 255;
        alpha /= 255;
        setColor(red, green, blue, alpha);
    }
}
