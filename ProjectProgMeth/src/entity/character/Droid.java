package entity.character;

import javafx.scene.canvas.GraphicsContext;
import logic.Position;
import sharedObject.RenderableHolder;

public class Droid extends Enemy{
    public Droid(int y) {
        super("Droid", 10, 30, 30, 0);
        currentPosition = new Position(1050,y);
        visible = true;
        z = 1;
        destroy = false;
    }
    @Override
    public void draw(GraphicsContext gc) {
        double x = getCurrentPosition().getX();
        double y = getCurrentPosition().getY();
        gc.drawImage(RenderableHolder.droid,x,y,50,50);
    }

}
