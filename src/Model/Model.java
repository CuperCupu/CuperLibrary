package Model;

import Main.Main;
import Model.Texture.TexturePack;
import Point.Point;
import Serializable.SerializableModel;
import Serializable.SerializableModelItem;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Cuper
 */
public class Model {

    protected ArrayList<ModelItem> group;
    protected ArrayList<Object[]> tGroup;
    protected ArrayList<ModelItem> content;
    protected ArrayList<ModelGroup> contentGroup;
    protected ArrayList<ModelMesh> contentMesh;
    protected ArrayList<ModelPolygon> contentPolygon;
    protected ArrayList<ModelVertex> contentVertex;
    protected ModelItem property;
    protected String name;
    protected Main main;
    protected TexturePack textures;
    protected AnimationHandler animations;

    public Model(Main main, String name) {
        group = new ArrayList();
        tGroup = new ArrayList();
        content = new ArrayList();
        contentGroup = new ArrayList();
        contentMesh = new ArrayList();
        contentPolygon = new ArrayList();
        contentVertex = new ArrayList();
        property = new ModelItem(main, name, ModelItem.type_Property);
        this.name = name;
        this.main = main;
        textures = new TexturePack(main.textures());
        animations = new AnimationHandler(main, name, this);
        animations.start();
    }

    public Model(Main main, String name, TexturePack textures) {
        this(main, name);
        this.textures = textures;
    }

    public Model(Main main, SerializableModel m, TexturePack textures) {
        this(main, m.getName(), textures);
        loadModel(m);
    }

    public final void loadModel(SerializableModel m) {
        property = convertModelItem(m.getProperty(), null);
        for (int i = 0; i < m.getGroup().size(); i++) {
            Object[] o = new Object[2];
            o[0] = m.getGroup().get(i);
            ModelItem sm = convertModelItem(m.getGroup().get(i), null);
            o[1] = sm;
            tGroup.add(o);
        }

        for (int i = 0; i < m.getGroup().size(); i++) {
            ModelItem sm = getSerModelItem(m.getGroup().get(i));
            ModelItem smp = getSerModelItem(m.getGroup().get(i).getParent());
            if (smp == null) {
                smp = property;
            }
            sm.setParent(smp);

            addObject(sm);
        }
        
        
        for (int i = 0; i < m.getAnimations().size(); i++) {
            animations.addAnimation(m.getAnimations().get(i));
        }

        tGroup.clear();
        tGroup = null;
    }

    public void saveModel(String path) throws FileNotFoundException, IOException {
        SerializableModel sm = new SerializableModel(this);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(sm);
        out.close();
    }

    public void loadModel(String path) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(path);
        ObjectInputStream reader = new ObjectInputStream(in);
        try {
            SerializableModel x = (SerializableModel) reader.readObject();
            if (x instanceof SerializableModel) {
                loadModel(x);
            }
            x = null;
        } catch (ClassNotFoundException ex) {
            System.out.println("File not found in the directory: " + path);
        }

    }

    public final ModelItem getSerModelItem(SerializableModelItem m) {
        ModelItem sm = null;
        for (int i = 0; i < tGroup.size(); i++) {
            Object[] o = tGroup.get(i);
            if (o[0] == m) {
                sm = (ModelItem) o[1];
            }
        }
        return sm;
    }

    private ModelItem convertModelItem(SerializableModelItem o, ModelItem p) {
        ModelItem m = new ModelItem(main, o.getName(), p, o.getType());

        if (o.getType() == o.type_Group) {
            ModelGroup g = new ModelGroup(main, p, o.getName());
            for (int i = 0; i < o.getContent().size(); i++) {
                g.addContent(convertModelItem(o.getContent().get(i), g));
            }
            m = g;
        } else if (o.getType() == o.type_Mesh) {
            ModelMesh me = new ModelMesh(main, p, o.getName());
            for (int i = 0; i < o.getContent().size(); i++) {
                me.addPolygon((ModelPolygon) convertModelItem(o.getContent().get(i), me));
            }
            m = me;
        } else if (o.getType() == o.type_Polygon) {
            ModelPolygon pg = new ModelPolygon(main, p, o.getName());
            for (int i = 0; i < o.getContent().size(); i++) {
                pg.addVertex((ModelVertex) convertModelItem(o.getContent().get(i), pg));
            }
            pg.setTexture(textures.getTexture(o.getTexture()));
            m = pg;
        } else if (o.getType() == o.type_Vertex) {
            ModelVertex v = new ModelVertex(main, p, o.getName(), o.getLocation()[0], o.getLocation()[1], o.getLocation()[2]);
            m = v;
        } else if (o.getType() == o.type_Attachment) {
            ModelAttachmentPoint at = new ModelAttachmentPoint(main, o.getName(), p);
            m = at;
        }

        Point po = new Point(o.getLocation()[0], o.getLocation()[1], o.getLocation()[2]);
        m.setPoint(po);
        po.getRotation().setYaw(o.getRotation()[0]);
        po.getRotation().setPitch(o.getRotation()[1]);
        po.getRotation().setRoll(o.getRotation()[2]);

        return m;
    }

    private void addItem(ModelItem o) {
        content.add(o);
        if (o instanceof ModelGroup) {
            ModelGroup g = (ModelGroup) o;
            contentGroup.add(g);
            for (int i = 0; i < g.getContent().size(); i++) {
                addItem(g.getContent().get(i));
            }
        } else if (o instanceof ModelMesh) {
            ModelMesh m = (ModelMesh) o;
            contentMesh.add(m);
            for (int i = 0; i < m.getPolygon().size(); i++) {
                addItem(m.getPolygon().get(i));
            }
        } else if (o instanceof ModelPolygon) {
            ModelPolygon p = (ModelPolygon) o;
            contentPolygon.add(p);
            for (int i = 0; i < p.getVertex().size(); i++) {
                addItem(p.getVertex().get(i));
            }
        } else if (o instanceof ModelVertex) {
            contentVertex.add((ModelVertex) o);
        }
    }

    public final void addObject(ModelItem o) {
        group.add(o);
        addItem(o);
    }

    public void removeObject(ModelItem o) {
        group.remove(o);
    }

    public ArrayList<ModelItem> getGroup() {
        return group;
    }

    public ModelItem getProperty() {
        return property;
    }

    public void setProperty(ModelItem property) {
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TexturePack getTextures() {
        return textures;
    }

    public void setTextures(TexturePack textures) {
        this.textures = textures;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<ModelGroup> getContentGroup() {
        return contentGroup;
    }

    public void setContentGroup(ArrayList<ModelGroup> contentGroup) {
        this.contentGroup = contentGroup;
    }

    public ArrayList<ModelMesh> getContentMesh() {
        return contentMesh;
    }

    public void setContentMesh(ArrayList<ModelMesh> contentMesh) {
        this.contentMesh = contentMesh;
    }

    public ArrayList<ModelPolygon> getContentPolygon() {
        return contentPolygon;
    }

    public void setContentPolygon(ArrayList<ModelPolygon> contentPolygon) {
        this.contentPolygon = contentPolygon;
    }

    public ArrayList<ModelVertex> getContentVertex() {
        return contentVertex;
    }

    public void setContentVertex(ArrayList<ModelVertex> contentVertex) {
        this.contentVertex = contentVertex;
    }

    public ArrayList<ModelItem> getContent() {
        return content;
    }

    public void setContent(ArrayList<ModelItem> content) {
        this.content = content;
    }

    public ModelGroup getGroupByName(String name) {
        ModelGroup g = null;
        for (int i = 0; i < contentGroup.size(); i++) {
            ModelGroup o = contentGroup.get(i);
            if (o.getName() == name) {
                g = o;
            }
        }
        return g;
    }

    public ModelMesh getMeshByName(String name) {
        ModelMesh m = null;
        for (int i = 0; i < contentMesh.size(); i++) {
            ModelMesh o = contentMesh.get(i);
            if (o.getName() == name) {
                m = o;
            }
        }
        return m;
    }

    public ModelPolygon getPolygonByName(String name) {
        ModelPolygon p = null;
        for (int i = 0; i < contentPolygon.size(); i++) {
            ModelPolygon o = contentPolygon.get(i);
            if (o.getName() == name) {
                p = o;
            }
        }
        return p;
    }

    public ModelVertex getVertexByName(String name) {
        ModelVertex v = null;
        for (int i = 0; i < contentVertex.size(); i++) {
            ModelVertex o = contentVertex.get(i);
            if (o.getName() == name) {
                v = o;
            }
        }
        return v;
    }

    public AnimationHandler getAnimations() {
        return animations;
    }

    public void setAnimations(AnimationHandler animations) {
        this.animations = animations;
    }
}
