package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Field implements IRenderable {

    @Override
    public int getZ() {
        return -9999;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.grassField, 0, 0, 1200, 750);

    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public boolean isDestroyed() {
        return true;
    }
}
