package entity.character;

import entity.base.Character;
import logic.Direction;
import logic.GameLogic;
import sharedObject.RenderableHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Enemy extends Character {
    public static ArrayList<Integer> yPoints = new ArrayList<>(List.of(75,150,225,300,375,450,525,600,675));
    public static Random randomY = new Random();
    public static int randomIndex = randomY.nextInt(yPoints.size());
    public static int randomItem = yPoints.get(randomIndex);
    public Enemy(String name, int damage, int hp, int maxHp, int defense) {
        super(name, damage, hp, maxHp, defense);
        setDirection(Direction.WEST);
    }

    public void forward() {
        if(getCurrentPosition().getX() <= 5 ) {
            destroy = true;
            RenderableHolder.explode.play();
            if (GameLogic.player.getDefense()!=0){
                GameLogic.player.setDefense(GameLogic.player.getDefense()-15);
            } else {
                GameLogic.player.setHp(GameLogic.player.getHp()-15);
            }
            System.out.println("Hp player slime died: " + GameLogic.player.getHp());
        }
        if( ( 670 < getCurrentPosition().getY() && getCurrentPosition().getY() < 680 &&
                570<getCurrentPosition().getX() && getCurrentPosition().getX() < 580)
                ||
                (25<getCurrentPosition().getY() && getCurrentPosition().getY()<30 &&
                        585 < getCurrentPosition().getX() && getCurrentPosition().getX() < 590)){
            RenderableHolder.explode.play();
            destroy = true;
        }
        else {
            getCurrentPosition().setX(getCurrentPosition().getX() - 1*GameLogic.speedDroid);
        }
    }

    public void update() {
        forward();
        attack();
    }

    @Override
    public void attack(){
        double dx = GameLogic.player.getCurrentPosition().getX()-this.getCurrentPosition().getX();
        double dy = GameLogic.player.getCurrentPosition().getY()-this.getCurrentPosition().getY();
        if ( GameLogic.curTime%50==0 && -30 <=dx && dx<=20 && -30<=dy && dy<=30){
            if (GameLogic.player.getDefense()!=0){
                GameLogic.player.setDefense(GameLogic.player.getDefense() - this.getDamage());
            } else{
                GameLogic.player.setHp(GameLogic.player.getHp() - this.getDamage());
            }
            RenderableHolder.hurt_sound.play();
            System.out.println("Hp Player: " + GameLogic.player.getHp());
        }

    }
    @Override
    public boolean isVisible() {
        return getHp() != 0 && !destroy ;
    }
    @Override
    public boolean isDestroyed() {
        return !destroy;
    }
}
