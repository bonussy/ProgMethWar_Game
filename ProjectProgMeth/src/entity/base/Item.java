package entity.base;

import entity.character.Player;
import logic.GameLogic;
import logic.Position;
import sharedObject.RenderableHolder;

public abstract class Item extends Entity {
    protected boolean visible, active;
    protected int z;
    protected int initTime;

    public Item(String name,Position pos){
        super(name,pos);
        z = -10;
        active = false;
        visible = true;
        initTime = GameLogic.curTime;
    }
    public abstract void performItem(Player player);

    public void update(){
        if(isVisible()) {
            double dx = getCurrentPosition().getX() - GameLogic.player.getCurrentPosition().getX();
            double dy = getCurrentPosition().getY() - GameLogic.player.getCurrentPosition().getY();
            if (-20 <= dx && dx <= 20 && -20 <= dy && dy <= 20) {
                performItem(GameLogic.player);
                RenderableHolder.collect_sound.play();
            }
        }
    }
    @Override
    public int getZ() {
        return z;
    }
    @Override
    public boolean isVisible() {
        return visible && isDestroyed();
    }
    @Override
    public boolean isDestroyed() {
        return initTime + 2500 > GameLogic.curTime;
    }

}
