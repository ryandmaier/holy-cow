package io.github.ryandmaier.holycow.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Ryan Maier on 10/10/16.
 */
public class Person extends Entity
{
<<<<<<< HEAD
    double aRot;
    double aSpeed;
=======
    double bodyX;
    double bodyY;
    double bodyW;
    double bodyH;

  boolean alive;

<<<<<<< HEAD
>>>>>>> origin/master
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
        aRot = 0;
        aSpeed = 0;
=======

        bodyX = 50;
        bodyY = 50;
        bodyW = 20;
        bodyH = bodyW*2;

      type = "Person";
<<<<<<< HEAD
=======

      alive = true;

>>>>>>> origin/master

      alive = true;


>>>>>>> origin/master
    }
    
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

<<<<<<< HEAD
<<<<<<< HEAD
        RoundedRectangle2D.Double() body = 
=======
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
    super.tick();
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
