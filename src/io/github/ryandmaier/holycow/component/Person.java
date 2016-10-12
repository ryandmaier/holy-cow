package io.github.ryandmaier.holycow.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

/**
 * Created by Ryan Maier on 10/10/16.
 */
public class Person extends Entity
{
<<<<<<< HEAD
//<<<<<<< HEAD
    double aRot;
    double aSpeed;
//=======

=======

    double aRot;
    double aSpeed;

    double bodyX;
    double bodyY;
>>>>>>> origin/master
    double bodyW;
    double bodyH;

    double dSpeedX;
    double ddSpeedX;
    double dSpeedY;
    double ddSpeedY;
    double speedX;
    double speedY;

  boolean alive;

<<<<<<< HEAD
    Random rand;

//<<<<<<< HEAD
//>>>>>>> origin/master
//=======
//>>>>>>> origin/master
=======
>>>>>>> origin/master
    private static final double MAX_SPEED = 10;
    private static final double TURN_RADIUS = 5;
    private static final double SPEED_CHANGE = 0.33;

    public Person(int width, int height)
    {
        super(width, height);
        
        facingAngle = -45;
        speed = 2;
<<<<<<< HEAD
//<<<<<<< HEAD
        aRot = 0;
        aSpeed = 0;
//=======
=======

        aRot = 0;
        aSpeed = 0;
>>>>>>> origin/master

        xPos = 250;
        yPos = 250;
        bodyW = 20;
        bodyH = bodyW*2;

        dSpeedX = 0;
        ddSpeedX = 0;
        dSpeedY = 0;
        ddSpeedY = 0;

        rand = new Random();

      type = "Person";
<<<<<<< HEAD
//<<<<<<< HEAD
//=======

      alive = true;

//>>>>>>> origin/master
=======

      alive = true;

>>>>>>> origin/master


<<<<<<< HEAD

//>>>>>>> origin/master
=======
>>>>>>> origin/master
    }
    
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

<<<<<<< HEAD
//<<<<<<< HEAD
//<<<<<<< HEAD
        //RoundedRectangle2D.Double() body =
//=======
//=======
//>>>>>>> origin/master
=======

>>>>>>> origin/master
        RoundRectangle2D.Double body = new RoundRectangle2D.Double(xPos - bodyW / 2.0,yPos - bodyH / 2.0,bodyW,bodyH,bodyW/10,bodyH/10);
        Ellipse2D.Double head = new Ellipse2D.Double(xPos - bodyW / 2.0, yPos-bodyW - bodyH / 2.0,bodyW,bodyW);


        g2.setColor(new Color(0xFF7654));
      if (!alive) {
        g2.setColor(new Color(0x8B8E7F));
        g2.rotate(180, xPos, yPos);
      }
        g2.fill(body);
        g2.fill(head);


        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.BLACK);
        g2.draw(body);
        g2.draw(head);
<<<<<<< HEAD
//>>>>>>> origin/master
=======

>>>>>>> origin/master
    }

  public static Person restore(double x, double y, double facing, double speed, boolean alive, int width, int height) {
    Person b = new Person(width, height);
    b.xPos = x;
    b.yPos = y;
    b.facingAngle = facing;
    b.speed = speed;
    b.alive = alive;

    return b;
  }

  @Override
  public void tick() {
      //dSpeedX += (rand.nextDouble()-.5)/10;
      //dSpeedY += (rand.nextDouble()-.5)/10;

      speedX += (rand.nextDouble()-.5)-speedX/200;
      speedY += (rand.nextDouble()-.5)*1.5-speedY/200;

      int changeX = rand.nextInt(1000);
      int changeY = rand.nextInt(1000);
      if(400-xPos>0&&speedX<0) {
          if (changeX % (int) (1000/(400 - xPos)) == 0) speedX = speedX * -1;
      }
      if(400-xPos<0&&speedX>0) {
          if (changeX % (int) (1000/(400 - xPos)) == 0) speedX = speedX * -1;
      }
      if(300-yPos>0&&speedY<0) {
          if (changeY % (int) (1000/(300 - yPos)) == 0) speedY = speedY * -1;
      }
      if(300-yPos<0&&speedY>0) {
          if (changeY % (int) (1000/(300 - yPos)) == 0) speedY = speedY * -1;
      }


      if (xPos >= upperxBound) speedX*=-1;
      else if (xPos <= loweryBound) speedX*=-1;
      if (yPos >= upperyBound) speedY*=-1;
      else if (yPos <= loweryBound) speedY*=-1;

      //super.tick();

      while (facingAngle < 0) facingAngle += 360;
      while (facingAngle > 360) facingAngle -= 360;
      xPos += speedX;
      yPos += speedY;

      normalizeLocation();
  }

  public void die() {
    alive = false;
    speed = 0;
  }

  public boolean isAlive() {
    return alive;
  }
}
