package entity.base;

import entity.character.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.Position;

public abstract class Block extends Entity {

    public Block(String name,Position pos) {
        super(name, pos);
    }

    public abstract void performBlock(Player player);

}
