package io.github.ryandmaier.holycow;

import io.github.ryandmaier.holycow.component.Bull;
import io.github.ryandmaier.holycow.component.Entity;
import io.github.ryandmaier.holycow.window.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 10/1/16.
 */
public class Engine implements ActionListener{

  MainWindow mainWindow;

  List<Entity> entityList;

  Bull player;

  Timer refreshTimer;

  SaveFileManager saveFileManager;


  public Engine(int width, int height) {

    mainWindow = new MainWindow(width, height);
    mainWindow.addKeyListener(new MovementListener());
    entityList = new ArrayList<>(15);

    saveFileManager = new SaveFileManager(new File("save/save.txt"), width, height);

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

  public void saveGame() {
    System.out.println("Saving game...");
    try {
      saveFileManager.writeSave(entityList);
    }
    catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }

  public void loadGame() {

    System.out.println("Loading game...");

    for (Entity e : entityList) {
      mainWindow.removeEntity(e);
    }

    player = null;

    try {
       entityList = saveFileManager.loadSave();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }

    for (Entity e : entityList) {
      mainWindow.addEntity(e);
      if (e.getType().equals("Bull")) player = (Bull) e;
    }

    if (player==null) {
      System.err.println("No player found in save file!!");
      System.exit(-1);
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
      else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
        saveGame();
      } else if (e.getKeyCode()==KeyEvent.VK_L) {
        loadGame();
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
