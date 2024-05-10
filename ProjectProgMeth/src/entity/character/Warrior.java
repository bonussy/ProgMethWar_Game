package entity.character;

import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.Position;
import sharedObject.RenderableHolder;

public class Warrior extends Player {

    public Warrior() {
        super("Warrior", 10,75,75,40);
        currentPosition = new Position(500,250);
        angle = 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double x = getCurrentPosition().getX();
        double y = getCurrentPosition().getY();
        if(!attacking) {
            if(getDirection() == Direction.EAST)
                gc.drawImage(RenderableHolder.warrior, x, y, 90, 90);
            else if(getDirection() == Direction.WEST)
                gc.drawImage(RenderableHolder.warrior2, x, y, 90, 90);
        }else {
            if(getDirection() == Direction.EAST)
                gc.drawImage(RenderableHolder.warriorHit, x, y, 90, 90);
            else if(getDirection() == Direction.WEST)
                gc.drawImage(RenderableHolder.warriorHit2, x, y, 90, 90);
        }
    }


}
