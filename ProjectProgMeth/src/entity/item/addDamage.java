package entity.item;

import entity.base.Item;
import entity.character.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import logic.Position;
import sharedObject.RenderableHolder;

public class addDamage extends Item {

    public addDamage(Position pos) {
        super("addDamage", pos);
    }

    @Override
    public void performItem(Player player) {
        if(!active) {
            initTime = GameLogic.curTime;
            active = true;
            player.setDamage(player.getDamage()+7);
            visible = false;
            System.out.println("Hp: " + GameLogic.player.getHp());
            System.out.println("Damage: " + GameLogic.player.getDamage());
            System.out.println("Defense:  " + GameLogic.player.getDefense());
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.drawImage(RenderableHolder.addDmg, getCurrentPosition().getX(), getCurrentPosition().getY()
                , 75, 75);
    }
}
