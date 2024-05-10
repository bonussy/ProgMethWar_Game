package entity.character;

import entity.base.Character;
import entity.block.WoodBlock;
import input.InputUtility;
import javafx.scene.input.KeyCode;
import logic.Direction;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public abstract class Player extends Character {
    protected double angle;
    private int nowCount = 0;

    public Player(String name, int damage, int hp, int maxHp, int defense) {
        super(name, damage, hp, maxHp, defense);
        angle = 0;
        z = 1000;
    }

    public void forward(){
        if(angle == 0) {
            getCurrentPosition().setX(getCurrentPosition().getX() + 1*GameLogic.speedPlayer);
        } else if (angle == 180) {
            getCurrentPosition().setX(getCurrentPosition().getX() - 1*GameLogic.speedPlayer);
        } else if (angle == -90) {
            getCurrentPosition().setY(getCurrentPosition().getY() - 1*GameLogic.speedPlayer);
        } else if (angle == 90) {
            getCurrentPosition().setY(getCurrentPosition().getY() + 1*GameLogic.speedPlayer);
        }
        forwardBlock();
    }
    public void update() {
        if(nowCount < InputUtility.counter){
            attack();
            attacking = true;
            RenderableHolder.attacking_sound.play();
            nowCount = InputUtility.counter;
        }else{
            attacking = false;
        }

        if(InputUtility.getKeyCodes(KeyCode.D)){
            setDirection(Direction.EAST);
            angle = 0;
            forward();
        }
        if(InputUtility.getKeyCodes(KeyCode.S)){
            angle = 90;
            forward();
        }
        if(InputUtility.getKeyCodes(KeyCode.W)){
            angle = -90;
            forward();
        }
        if(InputUtility.getKeyCodes(KeyCode.A)){
            setDirection(Direction.WEST);
            angle = 180;
            forward();
        }
    }
    public void attack() {
        if (getDirection() == Direction.EAST) {
            for (Enemy opponent : GameLogic.enemies) {
                double dx = opponent.getCurrentPosition().getX() - this.getCurrentPosition().getX();
                double dy = opponent.getCurrentPosition().getY() - this.getCurrentPosition().getY();
                if (opponent.isVisible() && 0 <= dx && dx <= 70 && -70 <= dy && dy <= 70) {
                    if (opponent.getDefense()!=0){
                        opponent.setDefense(opponent.getDefense()-this.getDamage());
                    } else {
                        opponent.setHp(opponent.getHp()-this.getDamage());
                    }
                    RenderableHolder.dead_sound.play();
                    System.out.println(opponent.getHp());
                    if(opponent.getHp() == 0)
                        GameLogic.enemyCount++;
                }

            }
            for (WoodBlock block : GameLogic.woodBlocks) {
                double dx = block.getCurrentPosition().getX() - this.getCurrentPosition().getX();
                double dy = block.getCurrentPosition().getY() - this.getCurrentPosition().getY();
                if (block.isVisible() && 17.5 <= dx && dx <= 72.5 && -72.5 <= dy && dy <= 72.5) {
                    block.generateItem();
                    RenderableHolder.dead_sound.play();
                    block.setHp(0);
                }
            }

        }
        if (getDirection() == Direction.WEST) {
            for (Enemy opponent : GameLogic.enemies) {
                double dx = this.getCurrentPosition().getX() - opponent.getCurrentPosition().getX();
                double dy = this.getCurrentPosition().getY() - opponent.getCurrentPosition().getY();
                if (opponent.isVisible() && 0 <= dx && dx <= 70 && -70 <= dy && dy <= 70) {
                    if (opponent.getDefense()!=0){
                        opponent.setDefense(opponent.getDefense()-this.getDamage());
                    } else {
                        opponent.setHp(opponent.getHp()-this.getDamage());
                    }
                    RenderableHolder.dead_sound.play();
                    System.out.println(opponent.getHp());
                    if(opponent.getHp() == 0)
                        GameLogic.enemyCount++;

                }
            }
            for (WoodBlock block : GameLogic.woodBlocks) {
                double dx = this.getCurrentPosition().getX() - block.getCurrentPosition().getX();
                double dy = this.getCurrentPosition().getY() - block.getCurrentPosition().getY();
                if (block.isVisible() && 17.5 <= dx && dx <= 72.5 && -72.5 <= dy && dy <= 72.5) {
                    block.generateItem();
                    RenderableHolder.dead_sound.play();
                    block.setHp(0);

                }
            }
        }
        System.out.println("enemy count: " + GameLogic.enemyCount);
    }
    public void forwardBlock() {
        double playerX = getCurrentPosition().getX();
        double playerY = getCurrentPosition().getY();
        for(WoodBlock woodBlock : GameLogic.woodBlocks){
            if(woodBlock.isVisible()) {
                double woodX = woodBlock.getCurrentPosition().getX();
                double woodY = woodBlock.getCurrentPosition().getY();
                if (woodX + 35 <= playerX && playerX <= woodX + 45) {
                    if (woodY - 65 <= playerY && playerY <= woodY - 55) {
                        getCurrentPosition().setX(woodX + 50);
                        getCurrentPosition().setY(woodY - 68);
                    } else if (woodY - 65 < playerY && playerY < woodY + 35) {
                        getCurrentPosition().setX(woodX + 50);
                    } else if (woodY + 35 <= playerY && playerY <= woodY + 45) {
                        getCurrentPosition().setX(woodX + 50);
                        getCurrentPosition().setY(woodY + 50);
                    }
                } else if (woodX - 20 <= playerX && playerX <= woodX + 35) {
                    if (woodY - 65 <= playerY && playerY < woodY - 55) {
                        getCurrentPosition().setY(woodY - 68);
                    } else if (woodY + 35 <= playerY && playerY <= woodY + 45) {
                        getCurrentPosition().setY(woodY + 50);
                    }
                } else if (woodX - 65 <= playerX && playerX <= woodX - 20) {
                    if (woodY - 65 <= playerY && playerY <= woodY - 55) {
                        getCurrentPosition().setX(woodX - 68);
                        getCurrentPosition().setY(woodY - 68);
                    } else if (woodY - 65 <= playerY && playerY < woodY + 35) {
                        getCurrentPosition().setX(woodX - 68);
                    } else if (woodY + 35 <= playerY && playerY <= woodY + 45) {
                        getCurrentPosition().setX(woodX - 68);
                        getCurrentPosition().setY(woodY + 50);
                    }
                }
            }

        }

    }
}
