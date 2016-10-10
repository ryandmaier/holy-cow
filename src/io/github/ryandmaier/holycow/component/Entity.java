package io.github.ryandmaier.holycow.component;

import io.github.ryandmaier.holycow.window.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Adam on 10/1/16.
 */
public class Entity extends JComponent {

  protected int width;
  protected int height;

  protected double xPos;
  protected double yPos;

  protected double xSize;
  protected double ySize;

  protected double facingAngle;
  protected double speed;

  protected double friction;

  public Entity(int width, int height) {
    this.width = width;
    this.height = height;

    xPos = 0;
    yPos = 0;

    xSize = 60;
    ySize = 40;

    speed = 0;

    friction = 0;
    facingAngle = 0;

  }

  public void tick() {

    while (facingAngle < 0) facingAngle += 360;
    while (facingAngle > 360) facingAngle -= 360;

    xPos += speed * Math.cos(Math.toRadians(facingAngle));
    yPos -= speed * Math.sin(Math.toRadians(facingAngle));

  }




  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Draw a red rectangle for testing purposes
    Rectangle2D.Double body = new Rectangle2D.Double(xPos - 0.5 * xSize, yPos - 0.5 * ySize, xSize, ySize);
    g2.rotate(Math.toRadians(-facingAngle), xPos, yPos);
    g2.setStroke(new BasicStroke(10));
    g2.draw(body);
    g2.setColor(Color.red);
    g2.fill(body);

  }

  public void normalizeLocation() {
    if (xPos > width) xPos = width;
    else if (xPos < 0) xPos = 0;
    if (yPos > height) yPos = height;
    else if (yPos < 0) yPos = 0;
  }

}
