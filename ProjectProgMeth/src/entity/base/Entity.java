package entity.base;

import javafx.scene.canvas.GraphicsContext;
import logic.Position;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable {
    private String name;

    protected Position currentPosition;

    public Entity(String name,Position pos) {
        setName(name);
        setCurrentPosition(pos);
    }
    @Override
    public abstract int getZ();

    @Override
    public abstract void draw(GraphicsContext gc);

    @Override
    public abstract boolean isVisible();

    @Override
    public abstract boolean isDestroyed();

    public void setName(String name) {
        this.name = name;
    }


    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
