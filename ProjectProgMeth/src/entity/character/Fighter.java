package entity.character;

import javafx.scene.canvas.GraphicsContext;
import logic.Direction;
import logic.Position;
import sharedObject.RenderableHolder;

public class Fighter extends Player {
    public Fighter() {
        super("Fighter", 25, 50, 50, 35);
        currentPosition = new Position(500,250);
        angle = 0;
    }
    @Override
    public void draw(GraphicsContext gc) {
        double x = getCurrentPosition().getX();
        double y = getCurrentPosition().getY();
        if(!attacking) {
            if(getDirection() == Direction.EAST)
                gc.drawImage(RenderableHolder.fighter, x, y, 90, 90);
            else if(getDirection() == Direction.WEST)
                gc.drawImage(RenderableHolder.fighter2, x, y, 90, 90);
        }else {
            if(getDirection() == Direction.EAST)
                gc.drawImage(RenderableHolder.fighterHit, x, y, 90, 90);
            else if(getDirection() == Direction.WEST)
                gc.drawImage(RenderableHolder.fighterHit2, x, y, 90, 90);
        }
    }

}
