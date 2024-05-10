package sharedObject;


import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
    int getZ();
    void draw(GraphicsContext gc);
    boolean isVisible();
    boolean isDestroyed();

}
