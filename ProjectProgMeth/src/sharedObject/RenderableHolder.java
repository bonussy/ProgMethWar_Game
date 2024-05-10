package sharedObject;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Comparator;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();
    private ArrayList<IRenderable> entities;
    private Comparator<IRenderable> comparator;
    public static Image lava,item_crate,grassField;;
    public static Image droid, kingDroid1, kingDroid2;
    public static Image warrior,warrior2,warriorHit,warriorHit2;
    public static Image tank,tank2,tankHit,tankHit2;
    public static Image fighter,fighter2,fighterHit,fighterHit2;
    public static Image addHp,addDef,addDmg;
    public static Image startButton,startButtonPressed,startScreen, boom;
    public static Image backButton,backButtonPressed,exitButton,exitButtonPressed,helpButton,helpButtonPressed, playButton, playButtonPressed;
    public static Image masterPSelected, masterPUnselected, masterToeSelected, masterToeUnselected, sithSelected, sithUnselected;
    public static Image chooseCharacterBg, helpBox1, backToMenu1, backToMenu2, resume1, resume2, playAgain1, playAgain2;
    public static AudioClip attacking_sound, dead_sound, hurt_sound, button_sound, collect_sound, explode, intro, levelUp,
    jedi_playing;
    public static Font font;
    static {
        loadResource();
    }
    public RenderableHolder(){
        entities = new ArrayList<IRenderable>();
        comparator = (IRenderable lhs, IRenderable rhs) -> {
            if(lhs.getZ() > rhs.getZ())
                return 1;
            return -1;
        };
    }
    public static void loadResource(){
        grassField = new Image(ClassLoader.getSystemResource("field.png").toString());
        lava = new Image(ClassLoader.getSystemResource("lava.jpg").toString());

        item_crate = new Image(ClassLoader.getSystemResource("item_crate.png").toString());

        warrior = new Image(ClassLoader.getSystemResource("aj_A_1.png").toString());
        warrior2 = new Image(ClassLoader.getSystemResource("aj_A_2.png").toString());
        warriorHit = new Image(ClassLoader.getSystemResource("aj_A_3.png").toString());
        warriorHit2 = new Image(ClassLoader.getSystemResource("aj_A_4.png").toString());

        tank = new Image(ClassLoader.getSystemResource("aj_Toe_1.png").toString());
        tank2 = new Image(ClassLoader.getSystemResource("aj_Toe_2.png").toString());
        tankHit = new Image(ClassLoader.getSystemResource("aj_Toe_3.png").toString());
        tankHit2 = new Image(ClassLoader.getSystemResource("aj_Toe_4.png").toString());


        fighter= new Image(ClassLoader.getSystemResource("sith_1.png").toString());
        fighter2 = new Image(ClassLoader.getSystemResource("sith_2.png").toString());
        fighterHit = new Image(ClassLoader.getSystemResource("sith_3.png").toString());
        fighterHit2 = new Image(ClassLoader.getSystemResource("sith_4.png").toString());


        droid = new Image(ClassLoader.getSystemResource("droid.png").toString());
        kingDroid1 = new Image(ClassLoader.getSystemResource("bigBoss1.png").toString());
        kingDroid2 = new Image(ClassLoader.getSystemResource("bigBoss2.png").toString());
        //item----------------------------------------------------------------------------------
        addHp = new Image(ClassLoader.getSystemResource("addHP.png").toString());
        addDef = new Image(ClassLoader.getSystemResource("addDef.png").toString());
        addDmg = new Image(ClassLoader.getSystemResource("addDmg.png").toString());
        //Button-------------------------------------------------------------------------------
        startButton = new Image(ClassLoader.getSystemResource("start1.png").toString());
        startButtonPressed = new Image(ClassLoader.getSystemResource("start2.png").toString());

        backButton = new Image(ClassLoader.getSystemResource("back_unpressed.png").toString());
        backButtonPressed = new Image(ClassLoader.getSystemResource("back_pressed.png").toString());

        exitButton = new Image(ClassLoader.getSystemResource("exit1.png").toString());
        exitButtonPressed = new Image(ClassLoader.getSystemResource("exit2.png").toString());

        helpButton = new Image(ClassLoader.getSystemResource("help1.png").toString());
        helpButtonPressed = new Image(ClassLoader.getSystemResource("help2.png").toString());

        backToMenu1 = new Image(ClassLoader.getSystemResource("back_to_menu_unpressed.png").toString());
        backToMenu2 = new Image(ClassLoader.getSystemResource("back_to_menu_pressed.png").toString());

        resume1 = new Image(ClassLoader.getSystemResource("resume_unpressed.png").toString());
        resume2 = new Image(ClassLoader.getSystemResource("resume_pressed.png").toString());

        playAgain1 = new Image(ClassLoader.getSystemResource("play_again_button.png").toString());
        playAgain2 = new Image(ClassLoader.getSystemResource("play_again_button2.png").toString());

        playButton = new Image(ClassLoader.getSystemResource("play1.png").toString());
        playButtonPressed = new Image(ClassLoader.getSystemResource("play2.png").toString());

        //Player Selected---------------------------------------------------------------------
        masterToeSelected = new Image(ClassLoader.getSystemResource("master_toe_selected.png").toString());
        masterToeUnselected = new Image(ClassLoader.getSystemResource("master_toe_unselected.png").toString());
        masterPSelected = new Image(ClassLoader.getSystemResource("master_p_selected.png").toString());
        masterPUnselected = new Image(ClassLoader.getSystemResource("master_p_unselected.png").toString());
        sithSelected = new Image(ClassLoader.getSystemResource("sith_selected.png").toString());
        sithUnselected = new Image(ClassLoader.getSystemResource("sith_unselected.png").toString());
        //sound-------------------------------------------------------------------
        attacking_sound = new AudioClip(ClassLoader.getSystemResource("Lightsaber.mp3").toString());
        dead_sound = new AudioClip(ClassLoader.getSystemResource("dead.mp3").toString());
        hurt_sound = new AudioClip(ClassLoader.getSystemResource("hurt.mp3").toString());
        button_sound = new AudioClip(ClassLoader.getSystemResource("button.mp3").toString());
        collect_sound = new AudioClip(ClassLoader.getSystemResource("collect_item.mp3").toString());
        explode = new AudioClip(ClassLoader.getSystemResource("explode.mp3").toString());
        intro = new AudioClip(ClassLoader.getSystemResource("intro.mp3").toString());
        levelUp = new AudioClip(ClassLoader.getSystemResource("level_up.mp3").toString());
        jedi_playing = new AudioClip(ClassLoader.getSystemResource("jedi_playing.mp3").toString());
        //--------------------------------------------------------------------------------------
        chooseCharacterBg = new Image(ClassLoader.getSystemResource("choose_character_background.png").toString(),
                1200, 750, false, false);
        startScreen = new Image(ClassLoader.getSystemResource("start_screen_bg.png").toString(), 1200, 750, false, false);
        helpBox1 = new Image(ClassLoader.getSystemResource("help_box_1.png").toString());
        //Font--------------------------------------------------------------------------------------
        font = Font.loadFont(ClassLoader.getSystemResource("Early_GameBoy.ttf").toString(), 16);
        //gif---------------------------------------------------------------------------------------
        boom = new Image(ClassLoader.getSystemResource("boom.gif").toString());

    }
    public void add(IRenderable entity){
        entities.add(entity);
        entities.sort(comparator);
    }
    public void update(){
        for(int i = entities.size()-1; i>=0; i--){
            if(!entities.get(i).isVisible())
                entities.remove(i);
        }
    }
    public static RenderableHolder getInstance() {
        return instance;
    }

    public ArrayList<IRenderable> getEntities() {
        return entities;
    }
}
