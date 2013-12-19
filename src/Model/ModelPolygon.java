/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Main.Main;
import Model.Texture.Texture;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Cuper
 */
public class ModelPolygon extends ModelItem {

    protected Texture texture;
    protected ArrayList<ModelVertex> vertex;
    protected ColorOther color;
    protected int renderHandle = -1;

    public ModelPolygon(Main main, ModelItem i, String name) {
        super(main, name, i, ModelItem.type_Polygon);
        vertex = new ArrayList();
        color = new ColorOther(255, 255, 255);
        texture = null;
    }

    public ModelPolygon(Main main, ModelItem i, String name, ColorOther color) {
        this(main, i, name);
        this.color = color;
    }

    public ModelPolygon(Main main, ModelItem i, String name, float x, float y, float z) {
        this(main, i, name);
        point.getLocation().setLocation(x, y, z);
    }

    public ModelPolygon(Main main, ModelItem i, String name, ColorOther color, float x, float y, float z) {
        this(main, i, name, color);
        point.getLocation().setLocation(x, y, z);
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void addVertex(String name, float x, float y, float z) {
        ModelVertex v = new ModelVertex(main, this, name, x, y, z, color);
        vertex.add(v);
    }

    public void addVertex(ModelVertex v) {
        v.setParent(this);
        vertex.add(v);
    }

    public void removeVertex(ModelVertex v) {
        vertex.remove(v);
    }

    public ArrayList<ModelVertex> getVertex() {
        return vertex;
    }

    public ColorOther getColor() {
        return color;
    }

    public void setColor(float red, float green, float blue, float alpha) {
        this.color = new ColorOther(red, green, blue, alpha);
    }

    public void setColor(ColorOther color) {
        this.color = color;
    }

    public int getTextureID() {
        if (texture == null) {
            return -1;
        }
        return texture.getTextureID();
    }

    public int getRenderHandle() {
        if (renderHandle == -1) {
            generate();
        }
        return renderHandle;
    }

    public void setRenderHandle(int renderHandle) {
        this.renderHandle = renderHandle;
    }

    public void generate() {
        if (renderHandle != -1) {
            GL11.glDeleteLists(renderHandle, 1);
        }
        renderHandle = generateDisplayList();
    }

    private int generateDisplayList() {
        int handle = GL11.glGenLists(1);
        GL11.glNewList(handle, GL11.GL_COMPILE);
        ModelPolygon poly = this;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        if (poly.getTexture() != null) {
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            if ((poly.getTexture().isAlpha()) || (poly.getColor().getAlpha() < 1f)) {
                GL11.glEnable(GL11.GL_BLEND);
            }
        }
        if (poly.getVertex().size() == 3) {
            ModelVertex[] v = new ModelVertex[3];
            v[0] = poly.getVertex().get(0);
            v[1] = poly.getVertex().get(1);
            v[2] = poly.getVertex().get(2);
            if (poly.getTextureID() != -1) {
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, poly.getTextureID());
            }
            GL11.glBegin(GL11.GL_TRIANGLES);
            if (poly.getTextureID() != -1) {
                GL11.glTexCoord2f(0, 0);
            }
            GL11.glColor4f(v[0].getColor().getRed(), v[0].getColor().getGreen(), v[0].getColor().getBlue(), v[0].getColor().getAlpha());
            GL11.glVertex3f(v[0].getX(), v[0].getY(), v[0].getZ());
            if (poly.getTextureID() != -1) {
                GL11.glTexCoord2f(1, 0);
            }
            GL11.glColor4f(v[1].getColor().getRed(), v[1].getColor().getGreen(), v[1].getColor().getBlue(), v[1].getColor().getAlpha());
            GL11.glVertex3f(v[1].getX(), v[1].getY(), v[1].getZ());
            if (poly.getTextureID() != -1) {
                GL11.glTexCoord2f(0, 1);
            }
            GL11.glColor4f(v[2].getColor().getRed(), v[2].getColor().getGreen(), v[2].getColor().getBlue(), v[2].getColor().getAlpha());
            GL11.glVertex3f(v[2].getX(), v[2].getY(), v[2].getZ());
            GL11.glEnd();
        } else if (poly.getVertex().size() == 4) {
            ModelVertex[] v = new ModelVertex[4];
            v[0] = poly.getVertex().get(0);
            v[1] = poly.getVertex().get(1);
            v[2] = poly.getVertex().get(2);
            v[3] = poly.getVertex().get(3);
            if (poly.getTextureID() != -1) {
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, poly.getTextureID());
            }
            GL11.glBegin(GL11.GL_QUADS);
            if (poly.getTextureID() != -1) {
                GL11.glTexCoord2f(1, 0);
            }
            GL11.glColor4f(v[0].getColor().getRed(), v[0].getColor().getGreen(), v[0].getColor().getBlue(), v[0].getColor().getAlpha());
            GL11.glVertex3f(v[0].getX(), v[0].getY(), v[0].getZ());
            if (poly.getTextureID() != -1) {
                GL11.glTexCoord2f(0, 0);
            }
            GL11.glColor4f(v[1].getColor().getRed(), v[1].getColor().getGreen(), v[1].getColor().getBlue(), v[1].getColor().getAlpha());
            GL11.glVertex3f(v[1].getX(), v[1].getY(), v[1].getZ());
            if (poly.getTextureID() != -1) {
                GL11.glTexCoord2f(0, 1);
            }
            GL11.glColor4f(v[2].getColor().getRed(), v[2].getColor().getGreen(), v[2].getColor().getBlue(), v[2].getColor().getAlpha());
            GL11.glVertex3f(v[2].getX(), v[2].getY(), v[2].getZ());
            if (poly.getTextureID() != -1) {
                GL11.glTexCoord2f(1, 1);
            }
            GL11.glColor4f(v[3].getColor().getRed(), v[3].getColor().getGreen(), v[3].getColor().getBlue(), v[3].getColor().getAlpha());
            GL11.glVertex3f(v[3].getX(), v[3].getY(), v[3].getZ());
            GL11.glEnd();
        }
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEndList();
        return handle;
    }
}
