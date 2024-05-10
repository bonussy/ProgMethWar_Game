package entity.character;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import logic.Position;
import sharedObject.RenderableHolder;

public class KingDroid extends Enemy{
    private double speed = 0.5;
    public KingDroid(int y) {
        super("KingDroid", 25,100,100,100);
        currentPosition = new Position(1050,y);
        visible = true;
        z = 1;
        destroy = false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        double x = getCurrentPosition().getX();
        double y = getCurrentPosition().getY();
        gc.drawImage(RenderableHolder.kingDroid1,x,y,100,100);
    }

    @Override
    public void update() {
        forward();
        attack();
        if(GameLogic.curTime % 5000 == 0) {
            speed += speed * 0.1;
            System.out.println("speed : " + speed);
        }
    }

    @Override
    public void forward() {
        if(getCurrentPosition().getX() <= 10 ) {
            GameLogic.kingWin = true;
            destroy = true;
            GameLogic.player.setHp(0);
            System.out.println("Hp player slime died: " + GameLogic.player.getHp());
        }
        if((getCurrentPosition().getY() == 675 && getCurrentPosition().getX() == 600) ||
                (getCurrentPosition().getY() == 25 && getCurrentPosition().getX() == 600)){
            destroy = true;
        }
        else {
            getCurrentPosition().setX(getCurrentPosition().getX() - 1*speed);
        }
    }

}
