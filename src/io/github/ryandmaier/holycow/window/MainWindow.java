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
  JLabel points;
  JLabel pause;
  int pointValue;

  public MainWindow(int width, int height) {
    super("Holy Cow!");

    this.width = width;
    this.height = height;

    pointValue=0;
    points = new JLabel("Points: " + pointValue + " (Press esc for pause menu)");
    points.setVisible(true);
    //points.setOpaque(true);
    points.setForeground(Color.BLUE);           
    points.setLocation(width/2-100,10);
    points.setFont(new Font("newFont",0,30));
    points.setSize(points.getPreferredSize());

    pause = new JLabel("This game is now paused. Controls: arrow keys (except DOWN) to move bull. Press up arrow to move faster. Kill all the people. Press esc to resume.");
    pause.setVisible(true);
    //points.setOpaque(true);
    pause.setForeground(Color.BLUE);           
    pause.setLocation(10,height/2);
    pause.setFont(new Font("newFont",0,10));
    pause.setSize(pause.getPreferredSize());
    
    
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
    bg.setPreferredSize(new Dimension(width, height));
    bg.setBounds(0,0, width, height);
    mainView.add(bg,1);

    //mainView.add(points,0);
    
    this.add(points, BorderLayout.NORTH);
    this.add(mainView, BorderLayout.CENTER);
      //this.add(pause, BorderLayout.SOUTH);
      
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

  public void addPoints()
  {
    pointValue++;
    points.setText("Points: " + pointValue);
    this.add(points, BorderLayout.NORTH);  
    this.pack();
  }
  
  public void pauseMenu()
  {
      this.add(pause, BorderLayout.SOUTH);
      this.pack();
  }
  public void endPause()
  {
      this.remove(pause);
  }
  
  public int getPoints() {
    return pointValue;
  }

  public void setPointValue(int pointValue) {
    this.pointValue = pointValue;
  }
}

