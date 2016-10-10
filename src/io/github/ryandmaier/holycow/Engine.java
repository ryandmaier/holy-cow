package io.github.ryandmaier.holycow;

import io.github.ryandmaier.holycow.component.Bull;
import io.github.ryandmaier.holycow.component.Entity;
import io.github.ryandmaier.holycow.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Adam on 10/1/16.
 */
public class Engine implements ActionListener{

  MainWindow mainWindow;

  ArrayList<Entity> entityList;

  Bull player;

  Timer refreshTimer;


  public Engine(int width, int height) {

    mainWindow = new MainWindow(width, height);
    mainWindow.addKeyListener(new MovementListener());
    entityList = new ArrayList<>(15);

    refreshTimer = new Timer(25, this);

    player = new Bull(width, height);
    this.addEntity(player);

  }

  public void start() {
    refreshTimer.start();
  }

  public void addEntity(Entity e) {
    entityList.add(e);
    mainWindow.addEntity(e);
  }

  public void removeEntity(Entity e) {
    entityList.remove(e);
    mainWindow.removeEntity(e);
  }

  public void renderEntites() {
    for (Entity e : entityList) {
      e.repaint();
    }
  }

  public void tickEntities() {
    entityList.stream().forEach(Entity::tick);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource()==refreshTimer) {
      tickEntities();
      renderEntites();
    }
  }

  private class MovementListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode()==KeyEvent.VK_UP) {
        player.increaseSpeed();
      } else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
        player.decreseSpeed();
      } else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
        player.startTurnLeft();
      } else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
        player.startTurnRight();
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode()==KeyEvent.VK_UP) {
        player.endSpeedChange();
      } else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
        player.endSpeedChange();
      } else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
        player.endTurn();
      } else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
        player.endTurn();
      }
    }
  }
}
