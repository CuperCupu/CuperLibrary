/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Texture;

import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class TexturePack {

    protected ArrayList<Texture> content;
    protected TextureContainer texContainer;

    public TexturePack(TextureContainer tc) {
        texContainer = tc;
        content = new ArrayList();
    }

    public Texture getTexture(String name) {
        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).getName() == null ? name == null : content.get(i).getName().equals(name)) {
                return content.get(i);
            }
        }
        return texContainer.getNullTexture();
    }

    public void addTexture(Texture tex) {
        content.add(tex);
    }

    public void removeTexture(Texture tex) {
        content.remove(tex);
    }

    public void addTextureBoth(Texture tex) {
        content.add(tex);
        texContainer.addTexture(tex);
    }

    public void removeTextureBoth(Texture tex) {
        content.remove(tex);
        texContainer.removeTexture(tex);
    }
}
