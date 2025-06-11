package com.monique;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.mocha.Application;
import org.mocha.annotations.Multithreading;
import org.mocha.annotations.Window;

@Window(title = "Mocha Bird", width = 288, height = 512, fullScreen = false)
@Multithreading()
public class App extends Application {
    public boolean started = false;

    public App() throws IOException {
        input.addAction("up", KeyEvent.VK_SPACE);

        var bird = new Bird(getScreenWidth() / 2, getScreenHeight() / 2, this, input);

        var background = new Background();

        var pipe = new Pipe(this);

        scene.addActor(bird);
        scene.addActor(pipe);
        scene.addActor(background);

        init();
    }

    public static void main(String[] args) throws IOException {
        new App();
    }
}