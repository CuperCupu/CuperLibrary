/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Main.Main;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cuper
 */
public class AnimationHandler extends Thread {

    protected Boolean alive = true;
    protected ArrayList<Animation> animations;
    protected ArrayList<Animation> playingAnim;
    protected boolean playing = false;
    protected Main main;
    protected Model model;

    public AnimationHandler(Main main, String name, Model model) {
        this.main = main;
        animations = new ArrayList();
        playingAnim = new ArrayList();
        this.model = model;
    }

    public void playAnimation(String name) {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).getName() == null ? name == null : animations.get(i).getName().equals(name)) {
                playingAnim.add(animations.get(i));
                resumeAnimation();
            }
        }
    }

    public void pauseAnimation() {
        playing = false;
    }

    public void resumeAnimation() {
        playing = true;
    }

    public void stopAnimation(String name) {
        for (int i = 0; i < animations.size(); i++) {
            if (animations.get(i).getName() == null ? name == null : animations.get(i).getName().equals(name)) {
                playingAnim.remove(animations.get(i));
            }
        }
        pauseAnimation();
        resetAnimation();
    }

    public void resetAnimation() {
        for (int j = 0; j < playingAnim.size(); j++) {
            playingAnim.get(j).setTime(0);
        }
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public void addAnimation(Animation anim) {
        animations.add(anim);
    }

    public void removeAnimation(Animation anim) {
        animations.remove(anim);
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(ArrayList<Animation> animations) {
        this.animations = animations;
    }

    public void configureModel() {
        for (int j = 0; j < playingAnim.size(); j++) {
            for (int i = 0; i < model.getContent().size(); i++) {
                ModelItem mi = model.getContent().get(i);
                ArrayList ar = playingAnim.get(j).getData(mi.getName());
                if (ar != null) {
                    String mode = (String) ar.get(1);
                    float x = (float) ar.get(2);
                    float y = (float) ar.get(3);
                    float z = (float) ar.get(4);
                    if ("translate".equals(mode)) {
                        mi.getAnimPoint().move(x, 'x');
                        mi.getAnimPoint().move(y, 'y');
                        mi.getAnimPoint().move(z, 'z');
                    } else if ("set".equals(mode)) {
                        mi.getAnimPoint().setX(x);
                        mi.getAnimPoint().setY(y);
                        mi.getAnimPoint().setZ(z);
                    } else if ("rotate".equals(mode)) {
                        mi.getAnimPoint().rotate(x, 'y');
                        mi.getAnimPoint().rotate(y, 'p');
                        mi.getAnimPoint().rotate(z, 'r');
                    } else if ("rotation".equals(mode)) {
                        mi.getAnimPoint().getRotation().setYaw(x);
                        mi.getAnimPoint().getRotation().setPitch(y);
                        mi.getAnimPoint().getRotation().setRoll(z);
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        while (alive) {
            try {
                if (!main.isRunning()) {
                    alive = false;
                }

                for (int j = 0; j < playingAnim.size(); j++) {
                    if ((playingAnim.get(j) != null) && (playing)) {
                        int time = playingAnim.get(j).getTime();
                        time += 1;
                        if (time >= playingAnim.get(j).getDuration()) {
                            time = 0;
                        }
                        playingAnim.get(j).setTime(time);
                        configureModel();
                    }
                }
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(AnimationHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
