package entity.base;

import logic.Direction;
import logic.Position;

public abstract class Character extends Entity implements Fightable {
    private int damage;
    private int hp;
    private int maxHp;
    private int defense;
    private Direction direction;
    protected int z;
    protected boolean visible, attacking, destroy;

    public Character(String name, int damage, int hp, int maxHp, int defense) {
        super(name, new Position(0, 0));
        setDamage(damage);
        setMaxHp(maxHp);
        setHp(hp);
        setDefense(defense);
        setDirection(Direction.EAST);
        z = 0;
        visible = true;
        attacking = false;
    }

    public boolean isDead() {
        return getHp() == 0;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public boolean isVisible() {
        if(isDead())
            visible = false;
        return visible;
    }

    @Override
    public boolean isDestroyed() {
        return true;
    }
    public void setHp(int hp) {
        if (hp < 0)
            hp = 0;
        if (hp > maxHp)
            hp = maxHp;
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        if (maxHp < 0)
            maxHp = 0;
        this.maxHp = maxHp;
    }

    public void setDamage(int damage) {
        if (damage < 0)
            damage = 0;
        this.damage = damage;
    }

    public void setDefense(int defense) {
        if (defense < 0)
            defense = 0;
        this.defense = defense;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getDefense() {
        return defense;
    }

    public int getDamage() {
        return damage;
    }

    public Direction getDirection() {
        return direction;
    }

    public abstract void forward();

    public abstract void attack();
}
