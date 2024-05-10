package logic;

import entity.base.Block;
import entity.base.Entity;
import entity.base.Item;
import entity.block.Lava;
import entity.block.WoodBlock;
import entity.character.*;
import input.InputUtility;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.ArrayList;
import java.util.Random;

public class GameLogic {
    public static ArrayList<Enemy> enemies;
    public static ArrayList<Block> blocks;
    public static ArrayList<WoodBlock> woodBlocks;
    public static ArrayList<Lava> lavas;
    public static ArrayList<Item> items;

    public static Player player;
    public static KingDroid kingDroid;
    public static int curTime;
    public static int level;
    public static double speedDroid, speedPlayer;
    public static boolean kingWin;
    private static boolean kingBorn;
    public static int enemyCount;
    protected static int lastLevel;

    public GameLogic(){
        clearGame();
        curTime = 0;
        enemies = new ArrayList<>();
        blocks = new ArrayList<>();
        woodBlocks = new ArrayList<>();
        lavas = new ArrayList<>();
        items = new ArrayList<>();
        kingBorn = false;
        kingWin = false;
        Field field = new Field();
        RenderableHolder.getInstance().add(field);

        addNewObject(player);

        Random random = new Random();
        int randomIdx = random.nextInt(WoodBlock.woodBlockPos.size());
        for(Position position : WoodBlock.woodBlockPos.get(randomIdx)){
            addNewObject(new WoodBlock(position));
        }

        for(int x = 300; x<=525; x+=75){
            addNewObject(new Lava(new Position(x,0)));
            addNewObject(new Lava(new Position(x,675)));
        }
        enemyCount = 0;
        lastLevel = 1;
        speedDroid = 0.8;
        speedPlayer = 1.7;
    }
    public static void addNewObject(Entity entity){
        if(entity instanceof Enemy) enemies.add((Enemy) entity);
        if(entity instanceof WoodBlock) woodBlocks.add((WoodBlock) entity);
        if(entity instanceof Lava) lavas.add((Lava) entity);
        if(entity instanceof Item) items.add((Item) entity);
        RenderableHolder.getInstance().add((IRenderable) entity);
    }
    public void logicUpdate(){
        if(curTime % 100 == 0 && GameLogic.enemyCount < 48) {
            Droid.randomIndex = Droid.randomY.nextInt(Droid.yPoints.size());
            Droid.randomItem = Droid.yPoints.get(Droid.randomIndex);
            Droid droid = new Droid(Droid.randomItem);
            addNewObject(droid);
        }
        if(!kingBorn && GameLogic.enemyCount == 48){
            kingDroid = new KingDroid(325);
            addNewObject(kingDroid);
            kingBorn = true;
        }
        level = enemyCount/12 + 1;
        if(GameLogic.enemyCount % 12 == 0 && GameLogic.enemyCount!=0) {
            if (lastLevel < level) {
                GameLogic.speedDroid += GameLogic.speedDroid * 0.2;
                System.out.println("Droid Speed : " + GameLogic.speedDroid);
                GameLogic.speedPlayer += GameLogic.speedPlayer * 0.175;
                System.out.println("Player Speed : " + GameLogic.speedPlayer);
                RenderableHolder.levelUp.play();
                lastLevel = level;
                boolean isThereBox = false;
                for (WoodBlock woodBlock : woodBlocks){
                    if (woodBlock.isVisible()){
                        isThereBox = true;
                    }
                }
                if (!isThereBox){
                    Random random = new Random();
                    int randomIdx = random.nextInt(WoodBlock.woodBlockPos.size());
                    for(Position position : WoodBlock.woodBlockPos.get(randomIdx)){
                        addNewObject(new WoodBlock(position));
                    }
                }
            }
        }
        Thread thread = logicUpdateThread();
        if(gameEnd())
            thread.interrupt();
    }
    public static Thread logicUpdateThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                player.update();
                for(Enemy entity : enemies){
                    if(entity.isVisible())
                        entity.update();
                }
                for(Item item : items){
                    if(item.isDestroyed()){
                        item.update();
                    }
                }
                for (Lava lava : lavas)
                    lava.update();
            }
        });
        thread.start();
        return thread;
    }
    public static boolean isPlayerWin() {
        if (kingBorn){
            return kingDroid.isDead();
        }
        return false;
    }
    public static boolean isPlayerLose(){
        return player.getHp() == 0;
    }
    public static boolean gameEnd(){
        return isPlayerWin() || isPlayerLose();
    }
    public static void clearGame(){
        if (RenderableHolder.getInstance().getEntities()!=null)
            RenderableHolder.getInstance().getEntities().clear();
        if (enemies!=null)
            enemies.clear();
        if (blocks!=null)
            blocks.clear();
        if (woodBlocks!=null)
            woodBlocks.clear();
        if (lavas!=null)
            lavas.clear();
        if (items!=null)
            items.clear();
        InputUtility.counter = 0;
        speedDroid = 0.8;
        speedPlayer = 3;
        level = 1;
    }
}
