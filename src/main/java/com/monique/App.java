package com.monique;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.mocha.Application;
import org.mocha.actor.Box;
import org.mocha.annotations.Multithreading;
import org.mocha.annotations.Window;

@Window(title = "Mocha Bird", width = 288, height = 512, fullScreen = true)
@Multithreading()
public class App extends Application {
    public boolean started = false;

    public App() throws IOException {
        input.addAction("up", KeyEvent.VK_SPACE);

        var bird = new Bird(getScreenWidth() / 2, getScreenHeight() / 2, this);

        var background = new Background();

        var pipe = new Pipe(this);

        var pipe2 = new Pipe(this, pipe);

        scene.addActors(pipe, pipe2, bird, background);

        init();
    }

    public boolean checkCollision(Box a) {
        for (var actor : scene.getActors()) {
            if (actor == a) continue;

            if (actor instanceof Box) {
                var b = (Box) actor;
                if (b.checkCollision(a)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        new App();
    }
}