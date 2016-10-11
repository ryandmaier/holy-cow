package io.github.ryandmaier.holycow;

import io.github.ryandmaier.holycow.component.Bull;
import io.github.ryandmaier.holycow.component.Entity;
import io.github.ryandmaier.holycow.component.Person;

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
  public List<Entity> loadSave() throws IOException {

    String line = null;

    ArrayList<Entity> returnList = new ArrayList<>();


    while ((line = reader.readLine()) != null) {
      if (line.equals("Bull") || line.equals("Entity") || line.equals("Person")) {
        double x = 0;
        double y = 0;
        double facing = 0;
        double speed = 0;
        for (int i = 0; i < 4; i++) {

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

          }
        }

        if (line.equals("Bull")) {
          returnList.add(Bull.restore(x,y,facing,speed,screenWidth,screenHeight));

        } else if (line.equals("Entity")) {
          returnList.add(Entity.restore(x,y,facing,speed,screenWidth,screenHeight));

        } else if (line.equals("Person")) {
          returnList.add(Person.restore(x,y,facing,speed,screenWidth,screenHeight));

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

  public void writeSave(List<Entity> eList) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(file));

    for (int i = 0; i < eList.size(); i++) {
      Entity e = eList.get(i);
      writer.write (e.getType()+'\n');
      writer.write("x:"+e.getxPos()+'\n');
      writer.write("y:"+e.getyPos()+'\n');
      writer.write("facing:"+e.getFacingAngle()+'\n');
      writer.write("speed:"+e.getSpeed()+'\n');
    }

    writer.close();
  }

}
