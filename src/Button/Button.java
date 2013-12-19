/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Button;

import GraphicalInterface.GUI;
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
public class Button extends GUI {

    protected Main main;
    protected ArrayList<ButtonAction> actions;
    protected String name;
    protected String text;
    protected Texture normalTexture;
    protected Texture hoverTexture;
    protected Texture clickedTexture;
    protected float xMargin = 2f;
    protected float yMargin = 2f;
    protected ColorOther colorNormal = new ColorOther(0.65f, 0.65f, 0.65f);
    protected ColorOther colorHover = new ColorOther(0.8f, 0.8f, 0.8f);
    protected ColorOther colorClicked = new ColorOther(0.5f, 0.5f, 0.5f);

    /**
     * Button Constructor
     *
     * @param main
     * @param name
     * @param text
     * @param p The position of the button
     * @param sX size of the button in X
     * @param sY size of the button in Y
     */
    public Button(Main main, String name, String text, Point p, int sX, int sY) {
        super(main.getCurrentScene().getGuiContainer(), p, sX, sY);
        this.main = main;
        this.text = text;
        this.name = name;
        actions = new ArrayList();
    }

    /**
     * Override these as you please
     */
    @Override
    public void onRelease() {
        for (int i = 0; i < actions.size(); i++) {
            actions.get(i).action(this);
        }
    }

    @Override
    public void render() {
        Texture tex = this.getNormalTexture();
        ColorOther color = this.colorNormal;
        if (tex != null) {
            if ((this.isClicked()) && (this.getClickedTexture() != null)) {
                tex = this.getClickedTexture();
            } else if ((this.isHover()) && (this.getHoverTexture() != null)) {
                tex = this.getHoverTexture();
            }
        } else {
            if (this.isClicked()) {
                color = this.getColorClicked();
            } else if (this.isHover()) {
                color = this.getColorHover();
            }
        }
        float sX = sizeX / 2, sY = sizeY / 2;
        if (tex != null) {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            float x = this.getPoint().getX(), y = this.getPoint().getY();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getTextureID());
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex3f(x + sX, y + sY, 1f);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(x - sX, y + sY, 1f);
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex3f(x - sX, y - sY, 1f);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex3f(x + sX, y - sY, 1f);
            GL11.glEnd();
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
        } else {
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
            float x = this.getPoint().getX(), y = this.getPoint().getY();
            GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex3f(x + sX, y + sY, 1f);
            GL11.glVertex3f(x - sX, y + sY, 1f);
            GL11.glVertex3f(x - sX, y - sY, 1f);
            GL11.glVertex3f(x + sX, y - sY, 1f);
            GL11.glEnd();
        }
        float size = ((2 * sX) - this.getxMargin()) / this.getText().length();
        if (size > ((sY * 2) - this.getyMargin())) {
            size = (sY * 2) - this.getyMargin();
        }
        float tSize = size * this.getText().length();
        main.getTextRenderer().drawString(this.getText(), (int) (this.getPoint().getX() - (tSize / 2)), (int) (this.getPoint().getY() - (size / 2)), (int) size);

    }

    public final ArrayList<ButtonAction> getActions() {
        return actions;
    }

    public final void setActions(ArrayList<ButtonAction> actions) {
        this.actions = actions;
    }

    public final void addAction(ButtonAction ba) {
        actions.add(ba);
    }

    public final void removeAction(ButtonAction ba) {
        actions.remove(ba);
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getxMargin() {
        return xMargin;
    }

    public void setxMargin(float margin) {
        this.xMargin = margin;
    }

    public float getyMargin() {
        return yMargin;
    }

    public void setyMargin(float yMargin) {
        this.yMargin = yMargin;
    }

    public Texture getNormalTexture() {
        return normalTexture;
    }

    public void setNormalTexture(Texture normalTexture) {
        this.normalTexture = normalTexture;
    }

    public Texture getHoverTexture() {
        return hoverTexture;
    }

    public void setHoverTexture(Texture hoverTexture) {
        this.hoverTexture = hoverTexture;
    }

    public Texture getClickedTexture() {
        return clickedTexture;
    }

    public void setClickedTexture(Texture clickedTexture) {
        this.clickedTexture = clickedTexture;
    }

    public ColorOther getColor() {
        return colorNormal;
    }

    public void setColor(ColorOther color) {
        this.colorNormal = color;
    }

    public ColorOther getColorHover() {
        return colorHover;
    }

    public void setColorHover(ColorOther colorHover) {
        this.colorHover = colorHover;
    }

    public ColorOther getColorClicked() {
        return colorClicked;
    }

    public void setColorClicked(ColorOther colorClicked) {
        this.colorClicked = colorClicked;
    }
}
