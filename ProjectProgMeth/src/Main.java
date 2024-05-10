import drawing.GameScreen;
import drawing.StatusBar;
import entity.character.Tank;
import entity.character.Warrior;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameLogic;
import pane.ChoosePlayer;
import pane.Help;
import pane.Menu;
import sharedObject.RenderableHolder;

import java.io.File;

import static sharedObject.RenderableHolder.*;

public class Main extends Application {
    private Stage primaryStage;
    private Scene startScene;
    private Scene helpScene;
    private Scene chooseScene;
    private Scene gameScene;

    @Override
    public void start(Stage primaryStage)  {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("PROGMETH WARS");
        primaryStage.setResizable(false);
        createStartScene();
        primaryStage.show();

    }
    public void createStartScene(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Button start = createStartButton();
                Button help = createHelpButton();
                Button exit = createExitButton();

                VBox vBox = new VBox();
                vBox.setSpacing(10);
                vBox.setPadding(new Insets(100,0,0,0));
                vBox.getChildren().addAll(start,help,exit);
                vBox.setAlignment(Pos.BASELINE_LEFT);
                vBox.setTranslateX(120);
                vBox.setTranslateY(180);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Menu startPane = new Menu();
                        startPane.getChildren().add(vBox);
                        startScene = new Scene(startPane,1200,750);
                        primaryStage.setScene(startScene);
                        if (!RenderableHolder.intro.isPlaying()) {
                            RenderableHolder.intro.play();
                        }
                    }
                });

            }
        });
        thread.start();
    }
    public void createChooseScene(){
        if (!intro.isPlaying()){
            intro.play();
        }
        if (jedi_playing.isPlaying()){
            jedi_playing.stop();
        }
        ChoosePlayer choosePlayer = new ChoosePlayer();
        Button back = createBackButton();
        Button play = createPlayButton();
        choosePlayer.add(play,5,12);
        choosePlayer.add(back,1,12);
        this.chooseScene = new Scene(choosePlayer,1200,750);
        primaryStage.setScene(chooseScene);
    }
    public void createHelpScene(){
        Help help = new Help();
        Button back = createBackButton();
        help.getChildren().add(back);
        help.setAlignment(back, Pos.BOTTOM_CENTER);
        back.setTranslateY(-80);
        this.helpScene = new Scene(help, 1200, 750);
        primaryStage.setScene(helpScene);

    }
    public void startGame(){
        //if (GameLogic.player instanceof Warrior || GameLogic.player instanceof Tank) {
        RenderableHolder.jedi_playing.play();
        StackPane root = new StackPane();
        this.gameScene = new Scene(root);
        GameScreen gameScreen = new GameScreen(1200, 750);
        GameLogic gameLogic = new GameLogic();
        root.getChildren().add(gameScreen);
        gameScreen.requestFocus();

        AnimationTimer animationTimer = new AnimationTimer() {
            private int framesPerSec = 120; // Desired frames per second. You can modify this value!
            private long nSecPerFrame = Math.round(1.0/framesPerSec * 1e9);
            private long lastUpdate = 0;
            public void handle(long now) {
                if (now - lastUpdate > nSecPerFrame) {
                    if (GameLogic.isPlayerWin()){
                        gameScreen.paintBossBoom();
                    }
                    if(GameLogic.gameEnd()){
                        stop();
                        createEndScene(root);
                    }
                    gameScreen.paint();
                    gameLogic.logicUpdate();
                    StatusBar.paint(gameScreen);
                    RenderableHolder.getInstance().update();
                    GameLogic.curTime++;
                    if(InputUtility.isLeftClickTriggered())
                        InputUtility.counter++;
                    InputUtility.updateInputState();
                    if (InputUtility.getKeyCodes(KeyCode.ESCAPE)){
                        createPauseScene(this, root);
                    }
                    lastUpdate = now;
                }
            }
        };
        animationTimer.start();
        primaryStage.setScene(gameScene);
    }
    public void createPauseScene(AnimationTimer animationTimer, StackPane stackPane){
        animationTimer.stop();
        Button back = createBackToMenuButton();
        back.setTranslateY(150);
        stackPane.getChildren().addAll(back);
    }
    public void createEndScene(Pane pane){
        Button playAgain = createPlayAgainButton();
        Button backToMenu = createBackToMenuButton();
        backToMenu.setTranslateY(200);
        backToMenu.setTranslateX(-225);
        playAgain.setTranslateY(200);
        playAgain.setTranslateX(225);

        Text end = new Text();
        end.setFont(Font.loadFont(ClassLoader.getSystemResource("Early_GameBoy.ttf").toString(), 70));
        end.setFill(Color.BLACK);
        Text end1 = new Text();
        end1.setFont(Font.loadFont(ClassLoader.getSystemResource("Early_GameBoy.ttf").toString(), 70));
        end1.setFill(Color.WHITE);
        if (GameLogic.isPlayerWin()){
            end1.setFill(Color.GREENYELLOW);
            end.setText("YOU WIN!!");
            end1.setText("YOU WIN!!");
        } else {
            end1.setFill(Color.RED);
            end.setText("YOU LOSE!!");
            end1.setText("YOU LOSE!!");
        }
        pane.getChildren().addAll(end, end1, playAgain, backToMenu);
        end.setTranslateY(-130);
        end1.setTranslateX(-5);
        end1.setTranslateY(-135);
    }
    //Button------------------------------------------------------------------------------------
    public Button createStartButton(){
        ImageView startBtn1 = new ImageView(RenderableHolder.startButton);
        ImageView startBtn2 = new ImageView(RenderableHolder.startButtonPressed);
        startBtn1.setFitWidth(275); startBtn1.setFitHeight(93.75);
        startBtn2.setFitWidth(275); startBtn2.setFitHeight(93.75);
        Button start = new Button();
        start.setGraphic(startBtn1);
        start.setStyle("-fx-background-color: transparent;");
        start.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RenderableHolder.button_sound.play();
                start.setGraphic(startBtn2);
            }
        });
        start.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.setGraphic(startBtn1);
            }
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createChooseScene();
            }
        });
        return start;
    }
    public Button createHelpButton(){
        ImageView helpBtn1 = new ImageView(RenderableHolder.helpButton);
        ImageView helpBtn2 = new ImageView(RenderableHolder.helpButtonPressed);
        helpBtn1.setFitWidth(275); helpBtn1.setFitHeight(93.75);
        helpBtn2.setFitWidth(275); helpBtn2.setFitHeight(93.75);
        Button help = new Button();
        help.setGraphic(helpBtn1);
        help.setStyle("-fx-background-color: transparent;");
        help.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RenderableHolder.button_sound.play();
                help.setGraphic(helpBtn2);
            }
        });
        help.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                help.setGraphic(helpBtn1);
            }
        });
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createHelpScene();
            }
        });
        return help;
    }
    public Button createExitButton(){
        ImageView exitBtn1 = new ImageView(RenderableHolder.exitButton);
        ImageView exitBtn2 = new ImageView(RenderableHolder.exitButtonPressed);
        exitBtn1.setFitWidth(275); exitBtn1.setFitHeight(93.75);
        exitBtn2.setFitWidth(275); exitBtn2.setFitHeight(93.75);
        Button exit = new Button();
        exit.setGraphic(exitBtn1);
        exit.setStyle("-fx-background-color: transparent;");

        exit.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RenderableHolder.button_sound.play();
                exit.setGraphic(exitBtn2);
            }
        });
        exit.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exit.setGraphic(exitBtn1);
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });
        return exit;
    }
    public Button createBackButton(){
        ImageView backBtn1 = new ImageView(RenderableHolder.backButton);
        ImageView backBtn2 = new ImageView(RenderableHolder.backButtonPressed);
        backBtn1.setFitWidth(275); backBtn1.setFitHeight(93.75);
        backBtn2.setFitWidth(275); backBtn2.setFitHeight(93.75);
        Button back = new Button();
        back.setGraphic(backBtn1);
        back.setStyle("-fx-background-color: transparent;");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createStartScene();
            }
        });
        back.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RenderableHolder.button_sound.play();
                back.setGraphic(backBtn2);
            }
        });
        back.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                back.setGraphic(backBtn1);
            }
        });
        return back;
    }
    public Button createPlayButton(){
        ImageView playBtn1 = new ImageView(playButton);
        ImageView playBtn2 = new ImageView(RenderableHolder.playButtonPressed);
        playBtn1.setFitWidth(275); playBtn1.setFitHeight(93.75);
        playBtn2.setFitWidth(275); playBtn2.setFitHeight(93.75);
        Button play = new Button();
        play.setGraphic(playBtn1);
        play.setStyle("-fx-background-color: transparent;");
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (RenderableHolder.intro.isPlaying()){
                    RenderableHolder.intro.stop();
                }
                startGame();
            }
        });
        play.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RenderableHolder.button_sound.play();
                play.setGraphic(playBtn2);
            }
        });
        play.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                play.setGraphic(playBtn1);
            }
        });
        return play;
    }
    public Button createBackToMenuButton(){
        ImageView backBtn1 = new ImageView(RenderableHolder.backToMenu1);
        ImageView backBtn2 = new ImageView(RenderableHolder.backToMenu2);
        backBtn1.setFitWidth(275); backBtn1.setFitHeight(93.75);
        backBtn2.setFitWidth(275); backBtn2.setFitHeight(93.75);
        Button back = new Button();
        back.setGraphic(backBtn1);
        back.setStyle("-fx-background-color: transparent;");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (RenderableHolder.jedi_playing.isPlaying()){
                    jedi_playing.stop();
                }
                createStartScene();
            }
        });
        back.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RenderableHolder.button_sound.play();
                back.setGraphic(backBtn2);
            }
        });
        back.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                back.setGraphic(backBtn1);
            }
        });
        return back;
    }
    public Button createPlayAgainButton(){
        ImageView startBtn1 = new ImageView(RenderableHolder.playAgain1);
        ImageView startBtn2 = new ImageView(RenderableHolder.playAgain2);
        startBtn1.setFitWidth(275); startBtn1.setFitHeight(93.75);
        startBtn2.setFitWidth(275); startBtn2.setFitHeight(93.75);
        Button start = new Button();
        start.setGraphic(startBtn1);
        start.setStyle("-fx-background-color: transparent;");
        start.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                RenderableHolder.button_sound.play();
                start.setGraphic(startBtn2);
            }
        });
        start.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                start.setGraphic(startBtn1);
            }
        });
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createChooseScene();
            }
        });
        return start;
    }

    public static void main(String[] args){
        launch(args);
    }
}
