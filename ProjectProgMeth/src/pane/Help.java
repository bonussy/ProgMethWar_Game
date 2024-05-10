package pane;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import sharedObject.RenderableHolder;

public class Help extends StackPane {
    public Help(){
        BackgroundImage backgroundImage = new BackgroundImage(RenderableHolder.chooseCharacterBg, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,false));
        setBackground(new Background(backgroundImage));
        ImageView helpBox = new ImageView(RenderableHolder.helpBox1);
        this.getChildren().add(helpBox);
    }
}
