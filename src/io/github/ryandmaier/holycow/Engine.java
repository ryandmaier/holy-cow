package io.github.ryandmaier.holycow;

import io.github.ryandmaier.holycow.component.Bull;
import io.github.ryandmaier.holycow.component.Entity;
import io.github.ryandmaier.holycow.component.Person;
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

  boolean running = true;

  int peopleLeft;

  int round = 0;


  public Engine(int width, int height) {

    peopleLeft = 0;

    mainWindow = new MainWindow(width, height);
    mainWindow.addKeyListener(new MovementListener());
    entityList = new ArrayList<>(15);

    saveFileManager = new SaveFileManager(new File("save/save.txt"), width, height);

    refreshTimer = new Timer(25, this);

    player = new Bull(width, height);
    player.setPosition(width / 2.0, height / 2.0);
    this.addEntity(player);

  }

  public void start() {
    refreshTimer.start();
    round = 1;


  }

  public void nextRound() {
    
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
    entityList.forEach(Entity::repaint);
  }

  public void tickEntities() {
    entityList.stream().forEach(Entity::tick);
    collideBull();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource()==refreshTimer) {
      tickEntities();
      renderEntites();
    }
  }

  public void collideBull() {

    for (Entity e : entityList) {
      if (e==player) continue;

      else if (Math.abs(player.getxPos() - e.getxPos()) < 80) {
        if (Math.abs(player.getyPos() - e.getyPos()) < 80) {
          if (e instanceof Person) {
            ((Person) e).die();
          }
        }
      }
    }
  }

  public void saveGame() {
    System.out.println("Saving game...");
    //System.out.println("EntityList size = "+entityList.size());
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

  public void togglePause() {
    if (running) {
      refreshTimer.stop();
      running = false;
    }
    else {
      refreshTimer.restart();
      running = true;
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
      else if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
        togglePause();
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
