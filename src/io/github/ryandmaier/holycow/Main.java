package io.github.ryandmaier.holycow;

import io.github.ryandmaier.holycow.component.Bull;
import io.github.ryandmaier.holycow.component.Person;
import io.github.ryandmaier.holycow.window.MainWindow;

public class Main {

    public static void main(String[] args) {
      Engine e = new Engine(800, 600);
        e.addEntity(new Person(800,600));
      e.start();
    }
}
