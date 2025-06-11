package com.monique;

import java.awt.Graphics2D;
import java.io.IOException;

import org.mocha.actor.Box;
import org.mocha.actor.Sprite;
import org.mocha.enums.AnchorPoint;
import org.mocha.util.GraphicsUtil;

public class Pipe extends Box {
    private App app;
    private Sprite sprite;
    private double speed = 20;
    private int gap = 150;
    private int minY;

    public Pipe(App app) throws IOException {
        this.app = app;
        sprite = new Sprite("sprites/pipe-green.png", 0, 0);
        setWidth(sprite.getSprite().getWidth());
        setHeight(sprite.getSprite().getHeight());
        minY = app.getScreenHeight() - sprite.getSprite().getHeight() - gap;
        setAnchor(AnchorPoint.TOP_CENTER);
        addChildren(sprite);
        reset();
    }

    @Override
    public void update(double deltaTime) {
        if (!app.started) return;

        if (getX() + sprite.getSprite().getWidth() < 0) reset();

        velocity.subtract(speed * deltaTime, 0);
    }

    public void reset() {
        setX(app.getScreenWidth() + sprite.getSprite().getWidth() / 2);
        setY(app.getScreenHeight() - Math.max(Math.random() * sprite.getSprite().getHeight(), minY));
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        GraphicsUtil.drawRotatedImage(sprite.getSprite(), getX(), getY() - gap, Math.PI, scale, anchor, g2);
    }
}
