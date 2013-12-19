/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Texture;

import Main.Main;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Cuper
 */
public class TextureContainer {

    protected Main main;
    protected ArrayList<Texture> content;
    protected Texture nullTexture;
    protected ArrayList<Texture> wordTextures;

    public TextureContainer(Main main) throws IOException {
        this.main = main;
        content = new ArrayList();
        nullTexture = new Texture("null", -1);
        wordTextures = new ArrayList();
    }

    final public void initTextTexture() throws IOException {
        String path = "../Resource/font/default.png";
        URL resource = this.getClass().getResource(path);
        if (resource != null) {
            BufferedImage image = ImageIO.read(resource);
            int tileSize = image.getWidth() / 16;
            int xLoop = image.getWidth() / tileSize;
            int yLoop = image.getHeight() / tileSize;
            for (int y = 0; y < yLoop; y++) {
                for (int x = 0; x < xLoop; x++) {
                    BufferedImage temp = image.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize);
                    Texture tex = new Texture(Character.toChars((y + 1) * x).toString(),main.getRenderer().loadTexture(temp), true);
                    wordTextures.add(tex);
                    addTexture(tex);
                }
            }
        }
    }

    //Override this method
    public void initTexture() throws IOException {
    }

    public Texture getTexture(String name) {
        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).getName() == name) {
                return content.get(i);
            }
        }
        return nullTexture;
    }

    public void addTexture(Texture tex) {
        content.add(tex);
    }

    public void removeTexture(Texture tex) {
        content.remove(tex);
        GL11.glDeleteTextures(tex.getTextureID());
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList getWordTextures() {
        return wordTextures;
    }

    public void setWordTextures(ArrayList wordTextures) {
        this.wordTextures = wordTextures;
    }

    public Texture getNullTexture() {
        return nullTexture;
    }

    public void setNullTexture(Texture nullTexture) {
        this.nullTexture = nullTexture;
    }
}
