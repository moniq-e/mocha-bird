package com.monique;

import java.awt.Graphics2D;
import java.io.IOException;

import org.mocha.actor.Box;
import org.mocha.actor.Sprite;
import org.mocha.annotations.ShowHitbox;
import org.mocha.enums.AnchorPoint;
import org.mocha.util.GraphicsUtil;

@ShowHitbox
public class Pipe extends Box {
    private App app;
    private Sprite sprite;
    private double speed = 30;
    private int gap = 100;
    private int minY;
    private Pipe other;

    public Pipe(App app) throws IOException {
        this(app, null);
    }

    public Pipe(App app, Pipe other) {
        this.app = app;
        this.other = other;
        if (other != null) other.other = this;
        sprite = new Sprite("sprites/pipe-green.png", 0, 0);

        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());

        minY = app.getScreenHeight() - sprite.getHeight() - gap;

        setAnchor(AnchorPoint.TOP_CENTER);
        addChildren(sprite);
        reset();
    }

    @Override
    public void update(double deltaTime) {
        if (!app.started) return;

        if (getX() + sprite.getWidth() / 2 < 0) reset();

        velocity.subtract(speed * deltaTime, 0);
    }

    public void reset() {
        setX(app.getScreenWidth() + sprite.getWidth() + (other != null ? other.getX() / 2 : 0));
        setY(app.getScreenHeight() - Math.max(Math.random() * sprite.getHeight(), minY));
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        GraphicsUtil.drawRotatedImage(sprite.getSprite(), getX(), getY() - gap, Math.PI, scale, anchor, g2);
    }
}
