package com.monique;

import java.awt.Graphics2D;

import org.mocha.actor.AnimatedSprite;
import org.mocha.actor.Box;
import org.mocha.animation.Animation;
import org.mocha.animation.AnimationManager;
import org.mocha.animation.SpriteSheet;
import org.mocha.inputs.InputManager;

public class Bird extends Box {
    private AnimatedSprite animated;
    private InputManager input;
    private double acceleration = 0;
    
    public Bird(int x, int y, InputManager input) {
        this.input = input;
        setPosition(x, y);

        SpriteSheet sprites = null;
        try {
            sprites = new SpriteSheet("sprites/yellow_sprites.png", 34, 24);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        var animation = new Animation(sprites, 1);
        animation.start();
        var animationMan = new AnimationManager(animation, null);

        animated = new AnimatedSprite(getX(), getY(), animationMan);
        addChildren(animated);

        setWidth(sprites.getWidth());
        setHeight(sprites.getHeight());
    }

    @Override
    public void update(double deltaTime) {
        animated.innerUpdate(deltaTime);
        acceleration = Math.min(acceleration + 2.5, 25);

        if (input.getInputStatus("up") == 1) {
            acceleration = -40;
        }

        velocity.setY(acceleration);
    }

    @Override
    public void draw(Graphics2D g2) {
        animated.innerDraw(g2);
    }
}
