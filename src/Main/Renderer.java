/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GraphicalInterface.GUI;
import Model.ColorOther;
import Model.ModelItem;
import Model.ModelPolygon;
import Model.Sorter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Cuper
 */
public class Renderer {

    protected ColorOther clearColor = new ColorOther(1f, 1f, 1f, 1f);
    protected String icon16Path = "icon_16.png";
    protected String icon32Path = "icon_32.png";
    protected Main main;
    protected Sorter sorter;
    protected static final int BYTES_PER_PIXEL = 4;
    protected String title;
    protected float width, height;

    public Renderer(Main main) {
        this.main = main;
        sorter = new Sorter(main.getModelContainer().getContent(), main.getCamera().getEyePoint());
        title = main.getName() + " v" + main.getVersion();
    }

    public ByteBuffer loadIcon(String path, int width, int height) throws IOException {
        URL resource = this.getClass().getResource(path);
        BufferedImage image = ImageIO.read(resource); // load image

        // convert image to byte array
        byte[] imageBytes = new byte[width * height * 4];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel = image.getRGB(j, i);
                for (int k = 0; k < 3; k++) {
                    imageBytes[(i * 16 + j) * 4 + k] = (byte) (((pixel >> (2 - k) * 8)) & 255);
                }
                imageBytes[(i * 16 + j) * 4 + 3] = (byte) (((pixel >> (3) * 8)) & 255); // alpha
            }
        }
        return ByteBuffer.wrap(imageBytes);
    }

    public void initGL() throws IOException {
        try {
            Display.setDisplayMode(new DisplayMode(main.getScreenX(), main.getScreenY()));
            Display.setResizable(false);
            Display.create();
            Display.setVSyncEnabled(true);
            Display.setTitle(title);
            ByteBuffer[] icons = new ByteBuffer[2];
            icons[0] = loadIcon(icon16Path, 16, 16);
            icons[1] = loadIcon(icon32Path, 32, 32);
            Display.setIcon(icons);
        } catch (LWJGLException e) {
            System.exit(0);
        }

        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glClearColor(clearColor.getRed(), clearColor.getGreen(), clearColor.getBlue(), clearColor.getAlpha());
        GL11.glClearDepth(1);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glViewport(0, 0, main.getScreenX(), main.getScreenY());

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, main.getScreenX(), main.getScreenY(), 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

    }

    public void ready3D() {
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        GLU.gluPerspective((float) 50, ((float) width / (float) height), 0.001f, 1000);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glClearColor(clearColor.getRed(), clearColor.getGreen(), clearColor.getBlue(), clearColor.getAlpha());
        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }

    public void ready2D() {
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        GL11.glOrtho(0.0f, main.getScreenX(), 0.0f, main.getScreenY(), -100, 100);

        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.375F, 0.375F, 0.0F);
        GL11.glClearColor(1f, 1f, 1f, 0f);
        GL11.glClearDepth(1.0f);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }

    private void clearGL() {
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_POLYGON);
        GL11.glLoadIdentity();
    }

    public void render() {
        width = Display.getDisplayMode().getWidth();
        height = Display.getDisplayMode().getHeight();

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glLoadIdentity();
        clearColor = main.getCurrentScene().getClearColor();
        sorter.setContainer(main.getModelContainer().getContent());

        clearGL();
        ready3D();
        renderModel();
        main.action3D();
        
        
        ready2D();
        renderWindows();
        main.action2D();

        Display.update();
    }

    public void renderWindows() {
        for (int i = 0; i < main.getCurrentScene().getWindows().size(); i++) {
            main.getCurrentScene().getWindows().get(i).render();
        }
    }

    public void renderGUI() {
        for (int i = 0; i < main.getCurrentScene().getGuiContainer().getContent().size(); i++) {
            Object o = main.getCurrentScene().getGuiContainer().getContent().get(i);
            if (o instanceof GUI) {
                ((GUI) o).render();
            }
        }
    }

    public void renderModel() {
        GL11.glLoadIdentity();
        sorter.sort();
        ArrayList<ModelPolygon> toRender = sorter.getSortedRender();
        for (int i = 0; i < toRender.size(); i++) {
            translateCamera();
            renderPolygon(toRender.get(i));
            GL11.glLoadIdentity();
        }
    }

    public void translateCamera() {
        GL11.glRotatef(main.getCamera().getEyePoint().getRotation().getPitch(), 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(main.getCamera().getEyePoint().getRotation().getYaw(), 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(main.getCamera().getEyePoint().getRotation().getRoll(), 0.0F, 0.0F, 1.0F);
        GL11.glTranslatef(-main.getCamera().getEyePoint().getLocation().getX(), -main.getCamera().getEyePoint().getLocation().getY(), -main.getCamera().getEyePoint().getLocation().getZ());
    }

    public void translateWorld(ModelItem i) {
        if ((i.getParent() != null) && (i.getParent() != i)) {
            translateWorld(i.getParent());
        }

        GL11.glTranslatef(i.getPoint().getX(), i.getPoint().getY(), i.getPoint().getZ());

        GL11.glRotatef(i.getPoint().getRotation().getPitch(), 1, 0, 0);
        GL11.glRotatef(i.getPoint().getRotation().getRoll(), 0, 0, 1);
        GL11.glRotatef(i.getPoint().getRotation().getYaw(), 0, 1, 0);

        GL11.glTranslatef(i.getAnimPoint().getX(), i.getAnimPoint().getY(), i.getAnimPoint().getZ());

        GL11.glRotatef(i.getAnimPoint().getRotation().getPitch(), 1, 0, 0);
        GL11.glRotatef(i.getAnimPoint().getRotation().getRoll(), 0, 0, 1);
        GL11.glRotatef(i.getAnimPoint().getRotation().getYaw(), 0, 1, 0);

    }

    public void renderPolygon(ModelPolygon poly) {
        translateWorld(poly);
        GL11.glCallList(poly.getRenderHandle());
    }

    public int loadImageAsTexture(String path) throws IOException {
        URL resource = this.getClass().getResource(path);
        int id = -1;

        if (resource != null) {
            BufferedImage image = ImageIO.read(resource);

            id = loadTexture(image);
        }

        return id;
    }

    public int loadImageAsTexture(BufferedImage image, int x, int y, int w, int h) throws IOException {
        int id = -1;

        if (image != null) {
            BufferedImage subImage = image.getSubimage(x, y, w, h);

            id = loadTexture(subImage);
        }

        return id;
    }

    public int loadImageAsTexture(String path, int x, int y, int w, int h) throws IOException {
        URL resource = this.getClass().getResource(path);
        int id = -1;

        if (resource != null) {
            BufferedImage image = ImageIO.read(resource);
            BufferedImage subImage = image.getSubimage(x, y, w, h);

            id = loadTexture(subImage);
        }

        return id;
    }

    public static synchronized ByteBuffer createDirectByteBuffer(int par0) {
        return ByteBuffer.allocateDirect(par0).order(ByteOrder.nativeOrder());
    }

    public static IntBuffer createDirectIntBuffer(int par0) {
        return createDirectByteBuffer(par0 << 2).asIntBuffer();
    }

    public static int loadTexture(BufferedImage image) {

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
            }
        }

        buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS

        // You now have a ByteBuffer filled with the color data of each pixel.
        // Now just create a texture ID and bind it. Then you can load it using 
        // whatever OpenGL method you want, for example:

        int textureID = GL11.glGenTextures(); //Generate texture ID
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID); //Bind texture ID

        //Setup wrap mode
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        //Send texel data to OpenGL
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        //Return the texture ID so we can bind it later again
        return textureID;
    }

    public ColorOther getClearColor() {
        return clearColor;
    }

    public void setClearColor(ColorOther clearColor) {
        this.clearColor = clearColor;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getIcon16Path() {
        return icon16Path;
    }

    public void setIcon16Path(String icon16Path) {
        this.icon16Path = icon16Path;
    }

    public String getIcon32Path() {
        return icon32Path;
    }

    public void setIcon32Path(String icon32Path) {
        this.icon32Path = icon32Path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
