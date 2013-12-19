/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Texture;

/**
 *
 * @author Cuper
 */
public class Texture {

    private int textureID;
    private String name;
    private boolean alpha = false;

    public Texture(String name, int texID) {
        textureID = texID;
        this.name = name;
    }
    
    public Texture(String name, int texID, boolean alpha) {
        this(name, texID);
        setAlpha(alpha);
    }

    public int getTextureID() {
        return textureID;
    }

    public void setTextureID(int textureID) {
        this.textureID = textureID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public final boolean isAlpha() {
        return alpha;
    }

    public final void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }
    
    
}
