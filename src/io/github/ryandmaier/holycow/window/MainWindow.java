package io.github.ryandmaier.holycow.window;

import io.github.ryandmaier.holycow.component.BackgroundComponent;
import io.github.ryandmaier.holycow.component.Entity;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Adam on 10/1/16.
 */
public class MainWindow extends JFrame {

  JLayeredPane mainView;

  int width;
  int height;

  public MainWindow(int width, int height) {
    super("Holy Cow!");

    this.width = width;
    this.height = height;


    initWindow();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  private void initWindow() {

    //this.setLayout(new BorderLayout());

    mainView = new JLayeredPane();
    mainView.setPreferredSize(new Dimension(width, height));
    //mainView.setBackground(new Color(81, 159, 45));

    BackgroundComponent bg = new BackgroundComponent(width, height);
    bg.setBounds(0,0, width, height);
    mainView.add(bg, 0);


    this.add(mainView, BorderLayout.CENTER);

    this.pack();
    this.setLocationRelativeTo(null);
  }

  public void addEntity(Entity e) {
    mainView.add(e, 0);
    e.setBounds(0,0,width,height);
  }

  public void removeEntity(Entity e) {
    mainView.remove(e);
  }

}

