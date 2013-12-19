/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GraphicalInterface.CWindow;
import Listener.Listener;
import Model.ModelContainer;
import Model.Texture.TextureContainer;
import Text.TextRenderer;
import java.io.IOException;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Cuper
 */
public class Main {

    protected String name = "Insert Name Here";
    protected String version = "1.0";
    protected int screenX = 800;
    protected int screenY = 600;
    protected int fps = 100;
    protected boolean running;
    protected Camera camera;
    protected Renderer renderer;
    protected Listener listener;
    protected TextureContainer textureContainer;
    protected TextRenderer textRenderer;
    protected Scene currentScene;
    protected ArrayList<Scene> sceneList = new ArrayList();
    protected CWindow focusedWindow;

    public Main() throws IOException {
        currentScene = new Scene(this, "Main");
        sceneList.add(currentScene);
        camera = new Camera();
        renderer = new Renderer(this);
        listener = new Listener(this);
        textureContainer = new TextureContainer(this);
        textRenderer = new TextRenderer(this);
    }

    public void run() throws IOException {
        renderer.initGL();
        textureContainer.initTextTexture();
        textureContainer.initTexture();
        listener.Initialization();
        listener.start();
        initialization();
        Display.sync(getFps());
        while (running) {
            if (Display.isCloseRequested()) {
                closeAction();
                running = false;
            }
            if (running) {
                renderer.render();
                action();

                delay(10);
            }
        }
    }

    /**
     * Override
     */
    public void action() {
    }

    public void action3D() {
    }

    public void action2D() {
    }

    /**
     * Override
     */
    public void closeAction() {
    }

    //overide this
    public void initialization() throws IOException {
    }

    public void delay(int delay) {
        int n = (int) System.currentTimeMillis();
        while (System.currentTimeMillis() - n < delay) {
        }
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public ModelContainer getModelContainer() {
        return currentScene.getModelContainer();
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public TextureContainer textures() {
        return textureContainer;
    }

    public void setTextureContainer(TextureContainer textureContainer) {
        this.textureContainer = textureContainer;
    }

    public TextureContainer getTextureContainer() {
        return textureContainer;
    }

    public TextRenderer getTextRenderer() {
        return textRenderer;
    }

    public void setTextRenderer(TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public ArrayList<Scene> getSceneList() {
        return sceneList;
    }

    public void setSceneList(ArrayList<Scene> sceneList) {
        this.sceneList = sceneList;
    }

    public CWindow getFocusedWindow() {
        return focusedWindow;
    }

    public void setFocusedWindow(CWindow focusedWindow) {
        this.focusedWindow = focusedWindow;
    }
}
