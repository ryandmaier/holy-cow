package io.github.ryandmaier.holycow.component;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Adam on 10/1/16.
 */
public class Bull extends Entity {

  double aRot;
  double aSpeed;

  private static final double MAX_SPEED = 10;
  private static final double TURN_RADIUS = 5;
  private static final double SPEED_CHANGE = 0.33;

  public Bull(int width, int height) {
    super(width, height);

    facingAngle = 0;
    speed = 2;
    aRot = 0;
    aSpeed = 0;

    type = "Bull";
  }

  public void decreseSpeed() {
    aSpeed = -SPEED_CHANGE;
  }

  public void increaseSpeed() {
    aSpeed = SPEED_CHANGE;
  }

  public void endSpeedChange() {
    aSpeed = 0;
  }

  @Override
  public void tick() {

    facingAngle += aRot;
    speed += aSpeed;

    if (speed > MAX_SPEED) speed = MAX_SPEED;
    else if (speed < 0) speed = 0;

    super.tick();

    normalizeLocation();

  }

  @Override
  public void normalizeLocation() {
    if (xPos > width) {
      xPos = width;
      if (facingAngle < 45 || facingAngle > 315) {
        speed = 0;
      }
    }
    else if (xPos < 0) {
      xPos = 0;
      if (facingAngle > 135 && facingAngle < 225) {
        speed = 0;
      }
    }
    if (yPos > height) {
      yPos = height;
      if (facingAngle > 225 && facingAngle < 315) {
        speed = 0;
      }
    }
    else if (yPos < 0) {
      yPos = 0;
      if (facingAngle > 45 && facingAngle < 135) {
        speed = 0;
      }
    }
  }

  public void startTurnLeft() {
    aRot = TURN_RADIUS;
  }

  public void startTurnRight() {
    aRot = -TURN_RADIUS;
  }

  public void endTurn() {
    aRot = 0;
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Draw a red rectangle for testing purposes
    RoundRectangle2D.Double body = new RoundRectangle2D.Double(xPos - 0.5 * xSize, yPos - 0.5 * ySize, xSize, ySize,50,10);

    RoundRectangle2D.Double head = new RoundRectangle2D.Double(xPos + 0.5 * xSize - 0.5 * 30, yPos - 0.5 * 50, 30, 50, 10, 50);

    Ellipse2D.Double horn1 = new Ellipse2D.Double(xPos + 0.5 * xSize, yPos - 20, 30, 10);
    Ellipse2D.Double horn2 = new Ellipse2D.Double(xPos + 0.5 * xSize, yPos + 7, 30, 10);

    g2.rotate(Math.toRadians(-facingAngle), xPos, yPos);

    g2.setStroke(new BasicStroke(10));
    g2.setColor(Color.black);
    g2.draw(body);
    g2.setColor(new Color(123, 72, 10));
    g2.fill(body);

    g2.setStroke(new BasicStroke(3));
    g2.setColor(new Color(139, 142, 127));
    g2.fill(horn1);
    g2.fill(horn2);
    g2.setColor(Color.black);
    g2.draw(horn1);
    g2.draw(horn2);

    g2.setStroke(new BasicStroke(5));
    g2.setColor(new Color(141, 84, 10));
    g2.fill(head);
    g2.setColor(Color.black);
    g2.draw(head);


  }

  public static Bull restore(double x, double y, double facing, double speed, int width, int height) {
    Bull b = new Bull(width, height);
    b.xPos = x;
    b.yPos = y;
    b.facingAngle = facing;
    b.speed = speed;

    return b;
  }

}
