package pane;

import entity.character.Fighter;
import entity.character.Tank;
import entity.character.Warrior;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class ChoosePlayer extends GridPane {
    private Button warriorButton;
    private Button tankButton;
    private Button fighterButton;
    private final ImageView warrior = new ImageView(RenderableHolder.masterPSelected);
    private final ImageView warriorUnselected = new ImageView(RenderableHolder.masterPUnselected);
    private final ImageView tank = new ImageView(RenderableHolder.masterToeSelected);
    private final ImageView tankUnselected = new ImageView(RenderableHolder.masterToeUnselected);
    private final ImageView fighter = new ImageView(RenderableHolder.sithSelected);
    private final ImageView fighterUnselected = new ImageView(RenderableHolder.sithUnselected);
    protected Text characterName;

    public ChoosePlayer(){
        setAlignment(Pos.BASELINE_CENTER);
        setHgap(20);
        setVgap(10);
        setPadding(new Insets(100,100,100,100));
        BackgroundImage backgroundImage = new BackgroundImage(RenderableHolder.chooseCharacterBg, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,true,false));
        setBackground(new Background(backgroundImage));

        warrior.setFitHeight(300); warrior.setFitWidth(300);
        warriorUnselected.setFitHeight(300); warriorUnselected.setFitWidth(300);
        tank.setFitHeight(300); tank.setFitWidth(300);
        tankUnselected.setFitHeight(300); tankUnselected.setFitWidth(300);
        fighter.setFitHeight(300); fighter.setFitWidth(300);
        fighterUnselected.setFitHeight(300); fighterUnselected.setFitWidth(300);

        newWarriorButton();
        newTankButton();
        newFighterButton();

        add(warriorButton,1,2);
        add(tankButton,3,2);
        add(fighterButton,5,2);

        setLabel();

        Font font = Font.loadFont(ClassLoader.getSystemResource("Early_GameBoy.ttf").toString(), 16);
        characterName = new Text();
        characterName.setFont(font);
        characterName.setFill(Color.WHITE);
        add(characterName, 1, 8, 4 ,1);
        setTextWarrior();

    }
    public void setLabel(){

        Font font = Font.loadFont(ClassLoader.getSystemResource("Early_GameBoy.ttf").toString(), 32);
        Text t = new Text("CHOOSE YOUR CHARACTER");
        t.setFont(font);
        t.setFill(Color.WHITE);
        add(t, 0, 0, 5, 1);
    }
    public void newWarriorButton(){
        warriorButton = new Button();
        warriorButton.setGraphic(warrior);
        warriorButton.setStyle("-fx-background-color: transparent;");
        GameLogic.player = new Warrior();
        warriorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                warriorButton.setGraphic(warrior);
                RenderableHolder.button_sound.play();
                GameLogic.player = new Warrior();
                System.out.println("choose Warrior");
                tankButton.setGraphic(tankUnselected);
                fighterButton.setGraphic(fighterUnselected);
                setTextWarrior();
            }
        });
    }
    public void newTankButton(){
        tankButton = new Button();
        tankButton.setGraphic(tankUnselected);
        tankButton.setStyle("-fx-background-color: transparent;");
        tankButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tankButton.setGraphic(tank);
                RenderableHolder.button_sound.play();
                GameLogic.player = new Tank();
                System.out.println("choose Tank");
                warriorButton.setGraphic(warriorUnselected);
                fighterButton.setGraphic(fighterUnselected);
                setTextTank();
            }
        });
    }
    public void newFighterButton(){
        fighterButton = new Button();
        fighterButton.setGraphic(fighterUnselected);
        fighterButton.setStyle("-fx-background-color: transparent;");
        fighterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fighterButton.setGraphic(fighter);
                RenderableHolder.button_sound.play();
                GameLogic.player = new Fighter();
                System.out.println("new Fighter");
                warriorButton.setGraphic(warriorUnselected);
                tankButton.setGraphic(tankUnselected);
                setTextFighter();
            }
        });
    }
    public void setTextWarrior(){
        characterName.setText("Jedi Master P :  Balance! , Can Attack And Defense");
    }
    public void setTextTank(){
        characterName.setText("Jedi Master T :  Defender! , A Good patient");
    }
    public void setTextFighter(){
        characterName.setText("Sith Lord Nisit : Attacker! , Only Know How to Attack");
    }

}
