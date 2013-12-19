/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Text;

import Main.Main;
import Model.ColorOther;
import Model.Model;
import Model.ModelMesh;
import Model.ModelPolygon;
import Model.ModelVertex;
import Model.Texture.Texture;
import Point.Point;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author TOSHIBA L840
 */
public class TextRenderer {

    protected Main main;

    public TextRenderer(Main main) {
        this.main = main;
    }

    //to be used in create3DItem method
    private boolean checkEmpty(Color[][] cm, int x, int y) {
        boolean b = true;

        if ((y >= cm.length) || (y < 0)) {
        } else if ((x >= cm[y].length) || (x < 0)) {
        } else {
            if (cm[y][x].getAlpha() > 0) {
                b = false;
            } else {
                b = true;
            }
        }

        return b;
    }

    /**
     * Create 3D mesh from a BufferedImage
     *
     * @param name the name of the mesh
     * @param m a model for this mesh to be attached to, null if you don't want
     * it attached
     * @param image the buffered image the 3D mesh is made from
     * @param tex the texture of the front and back side of the mesh, if
     * specified null, will automatically create new texture from buffered image
     * @param size size of each pixel in openGL unit
     * @param width the distance between the front polygon and the back polygon,
     * the thickness of the 3D mesh
     * @return the created 3D mesh
     * @throws IOException
     */
    public ModelMesh create3DItem(String name, BufferedImage image, Texture tex, float size, float width, boolean b) throws IOException {
        if (tex == null) {
            tex = new Texture(name, main.getRenderer().loadImageAsTexture(image, 0, 0, image.getWidth(), image.getHeight()), true);
            main.textures().addTexture(tex);
        }

        Color[][] colorMap = new Color[image.getHeight()][image.getWidth()];
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color c = new Color(image.getRGB(j, i), true);
                colorMap[i][j] = c;
            }
        }
        ModelMesh me = new ModelMesh(main, null, name);

        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                float x = j * size, y = i * size;
                if (!checkEmpty(colorMap, j, i)) {
                    if (checkEmpty(colorMap, j, i - 1)) {
                        ModelPolygon p = new ModelPolygon(main, me, "top");
                        ModelVertex v1 = new ModelVertex(main, p, "", new Point(x, -y, width / 2));
                        ModelVertex v2 = new ModelVertex(main, p, "", new Point(x + size, -y, width / 2));
                        ModelVertex v3 = new ModelVertex(main, p, "", new Point(x + size, -y, -width / 2));
                        ModelVertex v4 = new ModelVertex(main, p, "", new Point(x, -y, -width / 2));
                        Color c = colorMap[i][j];
                        ColorOther co = new ColorOther(c);
                        if (!b) {
                            co.setAlpha(1f);
                        }
                        v1.setColor(co);
                        v2.setColor(co);
                        v3.setColor(co);
                        v4.setColor(co);
                        p.setColor(co);
                        p.addVertex(v1);
                        p.addVertex(v2);
                        p.addVertex(v3);
                        p.addVertex(v4);
                        me.addPolygon(p);
                    }
                    if (checkEmpty(colorMap, j, i + 1)) {
                        ModelPolygon p = new ModelPolygon(main, me, "bottom");
                        ModelVertex v1 = new ModelVertex(main, p, "", new Point(x, -y - size, width / 2));
                        ModelVertex v2 = new ModelVertex(main, p, "", new Point(x + size, -y - size, width / 2));
                        ModelVertex v3 = new ModelVertex(main, p, "", new Point(x + size, -y - size, -width / 2));
                        ModelVertex v4 = new ModelVertex(main, p, "", new Point(x, -y - size, -width / 2));
                        Color c = colorMap[i][j];
                        ColorOther co = new ColorOther(c);
                        if (!b) {
                            co.setAlpha(1f);
                        }
                        v1.setColor(co);
                        v2.setColor(co);
                        v3.setColor(co);
                        v4.setColor(co);
                        p.setColor(co);
                        p.addVertex(v1);
                        p.addVertex(v2);
                        p.addVertex(v3);
                        p.addVertex(v4);
                        me.addPolygon(p);
                    }
                    if (checkEmpty(colorMap, j - 1, i)) {
                        ModelPolygon p = new ModelPolygon(main, me, "left");
                        ModelVertex v1 = new ModelVertex(main, p, "", new Point(x, -y, width / 2));
                        ModelVertex v2 = new ModelVertex(main, p, "", new Point(x, -y - size, width / 2));
                        ModelVertex v3 = new ModelVertex(main, p, "", new Point(x, -y - size, -width / 2));
                        ModelVertex v4 = new ModelVertex(main, p, "", new Point(x, -y, -width / 2));
                        Color c = colorMap[i][j];
                        ColorOther co = new ColorOther(c);
                        if (!b) {
                            co.setAlpha(1f);
                        }
                        v1.setColor(co);
                        v2.setColor(co);
                        v3.setColor(co);
                        v4.setColor(co);
                        p.setColor(co);
                        p.addVertex(v1);
                        p.addVertex(v2);
                        p.addVertex(v3);
                        p.addVertex(v4);
                        me.addPolygon(p);
                    }
                    if (checkEmpty(colorMap, j + 1, i)) {
                        ModelPolygon p = new ModelPolygon(main, me, "right");
                        ModelVertex v1 = new ModelVertex(main, p, "", new Point(x + size, -y, width / 2));
                        ModelVertex v2 = new ModelVertex(main, p, "", new Point(x + size, -y - size, width / 2));
                        ModelVertex v3 = new ModelVertex(main, p, "", new Point(x + size, -y - size, -width / 2));
                        ModelVertex v4 = new ModelVertex(main, p, "", new Point(x + size, -y, -width / 2));
                        Color c = colorMap[i][j];
                        ColorOther co = new ColorOther(c);
                        if (!b) {
                            co.setAlpha(1f);
                        }
                        v1.setColor(co);
                        v2.setColor(co);
                        v3.setColor(co);
                        v4.setColor(co);
                        p.setColor(co);
                        p.addVertex(v1);
                        p.addVertex(v2);
                        p.addVertex(v3);
                        p.addVertex(v4);
                        me.addPolygon(p);
                    }
                    ModelPolygon p1 = new ModelPolygon(main, me, "front");
                    ModelVertex v11 = new ModelVertex(main, p1, "", new Point(x, -y, width / 2));
                    ModelVertex v21 = new ModelVertex(main, p1, "", new Point(x + size, -y, width / 2));
                    ModelVertex v31 = new ModelVertex(main, p1, "", new Point(x + size, -y - size, width / 2));
                    ModelVertex v41 = new ModelVertex(main, p1, "", new Point(x, -y - size, width / 2));
                    Color c1 = colorMap[i][j];
                    ColorOther co1 = new ColorOther(c1);
                    if (!b) {
                        co1.setAlpha(1f);
                    }
                    v11.setColor(co1);
                    v21.setColor(co1);
                    v31.setColor(co1);
                    v41.setColor(co1);
                    p1.setColor(co1);
                    p1.addVertex(v11);
                    p1.addVertex(v21);
                    p1.addVertex(v31);
                    p1.addVertex(v41);
                    me.addPolygon(p1);

                    ModelPolygon p2 = new ModelPolygon(main, me, "back");
                    ModelVertex v12 = new ModelVertex(main, p2, "", new Point(x, -y, -width / 2));
                    ModelVertex v22 = new ModelVertex(main, p2, "", new Point(x + size, -y, -width / 2));
                    ModelVertex v32 = new ModelVertex(main, p2, "", new Point(x + size, -y - size, -width / 2));
                    ModelVertex v42 = new ModelVertex(main, p2, "", new Point(x, -y - size, -width / 2));
                    Color c2 = colorMap[i][j];
                    ColorOther co2 = new ColorOther(c2);
                    if (!b) {
                        co2.setAlpha(1f);
                    }
                    v12.setColor(co2);
                    v22.setColor(co2);
                    v32.setColor(co2);
                    v42.setColor(co2);
                    p2.setColor(co2);
                    p2.addVertex(v12);
                    p2.addVertex(v22);
                    p2.addVertex(v32);
                    p2.addVertex(v42);
                    me.addPolygon(p2);
                }
            }
        }

        return me;
    }

    public ModelMesh create3DItem(String name, String path, Texture tex, float size, float width, boolean b) throws IOException {
        URL resource = this.getClass().getResource(path);
        if (resource != null) {
            BufferedImage image = ImageIO.read(resource);
            return create3DItem(name, image, tex, size, width, b);
        }
        return null;
    }

    /**
     *
     * @param text
     * @param size
     * @return
     */
    public ModelMesh createTextModel(String text, float size) {
        int ascii;
        ModelMesh me = new ModelMesh(main, null, text);
        float x = 0f;
        for (int i = 0; i < text.length(); i++) {
            ascii = (int) text.charAt(i);
            Texture texture = (Texture) main.textures().getWordTextures().get(ascii);

            ModelPolygon p = new ModelPolygon(main, me, text.substring(i, i + 1));
            p.setTexture(texture);

            ModelVertex v1 = new ModelVertex(main, p, text.substring(i, i + 1), new Point(x + size, size, 0f));
            ModelVertex v2 = new ModelVertex(main, p, text.substring(i, i + 1), new Point(x + 0f, size, 0f));
            ModelVertex v3 = new ModelVertex(main, p, text.substring(i, i + 1), new Point(x + 0f, 0f, 0f));
            ModelVertex v4 = new ModelVertex(main, p, text.substring(i, i + 1), new Point(x + size, 0f, 0f));

            x += size;
            p.addVertex(v1);
            p.addVertex(v2);
            p.addVertex(v3);
            p.addVertex(v4);

            me.addPolygon(p);
        }

        return me;
    }

    public ModelMesh createTextModel(String text, float size, char al) {
        ModelMesh m = createTextModel(text, size);

        if (al == 'l') {
        } else if (al == 'c') {
            m.getPoint().setX(-(text.length() * size) / 2);
            m.getPoint().setY(-size / 2);
        } else if (al == 'r') {
            m.getPoint().setX(-(text.length() * size));
        }

        return m;
    }

    public void drawString(String text, float x, float y, float size, char alignment, ColorOther color) {
        float currentX;
        int ascii;
        currentX = x;
        float w = size;
        float h = size;
        if (alignment == 'c') {
            currentX -= text.length() * size / 2;
        } else if (alignment == 'r') {
            currentX -= text.length() * size;
        }
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        for (int i = 0; i < text.length(); i++) {
            ascii = (int) text.charAt(i);
            int textureID = ((Texture) main.textures().getWordTextures().get(ascii)).getTextureID();
            
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glColor4f(color.getRed(), color.getBlue(), color.getGreen(), color.getAlpha());
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex3f(currentX, y, 1f);
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex3f(currentX + w, y, 1f);
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex3f(currentX + w, y + h, 1f);
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex3f(currentX, y + h, 1f);
            GL11.glEnd();
            currentX += size;
        }
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void drawString(String text, float x, float y, float size, ColorOther color) {
        drawString(text, x, y, size, 'l', color);
    }

    public void drawString(String text, float x, float y, float size) {
        drawString(text, x, y, size, 'l', new ColorOther(1f, 1f, 1f));
    }

    public void drawString(String text, float x, float y, float size, char c) {
        drawString(text, x, y, size, c, new ColorOther(1f, 1f, 1f));
    }
}
