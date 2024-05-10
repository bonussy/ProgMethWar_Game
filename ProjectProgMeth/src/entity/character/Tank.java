package entity.character;

import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.Position;
import sharedObject.RenderableHolder;

public class Tank extends Player {
    public Tank() {
        super("Tank", 12, 120, 120, 70);
        currentPosition = new Position(500,250);
        angle = 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double x = getCurrentPosition().getX();
        double y = getCurrentPosition().getY();
        if(!attacking) {
            if(getDirection() == Direction.EAST)
                gc.drawImage(RenderableHolder.tank, x, y, 90, 90);
            else if(getDirection() == Direction.WEST)
                gc.drawImage(RenderableHolder.tank2, x, y, 90, 90);
        }else {
            if(getDirection() == Direction.EAST)
                gc.drawImage(RenderableHolder.tankHit, x, y, 90, 90);
            else if(getDirection() == Direction.WEST)
                gc.drawImage(RenderableHolder.tankHit2, x, y, 90, 90);
        }
    }


}
