/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GraphicalInterface.CWindow;
import GraphicalInterface.GUIContainer;
import Model.ColorOther;
import Model.ModelContainer;
import java.util.ArrayList;

/**
 *
 * @author Cuper
 */
public class Scene {

    protected Main main;
    protected ModelContainer modelContainer;
    protected GUIContainer guiContainer;
    protected String name;
    protected ColorOther clearColor = new ColorOther(0.5f, 0.5f, 0.5f);
    protected ArrayList<CWindow> windows;
    protected CWindow screen;

    public Scene(Main main, String name) {
        this.main = main;
        this.name = name;
        modelContainer = new ModelContainer();
        guiContainer = new GUIContainer();
        windows = new ArrayList();
        screen = new CWindow(main, main.getScreenX(), main.getScreenY());
        screen.setBackground(false);
        screen.setMovable(false);
        getWindows().add(screen);
    }
    
    public void addWindow(CWindow win) {
        getWindows().add(win);
    }

    public void renderWindow() {
        for (int i = 0; i < windows.size(); i++) {
            windows.get(i).render();
        }
    }

    public ArrayList<CWindow> getWindows() {
        return windows;
    }

    public void setWindows(ArrayList<CWindow> windows) {
        this.windows = windows;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ModelContainer getModelContainer() {
        return modelContainer;
    }

    public void setModelContainer(ModelContainer modelContainer) {
        this.modelContainer = modelContainer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColorOther getClearColor() {
        return clearColor;
    }

    public void setClearColor(ColorOther clearColor) {
        this.clearColor = clearColor;
    }

    public GUIContainer getGuiContainer() {
        return guiContainer;
    }

    public void setGuiContainer(GUIContainer guiContainer) {
        this.guiContainer = guiContainer;
    }

    public CWindow getScreen() {
        return screen;
    }

    public void setScreen(CWindow screen) {
        this.screen = screen;
    }
}
