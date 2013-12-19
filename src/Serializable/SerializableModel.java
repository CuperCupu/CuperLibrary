/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializable;

import Model.Animation;
import Model.Model;
import Model.ModelGroup;
import Model.ModelItem;
import Model.ModelMesh;
import Model.ModelPolygon;
import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class SerializableModel implements java.io.Serializable {

    protected SerializableModelItem property;
    protected ArrayList<SerializableModelItem> group;
    protected ArrayList<Object[]> tGroup;
    protected String name;
    protected ArrayList<Animation> animations;

    public SerializableModel(Model m) {
        name = m.getName();
        group = new ArrayList();
        tGroup = new ArrayList();
        animations = new ArrayList();

        for (int i = 0; i < m.getAnimations().getAnimations().size(); i++) {
            Animation a = m.getAnimations().getAnimations().get(i);
            a.setTime(0);
            animations.add(a);
        }
        
        property = convertModelItem(m.getProperty());
        for (int i = 0; i < m.getGroup().size(); i++) {
            Object[] o = new Object[2];
            o[0] = m.getGroup().get(i);
            SerializableModelItem sm = convertModelItem(m.getGroup().get(i));
            o[1] = sm;
            tGroup.add(o);
        }

        for (int i = 0; i < m.getGroup().size(); i++) {
            SerializableModelItem sm = getSerModelItem(m.getGroup().get(i));
            SerializableModelItem smp = getSerModelItem(m.getGroup().get(i).getParent());
            if (smp == null) {
                smp = property;
            }
            sm.setParent(smp);

            group.add(sm);
        }

        tGroup.clear();
        tGroup = null;
    }

    public SerializableModelItem convertModelItem(ModelItem m, SerializableModelItem p) {
        SerializableModelItem sm = new SerializableModelItem(m, p);
        if (m instanceof ModelGroup) {
            ModelGroup g = (ModelGroup) m;
            for (int i = 0; i < g.getContent().size(); i++) {
                sm.addContent(convertModelItem(g.getContent().get(i), sm));
            }
        } else if (m instanceof ModelMesh) {
            ModelMesh me = (ModelMesh) m;
            for (int i = 0; i < me.getPolygon().size(); i++) {
                sm.addContent(convertModelItem(me.getPolygon().get(i), sm));
            }
        } else if (m instanceof ModelPolygon) {
            ModelPolygon pg = (ModelPolygon) m;
            for (int i = 0; i < pg.getVertex().size(); i++) {
                sm.addContent(convertModelItem(pg.getVertex().get(i), sm));
            }
        }
        return sm;
    }

    public final SerializableModelItem getSerModelItem(ModelItem m) {
        SerializableModelItem sm = null;
        for (int i = 0; i < tGroup.size(); i++) {
            Object[] o = tGroup.get(i);
            if (o[0] == m) {
                sm = (SerializableModelItem) o[1];
            }
        }
        return sm;
    }

    private SerializableModelItem convertModelItem(ModelItem property) {
        return convertModelItem(property, null);
    }

    public SerializableModelItem getProperty() {
        return property;
    }

    public void setProperty(SerializableModelItem property) {
        this.property = property;
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(ArrayList<Animation> animations) {
        this.animations = animations;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SerializableModelItem> getGroup() {
        return group;
    }

    public void setGroup(ArrayList<SerializableModelItem> group) {
        this.group = group;
    }
}
