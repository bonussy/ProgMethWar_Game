package pane;

import javafx.scene.layout.*;
import sharedObject.RenderableHolder;

public class Menu extends StackPane {

    public Menu(){
        BackgroundImage backgroundImage = new BackgroundImage(RenderableHolder.startScreen, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,false));
        setBackground(new Background(backgroundImage));
        this.resize(1200, 750);
    }

}
