package logic;

public class Position {
    private double x;
    private double y;
    public Position(double x, double y){
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        if(x > 1125)
            x = 1125;
        if(x < 5)
            x = 5;
        this.x = x;
    }

    public void setY(double y) {
        if(y > 675)
            y = 675;
        if(y < 0)
            y = 0;
        this.y = y;
    }
}
