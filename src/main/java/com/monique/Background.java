package com.monique;

import java.awt.Graphics2D;
import java.io.IOException;

import org.mocha.actor.Sprite;
import org.mocha.util.GraphicsUtil;
import org.mocha.util.platform.Resources;

public class Background extends Sprite {
    private double speed = 15;

    public Background() throws IOException {
        super(0, 0, Resources.getImage("sprites/background-day.png"));
    }

    @Override
    public void update(double deltaTime) {
        velocity.subtract(speed * deltaTime, 0);
        if (getX() + getSprite().getWidth() < 0) setX(0);
    }

    @Override
    public void draw(Graphics2D g2) {
        GraphicsUtil.drawRotatedImage(getSprite(), getX(), getY(), rotation, scale, anchor, g2);
        GraphicsUtil.drawRotatedImage(getSprite(), getX() + getSprite().getWidth(), getY(), rotation, scale, anchor, g2);
    }
}
