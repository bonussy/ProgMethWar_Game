package entity.block;

import entity.base.Block;
import entity.character.Player;
import entity.item.addDamage;
import entity.item.addDefense;
import entity.item.addHp;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import logic.Position;
import sharedObject.RenderableHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WoodBlock extends Block {
    private int hp;
    protected boolean visible;
    public static ArrayList<ArrayList<Position>> woodBlockPos = new ArrayList<>(List.of(new ArrayList<Position>(List.of(new Position(175,150),new Position(175,525))),
            new ArrayList<Position>(List.of(new Position(250,225),new Position(250,375))),new ArrayList<Position>(List.of(new Position(175,150),new Position(250,225))),
            new ArrayList<Position>(List.of(new Position(175,525),new Position(250,375))),
            new ArrayList<Position>(List.of(new Position(175,525),new Position(250,225)))));


    public WoodBlock(Position pos) {
        super("Wood BLock", pos);
        setHp(1);
        visible=true;
    }
    public void generateItem(){
        System.out.println("generate item : ");
        Random rand = new Random();
        int int_random = rand.nextInt(3);
        System.out.println(int_random);
        if(int_random == 0){
            GameLogic.addNewObject(new addDamage(getCurrentPosition()));
            System.out.println("addDamage");
        }else if(int_random == 1){
            GameLogic.addNewObject(new addDefense(getCurrentPosition()));
            System.out.println("addDefense");
        }else if(int_random == 2){
            GameLogic.addNewObject(new addHp(getCurrentPosition()));
            System.out.println("addHp");
        }
    }

    @Override
    public void performBlock(Player player) {
        visible = false;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(RenderableHolder.item_crate, getCurrentPosition().getX(), getCurrentPosition().getY(), 75, 75);
    }

    @Override
    public boolean isVisible() {
        return hp != 0;
    }

    @Override
    public boolean isDestroyed() {
        return true;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        if (hp<0){
            this.hp=0;
            return;
        }
        this.hp = hp;
    }
}

