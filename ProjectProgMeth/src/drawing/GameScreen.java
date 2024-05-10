package drawing;


import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class GameScreen extends Canvas {
    public GameScreen(double width, double height){
        super(width, height);
        this.setVisible(true);
        addKeycode();
    }
    public void addKeycode(){
        this.setOnKeyPressed((KeyEvent e) -> {
            InputUtility.setKeyCodes(e.getCode(),true);
        });
        this.setOnKeyReleased((KeyEvent e) -> {
            InputUtility.setKeyCodes(e.getCode(),false);
        });
        this.setOnMouseClicked((MouseEvent e) ->{
            if (e.getButton()== MouseButton.PRIMARY)
                InputUtility.mouseLeftDown();
        });
        this.setOnMouseReleased((MouseEvent e) ->{
            if (e.getButton() == MouseButton.PRIMARY)
                InputUtility.mouseLeftRelease();
        });
    }
    public void paint(){
        GraphicsContext gc = this.getGraphicsContext2D();
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
                if (entity.isVisible())
                    entity.draw(gc);
        }
    }
    public void paintBossBoom(){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.drawImage(RenderableHolder.boom, 600, 600);
    }

}
