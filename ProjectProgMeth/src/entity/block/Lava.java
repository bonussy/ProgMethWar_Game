package entity.block;

import entity.base.Block;
import entity.character.Player;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import logic.Position;
import sharedObject.RenderableHolder;

public class Lava extends Block  {
    public Lava(Position pos) {
        super("Lava",pos);
    }
    @Override
    public void performBlock(Player player) {
        RenderableHolder.hurt_sound.play();
        player.setHp(player.getHp() - 10);
    }

    @Override
    public int getZ() {
        return -9990;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.lava,getCurrentPosition().getX(),getCurrentPosition().getY(),75,75);
    }
    public void update(){
        double dx = getCurrentPosition().getX() - GameLogic.player.getCurrentPosition().getX();
        double dy = getCurrentPosition().getY() - GameLogic.player.getCurrentPosition().getY();
        if (GameLogic.curTime % 40 == 0 && 0 <= dx && dx <= 60 && -60 <= dy && dy <= 60) {
            performBlock(GameLogic.player);
            System.out.println("Hp: " + GameLogic.player.getHp());
        }
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
