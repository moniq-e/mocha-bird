package com.monique;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.mocha.Application;
import org.mocha.actor.Sprite;
import org.mocha.annotations.Window;
import org.mocha.util.platform.Resources;

@Window(title = "Mocha Bird", width = 288, height = 512)
public class App extends Application {

    public App() throws IOException {
        input.addAction("up", KeyEvent.VK_SPACE);

        var bird = new Bird(290 / 2, 512 / 2, input);
        

        var sprite = new Sprite(0, 0, Resources.getImage("sprites/background-day.png"));

        scene.addActor(sprite);
        scene.addActor(bird);

        init();
    }

    public static void main(String[] args) throws IOException {
        new App();
    }
}