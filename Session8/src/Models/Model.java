package Models;

import Controllers.GameVector;

import java.awt.*;

/**
 * Created by Hieu It on 12/7/2016.
 */
public class Model {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive = true;

    public Model(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(GameVector gameVector){
        this.move(gameVector.dx,gameVector.dy);
    }

    public GameVector subtract(Model model){
        return new GameVector(this.x - model.x,this.y - model.y);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getMidX() {
        return this.x + this.width / 2;
    }

    public int getMidY() {
        return this.y + this.height / 2;
    }

    public int getBottom() {
        return this.y+this.height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }

    public boolean intersects(Model other){
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = other.getRect();
        return rect1.intersects(rect2);
    }
}
