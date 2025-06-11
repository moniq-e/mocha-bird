package com.monique;

import org.mocha.actor.AnimatedSprite;
import org.mocha.actor.Box;
import org.mocha.animation.Animation;
import org.mocha.animation.AnimationManager;
import org.mocha.animation.SpriteSheet;
import org.mocha.annotations.ShowHitbox;
import org.mocha.enums.AnchorPoint;
import org.mocha.inputs.InputManager;

@ShowHitbox
public class Bird extends Box {
    private AnimatedSprite animated;
    private InputManager input;
    private App app;
    private boolean justPressed = true;

    private double acceleration = 0;
    private double maxRotation = Math.PI / 3;
    private double minRotation = -Math.PI / 3;
    private double diffRotation = maxRotation - minRotation;
    private double maxAcc = 50;
    private double minAcc = -27;
    private double diffAcc = maxAcc - minAcc;

    public Bird(int x, int y, App app) {
        this.app = app;
        this.input = app.getInput();
        setPosition(x, y);
        setAnchor(AnchorPoint.MIDDLE_CENTER);

        SpriteSheet sprites = null;
        try {
            sprites = new SpriteSheet("sprites/yellow_sprites.png", 3, 1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        var animation = new Animation(sprites, .75);
        var animationMan = AnimationManager.singleAnimationManager(animation);

        animated = new AnimatedSprite(getX(), getY(), animationMan);
        addChild(animated);

        setWidth(animation.getSprite().getWidth());
        setHeight(sprites.getHeight());
    }

    @Override
    public void update(double deltaTime) {
        if (!app.started) {
            app.started = input.getInputStatus("up") == 1;
            return;
        } else {
            app.started = !app.checkCollision(this);
        }

        acceleration = Math.min(acceleration + 1.8, maxAcc);

        setRotation(minRotation + diffRotation * ((acceleration - minAcc) / diffAcc));

        if (input.getInputStatus("up") == 1) {
            if (justPressed) {
                acceleration = minAcc;
                setRotation(minRotation);
                justPressed = false;
            }
        } else {
            justPressed = true;
        }

        velocity.setY(acceleration);
    }
}
