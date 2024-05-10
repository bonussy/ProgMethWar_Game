package drawing;

import drawing.GameScreen;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.GameLogic;
import sharedObject.RenderableHolder;


public class StatusBar {

    public static void paint(GameScreen gameScreen){
        String hp = "HP  : " + Integer.toString(GameLogic.player.getHp());
        String defense = "DEF : " + Integer.toString(GameLogic.player.getDefense());
        String lv = "Level. " + Integer.toString(GameLogic.level);
        String killCount = " x " + Integer.toString(GameLogic.enemyCount);
        GraphicsContext gc = gameScreen.getGraphicsContext2D();
        //draw maxHp----------------------------------------------------------------------------
        gc.setLineWidth(16);
        gc.setStroke(Color.BLACK);
        gc.moveTo(92, 65);
        gc.lineTo(GameLogic.player.getMaxHp()*3+92, 65);
        gc.stroke();
        //drawHp-----------------------------------------------------------------------------------
        if (GameLogic.player.getHp()!=0) {
            gc.setStroke(Color.web("0x8A0303",1.0));
            gc.beginPath();
            gc.moveTo(90, 63);
            gc.lineTo(GameLogic.player.getHp() * 3 + 90, 63);
            gc.stroke();
        }
        //drawDef----------------------------------------------------------------------------------------------
        if (GameLogic.player.getDefense()!=0) {
            gc.setStroke(Color.BLACK);
            gc.beginPath();
            gc.moveTo(92, 102);
            gc.lineTo(GameLogic.player.getDefense() * 3 + 92, 102);
            gc.stroke();
            gc.setStroke(Color.web("0xaaaaaa",1.0));
            gc.beginPath();
            gc.moveTo(90, 100);
            gc.lineTo(GameLogic.player.getDefense() * 3 + 90, 100);
            gc.stroke();

        }
        //----------------------------------------------------------------------------------------------
        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.setStroke(javafx.scene.paint.Color.WHITE);
        gc.setFont(RenderableHolder.font);
        gc.fillText(hp, 85, 69);
        gc.fillText(defense, 85, 105.5);
        gc.fillText(lv, 765, 69);
        gc.fillText(killCount, 956, 69);
        Image slime = new Image(ClassLoader.getSystemResource("killCount.png").toString(), 30, 30, false, false);
        gc.drawImage(slime, 917, 49.5);
        //--------------------------------------------

    }
}
