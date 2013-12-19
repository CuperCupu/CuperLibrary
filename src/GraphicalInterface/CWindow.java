/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import Main.Main;
import Model.ColorOther;
import Model.Texture.Texture;
import Point.Point;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Cuper
 */
public class CWindow extends GUI {

    protected Main main;
    protected ArrayList<GUI> guis;
    protected boolean border = true, background = true;
    protected Texture backgroundTexture = null, borderTexture = null;
    protected float borderWidth = 1f;
    protected ColorOther backgroundColor = new ColorOther(1f, 1f, 1f), borderColor = new ColorOther(0f, 0f, 0f);
    protected boolean movable = true;

    public CWindow(Main main, float sizeX, float sizeY) {
        super(new Point(), sizeX, sizeY);
        this.main = main;
        guis = new ArrayList();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public CWindow(Main main, float sizeX, float sizeY, Point point) {
        this(main, sizeX, sizeY);
        this.point = point;
    }

    protected void renderBackground() {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        if (backgroundTexture != null) {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            if (backgroundTexture.isAlpha()) {
                GL11.glEnable(GL11.GL_BLEND);
            }
        } else {
            if (backgroundColor.getAlpha() < 1f) {
                GL11.glEnable(GL11.GL_BLEND);
            }
        }
        float x = point.getX(), y = point.getY();
        float depth = 0;
        if ((backgroundTexture == null) || (backgroundTexture.getTextureID() == -1)) {
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glColor4f(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha());
            GL11.glVertex3f(x, y, depth);
            GL11.glVertex3f(x + sizeX, y, depth);
            GL11.glVertex3f(x + sizeX, y + sizeY, depth);
            GL11.glVertex3f(x, y + sizeY, depth);
            GL11.glEnd();
        } else {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, backgroundTexture.getTextureID());
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glColor4f(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundColor.getAlpha());
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x, y, depth);
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex3f(x + sizeX, y, depth);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex3f(x + sizeX, y + sizeY, depth);
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex3f(x, y + sizeY, depth);
            GL11.glEnd();
        }
        if ((border) && ((borderTexture == null) || (borderTexture.getTextureID() == -1))) {
            GL11.glColor4f(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), borderColor.getAlpha());
            GL11.glBegin(GL11.GL_LINES);
            GL11.glLineWidth(borderWidth);
            GL11.glVertex3f(x, y, depth);
            GL11.glVertex3f(x + sizeX, y, depth);
            GL11.glEnd();

            GL11.glColor4f(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), borderColor.getAlpha());
            GL11.glBegin(GL11.GL_LINES);
            GL11.glLineWidth(borderWidth);
            GL11.glVertex3f(x + sizeX - borderWidth, y, depth);
            GL11.glVertex3f(x + sizeX - borderWidth, y + sizeY, depth);
            GL11.glEnd();

            GL11.glColor4f(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), borderColor.getAlpha());
            GL11.glBegin(GL11.GL_LINES);
            GL11.glLineWidth(borderWidth);
            GL11.glVertex3f(x, y + sizeY, depth);
            GL11.glVertex3f(x + sizeX, y + sizeY, depth);
            GL11.glEnd();

            GL11.glColor4f(borderColor.getRed(), borderColor.getGreen(), borderColor.getBlue(), borderColor.getAlpha());
            GL11.glBegin(GL11.GL_LINES);
            GL11.glLineWidth(borderWidth);
            GL11.glVertex3f(x, y, depth);
            GL11.glVertex3f(x, y + sizeY, depth);
            GL11.glEnd();
        }
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @Override
    public void render() {
        GL11.glLoadIdentity();
        if (background) {
            renderBackground();
        }
        for (int i = 0; i < guis.size(); i++) {
            if (guis.get(i).isVisible()) {
                GL11.glLoadIdentity();
                guis.get(i).render();
            }
        }
    }

    public void move(float x, float y) {
        point.move(x, y, 0);
        for (int i = 0; i < guis.size(); i++) {
            guis.get(i).getPoint().move(x, y, 0);
        }
    }

    public void addGUI(GUI i) {
        guis.add(i);
        i.getPoint().move(point.getX(), point.getY(), 0f);
    }

    public void removeGUI(GUI i) {
        guis.remove(i);
        i.getPoint().move(-point.getX(), -point.getY(), 0f);
    }

    public Texture getBorderTexture() {
        return borderTexture;
    }

    public void setBorderTexture(Texture borderTexture) {
        this.borderTexture = borderTexture;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public ColorOther getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(ColorOther borderColor) {
        this.borderColor = borderColor;
    }

    public ColorOther getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(ColorOther backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<GUI> getGuis() {
        return guis;
    }

    public void setGuis(ArrayList<GUI> guis) {
        this.guis = guis;
    }
    
    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public boolean isBackground() {
        return background;
    }

    public void setBackground(boolean background) {
        this.background = background;
    }
}
