package io.github.ryandmaier.holycow;

import io.github.ryandmaier.holycow.component.Bull;
import io.github.ryandmaier.holycow.component.Entity;
import io.github.ryandmaier.holycow.component.Person;
import io.github.ryandmaier.holycow.window.MainWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Adam on 10/10/16.
 */
public class SaveFileManager {

  File file;

  BufferedReader reader;

  int screenWidth;
  int screenHeight;

  public SaveFileManager(File f, int screenWidth, int screenHeight) {

    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;

    file = f;

    InputStreamReader inputStreamReader;
    FileInputStream fileIn = null;

    try {
      fileIn = new FileInputStream(f);
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      e.printStackTrace();
      System.exit(-1);
    }

    inputStreamReader = new InputStreamReader(fileIn);

    reader = new BufferedReader(inputStreamReader);
  }

  /**
   * @return list of all entities in the save file, the first one is the player
   */
  public List<Entity> loadSave(MainWindow mainWindow) throws IOException {

    String line = null;

    ArrayList<Entity> returnList = new ArrayList<>();

    String score = reader.readLine();
    StringTokenizer scoreTokenizer = new StringTokenizer(score,":");
    scoreTokenizer.nextToken();

    mainWindow.setPointValue(Integer.valueOf(scoreTokenizer.nextToken())-1);
    mainWindow.addPoints();

    while ((line = reader.readLine()) != null) {
      if (line.equals("Bull") || line.equals("Entity") || line.equals("Person")) {
        double x = 0;
        double y = 0;
        double facing = 0;
        double speed = 0;
        boolean alive = true;

        int numParts = 4;
        if (line.equals("Person")) numParts = 5;
        for (int i = 0; i < numParts; i++) {

          String subline = reader.readLine();
          StringTokenizer st2 = new StringTokenizer(subline,":");
          String id = st2.nextToken();
          if (id.equals("x")) {
            x = Double.valueOf(st2.nextToken());
          } else if (id.equals("y")) {
            y = Double.valueOf(st2.nextToken());
          } else if (id.equals("facing")) {
            facing = Double.valueOf(st2.nextToken());
          } else if (id.equals("speed")) {
            speed = Double.valueOf(st2.nextToken());
          } else if (id.equals("alive")) {
            alive = Boolean.valueOf(st2.nextToken());
            //System.out.println(alive);

          }
        }

        if (line.equals("Bull")) {
          returnList.add(Bull.restore(x,y,facing,speed,screenWidth,screenHeight));

        } else if (line.equals("Entity")) {
          returnList.add(Entity.restore(x,y,facing,speed,screenWidth,screenHeight));

        } else if (line.equals("Person")) {
          returnList.add(Person.restore(x,y,facing,speed, alive, screenWidth,screenHeight));

        }


      }

    }

    recreateReader();

    return returnList;

  }

  private void recreateReader() {
    InputStreamReader inputStreamReader;
    FileInputStream fileIn = null;

    try {
      fileIn = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      e.printStackTrace();
      System.exit(-1);
    }

    inputStreamReader = new InputStreamReader(fileIn);

    reader = new BufferedReader(inputStreamReader);
  }

  public void writeSave(List<Entity> eList, int score) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));


    writer.write("Score:"+score+'\n');

    for (int i = 0; i < eList.size(); i++) {
      Entity e = eList.get(i);
      writer.write (e.getType()+'\n');
      writer.write("x:"+e.getxPos()+'\n');
      writer.write("y:"+e.getyPos()+'\n');
      writer.write("facing:"+e.getFacingAngle()+'\n');
      writer.write("speed:"+e.getSpeed()+'\n');
      if (e instanceof Person) writer.write("alive:"+((Person) e).isAlive()+'\n');
    }

    writer.close();
  }

}
