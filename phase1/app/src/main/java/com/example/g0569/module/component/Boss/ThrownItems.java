package com.example.g0569.module.component.Boss;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.g0569.module.component.Item;
import com.example.g0569.module.component.NonPlayerItem;
import com.example.g0569.module.game.Game;
import com.example.g0569.module.utils.Coordinate;

public class ThrownItems extends NonPlayerItem {

    public int damage;
    public boolean thrown;
    private double shrink;
    private int x_direction;
    private int y_direction;
    public Rect dest_rect;
    public Rect src_rect;

    public ThrownItems(Game game, float screen_width, float screen_height, Resources resource) {
        super(game);
        size = (int) screen_width / 12;
        float x = (int) (screen_width / 2 - size / 2);
        float y = (int) (screen_height - screen_width * 3 / 32 - size / 2);
        coordinate = new Coordinate(x, y);
        shrink = 0.99;
        x_direction = (int) (size * (1 - shrink));
        y_direction = (int) (-screen_height / 100 + size * (1 - shrink));
        thrown = false;
        dest_rect = new Rect((int) x, (int) y, (int) x + size, (int) y + size);
//        super.appearance = appearance;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(appearance, src_rect, dest_rect, paint);
    }

    public int getDamage() {
        return damage;
    }

    public void action() {
        System.out.println("Action called");
        System.out.println(thrown);
        if (thrown) {
            System.out.println(this.coordinate.getY());
            System.out.println("Should be moving");
            size = (int) (size * shrink);
            actionHelp();
        }
    }

    public boolean inTheScreen(float screen_height) {
        return 0 <= (coordinate.getY() + size) && (coordinate.getY() + size) <= screen_height;
    }

    public void thrown() {
        System.out.println("thrown");
        thrown = true;
        System.out.println(thrown + " we throw now");
    }

    public void actionHelp() {
        coordinate.setX(coordinate.getX() + x_direction);
        coordinate.setY(coordinate.getY() + y_direction - 50);
        dest_rect.set(
                (int) coordinate.getX(),
                (int) coordinate.getY(),
                (int) coordinate.getX() + size,
                (int) coordinate.getY() + size);
    }

    public float getRadius() {
        return size;
    }
}
