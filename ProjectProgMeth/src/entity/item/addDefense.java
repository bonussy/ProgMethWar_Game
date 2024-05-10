package entity.item;

import entity.base.Item;
import entity.character.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import logic.Position;
import sharedObject.RenderableHolder;

public class addDefense extends Item {

    public addDefense(Position pos) {
        super("addDefense", pos);
    }

    @Override
    public void performItem(Player player) {
        if(!active) {
            initTime = GameLogic.curTime;
            active = true;
            visible = false;
            player.setDefense(player.getDefense()+25);
            System.out.println("Hp: " + GameLogic.player.getHp());
            System.out.println("Damage: " + GameLogic.player.getDamage());
            System.out.println("Defense: " + GameLogic.player.getDefense());
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        //
        gc.drawImage(RenderableHolder.addDef, getCurrentPosition().getX(), getCurrentPosition().getY()
                , 75, 75);
    }
}
