package input;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class InputUtility {
    public static boolean isLeftDown = false;
    public static boolean isLeftClickedLastTick = false;
    protected static ArrayList<KeyCode> keyPressed = new ArrayList<>();
    public static int counter = 0;

    public static boolean getKeyCodes(KeyCode keyCode) {
        return keyPressed.contains(keyCode);
    }

    public static void setKeyCodes(KeyCode keyCode, boolean pressed) {
        if(pressed){
            if(!getKeyCodes(keyCode))
                keyPressed.add(keyCode);
        }else{
            keyPressed.remove(keyCode);
        }
        System.out.println(keyPressed);
    }
    public static void mouseLeftDown(){
        isLeftDown = true;
        isLeftClickedLastTick = true;
        System.out.println("click");
    }
    public static void mouseLeftRelease() {
        isLeftDown = false;
        System.out.println("release");
    }
    public static boolean isLeftClickTriggered(){
        return isLeftClickedLastTick;
    }
    public static void updateInputState(){
        isLeftClickedLastTick = false;
    }
}
